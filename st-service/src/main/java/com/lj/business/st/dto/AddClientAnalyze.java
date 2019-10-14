package com.lj.business.st.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AddClientAnalyze.
 */
public class AddClientAnalyze implements Serializable { 

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

    /**
     * 男性比例 .
     */
    private Double ratioMale;

    /**
     * 女性比例 .
     */
    private Double ratioFemale;

    /**
     * 客户数量 .
     */
    private Long numPm;

    /**
     * 10-19岁占比 .
     */
    private Double ratioAgeTen;

    /**
     * 10-19岁数量 .
     */
    private Integer numAgeTen;

    /**
     * 20-29岁占比 .
     */
    private Double ratioAgeTwenty;

    /**
     * 20-29岁数量 .
     */
    private Integer numAgeTwenty;

    /**
     * 30-39岁占比 .
     */
    private Double ratioAgeThirty;

    /**
     * 30-39岁数量 .
     */
    private Integer numAgeThirty;

    /**
     * 40-49岁占比 .
     */
    private Double ratioAgeForty;

    /**
     * 40-49岁数量 .
     */
    private Integer numAgeForty;

    /**
     * 50-59岁占比 .
     */
    private Double ratioAgeFifty;

    /**
     * 50-59岁数量 .
     */
    private Integer numAgeFifty;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * 维度
	 */
	private String dimensionSt;

	/**
	 * 男性数量
	 */
	private Integer numMale;

	/**
	 * 女性数量
	 */
	private Integer numFemale;

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

	/**
	 * Gets the ratio male.
	 *
	 * @return the ratio male
	 */
	public Double getRatioMale() {
		return ratioMale;
	}

	/**
	 * Sets the ratio male.
	 *
	 * @param ratioMale the ratio male
	 */
	public void setRatioMale(Double ratioMale) {
		this.ratioMale = ratioMale;
	}

	/**
	 * Gets the ratio female.
	 *
	 * @return the ratio female
	 */
	public Double getRatioFemale() {
		return ratioFemale;
	}

	/**
	 * Sets the ratio female.
	 *
	 * @param ratioFemale the ratio female
	 */
	public void setRatioFemale(Double ratioFemale) {
		this.ratioFemale = ratioFemale;
	}

	/**
	 * Gets the num pm.
	 *
	 * @return the num pm
	 */
	public Long getNumPm() {
		return numPm;
	}

	/**
	 * Sets the num pm.
	 *
	 * @param numPm the num pm
	 */
	public void setNumPm(Long numPm) {
		this.numPm = numPm;
	}

	/**
	 * Gets the ratio age ten.
	 *
	 * @return the ratio age ten
	 */
	public Double getRatioAgeTen() {
		return ratioAgeTen;
	}

	/**
	 * Sets the ratio age ten.
	 *
	 * @param ratioAgeTen the ratio age ten
	 */
	public void setRatioAgeTen(Double ratioAgeTen) {
		this.ratioAgeTen = ratioAgeTen;
	}

	/**
	 * Gets the num age ten.
	 *
	 * @return the num age ten
	 */
	public Integer getNumAgeTen() {
		return numAgeTen;
	}

	/**
	 * Sets the num age ten.
	 *
	 * @param numAgeTen the num age ten
	 */
	public void setNumAgeTen(Integer numAgeTen) {
		this.numAgeTen = numAgeTen;
	}

	/**
	 * Gets the ratio age twenty.
	 *
	 * @return the ratio age twenty
	 */
	public Double getRatioAgeTwenty() {
		return ratioAgeTwenty;
	}

	/**
	 * Sets the ratio age twenty.
	 *
	 * @param ratioAgeTwenty the ratio age twenty
	 */
	public void setRatioAgeTwenty(Double ratioAgeTwenty) {
		this.ratioAgeTwenty = ratioAgeTwenty;
	}

	/**
	 * Gets the num age twenty.
	 *
	 * @return the num age twenty
	 */
	public Integer getNumAgeTwenty() {
		return numAgeTwenty;
	}

	/**
	 * Sets the num age twenty.
	 *
	 * @param numAgeTwenty the num age twenty
	 */
	public void setNumAgeTwenty(Integer numAgeTwenty) {
		this.numAgeTwenty = numAgeTwenty;
	}

	/**
	 * Gets the ratio age thirty.
	 *
	 * @return the ratio age thirty
	 */
	public Double getRatioAgeThirty() {
		return ratioAgeThirty;
	}

	/**
	 * Sets the ratio age thirty.
	 *
	 * @param ratioAgeThirty the ratio age thirty
	 */
	public void setRatioAgeThirty(Double ratioAgeThirty) {
		this.ratioAgeThirty = ratioAgeThirty;
	}

	/**
	 * Gets the num age thirty.
	 *
	 * @return the num age thirty
	 */
	public Integer getNumAgeThirty() {
		return numAgeThirty;
	}

	/**
	 * Sets the num age thirty.
	 *
	 * @param numAgeThirty the num age thirty
	 */
	public void setNumAgeThirty(Integer numAgeThirty) {
		this.numAgeThirty = numAgeThirty;
	}

	/**
	 * Gets the ratio age forty.
	 *
	 * @return the ratio age forty
	 */
	public Double getRatioAgeForty() {
		return ratioAgeForty;
	}

	/**
	 * Sets the ratio age forty.
	 *
	 * @param ratioAgeForty the ratio age forty
	 */
	public void setRatioAgeForty(Double ratioAgeForty) {
		this.ratioAgeForty = ratioAgeForty;
	}

	/**
	 * Gets the num age forty.
	 *
	 * @return the num age forty
	 */
	public Integer getNumAgeForty() {
		return numAgeForty;
	}

	/**
	 * Sets the num age forty.
	 *
	 * @param numAgeForty the num age forty
	 */
	public void setNumAgeForty(Integer numAgeForty) {
		this.numAgeForty = numAgeForty;
	}

	/**
	 * Gets the ratio age fifty.
	 *
	 * @return the ratio age fifty
	 */
	public Double getRatioAgeFifty() {
		return ratioAgeFifty;
	}

	/**
	 * Sets the ratio age fifty.
	 *
	 * @param ratioAgeFifty the ratio age fifty
	 */
	public void setRatioAgeFifty(Double ratioAgeFifty) {
		this.ratioAgeFifty = ratioAgeFifty;
	}

	/**
	 * Gets the num age fifty.
	 *
	 * @return the num age fifty
	 */
	public Integer getNumAgeFifty() {
		return numAgeFifty;
	}

	/**
	 * Sets the num age fifty.
	 *
	 * @param numAgeFifty the num age fifty
	 */
	public void setNumAgeFifty(Integer numAgeFifty) {
		this.numAgeFifty = numAgeFifty;
	}

	/**
	 * Gets the create date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the create date.
	 *
	 * @param createDate the creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public Integer getNumMale() {
		return numMale;
	}

	public void setNumMale(Integer numMale) {
		this.numMale = numMale;
	}

	public Integer getNumFemale() {
		return numFemale;
	}

	public void setNumFemale(Integer numFemale) {
		this.numFemale = numFemale;
	}

	/* (non-Javadoc)
             * @see java.lang.Object#toString()
             */
	@Override
	public String toString() {
		return "AddClientAnalyze [code=" + code + ", merchantNo=" + merchantNo
				+ ", areaCode=" + areaCode + ", areaName=" + areaName
				+ ", shopNo=" + shopNo + ", shopName=" + shopName + ", stDate="
				+ stDate + ", ratioMale=" + ratioMale + ", ratioFemale="
				+ ratioFemale + ", numPm=" + numPm + ", ratioAgeTen="
				+ ratioAgeTen + ", numAgeTen=" + numAgeTen
				+ ", ratioAgeTwenty=" + ratioAgeTwenty + ", numAgeTwenty="
				+ numAgeTwenty + ", ratioAgeThirty=" + ratioAgeThirty
				+ ", numAgeThirty=" + numAgeThirty + ", ratioAgeForty="
				+ ratioAgeForty + ", numAgeForty=" + numAgeForty
				+ ", ratioAgeFifty=" + ratioAgeFifty + ", numAgeFifty="
				+ numAgeFifty + ", createDate=" + createDate + "]";
	}
    
}
