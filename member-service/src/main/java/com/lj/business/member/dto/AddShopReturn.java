package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * The Class AddShopReturn.
 */
public class AddShopReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3795832192316338939L; 
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
		builder.append("AddShopReturn [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
    
}
