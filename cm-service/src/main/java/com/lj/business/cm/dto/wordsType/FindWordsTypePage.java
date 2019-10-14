package com.lj.business.cm.dto.wordsType;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindWordsTypePage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3491233842731529259L; 
	/**
     * CODE .
     */
    private String code;
    /**
     * 导购号
     */
    private String memberNoGm;
    /**
     * 商户号
     */
    private String merchantNo;
    
    
    
	public String getMemberNoGm() {
		return memberNoGm;
	}
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
		builder.append("FindWordsTypePage [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
    
}
