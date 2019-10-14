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
 * 类说明：视频类型聊天消息请求中控上传视频文件返回结果
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
public class UploadChatVideoResponse extends BaseResponse {

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
	private String videoUrl;

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
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * @param videoUrl the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadChatVideoResponse [msgId=");
		builder.append(msgId);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append(", videoUrl=");
		builder.append(videoUrl);
		builder.append("]");
		return builder.toString();
	}

}
