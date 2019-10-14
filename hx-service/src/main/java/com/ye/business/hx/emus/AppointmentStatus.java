package com.ye.business.hx.emus;

/**
 * 预约：客户预约状态
 * 
 * @author Administrator
 *
 */
public enum AppointmentStatus {
	// UNCONFIRM 未确认，CONFIRM 确认 CANCEL取消
	UNCONFIRM("未确认"), CONFIRM("确认"), CANCEL("取消");

	AppointmentStatus(String name) {
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
