package com.lj.business.st.dto.wxPmFollow;

import java.io.Serializable;
import java.util.Date;

public class FindWxPmFollowReportCompanyReturn implements Serializable { 

    /**
     * 
     */
    private static final long serialVersionUID = -2765981412166798325L;

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
        builder.append("FindWxPmFollowReportCompanyReturn [code=");
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
