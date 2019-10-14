package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class MemberNoAndShopNoPageDto extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5184536612793858194L;
	/**
	 * 客户编号
	 */
	private String memberNo;
	/**
	 * 导购编号
	 */
	private String guidNo;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	

	public String getGuidNo() {
		return guidNo;
	}

	public void setGuidNo(String guidNo) {
		this.guidNo = guidNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberNoAndShopNoPageDto [memberNo=");
		builder.append(memberNo);
		builder.append(", guidNo=");
		builder.append(guidNo);
		builder.append("]");
		return builder.toString();
	}

}
