package com.lj.business.st.domain;

import java.util.Date;

public class WorkBrDay {
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
     * 项目CODE .
     */
    private String codeList;

    /**
     * 项目名称 .
     */
    private String nameList;

    /**
     * 项目统计值 .
     */
    private String valueList;

    /**
     * 统计日期 .
     */
    private Date daySt;

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
     * 项目CODE .
     *
     */
    public String getCodeList() {
        return codeList;
    }

    /**
     * 项目CODE .
     *
     */
    public void setCodeList(String codeList) {
        this.codeList = codeList == null ? null : codeList.trim();
    }

    /**
     * 项目名称 .
     *
     */
    public String getNameList() {
        return nameList;
    }

    /**
     * 项目名称 .
     *
     */
    public void setNameList(String nameList) {
        this.nameList = nameList == null ? null : nameList.trim();
    }

    /**
     * 项目统计值 .
     *
     */
    public String getValueList() {
        return valueList;
    }

    /**
     * 项目统计值 .
     *
     */
    public void setValueList(String valueList) {
        this.valueList = valueList == null ? null : valueList.trim();
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
        builder.append("WorkBrDay [code=").append(code);
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",memberNameGm=").append(memberNameGm); 
        builder.append(",codeList=").append(codeList); 
        builder.append(",nameList=").append(nameList); 
        builder.append(",valueList=").append(valueList); 
        builder.append(",daySt=").append(daySt); 
        builder.append(",dimensionSt=").append(dimensionSt); 
        builder.append(",createDate=").append(createDate); 
        builder.append("]");
        return builder.toString();
    }
}