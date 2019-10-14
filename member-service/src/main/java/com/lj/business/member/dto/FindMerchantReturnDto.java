package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * The Class FindMerchantReturn.
 */
public class FindMerchantReturnDto implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2491970527948535892L;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * LOGO图片 .
     */
    private String logoAddr;

    /**
     * 官方网站 .
     */
    private String websiteUrl;

    /**
     * 联系电话 .
     */
    private String telephone;

    /**
     * 商户试用开始时间 .
     */
    private Date beginProbationTime;
    
    /**
     * 商户试用结束时间 .
     */
    private Date endProbationTime;
    
    /**
     * 商户试用状态 .
     */
    private String probationStatus;
    
    /**
     * 商户电商状态 .
     */
    private String eshopStatus;

    /**
     * 电商网站：
     */
    private String eshopUrl;
    
    /** 产品类型. */
    private String productType;

	public String getEshopUrl() {
		return eshopUrl;
	}

	public void setEshopUrl(String eshopUrl) {
		this.eshopUrl = eshopUrl;
	}

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
	 * Gets the 商户名称 .
	 *
	 * @return the 商户名称 
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the new 商户名称 
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * Gets the lOGO图片 .
	 *
	 * @return the lOGO图片 
	 */
	public String getLogoAddr() {
		return logoAddr;
	}

	/**
	 * Sets the lOGO图片 .
	 *
	 * @param logoAddr the new lOGO图片 
	 */
	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	/**
	 * Gets the 官方网站 .
	 *
	 * @return the 官方网站 
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * Sets the 官方网站 .
	 *
	 * @param websiteUrl the new 官方网站 
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * Gets the 联系电话 .
	 *
	 * @return the 联系电话 
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the 联系电话 .
	 *
	 * @param telephone the new 联系电话 
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the 商户试用开始时间 .
	 *
	 * @return the 商户试用开始时间 
	 */
	public Date getBeginProbationTime() {
		return beginProbationTime;
	}

	/**
	 * Sets the 商户试用开始时间 .
	 *
	 * @param beginProbationTime the new 商户试用开始时间 
	 */
	public void setBeginProbationTime(Date beginProbationTime) {
		this.beginProbationTime = beginProbationTime;
	}

	/**
	 * Gets the 商户试用结束时间 .
	 *
	 * @return the 商户试用结束时间 
	 */
	public Date getEndProbationTime() {
		return endProbationTime;
	}

	/**
	 * Sets the 商户试用结束时间 .
	 *
	 * @param endProbationTime the new 商户试用结束时间 
	 */
	public void setEndProbationTime(Date endProbationTime) {
		this.endProbationTime = endProbationTime;
	}

	/**
	 * Gets the 商户试用状态 .
	 *
	 * @return the 商户试用状态 
	 */
	public String getProbationStatus() {
		return probationStatus;
	}

	/**
	 * Sets the 商户试用状态 .
	 *
	 * @param probationStatus the new 商户试用状态 
	 */
	public void setProbationStatus(String probationStatus) {
		this.probationStatus = probationStatus;
	}

	/**
	 * Gets the 产品类型.
	 *
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * Sets the 产品类型.
	 *
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Gets the 商户电商状态 .
	 *
	 * @return the 商户电商状态 
	 */
	public String getEshopStatus() {
		return eshopStatus;
	}

	/**
	 * Sets the 商户电商状态 .
	 *
	 * @param eshopStatus the new 商户电商状态 
	 */
	public void setEshopStatus(String eshopStatus) {
		this.eshopStatus = eshopStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMerchantReturnDto [merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", logoAddr=");
		builder.append(logoAddr);
		builder.append(", websiteUrl=");
		builder.append(websiteUrl);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", beginProbationTime=");
		builder.append(beginProbationTime);
		builder.append(", endProbationTime=");
		builder.append(endProbationTime);
		builder.append(", probationStatus=");
		builder.append(probationStatus);
		builder.append(", eshopStatus=");
		builder.append(eshopStatus);
		builder.append(", eshopUrl=");
		builder.append(eshopUrl);
		builder.append(", productType=");
		builder.append(productType);
		builder.append("]");
		return builder.toString();
	}
	
}
