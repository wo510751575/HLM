/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：撤回聊天消息结果报文
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月30日
 */
public class MemberCancelChatInfoResponse extends BaseResponse {

	private static final long serialVersionUID = -201712295019261743L;

	/**
	 * 消息ID，非空
	 */
	private String msgId;
	/**
	 * 客户编号
	 */
	private String memberNo;
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CancelChatInfoResponse [msgId=");
		builder.append(msgId);
		builder.append("]");
		return builder.toString();
	}
	
}
