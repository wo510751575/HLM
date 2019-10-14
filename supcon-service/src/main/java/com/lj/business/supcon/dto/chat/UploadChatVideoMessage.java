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
 * 类说明：请求上传视频消息视频文件
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月3日
 */
public class UploadChatVideoMessage implements Serializable {

	private static final long serialVersionUID = -8790472874125527802L;
	
	/**
	 * 终端微信，非空
	 */
	private String noWxShop;

	/**
	 * 视频消息ID，非空
	 */
	private String msgId;
	
	/**
	 * 视频内容参数，非空
	 */
	private String content;
	
	/**
	 * 查询标志，非空
	 * 1：导购端请求查询
	 * 2：导购助手请求查询(默认)
	 */
	private Integer findFlag = 2;

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

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the findFlag
	 */
	public Integer getFindFlag() {
		return findFlag;
	}

	/**
	 * @param findFlag the findFlag to set
	 */
	public void setFindFlag(Integer findFlag) {
		this.findFlag = findFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadChatVideoMessage [noWxShop=");
		builder.append(noWxShop);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", content=");
		builder.append(content);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append("]");
		return builder.toString();
	}
	
}
