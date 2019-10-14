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
 * 类说明：聊天消息响应报文参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月1日
 */
public class ChatInfoResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3657894547701614840L;
	
	/**
	 * 原消息ID
	 */
	private String msgId;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatInfoResponse [");
		builder.append(super.toString());
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append("]");
		return builder.toString();
	}

	
}
