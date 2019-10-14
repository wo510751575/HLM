/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.chatroom;

import java.io.Serializable;

/**
 * 
 * 类说明：解散群消息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 段志鹏
 *   
 * CreateDate: 2018年10月29日
 */
public class DismissChatRoomMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130832312642734758L;

	/**
	 * 中控微信，非空
	 */
	private String noWxZk;
	
	/**
	 * 微信群名（ID）
	 */
	private String chatRoomName;
	

	/**
	 * @return the noWxZk
	 */
	public String getNoWxZk() {
		return noWxZk;
	}

	/**
	 * @param noWxZk the noWxZk to set
	 */
	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
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
		builder.append("DelChatRoomMessage [noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append("]");
		return builder.toString();
	}

}
