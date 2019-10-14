package com.lj.business.member.dto.guidCard;

import java.io.Serializable;

public class DelGuidCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7652623062512213804L; 

	/**
	 * code .
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DelGuidCard [code=" + code + "]";
	}
}
