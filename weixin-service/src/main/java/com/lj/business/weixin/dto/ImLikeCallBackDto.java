package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

public class ImLikeCallBackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1003902038503096163L;

	private String friendsId;

	private String friendsCode;

	private String likesCode;

	private Date sendSuccessTime;
	
	private Integer status;
	
	private String remark;
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	public String getFriendsCode() {
		return friendsCode;
	}

	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
	}

	public String getLikesCode() {
		return likesCode;
	}

	public void setLikesCode(String likesCode) {
		this.likesCode = likesCode;
	}

	public Date getSendSuccessTime() {
		return sendSuccessTime;
	}

	public void setSendSuccessTime(Date sendSuccessTime) {
		this.sendSuccessTime = sendSuccessTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImLikeCallBackDto [friendsId=");
		builder.append(friendsId);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", likesCode=");
		builder.append(likesCode);
		builder.append(", sendSuccessTime=");
		builder.append(sendSuccessTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

}
