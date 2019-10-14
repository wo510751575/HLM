package com.lj.oms.entity.dto;

import java.io.Serializable;

public class AreaPinyinDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3656867283185894305L;
	
	private String pinyin;
	
	private String citys;

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getCitys() {
		return citys;
	}

	public void setCitys(String citys) {
		this.citys = citys;
	}
	
}
