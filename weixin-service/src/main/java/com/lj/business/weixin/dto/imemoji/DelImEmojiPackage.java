package com.lj.business.weixin.dto.imemoji;

import java.io.Serializable;

public class DelImEmojiPackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3076363699875833701L; 

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
		builder.append("DelImEmojiPackage [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
