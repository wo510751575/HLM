package com.ye.business.hx.emus;

/**
 * ServerDetail IS_SHOP
 * 
 * @author bobo
 *
 */
public enum ServerDetailShop {

	YES("是"),

	NO("否");

	private String name;

	private ServerDetailShop(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}