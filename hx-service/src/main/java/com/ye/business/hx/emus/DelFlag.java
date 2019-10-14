/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.ye.business.hx.emus;

/**
 * 
 * 类说明：删除标记。
 * <p>
 * 
 * @Company: 杨恩科技有限公司
 * @author lhy
 * 
 *         CreateDate: 2019.03.11
 */
public enum DelFlag {
	Y("删除"), N("未删除");
	DelFlag(String name) {
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
