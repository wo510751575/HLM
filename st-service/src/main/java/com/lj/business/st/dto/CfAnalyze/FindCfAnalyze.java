package com.lj.business.st.dto.CfAnalyze;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindCfAnalyze.
 */
public class FindCfAnalyze implements Serializable {

	 /** Generate cron. */
	private static final long serialVersionUID = 3792753417359654743L; 

    /**
     * 商户编号(必填) .
     */
    private String merchantNo;
    

    /**
     * 导购编号(必填)  .
     */
    private String memberNoGm;

    /**
     * 统计日期,默认昨天 .
     */
    private Date daySt;
    
    /** 门店编号. */
    private String shopNo;

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the 商户编号(必填) .
	 *
	 * @return the 商户编号(必填) 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号(必填) .
	 *
	 * @param merchantNo the new 商户编号(必填) 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 导购编号(必填)  .
	 *
	 * @return the 导购编号(必填)  
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号(必填)  .
	 *
	 * @param memberNoGm the new 导购编号(必填)  
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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

	@Override
	public String toString() {
		return "FindCfAnalyze [merchantNo=" + merchantNo + ", memberNoGm="
				+ memberNoGm + ", daySt=" + daySt + ", shopNo=" + shopNo + "]";
	}

}
