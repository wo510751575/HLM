package com.lj.business.member.domain;

import java.util.Date;

public class ServicePerson {
    /**
     * CODE .
     */
    private String code;

    /**
     * 人员编号 .
     */
    private String personNo;

    /**
     * 人员姓名 .
     */
    private String personName;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 服务选择CODE .
     */
    private String serviceChooseCode;
    
    /**
     * 头像地址
     */
    private String headAddress;

    /**
     * 职称 .
     */
    private String title;

    /**
     * 服务价格 .
     */
    private Long servicePrice;

    /**
     * 标签，多个用英文逗号,隔开 .
     */
    private String hcLabel;

    /**
     * 简介 .
     */
    private String summary;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;

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
     * 人员编号 .
     *
     */
    public String getPersonNo() {
        return personNo;
    }

    /**
     * 人员编号 .
     *
     */
    public void setPersonNo(String personNo) {
        this.personNo = personNo == null ? null : personNo.trim();
    }

    /**
     * 人员姓名 .
     *
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 人员姓名 .
     *
     */
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
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
     * 商户名称 .
     *
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 商户名称 .
     *
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * 服务选择CODE .
     *
     */
    public String getServiceChooseCode() {
        return serviceChooseCode;
    }

    /**
     * 服务选择CODE .
     *
     */
    public void setServiceChooseCode(String serviceChooseCode) {
        this.serviceChooseCode = serviceChooseCode == null ? null : serviceChooseCode.trim();
    }

    /**
     * 头像地址
     */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
     * 头像地址
     */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
     * 职称 .
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * 职称 .
     *
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 服务价格 .
     *
     */
    public Long getServicePrice() {
        return servicePrice;
    }

    /**
     * 服务价格 .
     *
     */
    public void setServicePrice(Long servicePrice) {
        this.servicePrice = servicePrice;
    }

    /**
     * 标签，多个用英文逗号,隔开 .
     *
     */
    public String getHcLabel() {
        return hcLabel;
    }

    /**
     * 标签，多个用英文逗号,隔开 .
     *
     */
    public void setHcLabel(String hcLabel) {
        this.hcLabel = hcLabel == null ? null : hcLabel.trim();
    }

    /**
     * 简介 .
     *
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 简介 .
     *
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServicePerson [code=").append(code);
        builder.append(",personNo=").append(personNo); 
        builder.append(",personName=").append(personName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",serviceChooseCode=").append(serviceChooseCode); 
        builder.append(",headAddress=").append(headAddress); 
        builder.append(",title=").append(title); 
        builder.append(",servicePrice=").append(servicePrice); 
        builder.append(",hcLabel=").append(hcLabel); 
        builder.append(",summary=").append(summary); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}