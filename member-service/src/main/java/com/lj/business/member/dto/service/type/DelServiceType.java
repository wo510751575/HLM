package com.lj.business.member.dto.service.type;

import java.io.Serializable;

public class DelServiceType implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3185814352291210478L;
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
		builder.append("DelServiceType [code=").append(code).append("]");
		return builder.toString();
	}
}
