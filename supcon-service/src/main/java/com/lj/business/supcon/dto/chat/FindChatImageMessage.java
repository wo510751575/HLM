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
 * 类说明：请求中控获取图片消息图片
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月30日
 */
public class FindChatImageMessage implements Serializable {

	private static final long serialVersionUID = 2540099082956449798L;

	/**
	 * 终端微信，非空
	 */
	private String noWxShop;

	/**
	 * 图片消息ID，非空
	 */
	private String msgId;

	/**
	 * 获取图片的类型，非空
	 * 1:中图(默认)
	 * 2:大图
	 */
	private Integer type = 1;
	
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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
		builder.append("FindChatImageMessage [noWxShop=");
		builder.append(noWxShop);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append("]");
		return builder.toString();
	}

}
