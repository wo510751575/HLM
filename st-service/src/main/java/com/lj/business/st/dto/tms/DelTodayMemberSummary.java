package com.lj.business.st.dto.tms;

import java.io.Serializable;

public class DelTodayMemberSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7600339530225450102L; 

	private String code;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DelTodayMemberSummary [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
