/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
 * CreateDate: 2018年7月17日
 */
public class FindMemberChatCount implements Serializable {
	
	private static final long serialVersionUID = 6363441203909296125L;

	
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 聊天时间 - 从
	 */
	private Date chatBeginTime;
	
	/**
	 * 聊天时间 - 到
	 */
	private Date chatEndTime;


	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the chatBeginTime
	 */
	public Date getChatBeginTime() {
		return chatBeginTime;
	}

	/**
	 * @param chatBeginTime the chatBeginTime to set
	 */
	public void setChatBeginTime(Date chatBeginTime) {
		this.chatBeginTime = chatBeginTime;
	}

	/**
	 * @return the chatEndTime
	 */
	public Date getChatEndTime() {
		return chatEndTime;
	}

	/**
	 * @param chatEndTime the chatEndTime to set
	 */
	public void setChatEndTime(Date chatEndTime) {
		this.chatEndTime = chatEndTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMemberChatCount [");
		builder.append("merchantNo=");
		builder.append(merchantNo);
		builder.append(", chatBeginTime=");
		builder.append(chatBeginTime);
		builder.append(", chatEndTime=");
		builder.append(chatEndTime);
		builder.append("]");
		return builder.toString();
	}

}
