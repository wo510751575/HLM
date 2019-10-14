package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.Date;

public class FindBranchCompanyPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5228594430338579668L; 
    
	/**
     * CODE .
     */
    private String code;

    /**
     * 分公司编号 .
     */
    private String companyNo;
    
    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 分公司名称 .
     */
    private String companyName;

    /**
     * 状态：NORMAL正常、CANCEL注销
              .
     */
    private String status;

    /**
     * 公司类型：1分公司、2经销商 .
     */
    private Integer companyType;
    
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
     * 注册时间 .
     */
    private Date registerTime;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getLegalPersonCardFront() {
        return legalPersonCardFront;
    }

    public void setLegalPersonCardFront(String legalPersonCardFront) {
        this.legalPersonCardFront = legalPersonCardFront;
    }

    public String getLegalPersonCardReverse() {
        return legalPersonCardReverse;
    }

    public void setLegalPersonCardReverse(String legalPersonCardReverse) {
        this.legalPersonCardReverse = legalPersonCardReverse;
    }

    public String getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(String businessPerson) {
        this.businessPerson = businessPerson;
    }

    public String getDealerResponsiblePerson() {
        return dealerResponsiblePerson;
    }

    public void setDealerResponsiblePerson(String dealerResponsiblePerson) {
        this.dealerResponsiblePerson = dealerResponsiblePerson;
    }

    public String getDealerResponsibleMobile() {
        return dealerResponsibleMobile;
    }

    public void setDealerResponsibleMobile(String dealerResponsibleMobile) {
        this.dealerResponsibleMobile = dealerResponsibleMobile;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBranchCompanyPageReturn [code=");
		builder.append(code);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", status=");
		builder.append(status);
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
		builder.append(", registerTime=");
		builder.append(registerTime);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
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
