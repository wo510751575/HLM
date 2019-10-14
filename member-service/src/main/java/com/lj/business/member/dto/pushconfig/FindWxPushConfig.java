package com.lj.business.member.dto.pushconfig;

import java.io.Serializable;

public class FindWxPushConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127764828732128726L; 

	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWxPushConfig [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
