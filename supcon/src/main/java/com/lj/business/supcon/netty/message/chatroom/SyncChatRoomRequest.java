/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chatroom;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 类说明：同步群通讯录请求
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年9月7日
 */
public class SyncChatRoomRequest extends BaseRequest {

	private static final long serialVersionUID = -1719510494885154052L;

	/**
	 * 群名，为空时同步所有群信息
	 */
	private String chatRoomName;

	/**
	 * @return the chatRoomName
	 */
	public String getChatRoomName() {
		return chatRoomName;
	}

	/**
	 * @param chatRoomName the chatRoomName to set
	 */
	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncChatRoomRequest [chatRoomName=");
		builder.append(chatRoomName);
		builder.append("]");
		return builder.toString();
	}
	
}
