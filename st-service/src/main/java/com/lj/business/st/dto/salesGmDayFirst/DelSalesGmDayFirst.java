package com.lj.business.st.dto.salesGmDayFirst;

import java.io.Serializable;

public class DelSalesGmDayFirst implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2588331303456963976L; 
	
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
		builder.append("DelSalesGmDayFirst [code=").append(code).append("]");
		return builder.toString();
	}
	
}
