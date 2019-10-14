package com.lj.business.cp.dto;

import com.lj.business.cp.dto.coupon.AddCouponVo;

public class AddCouponVoDto implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1901791292685923948L;

	private AddCouponVo[] couponList;

	public AddCouponVo[] getCouponList() {
		return couponList;
	}

	public void setCouponList(AddCouponVo[] couponList) {
		this.couponList = couponList;
	}

}
