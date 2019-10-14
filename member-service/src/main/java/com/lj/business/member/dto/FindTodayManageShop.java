package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindTodayManageShop.
 */
public class FindTodayManageShop implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2266411869941564405L;
	
	   /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    
    
    /**
     * 统计日期 .
     */
    private Date daySt;

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
	 * Gets the day st.
	 *
	 * @return the day st
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the day st.
	 *
	 * @param daySt the new day st
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTodayManageShop [merchantNo=");
		builder.append(merchantNo);
		builder.append(", daySt=");
		builder.append(daySt);
		builder.append("]");
		return builder.toString();
	}

}
