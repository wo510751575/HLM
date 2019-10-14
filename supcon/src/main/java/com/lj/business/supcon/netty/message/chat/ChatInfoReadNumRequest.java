/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 聊天未读数报文
 * @author wo510
 *
 */
public class ChatInfoReadNumRequest extends BaseRequest {

	private static final long serialVersionUID = 5069064010406196646L;
	
	/**
	 * 客户编号
	 * 群聊为群code
	 */
	private String memberNo;
	
	/**
	 * 该客户未读数量
	 * 为0时取消气泡
	 */
	private int num;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatInfoReadNumRequest [memberNo=");
		builder.append(memberNo);
		builder.append(", num=");
		builder.append(num);
		builder.append("]");
		return builder.toString();
	}
}
