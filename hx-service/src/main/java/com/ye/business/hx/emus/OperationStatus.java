package com.ye.business.hx.emus;

/**
 * 操作状态
 * 
 * @author sjiying
 *
 */
public enum OperationStatus {

	VISTITING("就诊"),

	TRIAGE("分诊"),
	
	FINISHED("治疗完成"),
	
	REFERRAL("转诊");

	OperationStatus(String name) {
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
