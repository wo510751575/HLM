package com.lj.business.cf.dto.clientInvite;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class FindClientInvite.
 */
public class FindClientInvite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5985469290136075180L; 
	
	/** The code. */
	private String code;
	
	/** 导购编号. */
	private String  memberNoGm;
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the member no gm.
	 *
	 * @return the member no gm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the member no gm.
	 *
	 * @param memberNoGm the member no gm
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
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
		builder.append("FindClientInvite [code=").append(code).append("]");
		return builder.toString();
	}
	
}
