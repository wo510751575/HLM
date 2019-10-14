package com.lj.business.member.emus;

public enum GuidCardCustType {

	PAGE_VIEW("人气"),
	PRAISE("点赞"),
	COLLECTION("收藏")
	;
	
	GuidCardCustType(String name){
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
