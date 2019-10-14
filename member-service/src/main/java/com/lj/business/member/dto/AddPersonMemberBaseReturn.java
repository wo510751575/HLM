package com.lj.business.member.dto;

import java.io.Serializable;

import com.lj.business.member.domain.PersonMemberBase;

/**
 * The Class AddPersonMemberBaseReturn.
 */
public class AddPersonMemberBaseReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9080199812578568737L;
	
	/**
     * 客户编号 .
     */
    private String memberNo;
	
	/** The person member base. */
	PersonMemberBase personMemberBase;

	/**
	 * Gets the person member base.
	 *
	 * @return the person member base
	 */
	public PersonMemberBase getPersonMemberBase() {
		return personMemberBase;
	}

	/**
	 * Sets the person member base.
	 *
	 * @param personMemberBase the person member base
	 */
	public void setPersonMemberBase(PersonMemberBase personMemberBase) {
		this.personMemberBase = personMemberBase;
	}
	
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "AddPersonMemberBaseReturn [memberNo=" + memberNo + "]";
	}
	

}
