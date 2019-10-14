package com.lj.business.st.dto.bestGmChoose;

import java.io.Serializable;

/**
 * The Class FindBestGmChoose.
 */
public class FindBestGmChoose implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4742991650911248643L; 
	
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
		builder.append("FindBestGmChoose [code=").append(code).append("]");
		return builder.toString();
	}
	

}
