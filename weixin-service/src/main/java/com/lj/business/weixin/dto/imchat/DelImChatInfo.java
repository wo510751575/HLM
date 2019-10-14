package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class DelImChatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5906329824362762987L; 

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
		return "DelImChatInfo [code=" + code + "]";
	}
    
    
}
