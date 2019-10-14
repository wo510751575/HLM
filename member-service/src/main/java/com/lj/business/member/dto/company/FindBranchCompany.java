package com.lj.business.member.dto.company;

import java.io.Serializable;

public class FindBranchCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789761450486564988L; 
    /**
     * CODE .
     */
    private String code;

    /**
     * 分公司编号 .
     */
    private String companyNo;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindBranchCompany [code=");
		builder.append(code);
		builder.append(", companyNo=");
		builder.append(companyNo);
		builder.append("]");
		return builder.toString();
	}
}
