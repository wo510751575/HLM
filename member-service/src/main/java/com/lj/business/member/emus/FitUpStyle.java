/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.member.emus;


/**
 * 类说明：风格
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * CreateDate: 2017-5-28
 */
public enum FitUpStyle {

	/** 北欧. */
	BEI_OU("北欧"),
	
	/** 新中式. */
	XIN_ZHONG_SHI("新中式"),
	
	/** 简欧. */
	JIAN_OU("简欧"),
	
	/** 美式. */
	MEI_SHI("美式"),
	
	/** 欧式. */
	OU_SHI("欧式"),
	
	/** 现代简约. */
	XIAN_DAI_JIAN_YUE("现代简约"),
	
	/** 地中海. */
	DI_ZHONG_HAI("地中海"),
	
	/** 简美. */
	JIAN_MEI("简美"),
	
	/** 其他. */
	OTHER("其他"),
	;

	/**
	 * Instantiates a new member status.
	 *
	 * @param chName the ch name
	 */
	FitUpStyle(String chName){
		this.chName = chName;
	}

	/** 中文名称. */
	private String chName;

	/**
	 * Gets the 中文名称.
	 *
	 * @return the 中文名称
	 */
	public String getChName() {
		return chName;
	}

	/**
	 * Sets the 中文名称.
	 *
	 * @param chName the new 中文名称
	 */
	public void setChName(String chName) {
		this.chName = chName;
	}


}
