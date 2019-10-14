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
 * 类说明：创建群消息
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
public class CreateChatRoomMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5130832312642734758L;

	/**
	 * 中控微信，非空
	 */
	private String noWxZk;
	/**
	 * 群组编号
	 */
	private String pmCode;
	
    private String pmName;



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
     * 导购编号，非空 .
     */
    private String memberNoGm;
    
    
	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}
    
	public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}
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

	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateChatRoomMessage [noWxZk=");
		builder.append(noWxZk);
		builder.append(", code=");
		builder.append(code);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", userNames=");
		builder.append(userNames);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
}
