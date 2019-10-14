package com.lj.business.st.dto.tms;

import java.io.Serializable;

public class FindTodayMemberSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 81971505126402574L; 

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
		builder.append("FindTodayMemberSummary [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
