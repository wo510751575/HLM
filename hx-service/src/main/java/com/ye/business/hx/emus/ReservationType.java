package com.ye.business.hx.emus;

/**
 * 类说明：预约类型
 * 
 * @Company: 扬恩科技有限公司
 * @author sjiying CreateDate: 2019年03月08日
 *
 */
public enum ReservationType {

	RESERVATION("预约"),

	REGISTERED("挂号");

	ReservationType(String name) {
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
