package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ToDownloadPic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3966496957528552808L;

	private String noWx;

	private String friendsId;

	private String imgUrl;
	
	private String encKey;
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getEncKey() {
		return encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ToDownloadPic [noWx=");
		builder.append(noWx);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", imgUrl=");
		builder.append(imgUrl);
		builder.append("]");
		return builder.toString();
	}
	

}
