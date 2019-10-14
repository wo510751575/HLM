package com.lj.business.member.dto.company;

import java.io.Serializable;
import java.util.Date;

public class FindCompanyBankAccountPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2065994181237092751L; 
	/**
     * CODE .
     */
    private String code;

    /**
     * 分公司编号 .
     */
    private String companyNo;

    /**
     * 分公司名称 .
     */
    private String companyName;

    /**
     * 银行卡号 .
     */
    private String bankcardNo;

    /**
     * 银行CODE .
     */
    private String bankCode;

    /**
     * 银行名称 .
     */
    private String bankName;

    /**
     * 客户名称 .
     */
    private String custName;

    /**
     * 开户行所在省CODE
     */
    private String provinceCode;
    
    /**
     * 开户行所在省名称	
     */
    private String provinceName;
    
    /**
     * 开户行所在市CODE
     */
    private String cityCode;
    
    /**
     * 开户行所在市名称
     */
    private String cityName;

    /**
     * 开户支行 .
     */
    private String branch;

    /**
     * 默认账户 .
     */
    private Integer isDefault;

    /**
     * 所属商户编号 .
     */
    private String merchantNo;

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
     * 分公司编号 .
     *
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * 分公司编号 .
     *
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    /**
     * 分公司名称 .
     *
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 分公司名称 .
     *
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 银行卡号 .
     *
     */
    public String getBankcardNo() {
        return bankcardNo;
    }

    /**
     * 银行卡号 .
     *
     */
    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo == null ? null : bankcardNo.trim();
    }

    /**
     * 银行CODE .
     *
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 银行CODE .
     *
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     * 银行名称 .
     *
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 银行名称 .
     *
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 客户名称 .
     *
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 客户名称 .
     *
     */
    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    /**
     * 开户支行 .
     *
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 开户支行 .
     *
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 默认账户 .
     *
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 默认账户 .
     *
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 所属商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 所属商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
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
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
		builder.append("FindCompanyBankAccountPageReturn [code=");
		builder.append(code);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", bankcardNo=");
		builder.append(bankcardNo);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", provinceName=");
		builder.append(provinceName);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityName=");
		builder.append(cityName);
		builder.append(", branch=");
		builder.append(branch);
		builder.append(", isDefault=");
		builder.append(isDefault);
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
