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
 * 类说明：请求下载文件报文
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月30日
 */
public class UploadChatFileRequest extends BaseDto {

	private static final long serialVersionUID = 7999802407814209750L;
	
	/**
	 * 消息ID，非空
	 */
	private String msgId;

	private String content;

	private int findFlag;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFindFlag() {
		return findFlag;
	}

	public void setFindFlag(int findFlag) {
		this.findFlag = findFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CancelChatInfoRequest [msgId=");
		builder.append(msgId);
		builder.append(",content=").append(content);
		builder.append(",findFlag=").append(findFlag);
		builder.append("]");
		return builder.toString();
	}
	
}
