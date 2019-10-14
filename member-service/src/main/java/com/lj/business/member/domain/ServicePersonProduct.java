package com.lj.business.member.domain;

import java.util.Date;

public class ServicePersonProduct {
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
     * 成交价 .
     */
    private Long price;

    /**
     * 描述 .
     */
    private String description;

    /**
     * 封面地址 .
     */
    private String coverAddr;

    /**
     * 照片地址 .
     */
    private String imgAddr;

    /**
     * 服务选择CODE .
     */
    private String serviceChooseCode;

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
     * 成交价 .
     *
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 成交价 .
     *
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 描述 .
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述 .
     *
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 封面地址 .
     *
     */
    public String getCoverAddr() {
        return coverAddr;
    }

    /**
     * 封面地址 .
     *
     */
    public void setCoverAddr(String coverAddr) {
        this.coverAddr = coverAddr == null ? null : coverAddr.trim();
    }

    /**
     * 照片地址 .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 照片地址 .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServicePersonProduct [code=");
		builder.append(code);
		builder.append(", personNo=");
		builder.append(personNo);
		builder.append(", personName=");
		builder.append(personName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", coverAddr=");
		builder.append(coverAddr);
		builder.append(", imgAddr=");
		builder.append(imgAddr);
		builder.append(", serviceChooseCode=");
		builder.append(serviceChooseCode);
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