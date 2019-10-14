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
 * 类说明：添加群成员请求
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 段志鹏
 *   
 * CreateDate: 2018年10月26日
 */
public class AddChatRoomRequest extends BaseRequest {

	private static final long serialVersionUID = -1719510494885154052L;

	
	/**
	 * 微信群名（ID）
	 */
	private String chatRoomName;
	
	/**
	 * 群成员微信
	 */
	private String userNames;


	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChatRoomRequest [chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", userNames=");
		builder.append(userNames);
		builder.append("]");
		return builder.toString();
	}

}
