package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSalesGmDayFirstIndex.
 */
public class FindSalesGmDayFirstIndex implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3262914544875776538L;
	
	 /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域编号 .
     */
    private String areaCode;
    
    /** 月开始时间. */
    private Date beginDate;
    
    /** 月结束时间 昨天. */
    private Date endDate;
    
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
	 * Gets the begin date.
	 *
	 * @return the begin date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Sets the begin date.
	 *
	 * @param beginDate the new begin date
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		builder.append("FindSalesGmDayFirstIndex [merchantNo=")
				.append(merchantNo).append(", areaCode=").append(areaCode)
				.append(", beginDate=").append(beginDate).append(", endDate=")
				.append(endDate).append(", now=").append(now).append("]");
		return builder.toString();
	}

}
