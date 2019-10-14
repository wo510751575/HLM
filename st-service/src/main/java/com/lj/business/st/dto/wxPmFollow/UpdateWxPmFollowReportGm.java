package com.lj.business.st.dto.wxPmFollow;

import java.io.Serializable;
import java.util.Date;

public class UpdateWxPmFollowReportGm implements Serializable { 

    /**
     * 
     */
    private static final long serialVersionUID = -2509676521205783997L;

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
        this.code = code;
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
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    public String getMemberNameGm() {
        return memberNameGm;
    }

    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm;
    }

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
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
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UpdateWxPmFollowReportGm [code=");
        builder.append(code);
        builder.append(", reportDate=");
        builder.append(reportDate);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append(", merchantName=");
        builder.append(merchantName);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", dealerCode=");
        builder.append(dealerCode);
        builder.append(", shopNo=");
        builder.append(shopNo);
        builder.append(", shopName=");
        builder.append(shopName);
        builder.append(", shopCode=");
        builder.append(shopCode);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", memberNameGm=");
        builder.append(memberNameGm);
        builder.append(", headAddress=");
        builder.append(headAddress);
        builder.append(", numPmNew=");
        builder.append(numPmNew);
        builder.append(", numPmNewNotFollow=");
        builder.append(numPmNewNotFollow);
        builder.append(", numPmNewFollow=");
        builder.append(numPmNewFollow);
        builder.append(", numPmOld=");
        builder.append(numPmOld);
        builder.append(", numPmOldNotFollow=");
        builder.append(numPmOldNotFollow);
        builder.append(", numPmOldFollow=");
        builder.append(numPmOldFollow);
        builder.append(", numPmTotal=");
        builder.append(numPmTotal);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append(", updateDate=");
        builder.append(updateDate);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
    
}
