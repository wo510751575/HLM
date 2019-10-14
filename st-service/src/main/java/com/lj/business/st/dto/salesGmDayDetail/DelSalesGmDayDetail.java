package com.lj.business.st.dto.salesGmDayDetail;

import java.io.Serializable;

public class DelSalesGmDayDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -909199627520841607L;
	
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
		builder.append("DelSalesGmDayDetail [code=").append(code).append("]");
		return builder.toString();
	}
	
}
