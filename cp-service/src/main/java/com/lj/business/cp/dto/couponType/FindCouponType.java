package com.lj.business.cp.dto.couponType;

import java.io.Serializable;

public class FindCouponType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3358785850213724841L;

	/**
	 * CODE .
	 */
	private String code;
	/**
     * 商户编号 .
     */
    private String merchantNo;

	/**
	 * 是否启用 启用：YES 不启用：NO .
	 */
	private String useEnable;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUseEnable() {
		return useEnable;
	}

	public void setUseEnable(String useEnable) {
		this.useEnable = useEnable;
	}

}
