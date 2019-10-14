package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class FindOtherAllotGuidMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1368637891232355934L; 

	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 客户微信
	 */
	private String noWx;
	
	/**
	 * 导购微信
	 */
	private String noWxGm;

	/**
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}


	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindOtherAllotGuidMember [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}

}
