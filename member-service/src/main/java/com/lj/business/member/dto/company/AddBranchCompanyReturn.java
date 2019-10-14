package com.lj.business.member.dto.company;

import java.io.Serializable;

public class AddBranchCompanyReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3333821823953015055L; 

	private String companyNo;

	/**
	 * @return the companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * @param companyNo the companyNo to set
	 */
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddBranchCompanyReturn [companyNo=");
		builder.append(companyNo);
		builder.append("]");
		return builder.toString();
	}
	
}
