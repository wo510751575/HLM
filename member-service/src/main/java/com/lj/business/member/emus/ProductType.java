/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.emus;

/**
 * 
 * 类说明：产品类型
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月22日
 */
public enum ProductType {

	/** 邀约型. */
	INVITE("邀约型"),

	/** 非邀约型. */
	NOINVITE("非邀约型"),
	;

	ProductType(String name){
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
