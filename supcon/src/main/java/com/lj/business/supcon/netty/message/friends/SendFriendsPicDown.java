package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseDto;

public class SendFriendsPicDown extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4480123458857181003L;
	
	
	private String friendsId;
	
	/**
	 * 终端微信号
	 */
	private String noWx;
	
	/**
	 * 图片地址 
	 */
	private String imgUrl;
	
	/**
	 * 朋友圈秘钥
	 */
	private String encKey;

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getEncKey() {
		return encKey;
	}

	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsPicDown [friendsId=");
		builder.append(friendsId);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", imgUrl=");
		builder.append(imgUrl);
		builder.append(", encKey=");
		builder.append(encKey);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	

}
