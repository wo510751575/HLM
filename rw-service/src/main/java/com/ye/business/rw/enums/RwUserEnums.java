package com.ye.business.rw.enums;

public enum RwUserEnums {

	// 用户等级 userLevel 用户级别：1-普通用户；2-个体用户；3-商户
	Ordinary("1"), Individual("2"), Merchant("3");

	RwUserEnums(String name) {
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
