package com.lj.business.cf.dto.memberMessageRelation;

import java.io.Serializable;

public class DelMemberMessageRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5357020505484818658L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DelMemberMessageRelation [code=" + code + "]";
	}
	
}
