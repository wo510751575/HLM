package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class ClientAnalyze implements Serializable {

    private static final long serialVersionUID = -4122623716699263937L;

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
     * 统计维度\r\n            商户：MERCHANT\r\n            区域：AREA\r\n            门店：SHOP
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
     * 男性比例 .
     *
     */
    public Double getRatioMale() {
        return ratioMale;
    }

    /**
     * 男性比例 .
     *
     */
    public void setRatioMale(Double ratioMale) {
        this.ratioMale = ratioMale;
    }

    /**
     * 女性比例 .
     *
     */
    public Double getRatioFemale() {
        return ratioFemale;
    }

    /**
     * 女性比例 .
     *
     */
    public void setRatioFemale(Double ratioFemale) {
        this.ratioFemale = ratioFemale;
    }

    /**
     * 客户数量 .
     *
     */
    public Long getNumPm() {
        return numPm;
    }

    /**
     * 客户数量 .
     *
     */
    public void setNumPm(Long numPm) {
        this.numPm = numPm;
    }

    /**
     * 10-19岁占比 .
     *
     */
    public Double getRatioAgeTen() {
        return ratioAgeTen;
    }

    /**
     * 10-19岁占比 .
     *
     */
    public void setRatioAgeTen(Double ratioAgeTen) {
        this.ratioAgeTen = ratioAgeTen;
    }

    /**
     * 10-19岁数量 .
     *
     */
    public Integer getNumAgeTen() {
        return numAgeTen;
    }

    /**
     * 10-19岁数量 .
     *
     */
    public void setNumAgeTen(Integer numAgeTen) {
        this.numAgeTen = numAgeTen;
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientAnalyze [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",stDate=").append(stDate); 
        builder.append(",ratioMale=").append(ratioMale); 
        builder.append(",ratioFemale=").append(ratioFemale); 
        builder.append(",numPm=").append(numPm); 
        builder.append(",ratioAgeTen=").append(ratioAgeTen); 
        builder.append(",numAgeTen=").append(numAgeTen); 
        builder.append(",ratioAgeTwenty=").append(ratioAgeTwenty); 
        builder.append(",numAgeTwenty=").append(numAgeTwenty); 
        builder.append(",ratioAgeThirty=").append(ratioAgeThirty); 
        builder.append(",numAgeThirty=").append(numAgeThirty); 
        builder.append(",ratioAgeForty=").append(ratioAgeForty); 
        builder.append(",numAgeForty=").append(numAgeForty); 
        builder.append(",ratioAgeFifty=").append(ratioAgeFifty); 
        builder.append(",numAgeFifty=").append(numAgeFifty); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}