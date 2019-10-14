/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.common;

import com.lj.business.supcon.netty.message.BaseRequest;


/**
 * 
 * 类说明：心跳包请求参数
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
public class HeartBeatRequest extends BaseRequest {

	private static final long serialVersionUID = -3795193042678357761L;

	/**
	 * 时间戳
	 */
	private long timestamp;

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeartBeatRequest [timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}
	
}
