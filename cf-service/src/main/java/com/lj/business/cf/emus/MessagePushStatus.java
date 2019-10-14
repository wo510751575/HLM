package com.lj.business.cf.emus;

public enum MessagePushStatus {

	VALID("有效"),
	INVALID("无效");
	
	MessagePushStatus(String name){
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
