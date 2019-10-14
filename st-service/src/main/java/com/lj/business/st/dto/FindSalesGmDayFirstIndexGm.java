package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirstIndexGm.
 */
public class FindSalesGmDayFirstIndexGm implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5331972268740544291L;
	
	 /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域编号 .
     */
    private String areaCode;
    
    /** 导购编号. */
    private String memberNoGm;
    
    
    /** 当前时间. */
    private Date now;


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
	 * Gets the now.
	 *
	 * @return the now
	 */
	public Date getNow() {
		return now;
	}


	/**
	 * Sets the now.
	 *
	 * @param now the new now
	 */
	public void setNow(Date now) {
		this.now = now;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayFirstIndexGm [merchantNo=")
				.append(merchantNo).append(", areaCode=").append(areaCode)
				.append(", memberNoGm=").append(memberNoGm).append(", now=")
				.append(now).append("]");
		return builder.toString();
	}
    
}
