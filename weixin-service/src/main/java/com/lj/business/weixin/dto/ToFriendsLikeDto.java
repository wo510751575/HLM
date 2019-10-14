package com.lj.business.weixin.dto;

import java.io.Serializable;
/**
 * 点赞
 * @author ldq
 *
 */
public class ToFriendsLikeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7272007118258866466L;
	
	
	private  String toWxNo;
	
	private String toNickName;
	
	private String toFriendsId;
	
	private String memberNoGm;
	
	private String memberNo;
	
	private String memberName;
	
	
	
	private String shopWxNo;
	
	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getShopWxNo() {
		return shopWxNo;
	}

	public void setShopWxNo(String shopWxNo) {
		this.shopWxNo = shopWxNo;
	}

	public String getToWxNo() {
		return toWxNo;
	}

	public void setToWxNo(String toWxNo) {
		this.toWxNo = toWxNo;
	}

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	public String getToFriendsId() {
		return toFriendsId;
	}

	public void setToFriendsId(String toFriendsId) {
		this.toFriendsId = toFriendsId;
	}
 
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ToFriendsLikeDto [toWxNo=");
		builder.append(toWxNo);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append(", toFriendsId=");
		builder.append(toFriendsId);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", shopWxNo=");
		builder.append(shopWxNo);
		builder.append("]");
		return builder.toString();
	}
	
	

}
