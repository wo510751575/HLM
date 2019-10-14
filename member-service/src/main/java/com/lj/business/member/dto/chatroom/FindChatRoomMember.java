package com.lj.business.member.dto.chatroom;

import java.io.Serializable;

public class FindChatRoomMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7353480086428831109L; 

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
		builder.append("FindChatRoomMember [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
}
