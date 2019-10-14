/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

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
public class PushNotifyMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 770407996392305345L;

	/**
	 * 导购编号，非空
	 * 向中控推送是为noWx
	 */
	private String cacheAccountNo;
	
	/**
	 * 通知类型，非空
	 */
	private String type;
	
	/**
	 * 通知标题，非空
	 */
	private String title;
	
	/**
	 * 通知内容
	 */
	private String content;
	
	/**
	 * 是否允许离线推送,即客户端离线时做为离线消息保存起来,客户端重登录后再推给客户端
	 * 默认为不允许
	 */
	private boolean offlinePush =false;


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
	 * @return the offlinePush
	 */
	public boolean isOfflinePush() {
		return offlinePush;
	}

	/**
	 * @param offlinePush the offlinePush to set
	 */
	public void setOfflinePush(boolean offlinePush) {
		this.offlinePush = offlinePush;
	}

	public String getCacheAccountNo() {
		return cacheAccountNo;
	}

	public void setCacheAccountNo(String cacheAccountNo) {
		this.cacheAccountNo = cacheAccountNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PushNotifyMessage [cacheAccountNo=");
		builder.append(cacheAccountNo);
		builder.append(", type=");
		builder.append(type);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", offlinePush=");
		builder.append(offlinePush);
		builder.append("]");
		return builder.toString();
	}

}
