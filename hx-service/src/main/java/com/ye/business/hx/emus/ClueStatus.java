package com.ye.business.hx.emus;

/**
 * 线索表 STATUS
 * 
 * @author bobo
 *
 */
public enum ClueStatus {

	FREEZE("已锁定"),

	CANG("可抢"),

	HASG("已被抢");

	private String name;

	private ClueStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}