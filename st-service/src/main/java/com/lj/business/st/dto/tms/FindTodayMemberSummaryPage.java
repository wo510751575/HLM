package com.lj.business.st.dto.tms;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindTodayMemberSummaryPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7678781025947672329L; 

	/**
     * 汇总日期 .
     */
    private Date summaryDate;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

	/**
	 * @return the summaryDate
	 */
	public Date getSummaryDate() {
		return summaryDate;
	}

	/**
	 * @param summaryDate the summaryDate to set
	 */
	public void setSummaryDate(Date summaryDate) {
		this.summaryDate = summaryDate;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the shopNo
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * @param shopNo the shopNo to set
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTodayMemberSummaryPage [summaryDate=");
		builder.append(summaryDate);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopNo=");
		builder.append(shopNo);
		builder.append("]");
		return builder.toString();
	}
}
