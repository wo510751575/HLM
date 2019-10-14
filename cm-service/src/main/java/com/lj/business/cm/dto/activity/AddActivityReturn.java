package com.lj.business.cm.dto.activity;

import java.io.Serializable;

public class AddActivityReturn implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "AddActivityReturn [code=" + code + "]";
	}
	
}
