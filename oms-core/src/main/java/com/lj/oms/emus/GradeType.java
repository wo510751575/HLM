/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.emus;

/**
 * 
 * 
 * 类说明：机构级别
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年6月13日
 */
public enum GradeType {
	
	/** 系统 */
	SYSTEM("1", "系统"),
	
	/** 商户 */
	MERCHANT("2", "商户"),
	
	/** 经销商 */
	DEALER("3", "经销商"),
	
	/** 终端 */
	SHOP("4", "终端"),
	;
	
	GradeType(String value, String name) {
		this.value = value;
		this.name = name;
	}

	private String value;	// DB 存贮值
	private String name;	// 值对应的中文描述
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
