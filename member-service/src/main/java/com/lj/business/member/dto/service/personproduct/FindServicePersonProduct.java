package com.lj.business.member.dto.service.personproduct;

import java.io.Serializable;

/**
 * The Class FindPhoneInfo.
 */
public class FindServicePersonProduct implements Serializable { 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5822120544290655525L;
	/**
     * CODE .
     */
    private String code;

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
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
		builder.append("FindServicePersonProduct [code=").append(code).append("]");
		return builder.toString();
	}
}
