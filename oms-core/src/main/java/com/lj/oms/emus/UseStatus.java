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
 * 类说明：启用状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年1月29日
 */
public enum UseStatus {
	/** 是. */
	YES("1", "是"),
	/** 否. */
	NO("0", "否"),
	;
	
	UseStatus(String value, String name) {
		this.value = value;
		this.name = name;
	}

	private String value;// DB 存贮值
	private String name;// 值对应的中文描述
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
