package com.ye.business.hx.emus;

/**
 * 线索订单表 STATUS
 * 
 * @author bobo
 *
 */
public enum ClueOrderStatus {

	FREEZE("已锁定"),

	OK("已使用"),

	CANCEL("已取消");

	private String name;

	private ClueOrderStatus(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
