package com.lj.business.member.dto.pushconfig;

import java.io.Serializable;

public class DelWxPushConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3500529626278480082L; 

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
		builder.append("DelWxPushConfig [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
