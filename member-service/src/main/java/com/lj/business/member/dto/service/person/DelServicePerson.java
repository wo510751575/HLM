package com.lj.business.member.dto.service.person;

import java.io.Serializable;

/**
 * The Class DelServicePerson.
 */
public class DelServicePerson implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -7519888560385307951L;
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
		builder.append("DelServicePerson [code=").append(code).append("]");
		return builder.toString();
	}
}
