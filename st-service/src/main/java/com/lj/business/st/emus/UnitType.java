package com.lj.business.st.emus;

public enum UnitType {
	
	GE("个"),

	YUAN("元"),
	
	CI("次"),
	
	PERCENT("%");

	UnitType(String name){
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
