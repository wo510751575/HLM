package com.ye.business.ad.enums;

/**
 * 
 * *类说明：
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年6月28日
 */
public enum AdvStatus {

	// 广告状态：no-未上架，up-已上架；down-已下架 .
	no("未上架"),

	up("已上架"),

	down("已下架");

	AdvStatus(String name) {
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
