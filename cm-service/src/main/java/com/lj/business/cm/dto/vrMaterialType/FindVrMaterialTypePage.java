package com.lj.business.cm.dto.vrMaterialType;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindVrMaterialTypePage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961825975536522462L; 
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 类型名称
	 */
	private String typeName;
	
	
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	
}
