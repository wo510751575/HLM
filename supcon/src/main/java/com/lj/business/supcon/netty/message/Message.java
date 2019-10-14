/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;


/**
 * 
 * 
 * 类说明：消息报文
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
public class Message {
	
	/** 消息ID长度 **/
	public static final int MSGID_LENGTH = 32;
	
	/**
	 * 消息编码
	 */
	private MessageCode code;
	
	/**
	 * 消息ID
	 */
	private String msgId;
	
	/**
	 * 消息体
	 */
	private String body;
	
	public Message() {
		super();
	}
	
	public Message(MessageCode code) {
		super();
		this.code = code;
	}
	
	public Message(MessageCode code, String body) {
		super();
		this.code = code;
		this.msgId = GUID.generateByUUID();
		this.body = StringUtils.toString(body);
	}
	
	public Message(MessageCode code, String msgId, String body) {
		super();
		this.code = code;
		this.msgId = msgId;
		this.body = StringUtils.toString(body);
	}

	/**
	 * @return the code
	 */
	public MessageCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(MessageCode code) {
		this.code = code;
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

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [code=");
		builder.append(code.getCode());
		builder.append("-");
		builder.append(code);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}

}
