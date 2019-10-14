/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.chat;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：请求撤回聊天记录
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
public class CancelChatMessage implements Serializable {

	private static final long serialVersionUID = -8667422607532893994L;

	/**
	 * 终端微信，非空
	 */
	private String noWxShop;

	/**
	 * 消息ID，非空
	 */
	private String msgId;

	/**
	 * @return the noWxShop
	 */
	public String getNoWxShop() {
		return noWxShop;
	}

	/**
	 * @param noWxShop the noWxShop to set
	 */
	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CancelChatMessage [noWxShop=");
		builder.append(noWxShop);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append("]");
		return builder.toString();
	}
	
}
