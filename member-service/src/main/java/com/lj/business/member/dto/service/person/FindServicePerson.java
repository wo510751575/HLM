package com.lj.business.member.dto.service.person;

import java.io.Serializable;

/**
 * The Class FindPhoneInfo.
 */
public class FindServicePerson implements Serializable { 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5828612468158697380L;
	/**
     * CODE .
     */
    private String code;
    /**
     * 商户编号 .
     */
    private String merchantNo;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

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

	@Override
	public String toString() {
		return "FindServicePerson [code=" + code + ", merchantNo=" + merchantNo
				+ "]";
	}

}
