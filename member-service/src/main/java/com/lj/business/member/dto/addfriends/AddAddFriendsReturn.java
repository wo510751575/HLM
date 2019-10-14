package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class AddAddFriendsReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6441097273345017188L; 

	/**
	 * code
	 */
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
		builder.append("AddAddFriendsReturn [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
