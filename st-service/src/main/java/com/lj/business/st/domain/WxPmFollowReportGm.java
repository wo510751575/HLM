package com.lj.business.st.domain;

import java.util.Date;

public class WxPmFollowReportGm {
    /**
     * CODE
     */
    private String code;

    /**
     * 统计日期
     */
    private Date reportDate;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 经销商编号
     */
    private String companyNo;

    /**
     * 经销商名称
     */
    private String companyName;

    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 门店编号
     */
    private String shopNo;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 门店代码
     */
    private String shopCode;

    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     * 导购姓名
     */
    private String memberNameGm;

    /**
     * 头像地址
     */
    private String headAddress;

    /**
     * 新增客户数
     */
    private Long numPmNew;
    
    /**
     * 未跟踪新增客户数
     */
    private Long numPmNewNotFollow;

    /**
     * 已跟踪新增客户数
     */
    private Long numPmNewFollow;

    /**
     * 老客户数
     */
    private Long numPmOld;

    /**
     * 未跟踪老客户数
     */
    private Long numPmOldNotFollow;

    /**
     * 已跟踪老客户数
     */
    private Long numPmOldFollow;

    /**
     * 客户总数
     */
    private Long numPmTotal;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode == null ? null : dealerCode.trim();
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

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    public String getMemberNameGm() {
        return memberNameGm;
    }

    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    public Long getNumPmNew() {
        return numPmNew;
    }

    public void setNumPmNew(Long numPmNew) {
        this.numPmNew = numPmNew;
    }

    public Long getNumPmNewNotFollow() {
        return numPmNewNotFollow;
    }

    public void setNumPmNewNotFollow(Long numPmNewNotFollow) {
        this.numPmNewNotFollow = numPmNewNotFollow;
    }

    public Long getNumPmNewFollow() {
        return numPmNewFollow;
    }

    public void setNumPmNewFollow(Long numPmNewFollow) {
        this.numPmNewFollow = numPmNewFollow;
    }

    public Long getNumPmOld() {
        return numPmOld;
    }

    public void setNumPmOld(Long numPmOld) {
        this.numPmOld = numPmOld;
    }

    public Long getNumPmOldNotFollow() {
        return numPmOldNotFollow;
    }

    public void setNumPmOldNotFollow(Long numPmOldNotFollow) {
        this.numPmOldNotFollow = numPmOldNotFollow;
    }

    public Long getNumPmOldFollow() {
        return numPmOldFollow;
    }

    public void setNumPmOldFollow(Long numPmOldFollow) {
        this.numPmOldFollow = numPmOldFollow;
    }

    public Long getNumPmTotal() {
        return numPmTotal;
    }

    public void setNumPmTotal(Long numPmTotal) {
        this.numPmTotal = numPmTotal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}