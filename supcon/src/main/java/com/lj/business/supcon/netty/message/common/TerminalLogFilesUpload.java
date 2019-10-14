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
 * 类说明：中控端上传日志文件指令参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月27日
 */
public class TerminalLogFilesUpload extends BaseDto {

	private static final long serialVersionUID = 4243961651233309696L;

	/**
	 * 上传该时间（包括）以后的日志 格式：yyyyMMddHHmmss
	 */
	private String beginTime;

	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalLogFilesUpload [beginTime=");
		builder.append(beginTime);
		builder.append("]");
		return builder.toString();
	}
	
}
