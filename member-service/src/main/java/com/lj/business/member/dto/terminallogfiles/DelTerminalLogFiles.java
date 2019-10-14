package com.lj.business.member.dto.terminallogfiles;

import java.io.Serializable;

public class DelTerminalLogFiles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6795980277319682678L; 

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
		builder.append("DelTerminalLogFiles [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
    
    
}
