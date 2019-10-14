package com.lj.business.cp.dto.coupon;

import java.io.Serializable;

public class DelCoupon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -206817697629241475L;

	/**
	 * CODE .
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
