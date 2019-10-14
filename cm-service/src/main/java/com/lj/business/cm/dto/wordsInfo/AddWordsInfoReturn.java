package com.lj.business.cm.dto.wordsInfo;

import java.io.Serializable;

public class AddWordsInfoReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8150146039975634658L; 

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
		builder.append("AddWordsInfoReturn [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
}
