package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

public class AddLikesInfoMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1704673576481761956L;

	/**
	 * 终端号
	 */
	

	/**
	 * 设备号
	 */
	private String imei;

	/**
	 * 点赞导购
	 */
	private String memberNoGm;

	/**
	 * 被点赞客户
	 */
	private String memberNo;

	/**
	 * 被点赞朋友圈
	 */
	private String friendsId;

	/**
	 * 点赞CODE
	 */
	private String likesCode;

	/**
	 * 点赞微信
	 */
	private String noWxShop;

	/**
	 * 点赞微信昵称
	 */
	private String nickName;

	/**
	 * 被点赞微信
	 */
	private String toNoWx;

	/**
	 * 被点赞微信昵称
	 */
	private String toNickName;


	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

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

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getLikesCode() {
		return likesCode;
	}

	public void setLikesCode(String likesCode) {
		this.likesCode = likesCode;
	}

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getToNoWx() {
		return toNoWx;
	}

	public void setToNoWx(String toNoWx) {
		this.toNoWx = toNoWx;
	}

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddLikesInfoMessage [");
		builder.append("imei=");
		builder.append(imei);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", likesCode=");
		builder.append(likesCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", toNoWx=");
		builder.append(toNoWx);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
