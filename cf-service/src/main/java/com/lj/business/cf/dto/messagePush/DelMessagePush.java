package com.lj.business.cf.dto.messagePush;

import java.io.Serializable;

public class DelMessagePush implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2056376967335690896L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DelMessagePush [code=" + code + "]";
	}

}
