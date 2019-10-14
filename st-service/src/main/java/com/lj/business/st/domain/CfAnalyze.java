package com.lj.business.st.domain;

import java.util.Date;

public class CfAnalyze {
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
     * 统计维度             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     * 创建时间 .
     */
    private Date createDate;

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

    /**
     * 导购编号  .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 客户分析摘要 .
     *
     */
    public String getBriefClientAnalyze() {
        return briefClientAnalyze;
    }

    /**
     * 客户分析摘要 .
     *
     */
    public void setBriefClientAnalyze(String briefClientAnalyze) {
        this.briefClientAnalyze = briefClientAnalyze == null ? null : briefClientAnalyze.trim();
    }

    /**
     * 客户行为摘要 .
     *
     */
    public String getBriefClientAction() {
        return briefClientAction;
    }

    /**
     * 客户行为摘要 .
     *
     */
    public void setBriefClientAction(String briefClientAction) {
        this.briefClientAction = briefClientAction == null ? null : briefClientAction.trim();
    }

    /**
     * 社交分析摘要 .
     *
     */
    public String getBriefSocial() {
        return briefSocial;
    }

    /**
     * 社交分析摘要 .
     *
     */
    public void setBriefSocial(String briefSocial) {
        this.briefSocial = briefSocial == null ? null : briefSocial.trim();
    }

    /**
     * 素材分析摘要 .
     *
     */
    public String getBriefMaterial() {
        return briefMaterial;
    }

    /**
     * 素材分析摘要 .
     *
     */
    public void setBriefMaterial(String briefMaterial) {
        this.briefMaterial = briefMaterial == null ? null : briefMaterial.trim();
    }

    /**
     * 客户分类摘要 .
     *
     */
    public String getBriefClientType() {
        return briefClientType;
    }

    /**
     * 客户分类摘要 .
     *
     */
    public void setBriefClientType(String briefClientType) {
        this.briefClientType = briefClientType == null ? null : briefClientType.trim();
    }

    /**
     * 统计维度             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度             导购：GUID .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 统计日期 .
     *
     */
    public Date getDaySt() {
        return daySt;
    }

    /**
     * 统计日期 .
     *
     */
    public void setDaySt(Date daySt) {
        this.daySt = daySt;
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CfAnalyze [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",briefClientAnalyze=").append(briefClientAnalyze); 
        builder.append(",briefClientAction=").append(briefClientAction); 
        builder.append(",briefSocial=").append(briefSocial); 
        builder.append(",briefMaterial=").append(briefMaterial); 
        builder.append(",briefClientType=").append(briefClientType); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",daySt=").append(daySt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}