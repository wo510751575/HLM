package com.lj.business.cm.dto.friends;

import java.io.Serializable;

public class DelFriendsVideoMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6637036617202562733L; 

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
		builder.append("DelFriendsVideoMaterial [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
