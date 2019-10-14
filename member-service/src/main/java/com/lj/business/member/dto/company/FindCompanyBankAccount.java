package com.lj.business.member.dto.company;

import java.io.Serializable;

public class FindCompanyBankAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6519798459362682254L;

	private String code;
	
	private String companyNo;
	
	public FindCompanyBankAccount() {
		super();
	}
	
	public FindCompanyBankAccount(String code) {
		super();
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindCompanyBankAccount [code=");
        builder.append(code);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append("]");
        return builder.toString();
    }

}
