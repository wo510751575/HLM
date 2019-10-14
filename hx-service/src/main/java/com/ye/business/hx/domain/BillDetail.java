package com.ye.business.hx.domain;

import java.util.Date;

public class BillDetail {
    /** CODE*/
    private String code;

    /** 账单code*/
    private String billCode;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 项目code*/
    private String projectCode;

    /** 项目名称*/
    private String projectName;

    /** 单位*/
    private String projectUnit;

    /** 未折扣的单价*/
    private Long unitPrice;

    /** 单项折扣后的单价(分为单位）*/
    private Long itemDisUnitPrice;

    /** 数量*/
    private Integer itemNum;

    /** 未折扣的总原价(分为单位）*/
    private Long originalAmount;

    /** 单项折扣总价格(分为单位）*/
    private Long itemDiscountAmount;

    /** 单项折扣万分比*/
    private Integer discountItem;

    /** 已退款数量*/
    private Integer rtNum;

    /** 已退款总金额(分为单位）*/
    private Long rtAmount;

    /** 是否参与整单折扣（Y:是，N:否）*/
    private String discountOrderStatus;

    /** 咨询师编号*/
    private String advisoryNo;

    /** 咨询师名称*/
    private String advisoryName;

    /** 医生编号*/
    private String doctorNo;

    /** 医生名称*/
    private String doctorName;

    /** 助手编号*/
    private String assistantNo;

    /** 助手名称*/
    private String assistantName;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 创建时间*/
    private Date createDate;

    /** 备注*/
    private String remark;

    /** 备注1*/
    private String remark1;

    /** 备注2*/
    private String remark2;

    /** 备注3*/
    private String remark3;

    /** 备注4*/
    private String remark4;

    /** 实收(分为单位）*/
    private Long payAmount;

    /** 应收(分为单位）*/
    private Long reallyAmount;

    /** 欠费(分为单位）*/
    private Long debtAmount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectUnit() {
        return projectUnit;
    }

    public void setProjectUnit(String projectUnit) {
        this.projectUnit = projectUnit == null ? null : projectUnit.trim();
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getItemDisUnitPrice() {
        return itemDisUnitPrice;
    }

    public void setItemDisUnitPrice(Long itemDisUnitPrice) {
        this.itemDisUnitPrice = itemDisUnitPrice;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Long getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Long originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Long getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    public void setItemDiscountAmount(Long itemDiscountAmount) {
        this.itemDiscountAmount = itemDiscountAmount;
    }

    public Integer getDiscountItem() {
        return discountItem;
    }

    public void setDiscountItem(Integer discountItem) {
        this.discountItem = discountItem;
    }

    public Integer getRtNum() {
        return rtNum;
    }

    public void setRtNum(Integer rtNum) {
        this.rtNum = rtNum;
    }

    public Long getRtAmount() {
        return rtAmount;
    }

    public void setRtAmount(Long rtAmount) {
        this.rtAmount = rtAmount;
    }

    public String getDiscountOrderStatus() {
        return discountOrderStatus;
    }

    public void setDiscountOrderStatus(String discountOrderStatus) {
        this.discountOrderStatus = discountOrderStatus == null ? null : discountOrderStatus.trim();
    }

    public String getAdvisoryNo() {
        return advisoryNo;
    }

    public void setAdvisoryNo(String advisoryNo) {
        this.advisoryNo = advisoryNo == null ? null : advisoryNo.trim();
    }

    public String getAdvisoryName() {
        return advisoryName;
    }

    public void setAdvisoryName(String advisoryName) {
        this.advisoryName = advisoryName == null ? null : advisoryName.trim();
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo == null ? null : doctorNo.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getAssistantNo() {
        return assistantNo;
    }

    public void setAssistantNo(String assistantNo) {
        this.assistantNo = assistantNo == null ? null : assistantNo.trim();
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName == null ? null : assistantName.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getReallyAmount() {
        return reallyAmount;
    }

    public void setReallyAmount(Long reallyAmount) {
        this.reallyAmount = reallyAmount;
    }

    public Long getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Long debtAmount) {
        this.debtAmount = debtAmount;
    }
}