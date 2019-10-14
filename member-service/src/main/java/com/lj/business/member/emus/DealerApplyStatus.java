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
 * 类说明：经销商申请状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年5月1日
 */
public enum DealerApplyStatus {

	/** 待审核 */
	APPLY(0,"待审核"),
	
	/** 审核通过 */
	AUDIT(1,"审核通过"),
	
	/** 审核未通过 */
	REJECT(2,"审核未通过"),
	;
	
	private Integer code;
	private String name;
	
	DealerApplyStatus(Integer code, String name){
		this.code = code;
		this.name = name;
	}

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
