package cn.cld.untils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTPUtil {

	private static final String FTP_IP = PropertyUtils.getProperty("ftp.ip", "106.54.46.37");
	private static final int FTP_PORT = Integer.parseInt(PropertyUtils.getProperty("ftp.port", "21"));
	private static final String FTP_USERNAME = PropertyUtils.getProperty("ftp.username", "test");
	private static final String FTP_PASSWORD = PropertyUtils.getProperty("ftp.password", "test");
	private static final int FTP_DEFAULT_TIMEOUT_SECOND = Integer.parseInt(PropertyUtils.getProperty("ftp.default_timeout_second","3600"));
	
	private static final Logger logger = Logger.getLogger(FTPUtil.class);

	/**
	 * 文件上传
	 * 
	 * @param ftpUrl
	 * @param port
	 * @param filePath
	 */
	public static boolean fileUpload(
			String ftpPath
			,String ftpFileName
			,String filePath
			,String encode){
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null; 

        try { 
        	ftpClient.setDefaultTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setConnectTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setDataTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
            ftpClient.connect(FTP_IP,FTP_PORT); 
            boolean result = ftpClient.login(FTP_USERNAME, FTP_PASSWORD); 
            if(result ==false){//FTP连接失败
            	return false;
            }
            File srcFile = new File(filePath);
            fis = new FileInputStream(srcFile); 
            //设置上传目录 
            boolean changeDirectory = ftpClient.changeWorkingDirectory(ftpPath); 
            if(changeDirectory==false){
            	logger.error("FTP路径不存在 ftpPath:"+ftpPath);
            	return false;
            }
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding(encode); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            return ftpClient.storeFile(ftpFileName, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            logger.error("FTP客户端出错！", e);
            return false;
        } finally { 
            try { 
            	fis.close();
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("关闭FTP连接发生异常！", e);
            } 
        } 
	}

	/**
	 * 文件下载
	 * 
	 * @param ftpFilePath   文件所在路径
	 * @param filePath  文件下载
	 * @param encode    编码格式
	 * @param fileNames  需要下载的文件集合
	 * @param downLoadFilePaths  下载成功的文件集合 key：文件名；value:下载路径 
	 * @return
	 */
	public static boolean fileDownLoad(String ftpFilePath,String filePath,String encode,List<String> fileNames,Map<String,String> downLoadFilePaths){
		String fileNameStr = JSONUtil.toJsonString(fileNames);
		
		if(fileNameStr!=null){
			fileNameStr = fileNameStr.replace("[\"", "").replaceAll("\"]", "");
		}
		logger.info("+++++++++++++++++++++++++++ 下载后存放的目录:【 "+ filePath +" 】++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++ FTP服务器文件位置:【 "+ FTP_IP + ftpFilePath + fileNameStr +" 】++++++++++++++++++++++");

		FTPClient ftpClient = new FTPClient();
		FileOutputStream its = null;

        try { 
        	ftpClient.setDefaultTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setConnectTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setDataTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
            ftpClient.connect(FTP_IP,FTP_PORT); 
            boolean result = ftpClient.login(FTP_USERNAME, FTP_PASSWORD); 
            if(result ==false){//FTP连接失败
            	return false;
            }
            for (String fileName : fileNames) {
            	File srcFile = new File(filePath+File.separator+fileName);
    			if(!srcFile.exists()){  
                    (new File(srcFile.getParent())).mkdirs();  
                } 
            	its = new FileOutputStream(srcFile); 
            	
            	ftpClient.setBufferSize(1024); 
            	ftpClient.setControlEncoding(encode); 
            	//设置文件类型（二进制） 
            	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            	boolean downloadResult = ftpClient.retrieveFile(ftpFilePath+fileName,its);
            	if(downloadResult==false){//下载失败将生成的文件删除
            		its.close();
                    if (srcFile.isFile()) {  
                    	srcFile.delete();  
                    } 
            	}else{//下载成功
            		downLoadFilePaths.put(fileName, srcFile.getPath());
            	}
			}
            logger.info("+++++++++++++++++++++++++++下载成功+++++++++++++++++++++++++++");
            return true;
        } catch (IOException e) { 
            logger.error("FTP客户端出错！", e);
            return false; 
        } finally { 
            try { 
            	if(its!=null){
            		its.close();
            	}
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("关闭FTP连接发生异常！", e);
            } 
        } 
	}


    /**
     * 移动文件
     * @param oldPath
     * @param newPath
     * @return
     */
	public static boolean fileRemove(String oldPath,String newPath){
		FTPClient ftpClient = new FTPClient();
		FileOutputStream its = null;

        try { 
            ftpClient.connect(FTP_IP,FTP_PORT); 
            boolean result = ftpClient.login(FTP_USERNAME, FTP_PASSWORD); 
            if(result ==false){//FTP连接失败
            	return false;
            }
            boolean moved = ftpClient.rename(oldPath, newPath);
            return moved;
        } catch (IOException e) { 
            logger.error("FTP客户端出错！", e);
            return false; 
        } finally { 
            try { 
            	if(its!=null){
            		its.close();
            	}
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("关闭FTP连接发生异常！", e);
            } 
        } 
	}
	
	
	
	public static void main(String[] params){
//测试连接栗子
//		FTPClient ftpClient = new FTPClient();
//		try{
//			ftpClient.setDefaultTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
//			ftpClient.setConnectTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
//			ftpClient.setDataTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
//			ftpClient.connect(FTP_IP,FTP_PORT);
//			boolean result = ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
//			System.out.println(result);
//		}catch (Exception e){
//			e.printStackTrace();
//		}


		//从本地上传文件
		//上次的目录
//		String  ftpPath= "/home/ftpuser/usrmaster";
//		String fileName = "7.txt";
//		boolean ftpResult = FTPUtil.fileUpload(ftpPath, fileName, "D:\\tem\\7.txt", "utf-8");
//		System.out.println(ftpResult);

		//文件从ftp下载
//		Map<String,String> downLoadFilePaths = new HashMap<String, String>();
//		List<String> fileNames = new ArrayList<String>();
//		fileNames.add("7.txt");
//		//ftp服务器上的目录
//		String  ftpPath= "/home/test/";
//		boolean ftpResults = FTPUtil.fileDownLoad(ftpPath, "D:\\temDownLoad","GB2312", fileNames,downLoadFilePaths);
//		System.out.println(ftpResults);
//		//移动文件
		FTPUtil.fileRemove("/8002/ppp.jpg","/8002/Master//ppp.jpg");
		 
	}
    
}
