package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseResponse;

public class SendFriendsPicResultNotify extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956778200168858307L;
	
	
	private String friendsId;
	
	private String noWx;
	
	private String friendsCode;
	
	
	private String imgUrls;
	
	private String imgStatus;
	

	

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getFriendsCode() {
		return friendsCode;
	}

	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

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

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsPicResultNotify [friendsId=");
		builder.append(friendsId);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", imgUrls=");
		builder.append(imgUrls);
		builder.append(", imgStatus=");
		builder.append(imgStatus);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
