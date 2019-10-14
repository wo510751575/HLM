package com.lj.business.api.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindFriendsMessageRequestDto extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5439164910368814324L;
	
	
	private String friendsCode;
	
	private String noWxShop;
	
	private String memberNoGm;
	private String beginDate;
	
	private String endDate;
	
	private String memberName;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getFriendsCode() {
		return friendsCode;
	}

	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindFriendsMessageRequestDto [friendsCode=");
		builder.append(friendsCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
