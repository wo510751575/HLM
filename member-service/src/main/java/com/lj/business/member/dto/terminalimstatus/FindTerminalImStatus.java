package com.lj.business.member.dto.terminalimstatus;

import java.io.Serializable;

public class FindTerminalImStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754446530070452575L; 

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
		builder.append("FindTerminalImStatus [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
