/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.emus;

/**
 * 
 * 类说明：中控状态。
 *  
 * 
 * <p>'状态：0未激活、1正常、2注销
 *   
 * @Company: 扬恩科技有限公司
 * @author lhy
 *   
 * CreateDate: 2018年3月26日
 */
public enum ShopTerminalStatus {
	/** 未激活. */
	NOTACTIVE(0, "未激活"),
	/** 正常. */
	NORMAL(1, "正常"),
	/** 注销. */
	CANCELLATION(2, "注销");
	
	ShopTerminalStatus(Integer value, String chName) {
		this.value = value;
		this.chName = chName;
	}

	private Integer value;// DB 存贮值
	private String chName;// 值对应的中文描述
	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	/**
	 * @return the chName
	 */
	public String getChName() {
		return chName;
	}
	/**
	 * @param chName the chName to set
	 */
	public void setChName(String chName) {
		this.chName = chName;
	}
	
}
