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
 * 类说明：请求获取群二维码
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
public class GetChatRoomQRCodeResponse extends BaseResponse {

	
	private static final long serialVersionUID = -4981120081004299718L;

	/**
	 * 微信群id
	 */
	private String chatRoomName;
	
	/**
	 * 微信群二维码
	 */
	private String chatRoomRQCode;
	
	/**
	 * 群昵称
	 */
	private String roomNickName;
	/**
	 * type =1 获取群二维码
	 * type =2 修改群名称
	 */
	private int type;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRoomNickName() {
		return roomNickName;
	}
	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}
	public String getChatRoomName() {
		return chatRoomName;
	}
	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}
	public String getChatRoomRQCode() {
		return chatRoomRQCode;
	}
	public void setChatRoomRQCode(String chatRoomRQCode) {
		this.chatRoomRQCode = chatRoomRQCode;
	}
	
	
	

	
	

}
