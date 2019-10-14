/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.common;

import java.io.Serializable;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月9日
 */
public class VersionInfoMessage implements Serializable {

	private static final long serialVersionUID = 4302913010097396579L;
	
	/**
	 * 终端微信
	 */
	private String noWxGm;//
	
	/**
	 * 服务器当前版本号
	 */
	private int currentVersion;
	
	/**
	 * 服务器最低支持版本号
	 */
	private int limitVersion;

	/**
	 * 是否强制更新,默认为否
	 */
	private boolean forceUpdate = Boolean.FALSE;
	
	/**
	 * 版本名称
	 */
	private String versionName;
	
	/**
	 * 下载地址
	 */
	private String downloadUrl;
	
	/**
	 * 更新说明
	 */
	private String updateDesc;
	
	/**
	 * 文件大小
	 */
	private String fileSize;

//	/**
//	 * @return the imei
//	 */
//	public String getImei() {
//		return imei;
//	}
//
//	/**
//	 * @param imei the imei to set
//	 */
//	public void setImei(String imei) {
//		this.imei = imei;
//	}

	/**
	 * @return the currentVersion
	 */
	public int getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * @param currentVersion the currentVersion to set
	 */
	public void setCurrentVersion(int currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * @return the limitVersion
	 */
	public int getLimitVersion() {
		return limitVersion;
	}

	/**
	 * @param limitVersion the limitVersion to set
	 */
	public void setLimitVersion(int limitVersion) {
		this.limitVersion = limitVersion;
	}

	/**
	 * @return the forceUpdate
	 */
	public boolean isForceUpdate() {
		return forceUpdate;
	}

	/**
	 * @param forceUpdate the forceUpdate to set
	 */
	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the downloadUrl
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}

	/**
	 * @param downloadUrl the downloadUrl to set
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * @return the updateDesc
	 */
	public String getUpdateDesc() {
		return updateDesc;
	}

	/**
	 * @param updateDesc the updateDesc to set
	 */
	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	@Override
	public String toString() {
		return "VersionInfoMessage [noWxGm=" + noWxGm + ", currentVersion=" + currentVersion + ", limitVersion="
				+ limitVersion + ", forceUpdate=" + forceUpdate + ", versionName=" + versionName + ", downloadUrl="
				+ downloadUrl + ", updateDesc=" + updateDesc + ", fileSize=" + fileSize + "]";
	}
}
