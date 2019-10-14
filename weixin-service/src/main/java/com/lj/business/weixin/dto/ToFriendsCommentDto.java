package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ToFriendsCommentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2134004150414143298L;

	/**
	 * 评论导购会员号
	 */
	private String memberNoGm;

	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 终端微信号
	 */
	private String shopWxNo;
	/**
	 * 被评论微信号
	 */
	private String toWxNo;
	/**
	 * 被评论微信名
	 */
	private String toWxName;
	/**
	 * 被评论朋友圈信息
	 */
	private String toFriendsId;
	/**
	 * 回复评论ID
	 */
	private String toCommentCode;
	/**
	 * 评论内容
	 */
	private String toConent;

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}


	public String getToWxNo() {
		return toWxNo;
	}

	public void setToWxNo(String toWxNo) {
		this.toWxNo = toWxNo;
	}

	public String getToWxName() {
		return toWxName;
	}

	public void setToWxName(String toWxName) {
		this.toWxName = toWxName;
	}

	public String getToFriendsId() {
		return toFriendsId;
	}

	public void setToFriendsId(String toFriendsId) {
		this.toFriendsId = toFriendsId;
	}

	public String getToCommentCode() {
		return toCommentCode;
	}

	public void setToCommentCode(String toCommentCode) {
		this.toCommentCode = toCommentCode;
	}

	public String getToConent() {
		return toConent;
	}

	public void setToConent(String toConent) {
		this.toConent = toConent;
	}

	public String getShopWxNo() {
		return shopWxNo;
	}

	public void setShopWxNo(String shopWxNo) {
		this.shopWxNo = shopWxNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ToFriendsCommentDto [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", shopWxNo=");
		builder.append(shopWxNo);
		builder.append(", toWxNo=");
		builder.append(toWxNo);
		builder.append(", toWxName=");
		builder.append(toWxName);
		builder.append(", toFriendsId=");
		builder.append(toFriendsId);
		builder.append(", toCommentCode=");
		builder.append(toCommentCode);
		builder.append(", toConent=");
		builder.append(toConent);
		builder.append("]");
		return builder.toString();
	}

}
