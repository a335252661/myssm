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
	 * �ļ��ϴ�
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
            if(result ==false){//FTP����ʧ��
            	return false;
            }
            File srcFile = new File(filePath);
            fis = new FileInputStream(srcFile); 
            //�����ϴ�Ŀ¼ 
            boolean changeDirectory = ftpClient.changeWorkingDirectory(ftpPath); 
            if(changeDirectory==false){
            	logger.error("FTP·�������� ftpPath:"+ftpPath);
            	return false;
            }
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding(encode); 
            //�����ļ����ͣ������ƣ� 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            return ftpClient.storeFile(ftpFileName, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            logger.error("FTP�ͻ��˳���", e);
            return false;
        } finally { 
            try { 
            	fis.close();
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("�ر�FTP���ӷ����쳣��", e);
            } 
        } 
	}

	/**
	 * �ļ�����
	 * 
	 * @param ftpFilePath   �ļ�����·��
	 * @param filePath  �ļ�����
	 * @param encode    �����ʽ
	 * @param fileNames  ��Ҫ���ص��ļ�����
	 * @param downLoadFilePaths  ���سɹ����ļ����� key���ļ�����value:����·�� 
	 * @return
	 */
	public static boolean fileDownLoad(String ftpFilePath,String filePath,String encode,List<String> fileNames,Map<String,String> downLoadFilePaths){
		String fileNameStr = JSONUtil.toJsonString(fileNames);
		
		if(fileNameStr!=null){
			fileNameStr = fileNameStr.replace("[\"", "").replaceAll("\"]", "");
		}
		logger.info("+++++++++++++++++++++++++++ ���غ��ŵ�Ŀ¼:�� "+ filePath +" ��++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++ FTP�������ļ�λ��:�� "+ FTP_IP + ftpFilePath + fileNameStr +" ��++++++++++++++++++++++");

		FTPClient ftpClient = new FTPClient();
		FileOutputStream its = null;

        try { 
        	ftpClient.setDefaultTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setConnectTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
        	ftpClient.setDataTimeout(FTP_DEFAULT_TIMEOUT_SECOND);
            ftpClient.connect(FTP_IP,FTP_PORT); 
            boolean result = ftpClient.login(FTP_USERNAME, FTP_PASSWORD); 
            if(result ==false){//FTP����ʧ��
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
            	//�����ļ����ͣ������ƣ� 
            	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            	boolean downloadResult = ftpClient.retrieveFile(ftpFilePath+fileName,its);
            	if(downloadResult==false){//����ʧ�ܽ����ɵ��ļ�ɾ��
            		its.close();
                    if (srcFile.isFile()) {  
                    	srcFile.delete();  
                    } 
            	}else{//���سɹ�
            		downLoadFilePaths.put(fileName, srcFile.getPath());
            	}
			}
            logger.info("+++++++++++++++++++++++++++���سɹ�+++++++++++++++++++++++++++");
            return true;
        } catch (IOException e) { 
            logger.error("FTP�ͻ��˳���", e);
            return false; 
        } finally { 
            try { 
            	if(its!=null){
            		its.close();
            	}
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("�ر�FTP���ӷ����쳣��", e);
            } 
        } 
	}


    /**
     * �ƶ��ļ�
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
            if(result ==false){//FTP����ʧ��
            	return false;
            }
            boolean moved = ftpClient.rename(oldPath, newPath);
            return moved;
        } catch (IOException e) { 
            logger.error("FTP�ͻ��˳���", e);
            return false; 
        } finally { 
            try { 
            	if(its!=null){
            		its.close();
            	}
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                logger.error("�ر�FTP���ӷ����쳣��", e);
            } 
        } 
	}
	
	
	
	public static void main(String[] params){
//������������
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


		//�ӱ����ϴ��ļ�
		//�ϴε�Ŀ¼
//		String  ftpPath= "/home/ftpuser/usrmaster";
//		String fileName = "7.txt";
//		boolean ftpResult = FTPUtil.fileUpload(ftpPath, fileName, "D:\\tem\\7.txt", "utf-8");
//		System.out.println(ftpResult);

		//�ļ���ftp����
//		Map<String,String> downLoadFilePaths = new HashMap<String, String>();
//		List<String> fileNames = new ArrayList<String>();
//		fileNames.add("7.txt");
//		//ftp�������ϵ�Ŀ¼
//		String  ftpPath= "/home/test/";
//		boolean ftpResults = FTPUtil.fileDownLoad(ftpPath, "D:\\temDownLoad","GB2312", fileNames,downLoadFilePaths);
//		System.out.println(ftpResults);
//		//�ƶ��ļ�
		FTPUtil.fileRemove("/8002/ppp.jpg","/8002/Master//ppp.jpg");
		 
	}
    
}
