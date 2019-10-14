
package com.lj.business.enums;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：请求来源渠道
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public enum SourceChannel {

	/**
	 * 浏览器
	 */
	WEB("浏览器"),
	
	/**
	 * 移动浏览器
	 */
	WAP("移动浏览器"),
	
	/**
	 * 移动应用
	 */
	APP("移动应用"),
	
	/**
	 * 远程调用
	 */
	RMI("远程调用"),
	
	/**
	 * POS终端
	 */
	POS("POS终端"),
	
	/**
	 * 后台系统调用
	 */
	SYS("系统调用");
	
	private String text;
	
	private SourceChannel(String text) {
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
}
