package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.List;

public class AddDealerApply implements Serializable { 

	private static final long serialVersionUID = 6261170842053939914L;

	/**
     * 经销商名称
     */
    private String dealerName;
	
	/**
     * 经销商代码
     */
    private String dealerCode;

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
     * 商户编号
     */
    private String merchantNo;
    
    /**
     * 终端列表
     */
    private List<AddDealerApplyShop> shopList;

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

	public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public List<AddDealerApplyShop> getShopList() {
        return shopList;
    }

    public void setShopList(List<AddDealerApplyShop> shopList) {
        this.shopList = shopList;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddDealerApply [dealerName=");
		builder.append(dealerName);
		builder.append(", dealerCode=");
		builder.append(dealerCode);
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
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", shopList=");
		builder.append(shopList);
		builder.append("]");
		return builder.toString();
	}

}
