package cn.cld.untils.fastdfs;

import java.io.Serializable;

/**
 * FastDFSResult, 执行FastDFS的结果
 */
public class FastDFSResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4153526537576545302L;
	private boolean success;   //是否执行成功
	private String fileName;//原文件名称
	private String errorInfo;    //错误信息
	private String remoteFileUrl;  //远程文件的链接地址,不包括域名或IP，只包含后面的部分
	private String imgUrl; //图片完整地址
	private int imgWidth;   //图片宽
	private int imgHeight;  //图片高
	
	/**
	 * 是否执行成功
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * 原文件名称
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 错误信息
	 * 
	 * @return
	 */
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	/**
	 * 远程文件的链接地址,不包括域名或IP，只包含后面的部分
	 * 
	 * @return
	 */
	public String getRemoteFileUrl() {
		return remoteFileUrl;
	}
	public void setRemoteFileUrl(String remoteFileUrl) {
		this.remoteFileUrl = remoteFileUrl;
	}
	/**
	 * 图片宽
	 * 
	 * @return
	 */
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	/**
	 * 图片高
	 * 
	 * @return
	 */
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
