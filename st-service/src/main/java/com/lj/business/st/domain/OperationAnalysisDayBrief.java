package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class OperationAnalysisDayBrief implements Serializable {

    private static final long serialVersionUID = -1703398676249322203L;

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
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 销售漏斗摘要 .
     */
    private String briefSale;

    /**
     * 区域成单摘要
     */
    private String briefOrder;

    /**
     * 客户行为摘要
     */
    private String briefClientAction;

    /**
     * 客户画像摘要
     */
    private String briefClientAnalyze;

    /**
     * 跟进分析摘要
     */
    private String briefCf;

    /**
     * 区域客户分析摘要
     */
    private String briefCaArea;

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 创建时间 .
     */
    private Date createDate;
    /**
     * 统计日期
     */
    private Date daySt; 
    

    public Date getDaySt() {
		return daySt;
	}

	public void setDaySt(Date daySt) {
		this.daySt = daySt;
	}

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 区域CODE .
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 区域名称 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区域名称 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    public String getMemberNameGm() {
        return memberNameGm;
    }

    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm;
    }

    public String getBriefSale() {
        return briefSale;
    }

    public void setBriefSale(String briefSale) {
        this.briefSale = briefSale;
    }

    public String getBriefOrder() {
        return briefOrder;
    }

    public void setBriefOrder(String briefOrder) {
        this.briefOrder = briefOrder;
    }

    public String getBriefClientAction() {
        return briefClientAction;
    }

    public void setBriefClientAction(String briefClientAction) {
        this.briefClientAction = briefClientAction;
    }

    public String getBriefClientAnalyze() {
        return briefClientAnalyze;
    }

    public void setBriefClientAnalyze(String briefClientAnalyze) {
        this.briefClientAnalyze = briefClientAnalyze;
    }

    public String getBriefCf() {
        return briefCf;
    }

    public void setBriefCf(String briefCf) {
        this.briefCf = briefCf;
    }

    public String getBriefCaArea() {
        return briefCaArea;
    }

    public void setBriefCaArea(String briefCaArea) {
        this.briefCaArea = briefCaArea;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度
             商户：MERCHANT
             区域：AREA
             门店：SHOP
             导购：GUID .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
	public String toString() {
		return "OperationAnalysisDayBrief [code=" + code + ", merchantNo="
				+ merchantNo + ", areaCode=" + areaCode + ", areaName="
				+ areaName + ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", briefSale=" + briefSale + ", briefOrder="
				+ briefOrder + ", briefClientAction=" + briefClientAction
				+ ", briefClientAnalyze=" + briefClientAnalyze + ", briefCf="
				+ briefCf + ", briefCaArea=" + briefCaArea + ", dimensionSt="
				+ dimensionSt + ", createDate=" + createDate + ", daySt="
				+ daySt + "]";
	}
}