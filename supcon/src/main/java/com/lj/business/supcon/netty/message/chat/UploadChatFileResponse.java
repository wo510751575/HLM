/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：下载文件返回结果
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月3日
 */
public class UploadChatFileResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798462790159389979L;

	/**
	 * 视频消息ID，非空
	 */
	private String msgId;
	
	/**
	 * 查询标志，非空
	 * 1：导购端请求查询(默认)
	 * 2：导购助手请求查询
	 */
	private Integer findFlag = 1;
	
	/**
	 * 视频上传服务器后访问全路径
	 */
	private String fileUrl;

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the findFlag
	 */
	public Integer getFindFlag() {
		return findFlag;
	}

	/**
	 * @param findFlag the findFlag to set
	 */
	public void setFindFlag(Integer findFlag) {
		this.findFlag = findFlag;
	}

	/**
	 * @return the videoUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl the videoUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadChatVideoResponse [msgId=");
		builder.append(msgId);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append(", fileUrl=");
		builder.append(fileUrl);
		builder.append("]");
		return builder.toString();
	}

}
