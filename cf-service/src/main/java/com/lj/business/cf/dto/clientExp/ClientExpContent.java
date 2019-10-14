/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.cf.dto.clientExp;

import java.io.Serializable;

/**
 * 
 * 类说明：到店体验内容
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月14日
 */
public class ClientExpContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885952793095038022L;

	/**
	 * 类型：0文本、1语音
	 */
	private String type;
	
	/**
	 * 录入时间
	 */
	private String time;
	
	/**
	 * 文本内容（或语音转化的文字）
	 */
	private String text;
	
	/**
	 * 语音长度
	 */
	private String recordTime;
	
	/**
	 * 访问路径
	 */
	private String voidFilePath;
	
	/**
	 * 外网访问路径（建议使用）
	 */
	private String url;

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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the recordTime
	 */
	public String getRecordTime() {
		return recordTime;
	}

	/**
	 * @param recordTime the recordTime to set
	 */
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * @return the voidFilePath
	 */
	public String getVoidFilePath() {
		return voidFilePath;
	}

	/**
	 * @param voidFilePath the voidFilePath to set
	 */
	public void setVoidFilePath(String voidFilePath) {
		this.voidFilePath = voidFilePath;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientExpContent [type=");
		builder.append(type);
		builder.append(", time=");
		builder.append(time);
		builder.append(", text=");
		builder.append(text);
		builder.append(", recordTime=");
		builder.append(recordTime);
		builder.append(", voidFilePath=");
		builder.append(voidFilePath);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
