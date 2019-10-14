package com.lj.business.st.dto.MaterialTotal;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindMaterialTotal.
 */
public class FindMaterialTotal implements Serializable {
	
	/** Generate cron. */
	private static final long serialVersionUID = -8775097345265565188L;

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

	/**
	 * 统计维度 商户：MERCHANT 区域：AREA 门店：SHOP 导购：GUID .(必填)
	 */
	private String dimensionSt;

	/** 开始日期. 年月日  (必填) */
	private String beginDate;

	/** 结束日期. 年月日   (必填)*/
	private String endDate;
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
	 * Gets the dimension st.
	 *
	 * @return the dimension st
	 */
	public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the dimension st.
	 *
	 * @param dimensionSt the new dimension st
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMaterialTotal [merchantNo=").append(merchantNo)
				.append(", shopNo=").append(shopNo).append(", memberNoGm=")
				.append(memberNoGm).append(", dimensionSt=")
				.append(dimensionSt).append(", beginDate=").append(beginDate)
				.append(", endDate=").append(endDate).append("]");
		return builder.toString();
	}

}
