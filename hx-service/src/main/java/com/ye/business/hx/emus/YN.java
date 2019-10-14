package com.ye.business.hx.emus;

/**
 * 类说明：是否
 * 
 * @Company: 扬恩科技有限公司
 * @author lhy CreateDate: 2019.04.16
 *
 */
public enum YN {

	Y("是"),

	N("否");
 

	YN(String name) {
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
