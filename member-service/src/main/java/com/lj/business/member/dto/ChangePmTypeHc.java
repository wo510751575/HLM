package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class ChangePmType.
 */
public class ChangePmTypeHc implements Serializable {

	private static final long serialVersionUID = 8684269732979777357L;

	/**
     * 客户表CODE(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
     */
    private String codePm;

    /**
     * 客户分类CODE(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 )  .
     */
    private String pmTypeCode;
    

    /**
     * 导购编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
     */
    private String memberNoGm;
    
    /**
     * 客户编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
     */
    private String memberNo;
    
    /**
     * 客户分类类型(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) .
     */
    private String  pmTypeType;
    
    /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    
    /**
     * 终端微信号
     */
    private String shopWx;
    
    

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	/**
	 * Gets the 客户表CODE(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @return the 客户表CODE(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public String getCodePm() {
		return codePm;
	}

	/**
	 * Sets the 客户表CODE(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @param codePm the new 客户表CODE(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public void setCodePm(String codePm) {
		this.codePm = codePm;
	}

	/**
	 * Gets the 客户分类CODE(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 )  .
	 *
	 * @return the 客户分类CODE(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 )  
	 */
	public String getPmTypeCode() {
		return pmTypeCode;
	}

	/**
	 * Sets the 客户分类CODE(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 )  .
	 *
	 * @param pmTypeCode the new 客户分类CODE(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 )  
	 */
	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	/**
	 * Gets the 导购编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @return the 导购编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @param memberNoGm the new 导购编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 客户编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @return the 客户编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 客户编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) .
	 *
	 * @param memberNo the new 客户编号(必填，（导购编号 && 客户编号）客户表CODE 二选一 ) 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 客户分类类型(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) .
	 *
	 * @return the 客户分类类型(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) 
	 */
	public String getPmTypeType() {
		return pmTypeType;
	}

	/**
	 * Sets the 客户分类类型(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) .
	 *
	 * @param pmTypeType the new 客户分类类型(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) 
	 */
	public void setPmTypeType(String pmTypeType) {
		this.pmTypeType = pmTypeType;
	}

	/**
	 * Gets the 商户编号(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) .
	 *
	 * @return the 商户编号(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) .
	 *
	 * @param merchantNo the new 商户编号(必填，（客户分类类型 && 商户编号）客户分类CODE 二选一 ) 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePmType [codePm=").append(codePm)
				.append(", pmTypeCode=").append(pmTypeCode)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNo=").append(memberNo).append(", pmTypeType=")
				.append(pmTypeType).append(", merchantNo=").append(merchantNo)
				.append("]");
		return builder.toString();
	}

}
