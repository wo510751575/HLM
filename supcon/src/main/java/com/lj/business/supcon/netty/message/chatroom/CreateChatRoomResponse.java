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
 * 类说明：创建群请求结果返回
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
public class CreateChatRoomResponse extends BaseResponse {

	private static final long serialVersionUID = -1719510494885154052L;
	/**
	 * 服务器群code
	 */
	private String code;
	
	/**
	 * 群ID
	 */
	private String chatRoomName;
	
	/**
	 * 群头像
	 */
	private String headUrl;
	/**
	 * 群成员昵称微信（导购端用）
	 */
	private String nickNames;
	
	/**
	 * 终端微信号
	 */
	private String noWxGm;
	

	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	
	public String getNickNames() {
		return nickNames;
	}

	public void setNickNames(String nickNames) {
		this.nickNames = nickNames;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateChatRoomResponse [code=");
		builder.append(code);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", nickNames=");
		builder.append(nickNames);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append("]");
		return builder.toString()+super.toString();
	}

}
