/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.common;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：通讯响应报文参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月24日
 */
public class CommonResponse extends BaseResponse {

	private static final long serialVersionUID = 2261251154563976520L;

	/**
	 * 请求报文的消息编码，非空
	 */
	private short messageCode;
	
	public CommonResponse() {
		
	}
	
	public CommonResponse(short messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * @return the messageCode
	 */
	public short getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(short messageCode) {
		this.messageCode = messageCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonResponse [");
		builder.append(super.toString());
		builder.append(", messageCode=");
		builder.append(messageCode);
		builder.append("]");
		return builder.toString();
	}
	
}
