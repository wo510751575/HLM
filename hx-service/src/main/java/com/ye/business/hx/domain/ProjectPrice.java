package com.ye.business.hx.domain;

import java.util.Date;

public class ProjectPrice {
    /** CODE*/
    private String code;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 大类别名称*/
    private String type1Name;

    /** 大类别code*/
    private String type1Code;

    /** 小类别名称2*/
    private String type2Name;

    /** 小类别code2*/
    private String type2Code;

    /** 项目名称*/
    private String projectName;

    /** 项目编码*/
    private String projectNo;

    /** 项目拼音（用于检索）*/
    private String pinyin;

    /** 单位*/
    private String projectUnit;

    /** 单价（分为单位）*/
    private Long price;

    /** 单项允许打折否（Y:是，N:否）*/
    private String allowItemDiscount;

    /** 整单允许打折否（Y:是，N:否）*/
    private String allowOrderDiscount;

    /** 排序*/
    private Integer indexNo;

    /** 英文名称*/
    private String enname;

    /** 最小单价（分为单位）*/
    private Long minPrice;

    /** 最大单价（分为单位）*/
    private Long maxPrice;

    /** 单项折扣下限（万分比）*/
    private Integer minDiscount;

    /** 是否启用use("启用"), unuse("禁用")*/
    private String status;

    /** 是否成交（Y:是，N:否）*/
    private String allowDeal;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getType1Name() {
        return type1Name;
    }

    public void setType1Name(String type1Name) {
        this.type1Name = type1Name == null ? null : type1Name.trim();
    }

    public String getType1Code() {
        return type1Code;
    }

    public void setType1Code(String type1Code) {
        this.type1Code = type1Code == null ? null : type1Code.trim();
    }

    public String getType2Name() {
        return type2Name;
    }

    public void setType2Name(String type2Name) {
        this.type2Name = type2Name == null ? null : type2Name.trim();
    }

    public String getType2Code() {
        return type2Code;
    }

    public void setType2Code(String type2Code) {
        this.type2Code = type2Code == null ? null : type2Code.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getProjectUnit() {
        return projectUnit;
    }

    public void setProjectUnit(String projectUnit) {
        this.projectUnit = projectUnit == null ? null : projectUnit.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAllowItemDiscount() {
        return allowItemDiscount;
    }

    public void setAllowItemDiscount(String allowItemDiscount) {
        this.allowItemDiscount = allowItemDiscount == null ? null : allowItemDiscount.trim();
    }

    public String getAllowOrderDiscount() {
        return allowOrderDiscount;
    }

    public void setAllowOrderDiscount(String allowOrderDiscount) {
        this.allowOrderDiscount = allowOrderDiscount == null ? null : allowOrderDiscount.trim();
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinDiscount() {
        return minDiscount;
    }

    public void setMinDiscount(Integer minDiscount) {
        this.minDiscount = minDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAllowDeal() {
        return allowDeal;
    }

    public void setAllowDeal(String allowDeal) {
        this.allowDeal = allowDeal == null ? null : allowDeal.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}