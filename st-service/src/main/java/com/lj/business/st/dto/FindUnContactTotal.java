package com.lj.business.st.dto;

import java.io.Serializable;

/**
 * The Class FindUnContactTotal.
 */
public class FindUnContactTotal implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3019203636904733460L; 

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
		builder.append("FindUnContactTotal [code=").append(code).append("]");
		return builder.toString();
	}

}
