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
 * 类说明：发送人同步状态
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年3月1日
 */
public enum SenderSyncStatus {
	
	/** 未同步 */
	NO(0,"未同步"),
	
	/** 已同步 */
	YES(1,"已同步")
	;
	
	SenderSyncStatus(Integer code,String name){
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
