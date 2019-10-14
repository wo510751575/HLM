package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

public class ImCommentCallBackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7077207925238552650L;

	private String friendsId;

	private String friendsCode;

	private String commentsCode;

	private Date sendSuccessTime;

	private String status;
	
	private String remark;

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

	public String getCommentsCode() {
		return commentsCode;
	}

	public void setCommentsCode(String commentsCode) {
		this.commentsCode = commentsCode;
	}

	public Date getSendSuccessTime() {
		return sendSuccessTime;
	}

	public void setSendSuccessTime(Date sendSuccessTime) {
		this.sendSuccessTime = sendSuccessTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		builder.append("ImCommentCallBackDto [friendsId=");
		builder.append(friendsId);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", commentsCode=");
		builder.append(commentsCode);
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
