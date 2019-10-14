package com.lj.business.st.dto.SocialAnalyze;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindSocialAnalyzeTotal.
 */
public class FindSocialAnalyzeTotal implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8573627427447761179L;
	
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSocialAnalyzeTotal [merchantNo=")
				.append(merchantNo).append(", shopNo=").append(shopNo)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", dimensionSt=").append(dimensionSt)
				.append(", beginDate=").append(beginDate).append(", endDate=")
				.append(endDate).append("]");
		return builder.toString();
	}
}
