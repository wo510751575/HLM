package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;

public class DelImEmoji implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4268636696289565064L; 

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
		builder.append("DelImEmoji [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
    
}
