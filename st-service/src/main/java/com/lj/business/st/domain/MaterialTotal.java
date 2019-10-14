package com.lj.business.st.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MaterialTotal {
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

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
     * 回应数量 .
     */
    private Integer respondNum;

    /**
     * 回应所占比例 .
     */
    private BigDecimal ratioRespond;

    /**
     * 统计日期 .
     */
    private Date daySt;

    /**
     * 类型ID .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;

    /**
     * 统计维度             商户：MERCHANT             区域：AREA             门店：SHOP             导购：GUID .
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
     * 回应数量 .
     *
     */
    public Integer getRespondNum() {
        return respondNum;
    }

    /**
     * 回应数量 .
     *
     */
    public void setRespondNum(Integer respondNum) {
        this.respondNum = respondNum;
    }

    /**
     * 回应所占比例 .
     *
     */
    public BigDecimal getRatioRespond() {
        return ratioRespond;
    }

    /**
     * 回应所占比例 .
     *
     */
    public void setRatioRespond(BigDecimal ratioRespond) {
        this.ratioRespond = ratioRespond;
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
     * 类型ID .
     *
     */
    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    /**
     * 类型ID .
     *
     */
    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode == null ? null : materialTypeCode.trim();
    }

    /**
     * 类型名称 .
     *
     */
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    /**
     * 类型名称 .
     *
     */
    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName == null ? null : materialTypeName.trim();
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
        builder.append("MaterialTotal [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",respondNum=").append(respondNum); 
        builder.append(",ratioRespond=").append(ratioRespond); 
        builder.append(",daySt=").append(daySt); 
        builder.append(",materialTypeCode=").append(materialTypeCode); 
        builder.append(",materialTypeName=").append(materialTypeName); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}