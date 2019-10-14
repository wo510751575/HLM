package com.lj.business.member.emus;

public enum PmTypeTimeFlag {
	
	/**
	 * 今日新增客户
	 */
	TODAY("今日新增客户"),
	/**
	 * 7天内新增客户
	 */
	WEEK("7天内新增客户"),
	/**
	 * 30天内新增客户
	 */
	MONTH("30天内新增客户")
	;
	
	private String name;

	PmTypeTimeFlag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
