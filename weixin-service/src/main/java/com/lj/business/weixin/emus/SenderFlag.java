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
 * 类说明：消息发送人标识枚举
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年10月27日
 */
public enum SenderFlag {
	
	/** 导购发送 */
	GM(1,"导购发送"),
	
	/** 客户发送 */
	ZK(2,"客户发送")
	;
	
	SenderFlag(Integer code,String name){
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
