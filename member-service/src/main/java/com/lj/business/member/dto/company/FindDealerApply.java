package com.lj.business.member.dto.company;

import java.io.Serializable;

public class FindDealerApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 467782466904612364L; 

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
		builder.append("FindDealerApply [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
}
