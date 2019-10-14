package com.lj.business.cm.dto.wordsInfo;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindWordsInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -681631034704680027L; 

	/**
	 * 商户编号
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
    
    /**
     * 内容 .
     */
    private String content;

    
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWordsInfoPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", typeCode=");
		builder.append(typeCode);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
    
}
