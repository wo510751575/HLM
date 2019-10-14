package com.lj.business.st.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindWorkRatioGmPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5081863491018836244L; 

	 /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 统计日期 .
     */
    private Date daySt;

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 分店编号 .
	 *
	 * @return the 分店编号 
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * Sets the 分店编号 .
	 *
	 * @param shopNo the new 分店编号 
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 分店名称 .
	 *
	 * @return the 分店名称 
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the 分店名称 .
	 *
	 * @param shopName the new 分店名称 
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the 导购编号  .
	 *
	 * @return the 导购编号  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号  .
	 *
	 * @param memberNoGm the new 导购编号  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 统计日期 .
	 *
	 * @return the 统计日期 
	 */
	public Date getDaySt() {
		return daySt;
	}

	/**
	 * Sets the 统计日期 .
	 *
	 * @param daySt the new 统计日期 
	 */
	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindWorkRatioGmPage [merchantNo=").append(merchantNo)
				.append(", shopNo=").append(shopNo).append(", shopName=")
				.append(shopName).append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", daySt=").append(daySt).append("]");
		return builder.toString();
	}
}
