package com.lj.business.api.dto.friend;

public class AddLikesCommentRequestDto {

	private String snsId;
	
	private String msgId;
	
	
	private String direction;
	
	private String id;

	
	private String commentTime;
	
	private String commentType;
	
	private String currUserName;
	
	private String currNickName;
	
    private String friendUserName; // 朋友userName
	 
	 
	private String friendNickName;  // 朋友昵称
	
	private String comment;
	private String commentSvrId;
	
	public String getCommentSvrId() {
		return commentSvrId;
	}

	public void setCommentSvrId(String commentSvrId) {
		this.commentSvrId = commentSvrId;
	}
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	 
	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getCurrUserName() {
		return currUserName;
	}

	public void setCurrUserName(String currUserName) {
		this.currUserName = currUserName;
	}

	public String getCurrNickName() {
		return currNickName;
	}

	public void setCurrNickName(String currNickName) {
		this.currNickName = currNickName;
	}

	 

	public String getFriendUserName() {
		return friendUserName;
	}

	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}

	public String getFriendNickName() {
		return friendNickName;
	}

	public void setFriendNickName(String friendNickName) {
		this.friendNickName = friendNickName;
	}

	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddLikesCommentRequestDto [snsId=");
		builder.append(snsId);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", direction=");
		builder.append(direction);
		builder.append(", id=");
		builder.append(id);
		builder.append(", commentTime=");
		builder.append(commentTime);
		builder.append(", commentType=");
		builder.append(commentType);
		builder.append(", currUserName=");
		builder.append(currUserName);
		builder.append(", currNickName=");
		builder.append(currNickName);
		builder.append(", friendUserName=");
		builder.append(friendUserName);
		builder.append(", friendNickName=");
		builder.append(friendNickName);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}

	 
	
	


}
