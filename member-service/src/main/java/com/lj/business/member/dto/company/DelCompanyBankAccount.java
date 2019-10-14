package com.lj.business.member.dto.company;

import java.io.Serializable;

public class DelCompanyBankAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556240738733201994L; 

	private String code;
	
	/**
     * 分公司编号 .
     */
    private String companyNo;

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
        builder.append("DelCompanyBankAccount [code=");
        builder.append(code);
        builder.append(", companyNo=");
        builder.append(companyNo);
        builder.append("]");
        return builder.toString();
    }
	
}
