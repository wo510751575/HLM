package com.lj.business.cm.dto.merchantParams;

import java.io.Serializable;

/**
 * The Class FindMerchantParams.
 */
public class FindMerchantParams implements Serializable { 

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1577671266389419463L;
	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 分组信息 .
     */
    private String groupName;
    
    /**
     * 系统参数值 .
     */
    private String sysParamValue;

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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSysParamValue() {
		return sysParamValue;
	}

	public void setSysParamValue(String sysParamValue) {
		this.sysParamValue = sysParamValue;
	}

	@Override
	public String toString() {
		return "FindMerchantParams [code=" + code + ", merchantNo="
				+ merchantNo + ", groupName=" + groupName + ", sysParamValue="
				+ sysParamValue + "]";
	}
    
}
