/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.ye.business.hx.emus;

/**
 * 
 * 类说明：服务订单审核状态。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 杨恩科技有限公司
 * @author lhy
 * 
 */
public enum OrderStatus {
	WAIT("待审核"), PASS("通过"),UNPASS("不通过");
	
	OrderStatus(String name) {
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
