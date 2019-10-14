package com.lj.business.member.dto.service.person;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class UpdatePhoneInfo.
 */
public class UpdateServicePerson implements Serializable { 


	/**
	 * 
	 */
	private static final long serialVersionUID = 5496090512156785034L;

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
     * 分店编号
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
     * 创建时间.
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Long servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getHcLabel() {
		return hcLabel;
	}

	public void setHcLabel(String hcLabel) {
		this.hcLabel = hcLabel;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
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
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateServicePerson [code=");
		builder.append(code);
		builder.append(", personNo=");
		builder.append(personNo);
		builder.append(", personName=");
		builder.append(personName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", title=");
		builder.append(title);
		builder.append(", servicePrice=");
		builder.append(servicePrice);
		builder.append(", hcLabel=");
		builder.append(hcLabel);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append("]");
		return builder.toString();
	}

}
