package com.lj.business.weixin.dto.smallprogram;

import java.io.Serializable;

public class DelWxSmallProgram implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3022457849722456383L; 
	
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
		builder.append("DelWxSmallProgram [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
