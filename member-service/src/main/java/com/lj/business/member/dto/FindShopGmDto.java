package com.lj.business.member.dto;

import java.io.Serializable;

public class FindShopGmDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5014982912232070375L;
	
	/**
	 * 商户号
	 */
	private String merchantNo;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 工作微信号
	 */
	private String noWx;
	

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
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
		StringBuilder builder = new StringBuilder();
		builder.append("FindShopGmDto [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}
	
}
