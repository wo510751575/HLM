package com.lj.business.member.dto.service.personproduct;

import java.io.Serializable;

/**
 * The Class DelServicePerson.
 */
public class DelServicePersonProduct implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584589134981898063L;
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
		builder.append("DelServicePersonProduct [code=").append(code).append("]");
		return builder.toString();
	}
}
