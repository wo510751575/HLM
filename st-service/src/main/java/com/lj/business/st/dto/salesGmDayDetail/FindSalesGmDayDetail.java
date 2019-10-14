package com.lj.business.st.dto.salesGmDayDetail;

import java.io.Serializable;

public class FindSalesGmDayDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2312729430268188302L; 
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSalesGmDayDetail [code=").append(code).append("]");
		return builder.toString();
	}
	
}
