package com.ye.business.hx.emus;

/**
 * 线索表 VALID_STATUS
 * 
 * @author bobo
 *
 */
public enum ClueValidStatus {

	VALID("有效"),

	INVALID("无效");

	private String name;

	private ClueValidStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}