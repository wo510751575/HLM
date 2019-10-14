package com.lj.business.member.dto.company;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCompanyBankAccountPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2977611067547725305L; 

	
	/**
     * 经销商Code .
     */
    private String branchCompanyCode;
	
    /**
     * 分公司编号 .
     */
    private String companyNo;

    /**
     * 分公司名称 .
     */
    private String companyName;

    /**
     * 银行CODE .
     */
    private String bankCode;

    /**
     * 默认账户 .
     */
    private Integer isDefault;

    /**
     * 所属商户编号 .
     */
    private String merchantNo;

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

	public String getBranchCompanyCode() {
        return branchCompanyCode;
    }

    public void setBranchCompanyCode(String branchCompanyCode) {
        this.branchCompanyCode = branchCompanyCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindCompanyBankAccountPage [branchCompanyCode=");
        builder.append(branchCompanyCode);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append(", companyName=");
        builder.append(companyName);
        builder.append(", bankCode=");
        builder.append(bankCode);
        builder.append(", isDefault=");
        builder.append(isDefault);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append("]");
        return builder.toString();
    }

}
