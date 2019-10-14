/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.cf.emus;

/**
 * 
 * 类说明：客户类型标识
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2018年04月24日
 */
public enum PmTypeFlag {

	/** 非成单 */
	NO_SUCCESS("0","非成单"),
	
	/** 成单 */
	SUCCESS("1","成单"),
	
	/** 放弃 */
	GIVE_UP("2","放弃")
	;
	
	private String code;
	private String name;
	
	PmTypeFlag(String code, String name){
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
