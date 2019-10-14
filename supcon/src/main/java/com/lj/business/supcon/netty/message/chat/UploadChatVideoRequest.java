/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：视频类型聊天消息请求中控上传视频文件
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
public class UploadChatVideoRequest extends BaseDto {

	private static final long serialVersionUID = -5737319962906787258L;

	/**
	 * 视频消息ID，非空
	 */
	private String msgId;
	
	/**
	 * 视频内容参数，非空
	 */
	private String content;

	/**
	 * 查询标志，非空
	 * 1：导购端请求查询(默认)
	 * 2：导购助手请求查询
	 */
	private Integer findFlag = 1;
	/**
	 * 导购编号-导购端必填
	 */
	private String memberNoGm;
	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadChatVideoRequest [msgId=");
		builder.append(msgId);
		builder.append(", content=");
		builder.append(content);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append("]");
		return builder.toString();
	}

}
