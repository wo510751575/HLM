package com.ye.business.rw.enums;

/**
 * 
 * *类说明：投诉枚举
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年6月27日
 */
public enum Complaint {

	// auditorStatus
	noverify("未审核"),

	verify("审核"),

	// auditorResult
	normal("正常"),

	forbid("禁用")

	;

	Complaint(String name) {
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
