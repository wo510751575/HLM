package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindWorkDayGmIndex.
 */
public class FindWorkDayGmIndex implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1703398676249322203L;
	
	/**
	 * 商户编号 .(必填)
	 */
	private String merchantNo;

	/**
	 * 分店编号 .(必填)
	 */
	private String shopNo;

	/**
	 * 导购编号 .(必填)
	 */
	private String memberNoGm;
	
	/** 统计时间.(必填) */
	private String stDate;

	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the new merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the shop no.
	 *
	 * @return the shop no
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the shop no.
	 *
	 * @param shopNo the new shop no
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the member no gm.
	 *
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the new member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the st date.
	 *
	 * @return the st date
	 */
	public String getStDate() {
		return stDate;
	}

	/**
	 * Sets the st date.
	 *
	 * @param stDate the new st date
	 */
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkDayGmIndex [merchantNo=").append(merchantNo)
				.append(", shopNo=").append(shopNo).append(", memberNoGm=")
				.append(memberNoGm).append(", stDate=").append(stDate)
				.append("]");
		return builder.toString();
	}
	
}
