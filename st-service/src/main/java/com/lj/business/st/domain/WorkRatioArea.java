package com.lj.business.st.domain;

import java.util.Date;

/**
 * 
 * 
 * 类说明：区域工作统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月28日
 */
public class WorkRatioArea {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String memberNoMerchant;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 省名称 .
     */
    private String provinceName;

    /**
     * 门店数量 .
     */
    private Integer numShop;

    /**
     * 门店占比 .
     */
    private Double ratioShop;

    /**
     * 客户数量 .
     */
    private Long numPm;

    /**
     * 客户占比 .
     */
    private Double ratioPm;

    /**
     * 统计日期 .
     */
    private Date stDate;

    /**
     * 统计维度             区域：AREA             省：PROVINCE .
     */
    private String dimensionSt;

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
    public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant == null ? null : memberNoMerchant.trim();
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
     * 省CODE .
     *
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省CODE .
     *
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 省名称 .
     *
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 省名称 .
     *
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 门店数量 .
     *
     */
    public Integer getNumShop() {
        return numShop;
    }

    /**
     * 门店数量 .
     *
     */
    public void setNumShop(Integer numShop) {
        this.numShop = numShop;
    }

    /**
     * 门店占比 .
     *
     */
    public Double getRatioShop() {
        return ratioShop;
    }

    /**
     * 门店占比 .
     *
     */
    public void setRatioShop(Double ratioShop) {
        this.ratioShop = ratioShop;
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
     * 客户占比 .
     *
     */
    public Double getRatioPm() {
        return ratioPm;
    }

    /**
     * 客户占比 .
     *
     */
    public void setRatioPm(Double ratioPm) {
        this.ratioPm = ratioPm;
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
     * 统计维度             区域：AREA             省：PROVINCE .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 统计维度             区域：AREA             省：PROVINCE .
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkRatioArea [code=").append(code);
        builder.append(",memberNoMerchant=").append(memberNoMerchant); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",provinceCode=").append(provinceCode); 
        builder.append(",provinceName=").append(provinceName); 
        builder.append(",numShop=").append(numShop); 
        builder.append(",ratioShop=").append(ratioShop); 
        builder.append(",numPm=").append(numPm); 
        builder.append(",ratioPm=").append(ratioPm); 
        builder.append(",stDate=").append(stDate); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}