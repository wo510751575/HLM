package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class UpdateFirstIntroduce.
 */
public class UpdateFirstIntroduce implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6906013908684537716L;
	
	
	 /**
     * 客户会员号  .
     */
    private String memberNo;


    /**
     * 导购编号 .
     */
    private String memberNoGm;
    
    /**
     * 分店编号 .
     */
    
    
    /**
     * 商户编号(必填) .
     */
    private String merchantNo;

    /**
     *初次介绍
		是：Y
		否：N.
     */
    private String firstIntroduce;
    

	/**
	 * Gets the 客户会员号  .
	 *
	 * @return the 客户会员号  
	 */
	public String getMemberNo() {
		return memberNo;
	}


	/**
	 * Sets the 客户会员号  .
	 *
	 * @param memberNo the new 客户会员号  
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}


	/**
	 * Gets the 导购编号 .
	 *
	 * @return the 导购编号 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}


	/**
	 * Sets the 导购编号 .
	 *
	 * @param memberNoGm the new 导购编号 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}


	/**
	 * Gets the 初次介绍 是：Y 否：N.
	 *
	 * @return the 初次介绍 是：Y 否：N
	 */
	public String getFirstIntroduce() {
		return firstIntroduce;
	}

	


	public String getMerchantNo() {
		return merchantNo;
	}


	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}


	/**
	 * Sets the 初次介绍 是：Y 否：N.
	 *
	 * @param firstIntroduce the new 初次介绍 是：Y 否：N
	 */
	public void setFirstIntroduce(String firstIntroduce) {
		this.firstIntroduce = firstIntroduce;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateFirstIntroduce [memberNo=");
		builder.append(memberNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", firstIntroduce=");
		builder.append(firstIntroduce);
		builder.append("]");
		return builder.toString();
	}

}
