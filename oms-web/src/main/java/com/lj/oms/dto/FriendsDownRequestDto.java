package com.lj.oms.dto;

import java.io.Serializable;

public class FriendsDownRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2760749568899748895L;
	
	
	/**
	 * 终端微信号
	 */
	private String noWx;
	
	
	/**
	 * 图片地址
	 */
	private String picUrl;
	

	/**
	 * 朋友圈ID
	 */
	private String friendsId;
	
	
	/**
	 * 图片位置
	 */
	private String index;


	public String getNoWx() {
		return noWx;
	}


	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getFriendsId() {
		return friendsId;
	}


	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FriendsDownRequestDto [noWx=");
		builder.append(noWx);
		builder.append(", picUrl=");
		builder.append(picUrl);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", index=");
		builder.append(index);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
