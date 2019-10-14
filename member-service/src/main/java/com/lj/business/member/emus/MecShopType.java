package com.lj.business.member.emus;

public enum MecShopType {
	
	SELF("自营"), DEALER("经销商");
	
	
	MecShopType(String name){
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
