package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class AddPmLabelReturn.
 */
public class AddPmLabelReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4295396988399215334L; 

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "AddPmLabelReturn [code=" + code + "]";
	}
	
}
