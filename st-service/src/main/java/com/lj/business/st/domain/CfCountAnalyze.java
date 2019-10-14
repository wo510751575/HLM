package com.lj.business.st.domain;

import java.io.Serializable;
import java.util.Date;

public class CfCountAnalyze implements Serializable {

    private static final long serialVersionUID = 5310695367122093916L;

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
     * 总跟踪次数 .
     */
    private Long numCf;

    /**
     * 1-5次跟踪占比 .
     */
    private Double ratioCfFive;

    /**
     * 1-5次跟踪数量 .
     */
    private Integer numCfFive;

    /**
     * 6-10次跟踪占比 .
     */
    private Double ratioCfTen;

    /**
     * 6-10次跟踪数量 .
     */
    private Integer numCfTen;

    /**
     * 10-15次跟踪占比 .
     */
    private Double ratioCfFifteen;

    /**
     * 10-15次跟踪数量 .
     */
    private Integer numCfFifteen;

    /**
     * 16次以上跟踪占比 .
     */
    private Double ratioCfSixteen;

    /**
     * 16次跟踪数量 .
     */
    private Integer numCfSixteen;

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
     * 总跟踪次数 .
     *
     */
    public Long getNumCf() {
        return numCf;
    }

    /**
     * 总跟踪次数 .
     *
     */
    public void setNumCf(Long numCf) {
        this.numCf = numCf;
    }

    /**
     * 1-5次跟踪占比 .
     *
     */
    public Double getRatioCfFive() {
        return ratioCfFive;
    }

    /**
     * 1-5次跟踪占比 .
     *
     */
    public void setRatioCfFive(Double ratioCfFive) {
        this.ratioCfFive = ratioCfFive;
    }

    /**
     * 1-5次跟踪数量 .
     *
     */
    public Integer getNumCfFive() {
        return numCfFive;
    }

    /**
     * 1-5次跟踪数量 .
     *
     */
    public void setNumCfFive(Integer numCfFive) {
        this.numCfFive = numCfFive;
    }

    /**
     * 6-10次跟踪占比 .
     *
     */
    public Double getRatioCfTen() {
        return ratioCfTen;
    }

    /**
     * 6-10次跟踪占比 .
     *
     */
    public void setRatioCfTen(Double ratioCfTen) {
        this.ratioCfTen = ratioCfTen;
    }

    /**
     * 6-10次跟踪数量 .
     *
     */
    public Integer getNumCfTen() {
        return numCfTen;
    }

    /**
     * 6-10次跟踪数量 .
     *
     */
    public void setNumCfTen(Integer numCfTen) {
        this.numCfTen = numCfTen;
    }

    /**
     * 10-15次跟踪占比 .
     *
     */
    public Double getRatioCfFifteen() {
        return ratioCfFifteen;
    }

    /**
     * 10-15次跟踪占比 .
     *
     */
    public void setRatioCfFifteen(Double ratioCfFifteen) {
        this.ratioCfFifteen = ratioCfFifteen;
    }

    /**
     * 10-15次跟踪数量 .
     *
     */
    public Integer getNumCfFifteen() {
        return numCfFifteen;
    }

    /**
     * 10-15次跟踪数量 .
     *
     */
    public void setNumCfFifteen(Integer numCfFifteen) {
        this.numCfFifteen = numCfFifteen;
    }

    /**
     * 16次以上跟踪占比 .
     *
     */
    public Double getRatioCfSixteen() {
        return ratioCfSixteen;
    }

    /**
     * 16次以上跟踪占比 .
     *
     */
    public void setRatioCfSixteen(Double ratioCfSixteen) {
        this.ratioCfSixteen = ratioCfSixteen;
    }

    /**
     * 16次跟踪数量 .
     *
     */
    public Integer getNumCfSixteen() {
        return numCfSixteen;
    }

    /**
     * 16次跟踪数量 .
     *
     */
    public void setNumCfSixteen(Integer numCfSixteen) {
        this.numCfSixteen = numCfSixteen;
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
        builder.append("CfCountAnalyze [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",stDate=").append(stDate); 
        builder.append(",numCf=").append(numCf); 
        builder.append(",ratioCfFive=").append(ratioCfFive); 
        builder.append(",numCfFive=").append(numCfFive); 
        builder.append(",ratioCfTen=").append(ratioCfTen); 
        builder.append(",numCfTen=").append(numCfTen); 
        builder.append(",ratioCfFifteen=").append(ratioCfFifteen); 
        builder.append(",numCfFifteen=").append(numCfFifteen); 
        builder.append(",ratioCfSixteen=").append(ratioCfSixteen); 
        builder.append(",numCfSixteen=").append(numCfSixteen); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}