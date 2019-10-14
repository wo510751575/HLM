/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.member.emus;

/**
 * 
 * 
 * 类说明：终端开店年限
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月12日
 */
public enum ShopLife {


	/** 三个月. */
	MARCH("三个月以上", 3, 6),

	/** 六个月. */
	JUNE("六个月以上", 6, 12),

	/** 一年. */
	ONEYEAR("一年以上", 12, 24),

	/** 二年. */
	TWOYEARS("二年以上", 24, 36),

	/** 三年. */
	THREEYEARS("三年以上", 36, 48),

	/** 四年以上. */
	THREEYEARSABOVE("四年以上", 48, 1200),
	;
	private String name;
	private int beginMonth;
	private int endMonth;

	ShopLife(String name, int beginMonth, int endMonth){
		this.name = name;
		this.beginMonth=beginMonth;
		this.endMonth=endMonth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the beginMonth
	 */
	public int getBeginMonth() {
		return beginMonth;
	}

	/**
	 * @param beginMonth the beginMonth to set
	 */
	public void setBeginMonth(int beginMonth) {
		this.beginMonth = beginMonth;
	}

	/**
	 * @return the endMonth
	 */
	public int getEndMonth() {
		return endMonth;
	}

	/**
	 * @param endMonth the endMonth to set
	 */
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	
}
