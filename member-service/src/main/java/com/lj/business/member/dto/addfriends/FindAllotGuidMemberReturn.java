package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class FindAllotGuidMemberReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1368637891232355934L; 

	/**
	 * 导购编号
	 */
	private String memberNo; 
	
	/**
	 * 导购名称
	 */
	private String memberName; 
	
	/**
	 * 微信号
	 */
	private String noWx; 
	
	/**
	 * 手机号
	 */
	private String mobile; 
	
	/**
	 * 头像地址
	 */
	private String headAddress;

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAllotGuidMemberReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append("]");
		return builder.toString();
	} 
	
}

