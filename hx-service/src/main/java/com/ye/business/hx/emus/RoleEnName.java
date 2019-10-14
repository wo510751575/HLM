package com.ye.business.hx.emus;

/**
 * 角色: 医生 SYS_SHOP_DOCTOR 咨询师 SYS_SHOP_ADVISORY 护士 SYS_SHOP_NURSE
 * 
 * @author sjiying
 *
 */
public enum RoleEnName {

	SYS_SHOP_DOCTOR("医生"), SYS_SHOP_ADVISORY("咨询师"), SYS_SHOP_NURSE("护士");

	RoleEnName(String name) {
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
