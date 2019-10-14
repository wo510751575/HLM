/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.enums;

/**
 * 
 * 类说明：连接来源
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月13日
 */
public enum ConnectSource {
	
	/**
	 * 导购手机客户端
	 */
	GM("导购客户端"),
	
	/**
	 * 中控手机客户端
	 */
	ZK("中控客户端");
	
	private String name;

	ConnectSource(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
