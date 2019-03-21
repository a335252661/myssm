package cn.cld.untils.fastdfs;

import cn.cld.untils.PropertyUtils;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;


public class ConnectionPool {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
	// 链接池次数
	private int size = 30;
	// 工作连接
	private ConcurrentMap<StorageClient1, Object> busyConnectionPool = null;
	// 连接池
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;
	private Object obj = new Object();

	// 构造函数
	private ConnectionPool() {
		busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
		idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
		initClientGlobal();
		init(size);
	}

	private void initClientGlobal() {
		int connect_timeout = Integer.parseInt(PropertyUtils.getProperty("connect_timeout"));
		int network_timeout = Integer.parseInt(PropertyUtils.getProperty("network_timeout"));
		int http_tracker_http_port = Integer.parseInt(PropertyUtils.getProperty("http_tracker_http_port"));
		int http_anti_steal_token = Integer.parseInt(PropertyUtils.getProperty("http_anti_steal_token"));
		String http_secret_key = PropertyUtils.getProperty("http_secret_key");
		String str_arr_tracker_server = PropertyUtils.getProperty("tracker_server");
		String[] arr_tracker_servers = str_arr_tracker_server.split(";");
		String charset = PropertyUtils.getProperty("charset");

		boolean b_http_anti_steal_token = false;
		if (http_anti_steal_token == 1) {
			b_http_anti_steal_token = true;
		}

		ClientGlobal.setG_anti_steal_token(b_http_anti_steal_token);
		ClientGlobal.setG_charset(charset);
		ClientGlobal.setG_connect_timeout(connect_timeout);
		ClientGlobal.setG_network_timeout(network_timeout);
		ClientGlobal.setG_secret_key(http_secret_key);
		ClientGlobal.setG_tracker_http_port(http_tracker_http_port);
		InetSocketAddress[] tracker_servers = new InetSocketAddress[arr_tracker_servers.length];
		String[] parts;
		for (int i = 0; i < arr_tracker_servers.length; i++) {
			parts = arr_tracker_servers[i].split("\\:", 2);
			tracker_servers[i] = new InetSocketAddress(parts[0].trim(),
					Integer.parseInt(parts[1].trim()));
		}
		TrackerGroup g_tracker_group;
		g_tracker_group = new TrackerGroup(tracker_servers);

		ClientGlobal.setG_tracker_group(g_tracker_group);
	}

	private static ConnectionPool instance = new ConnectionPool();

	// get the connection pool instance
	public static ConnectionPool getPoolInstance() {
		return instance;
	}

	// 初始化
	private void init(int size) {
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			// Only tracker
			trackerServer = trackerClient.getConnection();

			for (int i = 0; i < size; i++) {
				StorageServer storageServer = null;
				StorageClient1 client = new StorageClient1(trackerServer, storageServer);
				idleConnectionPool.add(client);
			}
		} catch (IOException e) {
			logger.error("fastdfs池初始出错");
			logger.error(e.getMessage());
		} finally {
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {

					logger.error("fastdfs池trackerServer关闭出错");
					logger.error(e.getMessage());
				}
			}
		}
	}

	// 1. pop one connection from the idleConnectionPool,
	// 2. push the connection into busyConnectionPool;
	// 3. return the connection
	// 4. if no idle connection, do wait for wait_time seconds, and check again
	public StorageClient1 getStorgeClient(int waitTimes)
			throws InterruptedException {
		StorageClient1 client = idleConnectionPool.poll(waitTimes,
				TimeUnit.SECONDS);
		busyConnectionPool.put(client, obj);
		return client;
	}

	// 1. pop the connection from busyConnectionPool;
	// 2. push the connection into idleConnectionPool;
	// 3. do nessary cleanup works.
	public void releaseStorgeClient(StorageClient1 client) {
		if (client == null) return;
		if (busyConnectionPool.remove(client) != null) {
			idleConnectionPool.add(client);
		}
	}

	// so if the connection was broken due to some erros (like
	// : socket init failure, network broken etc), drop this connection
	// from the busyConnectionPool, and init one new connection.
	public void drop(StorageClient1 client) {
		if (client == null) return;
		if (busyConnectionPool.remove(client) != null) {
			TrackerServer trackerServer = null;
			try {
				TrackerClient trackerClient = new TrackerClient();
				trackerServer = trackerClient.getConnection();

				StorageServer storageServer = null;
				StorageClient1 newClient = new StorageClient1(trackerServer, storageServer);

				idleConnectionPool.add(newClient);
			} catch (IOException e) {
				logger.error("fastdfs池drop出错");
				logger.error(e.getMessage());
			} finally {
				if (trackerServer != null) {
					try {
						trackerServer.close();
					} catch (IOException e) {
						logger.error("fastdfs池drop关闭trackerServer出错");
						logger.error(e.getMessage());
					}
				}
			}
		}
	}

}
