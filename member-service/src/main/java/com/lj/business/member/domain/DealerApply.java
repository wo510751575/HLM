package com.lj.business.member.domain;

import java.util.Date;

public class DealerApply {
    /**
     * CODE
     */
    private String code;

    /**
     * 经销商编号
     */
    private String dealerNo;

    /**
     * 经销商代码
     */
    private String dealerCode;

    /**
     * 经销商名称
     */
    private String dealerName;

    /**
     * 申请状态
     */
    private Byte applyStatus;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 省CODE
     */
    private String provinceCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市CODE
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 法人姓名
     */
    private String legalPersonName;

    /**
     * 法人身份证号
     */
    private String legalPersonId;

    /**
     * 法人身份证正面
     */
    private String legalPersonCardFront;

    /**
     * 法人身份证反面
     */
    private String legalPersonCardReverse;

    /**
     * 业务对接人
     */
    private String businessPerson;

    /**
     * 经销商负责人姓名
     */
    private String dealerResponsiblePerson;

    /**
     * 经销商负责人手机号
     */
    private String dealerResponsibleMobile;

    /**
     * 银行卡号
     */
    private String bankcardNo;

    /**
     * 银行CODE
     */
    private String bankCode;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 账户名称
     */
    private String custName;
    
    /**
     * 开户行所在省CODE
     */
    private String bankProvinceCode;
    
    /**
     * 开户行所在省名称	
     */
    private String bankProvinceName;
    
    /**
     * 开户行所在市CODE
     */
    private String bankCityCode;
    
    /**
     * 开户行所在市名称
     */
    private String bankCityName;
    
    /**
     * 开户支行
     */
    private String branch;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 审核人编号
     */
    private String auditNo;

    /**
     * 审核人名称
     */
    private String auditName;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 商户编号
     */
    private String merchantNo;

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

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDealerNo() {
        return dealerNo;
    }

    public void setDealerNo(String dealerNo) {
        this.dealerNo = dealerNo == null ? null : dealerNo.trim();
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode == null ? null : dealerCode.trim();
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName == null ? null : dealerName.trim();
    }

    public Byte getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Byte applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId == null ? null : legalPersonId.trim();
    }

    public String getLegalPersonCardFront() {
        return legalPersonCardFront;
    }

    public void setLegalPersonCardFront(String legalPersonCardFront) {
        this.legalPersonCardFront = legalPersonCardFront == null ? null : legalPersonCardFront.trim();
    }

    public String getLegalPersonCardReverse() {
        return legalPersonCardReverse;
    }

    public void setLegalPersonCardReverse(String legalPersonCardReverse) {
        this.legalPersonCardReverse = legalPersonCardReverse == null ? null : legalPersonCardReverse.trim();
    }

    public String getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(String businessPerson) {
        this.businessPerson = businessPerson == null ? null : businessPerson.trim();
    }

    public String getDealerResponsiblePerson() {
        return dealerResponsiblePerson;
    }

    public void setDealerResponsiblePerson(String dealerResponsiblePerson) {
        this.dealerResponsiblePerson = dealerResponsiblePerson == null ? null : dealerResponsiblePerson.trim();
    }

    public String getDealerResponsibleMobile() {
        return dealerResponsibleMobile;
    }

    public void setDealerResponsibleMobile(String dealerResponsibleMobile) {
        this.dealerResponsibleMobile = dealerResponsibleMobile == null ? null : dealerResponsibleMobile.trim();
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo == null ? null : bankcardNo.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo == null ? null : auditNo.trim();
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName == null ? null : auditName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
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

	public String getBankProvinceCode() {
		return bankProvinceCode;
	}

	public void setBankProvinceCode(String bankProvinceCode) {
		this.bankProvinceCode = bankProvinceCode;
	}

	public String getBankProvinceName() {
		return bankProvinceName;
	}

	public void setBankProvinceName(String bankProvinceName) {
		this.bankProvinceName = bankProvinceName;
	}

	public String getBankCityCode() {
		return bankCityCode;
	}

	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}

	public String getBankCityName() {
		return bankCityName;
	}

	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DealerApply [code=");
		builder.append(code);
		builder.append(", dealerNo=");
		builder.append(dealerNo);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
		builder.append(", dealerName=");
		builder.append(dealerName);
		builder.append(", applyStatus=");
		builder.append(applyStatus);
		builder.append(", applyTime=");
		builder.append(applyTime);
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
		builder.append(", bankcardNo=");
		builder.append(bankcardNo);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", bankProvinceCode=");
		builder.append(bankProvinceCode);
		builder.append(", bankProvinceName=");
		builder.append(bankProvinceName);
		builder.append(", bankCityCode=");
		builder.append(bankCityCode);
		builder.append(", bankCityName=");
		builder.append(bankCityName);
		builder.append(", branch=");
		builder.append(branch);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", auditNo=");
		builder.append(auditNo);
		builder.append(", auditName=");
		builder.append(auditName);
		builder.append(", auditTime=");
		builder.append(auditTime);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
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