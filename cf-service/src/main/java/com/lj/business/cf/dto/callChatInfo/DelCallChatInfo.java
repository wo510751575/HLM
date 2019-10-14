package com.lj.business.cf.dto.callChatInfo;

import java.io.Serializable;

public class DelCallChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5647967973103530206L;
	
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
		return "DelCallChatInfo [code=" + code + "]";
	}

}
