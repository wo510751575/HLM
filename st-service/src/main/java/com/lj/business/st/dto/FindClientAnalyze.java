package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AddClientAnalyze.
 */
public class FindClientAnalyze implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -853255906220043996L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 统计日期 .
     */
    private Date stDate;
    
    /** 开始日期. */
    private Date startTime;
    
    /** 结束日期. */
    private Date endTime;
    
    /**统计维度 */
    private String dimensionSt;
    
    
    
	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}

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
	 * @param merchantNo the merchant no
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
	 * @param areaCode the area code
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Gets the area name.
	 *
	 * @return the area name
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the area name.
	 *
	 * @param areaName the area name
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	 * @param shopNo the shop no
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	/**
	 * Gets the shop name.
	 *
	 * @return the shop name
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the shop name.
	 *
	 * @param shopName the shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the st date.
	 *
	 * @return the st date
	 */
	public Date getStDate() {
		return stDate;
	}

	/**
	 * Sets the st date.
	 *
	 * @param stDate the st date
	 */
	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	@Override
	public String toString() {
		return "FindClientAnalyze [code=" + code + ", merchantNo=" + merchantNo
				+ ", areaCode=" + areaCode + ", areaName=" + areaName
				+ ", shopNo=" + shopNo + ", shopName=" + shopName + ", stDate="
				+ stDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", dimensionSt=" + dimensionSt + "]";
	}

    
}
