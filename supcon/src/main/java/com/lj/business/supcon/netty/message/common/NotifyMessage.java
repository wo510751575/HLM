/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.common;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：向客户端推送通知
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月20日
 */
public class NotifyMessage extends BaseDto {

	private static final long serialVersionUID = 7521684329309594311L;

	/**
	 * 通知类型，非空
	 * 10-中控手机异常预警
	 * 20-未认领客户提醒
	 * 30-设置添加好友数量
	 */
	private String type;
	
	/**
	 * 通知标题，非空
	 */
	private String title;
	
	/**
	 * 通知内容
	 * type = 30 时为json 串
	 */
	private String content;
	
	/**
	 * 发送通知时间戳
	 */
	private Long timestamp;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotifyMessage [type=");
		builder.append(type);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
