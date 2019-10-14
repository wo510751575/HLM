package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirstCompleteRateDto.
 */
public class FindSalesGmDayFirstCompleteRateDto implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5932627686427310157L;
	
	 /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域编号 .
     */
    private String areaCode;
    
	/**
     * 分店编号 .
     */
    private String shopNo;
    
    /**
     * 导购编号  .
     */
    private String memberNoGm;
    
    /**
     * 统计日期 .
     */
    private String daySt;

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
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the new area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * Gets the day st.
	 *
	 * @return the day st
	 */
	public String getDaySt() {
		return daySt;
	}

	/**
	 * Sets the day st.
	 *
	 * @param daySt the new day st
	 */
	public void setDaySt(String daySt) {
		this.daySt = daySt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayFirstCompleteRateDto [merchantNo=")
				.append(merchantNo).append(", areaCode=").append(areaCode)
				.append(", shopNo=").append(shopNo).append(", memberNoGm=")
				.append(memberNoGm).append(", daySt=").append(daySt)
				.append("]");
		return builder.toString();
	}
	
}
