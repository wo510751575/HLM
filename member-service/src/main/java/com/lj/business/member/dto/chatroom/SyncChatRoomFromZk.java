/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.chatroom;

import java.io.Serializable;
import java.util.List;

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
 * CreateDate: 2018年9月8日
 */
public class SyncChatRoomFromZk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3086241717691715015L;

	/**
	 * 中控微信，非空
	 */
	private String noWxZk;
	
	/**
	 * 群列表
	 */
	private List<SyncChatRoom> roomList;


	public String getNoWxZk() {
		return noWxZk;
	}

	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}

	/**
	 * @return the roomList
	 */
	public List<SyncChatRoom> getRoomList() {
		return roomList;
	}

	/**
	 * @param roomList the roomList to set
	 */
	public void setRoomList(List<SyncChatRoom> roomList) {
		this.roomList = roomList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncChatRoomFromZk [noWxZk=");
		builder.append(noWxZk);
		builder.append(", roomList=");
		builder.append(roomList);
		builder.append("]");
		return builder.toString();
	}

}
