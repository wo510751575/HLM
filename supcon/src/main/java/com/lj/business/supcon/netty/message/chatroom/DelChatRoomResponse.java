/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chatroom;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：删除群成员请求结果返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 段志鹏
 *   
 * CreateDate: 2018年10月25日
 */
public class DelChatRoomResponse extends BaseResponse {

	private static final long serialVersionUID = -1719510494885154052L;
	/**
	 * 群昵称
	 */
	private String chatRoomName;
	
	/**
	 * 群成员微信
	 */
	private String userNames;

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddChatRoomResponse [chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", userNames=");
		builder.append(userNames);
		builder.append("]");
		return builder.toString();
	}

}
