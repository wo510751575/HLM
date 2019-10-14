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
 * 类说明：创建群请求
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
public class CreateChatRoomRequest extends BaseRequest {

	private static final long serialVersionUID = -1719510494885154052L;

	/**
	 * 服务器群code
	 */
	private String code;
	
	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	/**
	 * 群成员微信
	 */
	private String userNames;
	
	/**
	 * 群主使用的微信号
	 */
	private String noWxGm;

	/**
	 * 导购编号
	 */
	private String memberNoGm;

	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateChatRoomRequest [code=");
		builder.append(code);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", userNames=");
		builder.append(userNames);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}

}
