package com.lj.business.weixin.dto.publicaccount;

import java.io.Serializable;

public class DelWxPublicAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4755758348015489901L; 

	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DelWxPublicAccount [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
