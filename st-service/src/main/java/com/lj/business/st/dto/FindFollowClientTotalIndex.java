package com.lj.business.st.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindFollowClientTotalIndex.
 */
public class FindFollowClientTotalIndex implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 907299700360787711L;
	
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
	
	/** 开始日期. 年月日  (必填) */
	private String beginDate;

	/** 结束日期. 年月日   (必填)*/
	private String endDate;
	
	/** 开始日期. 年月日  (必填) */
	private String beginDateDay;

	/** 结束日期. 年月日   (必填)*/
	private String endDateDay;
	
	/** 开始日期. 年月日  (必填) */
	private String beginDateWeek;

	/** 结束日期. 年月日   (必填)*/
	private String endDateWeek;
	
	/** 开始日期. 年月日  (必填) */
	private String beginDateMonth;

	/** 结束日期. 年月日   (必填)*/
	private String endDateMonth;

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
	 * Gets the begin date.
	 *
	 * @return the begin date
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * Sets the begin date.
	 *
	 * @param beginDate the new begin date
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the begin date day.
	 *
	 * @return the begin date day
	 */
	public String getBeginDateDay() {
		return beginDateDay;
	}

	/**
	 * Sets the begin date day.
	 *
	 * @param beginDateDay the new begin date day
	 */
	public void setBeginDateDay(String beginDateDay) {
		this.beginDateDay = beginDateDay;
	}

	/**
	 * Gets the end date day.
	 *
	 * @return the end date day
	 */
	public String getEndDateDay() {
		return endDateDay;
	}

	/**
	 * Sets the end date day.
	 *
	 * @param endDateDay the new end date day
	 */
	public void setEndDateDay(String endDateDay) {
		this.endDateDay = endDateDay;
	}

	/**
	 * Gets the begin date week.
	 *
	 * @return the begin date week
	 */
	public String getBeginDateWeek() {
		return beginDateWeek;
	}

	/**
	 * Sets the begin date week.
	 *
	 * @param beginDateWeek the new begin date week
	 */
	public void setBeginDateWeek(String beginDateWeek) {
		this.beginDateWeek = beginDateWeek;
	}

	/**
	 * Gets the end date week.
	 *
	 * @return the end date week
	 */
	public String getEndDateWeek() {
		return endDateWeek;
	}

	/**
	 * Sets the end date week.
	 *
	 * @param endDateWeek the new end date week
	 */
	public void setEndDateWeek(String endDateWeek) {
		this.endDateWeek = endDateWeek;
	}

	/**
	 * Gets the begin date month.
	 *
	 * @return the begin date month
	 */
	public String getBeginDateMonth() {
		return beginDateMonth;
	}

	/**
	 * Sets the begin date month.
	 *
	 * @param beginDateMonth the new begin date month
	 */
	public void setBeginDateMonth(String beginDateMonth) {
		this.beginDateMonth = beginDateMonth;
	}

	/**
	 * Gets the end date month.
	 *
	 * @return the end date month
	 */
	public String getEndDateMonth() {
		return endDateMonth;
	}

	/**
	 * Sets the end date month.
	 *
	 * @param endDateMonth the new end date month
	 */
	public void setEndDateMonth(String endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFollowClientTotalIndex [merchantNo=")
				.append(merchantNo).append(", shopNo=").append(shopNo)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", beginDate=").append(beginDate).append(", endDate=")
				.append(endDate).append(", beginDateDay=").append(beginDateDay)
				.append(", endDateDay=").append(endDateDay)
				.append(", beginDateWeek=").append(beginDateWeek)
				.append(", endDateWeek=").append(endDateWeek)
				.append(", beginDateMonth=").append(beginDateMonth)
				.append(", endDateMonth=").append(endDateMonth).append("]");
		return builder.toString();
	}
	
}
