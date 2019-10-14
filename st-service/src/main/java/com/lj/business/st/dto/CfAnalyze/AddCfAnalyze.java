package com.lj.business.st.dto.CfAnalyze;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AddCfAnalyze.
 */
public class AddCfAnalyze implements Serializable { 

     /** Generate cron. */
	private static final long serialVersionUID = -6527487769633903490L;

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
     * 导购编号  .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 客户分析摘要 .
     */
    private String briefClientAnalyze;

    /**
     * 客户行为摘要 .
     */
    private String briefClientAction;

    /**
     * 社交分析摘要 .
     */
    private String briefSocial;

    /**
     * 素材分析摘要 .
     */
    private String briefMaterial;

    /**
     * 客户分类摘要 .
     */
    private String briefClientType;

    /**
     * 统计维度
             导购：GUID .
     */
    private String dimensionSt;

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
	 * Gets the 区域CODE .
	 *
	 * @return the 区域CODE 
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the 区域CODE .
	 *
	 * @param areaCode the new 区域CODE 
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Gets the 区域名称 .
	 *
	 * @return the 区域名称 
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the 区域名称 .
	 *
	 * @param areaName the new 区域名称 
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	 * Gets the 客户分析摘要 .
	 *
	 * @return the 客户分析摘要 
	 */
	public String getBriefClientAnalyze() {
		return briefClientAnalyze;
	}

	/**
	 * Sets the 客户分析摘要 .
	 *
	 * @param briefClientAnalyze the new 客户分析摘要 
	 */
	public void setBriefClientAnalyze(String briefClientAnalyze) {
		this.briefClientAnalyze = briefClientAnalyze;
	}

	/**
	 * Gets the 客户行为摘要 .
	 *
	 * @return the 客户行为摘要 
	 */
	public String getBriefClientAction() {
		return briefClientAction;
	}

	/**
	 * Sets the 客户行为摘要 .
	 *
	 * @param briefClientAction the new 客户行为摘要 
	 */
	public void setBriefClientAction(String briefClientAction) {
		this.briefClientAction = briefClientAction;
	}

	/**
	 * Gets the 社交分析摘要 .
	 *
	 * @return the 社交分析摘要 
	 */
	public String getBriefSocial() {
		return briefSocial;
	}

	/**
	 * Sets the 社交分析摘要 .
	 *
	 * @param briefSocial the new 社交分析摘要 
	 */
	public void setBriefSocial(String briefSocial) {
		this.briefSocial = briefSocial;
	}

	/**
	 * Gets the 素材分析摘要 .
	 *
	 * @return the 素材分析摘要 
	 */
	public String getBriefMaterial() {
		return briefMaterial;
	}

	/**
	 * Sets the 素材分析摘要 .
	 *
	 * @param briefMaterial the new 素材分析摘要 
	 */
	public void setBriefMaterial(String briefMaterial) {
		this.briefMaterial = briefMaterial;
	}

	/**
	 * Gets the 客户分类摘要 .
	 *
	 * @return the 客户分类摘要 
	 */
	public String getBriefClientType() {
		return briefClientType;
	}

	/**
	 * Sets the 客户分类摘要 .
	 *
	 * @param briefClientType the new 客户分类摘要 
	 */
	public void setBriefClientType(String briefClientType) {
		this.briefClientType = briefClientType;
	}

	/**
	 * Gets the 统计维度 导购：GUID .
	 *
	 * @return the 统计维度 导购：GUID 
	 */
	public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the 统计维度 导购：GUID .
	 *
	 * @param dimensionSt the new 统计维度 导购：GUID 
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
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
		builder.append("AddCfAnalyze [merchantNo=").append(merchantNo)
				.append(", areaCode=").append(areaCode).append(", areaName=")
				.append(areaName).append(", shopNo=").append(shopNo)
				.append(", shopName=").append(shopName).append(", memberNoGm=")
				.append(memberNoGm).append(", memberNameGm=")
				.append(memberNameGm).append(", briefClientAnalyze=")
				.append(briefClientAnalyze).append(", briefClientAction=")
				.append(briefClientAction).append(", briefSocial=")
				.append(briefSocial).append(", briefMaterial=")
				.append(briefMaterial).append(", briefClientType=")
				.append(briefClientType).append(", dimensionSt=")
				.append(dimensionSt).append("]");
		return builder.toString();
	}

}
