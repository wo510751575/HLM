package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ImFriendsInfoCallBackDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6707745978119068253L;

	private String friendsId;

	private String friendsCode;

	private String status;

	private String sendSuccessTime;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendSuccessTime() {
		return sendSuccessTime;
	}

	public void setSendSuccessTime(String sendSuccessTime) {
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
		builder.append("ImFriendsInfoCallBackDto [friendsId=");
		builder.append(friendsId);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sendSuccessTime=");
		builder.append(sendSuccessTime);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

}
