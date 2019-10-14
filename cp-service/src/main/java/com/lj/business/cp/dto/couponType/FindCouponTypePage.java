package com.lj.business.cp.dto.couponType;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCouponTypePage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6279472044162994940L; 
	/**
	 * 商户编号
	 */
	private String merchantNo;
	/**
	 * 优惠券类型
	 */
	private String couponType;
	
	
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	@Override
	public String toString() {
		return "FindCouponTypePage [merchantNo=" + merchantNo + ", couponType="
				+ couponType + "]";
	}
	

}
