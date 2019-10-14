package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;

public class FindWordsInfoWeb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8570105982920744577L; 
    
	/**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 导购号
     */
    private String memberNoGm;
	
    /**
     * 类型CODE .
     */
    private String typeCode;
    
    

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsInfoWeb [merchantNo=");
		builder.append(merchantNo);
		builder.append(", typeCode=");
		builder.append(typeCode);
		builder.append("]");
		return builder.toString();
	}

}
