/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.weixin.emus;

/**
 * 
 * 
 * 类说明：朋友圈任务发送状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 *   
 * CreateDate: 2017年12月23日
 */
public enum SendFriendsJobStatus {
	
	INIT(1,"创建任务"),
	SENDING(2,"发送中"),
	SUCCESS(3,"发送成功"),
	FAILURE(4,"发送失败");
	
	SendFriendsJobStatus(Integer code,String name){
		this.code = code;
		this.name = name;
	}
	
	private Integer code;
	private String name;
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
