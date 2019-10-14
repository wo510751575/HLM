package com.lj.business.st.dto;

import java.io.Serializable;

public class DelWorkRatioGm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5651092444229150922L; 
	
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
		builder.append("DelWorkRatioGm [code=").append(code).append("]");
		return builder.toString();
	}

}
