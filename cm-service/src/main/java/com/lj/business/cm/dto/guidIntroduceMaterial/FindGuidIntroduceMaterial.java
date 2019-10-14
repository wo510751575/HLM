package com.lj.business.cm.dto.guidIntroduceMaterial;

import java.io.Serializable;

public class FindGuidIntroduceMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4464346832417302240L;
	
	/**
	 * 个人介绍素材编号 .
	 */
	private String code;
	
	/**
	 * 商户编号 .
	 */
	private String merchantNo;
	
	/**
	 * 导购编号 .
	 */
	private String memberNoGm;

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

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "FindGuidIntroduceMaterial [code=" + code + ", merchantNo="
				+ merchantNo + ", memberNoGm=" + memberNoGm + "]";
	}
}
