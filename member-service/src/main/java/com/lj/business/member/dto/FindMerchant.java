package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class FindMerchant.
 */
public class FindMerchant implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5272619738857111286L; 
	
	/** The code. */
	private String code;
	
	private String merchantNo;
	
	public String getMerchantNo() {
		return merchantNo;
	}


	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


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
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return "FindMerchant [code=" + code + ", merchantNo=" + merchantNo + "]";
	}


}
