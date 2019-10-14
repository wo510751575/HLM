package com.ye.business.hx.emus;

/**
 * 类说明：成交意愿
 * 
 * @Company: 扬恩科技有限公司
 * @author sjiying CreateDate: 2019年03月08日
 *
 */
public enum Want {

	HIGH("高"),

	CENTRAL("中"),

	LOW("低");

	Want(String name) {
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
