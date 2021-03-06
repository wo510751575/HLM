package com.lj.business.cm.dto;

import java.io.Serializable;

/**
 * The Class DelMerchantBom.
 */
public class DelMerchantBom implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8349099037126630716L;

	  /** The code. */
  	private String code;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DelMerchantBom [code=" + code + ", getCode()=" + getCode()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	  
	
}
