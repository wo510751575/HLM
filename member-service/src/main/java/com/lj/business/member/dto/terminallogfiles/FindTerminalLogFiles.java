package com.lj.business.member.dto.terminallogfiles;

import java.io.Serializable;

public class FindTerminalLogFiles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7681724467330524730L; 

	/**
     * CODE .
     */
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
		builder.append("FindTerminalLogFiles [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
    
}
