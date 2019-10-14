package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

public class AddFriendsNotifyMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4852725285860179065L;
	
	
	
	private String friendsId;
	
	
	private String friendsCode;
	
	
	private String memberNoGm;
	
	
	private String noWx;
	
	
	private String imgUrls;
	
	
	private String imgStatus;
	
	
	
	
	


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


	public String getFriendsId() {
		return friendsId;
	}


	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}


	public String getMemberNoGm() {
		return memberNoGm;
	}


	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}


	public String getNoWx() {
		return noWx;
	}


	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	

	public String getFriendsCode() {
		return friendsCode;
	}


	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddFriendsNotifyMessage [friendsId=");
		builder.append(friendsId);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", imgUrls=");
		builder.append(imgUrls);
		builder.append(", imgStatus=");
		builder.append(imgStatus);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
