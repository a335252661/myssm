package cn.cld.untils.fastdfs;

import cn.cld.untils.PropertyUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;

public  class FastDFSClient {
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;
    private String serviceURL = null;

    public FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
        }
        serviceURL=PropertyUtils.getProperty("tracker_server");
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        //拼接上服务器地址
        return serviceURL.replaceAll(":22122","").concat("/"+result);
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    /**
     * 上传fastdfs使用测试
     * @throws Exception
     */
    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("classpath:system.properties");
        String string = fastDFSClient.uploadFile("C:/tmp/ppp.jpg");
        //group1/M00/00/00/rBCWglyJONWAZixCAAD6XYkIIY4729.jpg
    }
}