package com.lj.business.cf.dto.comTask;

import java.io.Serializable;

/**
 * The Class CloseGroup.
 */
public class CloseGroup implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8864522875152304655L;

	
	/** 商户编号. */
	private String merchantNo;


	/**
	 * Gets the 商户编号.
	 *
	 * @return the 商户编号
	 */
	public String getMerchantNo() {
		return merchantNo;
	}


	/**
	 * Sets the 商户编号.
	 *
	 * @param merchantNo the new 商户编号
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
		builder.append("CloseGroup [merchantNo=").append(merchantNo)
				.append("]");
		return builder.toString();
	}
	
}
