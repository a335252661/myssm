package cn.cld.untils.fastdfs;

import cn.cld.untils.PropertyUtils;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.StorageClient1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * FastDFS 工具类,提供文件(主要是图片的分布式存储/获取/删除）
 * @author xuchangwen
 */

public class FastDFSUtil {
	//nginx http load balance serverip
	private static final String FASTDFS_SERVER_IP = PropertyUtils.getProperty("FASTDFS_SERVER_IP");
	private static final int HTTP_TRACKER_HTTP_PORT = Integer.parseInt(PropertyUtils.getProperty("http_tracker_http_port"));
	private static final Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

	/**
	 * 读取输入流信息并输出到目标文件
	 * 
	 * @param ins
	 * 			输入流
	 * @param file
	 * 			输出目标文件
	 * @return
	 */
	public static boolean inputstream2File(InputStream ins, File file) {
		boolean success = false;
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
			success = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return success;
	}
	
	/**
	 * 上传文件
	 * @param  InputStream
	 * @return FastDFSResult
	 */
	public static FastDFSResult uploadFile(InputStream fis, String file_ext_name) {
		FastDFSResult result = new FastDFSResult();
		StorageClient1 storageClient = null;
		try {
			storageClient = getStorageClient();
			if (storageClient == null) {
				 result.setSuccess(false);
				 result.setErrorInfo("未获取到storageClient");
				 return result;
			}
			
	        byte[] file_buff = null;  
	        
	        if (fis == null) {
	        	result.setSuccess(false);
	        	return result;
	        }
	        int len = fis.available();  
	        file_buff = new byte[len];  
	        fis.read(file_buff);  
	        
	        //创建一个临时文件，然后再获取宽度
	        result.setImgWidth(0);
	        result.setImgHeight(0);
	        
	        logger.info("file length: " + file_buff.length);  
	          
	        long startTime = System.currentTimeMillis();
	        //判断文件后缀名
	        logger.info("======================start upload_file");
	        String results = storageClient.upload_file1(file_buff, file_ext_name, null);
	        logger.info("======================end upload_file");
	        logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
	        if (results == null) {  
	        	logger.error("upload file fail, error code: " + storageClient.getErrorCode());  
	            result.setSuccess(false);
	            result.setErrorInfo("upload file fail, error code: " + storageClient.getErrorCode());
	            return result; 
	        }  
//	        if (!results.startsWith("/")) {
//	        	results = "/" + results;
//	        }
	        logger.info("************RemoteFileUrl="+results);
	        result.setRemoteFileUrl(results);
	        
//	        try {
//	        	// 2015/01/26 由URL修改为本地获取图片宽高
//	        	ByteArrayInputStream bais = new ByteArrayInputStream(file_buff);
//	        	BufferedImage img = ImageIO.read(bais);
//	        	if (img != null) {
//	        		result.setImgWidth(img.getWidth());
//		        	result.setImgHeight(img.getHeight());
//	        	} else {
//					logger.error("BufferedImage is null");
//				}
//			} catch (Exception e) {
//				logger.warn("获取图片宽高发生异常：" + e);
//			}
	        
	        releaseStorageClient(storageClient);
		} catch (InterruptedException e) {
			//确实没有空闲连接,并不需要删除与fastdfs连接
			result.setSuccess(false);
			result.setErrorInfo(e.getMessage());
			logger.warn("没有空闲连接：" + e);
			return result;
		} catch(Exception e) {
			 result.setSuccess(false);
			 result.setErrorInfo(e.getMessage());
			 ConnectionPool.getPoolInstance().drop(storageClient);
			 logger.error(e.getMessage());
			 return result;
		}
		
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 上传文件
	 * @param  localFilePath 上传文件的本地路径
	 * @return FastDFSResult
	 */
	public static FastDFSResult uploadFile(String localFilePath) {
		File localFile = new File(localFilePath);
		if (! localFile.exists()) {
			return null;
		}
		
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(localFilePath));
			FastDFSResult result = uploadFile(bis, FilenameUtils.getExtension(localFile.getName()));
			bis.close();
			return result;
		} catch (Exception e) {
			logger.error("读取本地文件出错", e);
			return null;
		}
		
	}
	
	/**
	 * 获取fastDFS 访问文件的URL地址
	 * fastDFS  可能没有通过域名访问文件的权限，只能通过IP访问
	 */
	public static String getFastDFSHttpRequestURL(String remoteUrl) {
		if (null == remoteUrl || 0 == remoteUrl.trim().length()) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(FASTDFS_SERVER_IP);
		int http_tracker_http_port = HTTP_TRACKER_HTTP_PORT;
		if (http_tracker_http_port != 80) {
			buffer.append(":");
			buffer.append(http_tracker_http_port);
		}
		if (!remoteUrl.startsWith("/")) {
			buffer.append("/");
		}
		buffer.append(remoteUrl);
		return buffer.toString();
	}
	
	/**
	 * 删除文件
	 */
	public static FastDFSResult deleteFile(String fileURL) {
		FastDFSResult result = new FastDFSResult();
		result.setSuccess(false);
		StorageClient1 storageClient = null;
		try {
			storageClient = getStorageClient();  
		    if (storageClient == null) {
		    	logger.error("未获取到storageClient");
				return null;
			}
		    int success = storageClient.delete_file1(fileURL);
		    if (0 == success) {
		    	result.setSuccess(true);
		    } else {
		    	logger.error("删除文件失败：result=" + success);
			}
		    releaseStorageClient(storageClient);
		} catch (InterruptedException e) {
			//确实没有空闲连接,并不需要删除与fastdfs连接
			logger.warn("没有空闲连接：" + e);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setErrorInfo(e.getMessage());
			ConnectionPool.getPoolInstance().drop(storageClient);
		}
		return result;
	}
	
	/**
	 * 下载图片
	 * @param fileURL
	 * @return
	 */
	public static byte[] downloadFile(String fileURL){
		byte[] iStream = null;
		StorageClient1 storageClient = null;
		try {
		    storageClient = getStorageClient();  
		    if (storageClient == null) {
		    	logger.error("未获取到storageClient");
				 return null;
			}
	        iStream = storageClient.download_file1(fileURL);	        	        
			releaseStorageClient(storageClient);
		} catch (InterruptedException e) {
			//确实没有空闲连接,并不需要删除与fastdfs连接
			logger.warn("没有空闲连接：" + e);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			ConnectionPool.getPoolInstance().drop(storageClient);
		} 
		return iStream;
	}
	
	public static boolean downloadFile(String fileURL, String destFileName){
		ByteArrayInputStream bais = null;
		try {
			byte[] file_buff = FastDFSUtil.downloadFile(fileURL);
			bais = new ByteArrayInputStream(file_buff);
			File file = new File(destFileName);
			if(!file.exists()){  
                (new File(file.getParent())).mkdirs();
            } 
			if (!FastDFSUtil.inputstream2File(bais, file)) {
				return false;
			}
		} catch (Exception e) {
			logger.error("downloadFile 下载error!!!", e);
			return false;
		} finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (Exception e) {
					logger.error("downloadFile 下载error!!!", e);
					return false;
				}
			}
		}
		return true;
	}
	
	public static StorageClient1 getStorageClient() throws InterruptedException {
		StorageClient1 storageClient =  ConnectionPool.getPoolInstance().getStorgeClient(10);
	    return storageClient;
	}
	
	public static void releaseStorageClient(StorageClient1 storageClient){
		ConnectionPool.getPoolInstance().releaseStorgeClient(storageClient);
	}
	

	public static boolean httpDownload(String httpUrl, String saveFile) {
       // 下载网络文件  
       int bytesum = 0;  
       int byteread = 0;    
       URL url = null;
	    try {  
	        url = new URL(httpUrl);
	    } catch (MalformedURLException e1) {
	        // TODO Auto-generated catch block  
	        e1.printStackTrace();  
	        return false;  
	    }  
	  
       try {  
           URLConnection conn = url.openConnection();
           InputStream inStream = conn.getInputStream();
           FileOutputStream fs = new FileOutputStream(saveFile);
  
           byte[] buffer = new byte[1204];  
           while ((byteread = inStream.read(buffer)) != -1) {  
               bytesum += byteread;  
               //System.out.println(bytesum);  
               fs.write(buffer, 0, byteread);  
           }  
           return true;  
       } catch (FileNotFoundException e) {
           e.printStackTrace();  
           return false;  
       } catch (IOException e) {
           e.printStackTrace();  
           return false;  
       }  
   }  	
	
	
}
