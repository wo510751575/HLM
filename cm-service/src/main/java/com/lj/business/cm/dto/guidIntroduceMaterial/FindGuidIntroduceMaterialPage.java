package com.lj.business.cm.dto.guidIntroduceMaterial;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindGuidIntroduceMaterialPage extends PageParamEntity {

	/**
	 * 
	 */  
	private static final long serialVersionUID = -4066814251257268360L; 

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
	/**
	 * 姓名
	 */
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "FindGuidIntroduceMaterialPage [code=" + code + ", merchantNo="
				+ merchantNo + ", memberNoGm=" + memberNoGm + ", name=" + name
				+ "]";
	}

}
