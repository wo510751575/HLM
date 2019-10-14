package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

public class AddCommentInfoMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2182713530407253739L;
	/**
	 * 终端号
	 */
	

	/**
	 * 设备号
	 */
	private String imei;
	/**
	 * 评论导购
	 */
	private String memberNoGm;

	private String friendsId;

	private String commentCode;

	private String noWxShop;
	/**
	 * 微信昵称
	 */
	private String nickName;

	/**
	 * 回复人微信号
	 */
	private String toNoWx;

	/**
	 * 回复人微信昵称
	 */
	private String toNickName;
	/**
	 * 内容
	 */
	private String content;
	
	private String commentSerId;


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

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 

	public String getCommentSerId() {
		return commentSerId;
	}

	public void setCommentSerId(String commentSerId) {
		this.commentSerId = commentSerId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddCommentInfoMessage [");
		builder.append("imei=");
		builder.append(imei);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", commentCode=");
		builder.append(commentCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", toNoWx=");
		builder.append(toNoWx);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append(", content=");
		builder.append(content);
		builder.append(", commentSerId=");
		builder.append(commentSerId);
		builder.append("]");
		return builder.toString();
	}
	
}
