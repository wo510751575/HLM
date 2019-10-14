package com.lj.business.st.domain;

import java.util.Date;

public class EmployeeAnalyze {
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
     * 客户数量 .
     */
    private Long numEmployee;

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
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     */
    private String dimensionSt;

    /**
     * 50-59岁数量 .
     */
    private Integer numAgeFifty;

    /**
     * 50-59岁占比 .
     */
    private Double ratioAgeFifty;

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
     * 统计日期 .
     *
     */
    public Date getStDate() {
        return stDate;
    }

    /**
     * 统计日期 .
     *
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    /**
     * 客户数量 .
     *
     */
    public Long getNumEmployee() {
        return numEmployee;
    }

    /**
     * 客户数量 .
     *
     */
    public void setNumEmployee(Long numEmployee) {
        this.numEmployee = numEmployee;
    }

    /**
     * 20-29岁占比 .
     *
     */
    public Double getRatioAgeTwenty() {
        return ratioAgeTwenty;
    }

    /**
     * 20-29岁占比 .
     *
     */
    public void setRatioAgeTwenty(Double ratioAgeTwenty) {
        this.ratioAgeTwenty = ratioAgeTwenty;
    }

    /**
     * 20-29岁数量 .
     *
     */
    public Integer getNumAgeTwenty() {
        return numAgeTwenty;
    }

    /**
     * 20-29岁数量 .
     *
     */
    public void setNumAgeTwenty(Integer numAgeTwenty) {
        this.numAgeTwenty = numAgeTwenty;
    }

    /**
     * 30-39岁占比 .
     *
     */
    public Double getRatioAgeThirty() {
        return ratioAgeThirty;
    }

    /**
     * 30-39岁占比 .
     *
     */
    public void setRatioAgeThirty(Double ratioAgeThirty) {
        this.ratioAgeThirty = ratioAgeThirty;
    }

    /**
     * 30-39岁数量 .
     *
     */
    public Integer getNumAgeThirty() {
        return numAgeThirty;
    }

    /**
     * 30-39岁数量 .
     *
     */
    public void setNumAgeThirty(Integer numAgeThirty) {
        this.numAgeThirty = numAgeThirty;
    }

    /**
     * 40-49岁占比 .
     *
     */
    public Double getRatioAgeForty() {
        return ratioAgeForty;
    }

    /**
     * 40-49岁占比 .
     *
     */
    public void setRatioAgeForty(Double ratioAgeForty) {
        this.ratioAgeForty = ratioAgeForty;
    }

    /**
     * 40-49岁数量 .
     *
     */
    public Integer getNumAgeForty() {
        return numAgeForty;
    }

    /**
     * 40-49岁数量 .
     *
     */
    public void setNumAgeForty(Integer numAgeForty) {
        this.numAgeForty = numAgeForty;
    }

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 50-59岁数量 .
     *
     */
    public Integer getNumAgeFifty() {
        return numAgeFifty;
    }

    /**
     * 50-59岁数量 .
     *
     */
    public void setNumAgeFifty(Integer numAgeFifty) {
        this.numAgeFifty = numAgeFifty;
    }

    /**
     * 50-59岁占比 .
     *
     */
    public Double getRatioAgeFifty() {
        return ratioAgeFifty;
    }

    /**
     * 50-59岁占比 .
     *
     */
    public void setRatioAgeFifty(Double ratioAgeFifty) {
        this.ratioAgeFifty = ratioAgeFifty;
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
        builder.append("EmployeeAnalyze [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",stDate=").append(stDate); 
        builder.append(",numEmployee=").append(numEmployee); 
        builder.append(",ratioAgeTwenty=").append(ratioAgeTwenty); 
        builder.append(",numAgeTwenty=").append(numAgeTwenty); 
        builder.append(",ratioAgeThirty=").append(ratioAgeThirty); 
        builder.append(",numAgeThirty=").append(numAgeThirty); 
        builder.append(",ratioAgeForty=").append(ratioAgeForty); 
        builder.append(",numAgeForty=").append(numAgeForty); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",numAgeFifty=").append(numAgeFifty); 
        builder.append(",ratioAgeFifty=").append(ratioAgeFifty); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}