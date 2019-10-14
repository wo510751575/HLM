package com.lj.business.member.dto;

import java.io.Serializable;

public class MecMemberNoReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5313004498407881629L;
	
    /**
     * code .
     */
    private String code;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	/**
	 * 客户编号
	 */
	private String memberName;
	
	
	/**
	 * 商户号
	 */
	private String merchantNo;
	

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecMemberNoReturn [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
