package com.ye.business.hx.emus;

/**
 * 线索订单表 TYPE
 * 
 * @author bobo
 *
 */
public enum ClueOrderType {

	VISITING("接诊"),

	ORDER("派单");

	private String name;

	private ClueOrderType(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
