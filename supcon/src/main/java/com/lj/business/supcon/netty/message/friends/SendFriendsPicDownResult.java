package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseResponse;

public class SendFriendsPicDownResult extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5120671212220710407L;
	/**
	 * 朋友圈ID
	 */
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
	 * 原图路径
	 */
	private String imgStatus;

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

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsPicDownResult [friendsId=");
		builder.append(friendsId);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", imgUrl=");
		builder.append(imgUrl);
		builder.append(", imgStatus=");
		builder.append(imgStatus);
		builder.append("]");
		return builder.toString();
	}

}
