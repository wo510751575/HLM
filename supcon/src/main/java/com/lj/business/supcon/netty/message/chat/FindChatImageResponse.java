/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.chat;

import com.lj.business.supcon.netty.message.BaseResponse;
import com.lj.business.supcon.netty.message.ResponseCode;

/**
 * 
 * 类说明：返回图片消息图片路径报文
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
public class FindChatImageResponse extends BaseResponse {

	private static final long serialVersionUID = 1771808723903718499L;

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
	 * 1：导购端请求查询(默认)
	 * 2：导购助手请求查询
	 */
	private Integer findFlag = 1;
	
	/**
	 * 图片访问路径，处理成功时(result=true)返回
	 */
	private String img;
	
	/**
	 * 图片扩展内容，处理成功时(result=true)返回
	 */
	private String content;
	
	public FindChatImageResponse() {
		super();
	}

	public FindChatImageResponse(ResponseCode responseCode) {
		super(responseCode);
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
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
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
		builder.append("FindChatImageResponse [msgId=");
		builder.append(msgId);
		builder.append(", type=");
		builder.append(type);
		builder.append(", findFlag=");
		builder.append(findFlag);
		builder.append(", img=");
		builder.append(img);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
