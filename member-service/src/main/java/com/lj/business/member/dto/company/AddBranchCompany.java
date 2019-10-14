package com.lj.business.member.dto.company;

import java.io.Serializable;

import com.lj.business.member.emus.CompanyType;

/**
 * The Class AddBranchCompany.
 */
public class AddBranchCompany implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6795430630638167356L;

	/**
     * 分公司编号 .
     */
    private String companyNo;
    
    /** 经销商代码. */
    private String dealerCode;

    /**
     * 分公司名称 .
     */
    private String companyName;

    /**
     * 公司类型：1分公司、2经销商 .
     */
    private CompanyType companyType;
    
    /**
     * 省编号 .
     */
    private String provinceCode;
    
    /**
     * 省名称 .
     */
    private String provinceName;
    
    /**
     * 市编号 .
     */
    private String cityCode;
    
    /**
     * 市名称 .
     */
    private String cityName;
    
    /**
     * 营业执照.
     */
    private String businessLicense;
    
    /**
     * 法人姓名 .
     */
    private String legalPersonName;
    
    /**
     * 法人身份证号 .
     */
    private String legalPersonId;
    
    /**
     * 法人身份证正面.
     */
    private String legalPersonCardFront;

    /**
     * 法人身份证反面 .
     */
    private String legalPersonCardReverse;
    
    /**
     * 业务对接人 .
     */
    private String businessPerson;
    
    /**
     * 经销商负责人姓名 .
     */
    private String dealerResponsiblePerson;
    
    /**
     * 经销商负责人手机号 .
     */
    private String dealerResponsibleMobile;
    
    /**
     * 附件 .
     */
    private String attachment;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

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
     * Gets the 分公司编号 .
     *
     * @return the 分公司编号 
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * Sets the 分公司编号 .
     *
     * @param companyNo the new 分公司编号 
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * Gets the 分公司名称 .
     *
     * @return the 分公司名称 
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the 分公司名称 .
     *
     * @param companyName the new 分公司名称 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the 公司类型：1分公司、2经销商 .
     *
     * @return the 公司类型：1分公司、2经销商 
     */
    public CompanyType getCompanyType() {
        return companyType;
    }

    /**
     * Sets the 公司类型：1分公司、2经销商 .
     *
     * @param companyType the new 公司类型：1分公司、2经销商 
     */
    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

	/**
     * Gets the 省名称 .
     *
     * @return the 省名称 
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Sets the 省名称 .
     *
     * @param provinceName the new 省名称 
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * Gets the 市名称 .
     *
     * @return the 市名称 
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the 市名称 .
     *
     * @param cityName the new 市名称 
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Gets the 营业执照.
     *
     * @return the 营业执照
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * Sets the 营业执照.
     *
     * @param businessLicense the new 营业执照
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    /**
     * Gets the 法人姓名 .
     *
     * @return the 法人姓名 
     */
    public String getLegalPersonName() {
        return legalPersonName;
    }

    /**
     * Sets the 法人姓名 .
     *
     * @param legalPersonName the new 法人姓名 
     */
    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    /**
     * Gets the 法人身份证号 .
     *
     * @return the 法人身份证号 
     */
    public String getLegalPersonId() {
        return legalPersonId;
    }

    /**
     * Sets the 法人身份证号 .
     *
     * @param legalPersonId the new 法人身份证号 
     */
    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    /**
     * Gets the 法人身份证正面.
     *
     * @return the 法人身份证正面
     */
    public String getLegalPersonCardFront() {
        return legalPersonCardFront;
    }

    /**
     * Sets the 法人身份证正面.
     *
     * @param legalPersonCardFront the new 法人身份证正面
     */
    public void setLegalPersonCardFront(String legalPersonCardFront) {
        this.legalPersonCardFront = legalPersonCardFront;
    }

    /**
     * Gets the 法人身份证反面 .
     *
     * @return the 法人身份证反面 
     */
    public String getLegalPersonCardReverse() {
        return legalPersonCardReverse;
    }

    /**
     * Sets the 法人身份证反面 .
     *
     * @param legalPersonCardReverse the new 法人身份证反面 
     */
    public void setLegalPersonCardReverse(String legalPersonCardReverse) {
        this.legalPersonCardReverse = legalPersonCardReverse;
    }

    /**
     * Gets the 业务对接人 .
     *
     * @return the 业务对接人 
     */
    public String getBusinessPerson() {
        return businessPerson;
    }

    /**
     * Sets the 业务对接人 .
     *
     * @param businessPerson the new 业务对接人 
     */
    public void setBusinessPerson(String businessPerson) {
        this.businessPerson = businessPerson;
    }

    /**
     * Gets the 经销商负责人姓名 .
     *
     * @return the 经销商负责人姓名 
     */
    public String getDealerResponsiblePerson() {
        return dealerResponsiblePerson;
    }

    /**
     * Sets the 经销商负责人姓名 .
     *
     * @param dealerResponsiblePerson the new 经销商负责人姓名 
     */
    public void setDealerResponsiblePerson(String dealerResponsiblePerson) {
        this.dealerResponsiblePerson = dealerResponsiblePerson;
    }

    /**
     * Gets the 经销商负责人手机号 .
     *
     * @return the 经销商负责人手机号 
     */
    public String getDealerResponsibleMobile() {
        return dealerResponsibleMobile;
    }

    /**
     * Sets the 经销商负责人手机号 .
     *
     * @param dealerResponsibleMobile the new 经销商负责人手机号 
     */
    public void setDealerResponsibleMobile(String dealerResponsibleMobile) {
        this.dealerResponsibleMobile = dealerResponsibleMobile;
    }

    /**
     * Gets the 附件 .
     *
     * @return the 附件 
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Sets the 附件 .
     *
     * @param attachment the new 附件 
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * Gets the 商户编号 .
     *
     * @return the 商户编号 
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * Sets the 商户编号 .
     *
     * @param merchantNo the new 商户编号 
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * Gets the 商户名称 .
     *
     * @return the 商户名称 
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets the 商户名称 .
     *
     * @param merchantName the new 商户名称 
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * Gets the 备注 .
     *
     * @return the 备注 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the 备注 .
     *
     * @param remark the new 备注 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets the 备注 .
     *
     * @return the 备注 
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * Sets the 备注 .
     *
     * @param remark2 the new 备注 
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * Gets the 备注 .
     *
     * @return the 备注 
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * Sets the 备注 .
     *
     * @param remark3 the new 备注 
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * Gets the 备注 .
     *
     * @return the 备注 
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * Sets the 备注 .
     *
     * @param remark4 the new 备注 
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    /**
     * Gets the 省编号 .
     *
     * @return the 省编号 
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the 省编号 .
     *
     * @param provinceCode the new 省编号 
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * Gets the 市编号 .
     *
     * @return the 市编号 
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the 市编号 .
     *
     * @param cityCode the new 市编号 
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Gets the 经销商代码.
     *
     * @return the 经销商代码
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * Sets the 经销商代码.
     *
     * @param dealerCode the new 经销商代码
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddBranchCompany [companyNo=");
		builder.append(companyNo);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", companyType=");
		builder.append(companyType);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", businessLicense=");
		builder.append(businessLicense);
		builder.append(", legalPersonName=");
		builder.append(legalPersonName);
		builder.append(", legalPersonId=");
		builder.append(legalPersonId);
		builder.append(", legalPersonCardFront=");
		builder.append(legalPersonCardFront);
		builder.append(", legalPersonCardReverse=");
		builder.append(legalPersonCardReverse);
		builder.append(", businessPerson=");
		builder.append(businessPerson);
		builder.append(", dealerResponsiblePerson=");
		builder.append(dealerResponsiblePerson);
		builder.append(", dealerResponsibleMobile=");
		builder.append(dealerResponsibleMobile);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
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
