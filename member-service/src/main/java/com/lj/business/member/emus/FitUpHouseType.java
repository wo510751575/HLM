/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

package com.lj.business.member.emus;


/**
 * 类说明：户型
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * CreateDate: 2017-5-28
 */
public enum FitUpHouseType {
	
	/** 一居. */
	YI_JU("一居"),
	
	/** 两居. */
	ER_JU("两居"),
	
	/** 三居. */
	SAN_JU("三居"),
	
	/** 四居. */
	SI_JU("四居"),
	
	/** 复式. */
	FU_SHI("复式"),
	
	/** 别墅. */
	BIE_SHU("别墅"),
	
	;

	/**
	 * Instantiates a new member status.
	 *
	 * @param chName the ch name
	 */
	FitUpHouseType(String chName){
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
