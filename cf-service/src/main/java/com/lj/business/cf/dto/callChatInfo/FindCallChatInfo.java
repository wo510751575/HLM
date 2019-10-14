package com.lj.business.cf.dto.callChatInfo;

import java.io.Serializable;

public class FindCallChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 943368788154166997L;
	
	/**
	 * code .
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
		return "FindCallChatInfo [code=" + code + "]";
	}
	
}
