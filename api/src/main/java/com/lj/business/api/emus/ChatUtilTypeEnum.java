package com.lj.business.api.emus;

public enum ChatUtilTypeEnum {

	IM_EMO_JI("表情包"),
	COUPON("优惠券"),
	GUID_CARD("导购名片"),
	MATERIAL("素材"),
	ACTIVITY("活动"),
	VR_MATERIAL("VR素材");
	
	ChatUtilTypeEnum(String name){
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
