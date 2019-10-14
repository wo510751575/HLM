package com.lj.business.cm.dto.friends;

import java.io.Serializable;

public class FindFriendsVideoMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8534475577380159863L; 

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
		builder.append("FindFriendsVideoMaterial [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
