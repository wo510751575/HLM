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
 * 类说明：区域类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月9日
 */
public enum AreaType {
	
	/** 国家 */
	COUNTRY("1", "国家"),
	
	/** 省 */
	PROVINCE("2", "省"),
	
	/** 省 */
	CITY("3", "地市"),
	
	/** 省 */
	AREA("4", "区县"),
	;
	
	AreaType(String value, String name) {
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
