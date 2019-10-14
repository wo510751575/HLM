package com.lj.business.weixin.service;

import java.io.Serializable;

public class ImFriendsPicDownCallBackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8543385712795435422L;
	
	
	private String noWx;
	
	private String friendsId;
	
	private String imgStatus;
	
	private String imgUrls;
	
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

	 
	 

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImFriendsPicDownCallBackDto [noWx=");
		builder.append(noWx);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append(", imgStatus=");
		builder.append(imgStatus);
		builder.append(", imgUrls=");
		builder.append(imgUrls);
		builder.append("]");
		return builder.toString();
	}

	 
	 
	
	
	

}
