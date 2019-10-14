package com.lj.business.st.dto;

import java.io.Serializable;

public class InitStListByMerchant implements Serializable {

	private static final long serialVersionUID = 1688362368099350526L;

	/**
	 * 商户编号，非空
	 */
	private String merchantNo;

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InitStListByMerchant [merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
	
}
