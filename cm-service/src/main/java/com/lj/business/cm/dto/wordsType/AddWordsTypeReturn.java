package com.lj.business.cm.dto.wordsType;

import java.io.Serializable;

public class AddWordsTypeReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 478653175401187532L; 

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "AddWordsTypeReturn [code=" + code + "]";
	}
	
}
