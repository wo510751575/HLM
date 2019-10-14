/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：终端类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月6日
 */
public enum TerminalType {
	
	/**
	 * 导购手机
	 */
	GM("导购手机"),
	
	/**
	 * 中控手机
	 */
	ZK("中控手机");

	TerminalType(String name){
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
