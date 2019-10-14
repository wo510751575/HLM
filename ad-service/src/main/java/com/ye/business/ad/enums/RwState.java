package com.ye.business.ad.enums;

/**
 * 
 * *类说明：文章状态：normal-正常；forbid-禁用；review-审核 .
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月6日
 */
public enum RwState {

	normal("正常"),

	forbid("禁用"),

	review("审核"),
	//////////////////////////////
	free("自助广告"), 
	
	pay("付费广告");

	RwState(String name) {
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
