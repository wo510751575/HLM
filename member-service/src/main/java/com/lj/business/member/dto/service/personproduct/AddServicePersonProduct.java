package com.lj.business.member.dto.service.personproduct;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class AddServicePerson.
 * 
 */
public class AddServicePersonProduct implements Serializable { 


	/**
	 * 
	 */
	private static final long serialVersionUID = 2718000021861127063L;

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
     * 成交价
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
     * 服务选择code .
     */
    private String serviceChooseCode;

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoverAddr() {
		return coverAddr;
	}

	public void setCoverAddr(String coverAddr) {
		this.coverAddr = coverAddr;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getServiceChooseCode() {
		return serviceChooseCode;
	}

	public void setServiceChooseCode(String serviceChooseCode) {
		this.serviceChooseCode = serviceChooseCode;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddServicePersonProduct [code=");
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
