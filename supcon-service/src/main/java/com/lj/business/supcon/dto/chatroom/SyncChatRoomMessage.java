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
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年9月10日
 */
public class SyncChatRoomMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130832312642734758L;

	/**
	 * 中控微信，非空
	 */
	private String noWxZk;
	
	/**
	 * 群名，为空时表示同步中控微信所有群信息
	 */
	private String chatRoomName;
	
	/**
	 * 是否立即同步
	 */
	private boolean nowSync;

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

	/**
	 * @return the nowSync
	 */
	public boolean isNowSync() {
		return nowSync;
	}

	/**
	 * @param nowSync the nowSync to set
	 */
	public void setNowSync(boolean nowSync) {
		this.nowSync = nowSync;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncChatRoomMessage [noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", nowSync=");
		builder.append(nowSync);
		builder.append("]");
		return builder.toString();
	}
}
