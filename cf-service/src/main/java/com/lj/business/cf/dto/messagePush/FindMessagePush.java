package com.lj.business.cf.dto.messagePush;

import java.io.Serializable;

public class FindMessagePush implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6505023359843608414L; 

	/**
	 * 消息编号
	 */
	private String code;
	
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 门店编号
	 */
	private String shopNo;
	
	/**
	 * 导购编号
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

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "FindMessagePush [code=" + code + ", merchantNo=" + merchantNo
				+ ", shopNo=" + shopNo + ", memberNoGm=" + memberNoGm + "]";
	}
}
