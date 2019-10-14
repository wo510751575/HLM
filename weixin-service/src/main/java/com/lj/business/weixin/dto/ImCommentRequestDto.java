package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ImCommentRequestDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486178216262367263L;
	private String commentTime;
	private String content;
	private String isRead;
	private String nickName;
	private String toNickName;
	private String toUserName;
	private String userName;
	/**
	 * 中控字段-对应后台数据库字段commentSerId
	 */
	private String commentSvrID;		
	
	
	public String getCommentSvrID() {
		return commentSvrID;
	}
	public void setCommentSvrID(String commentSvrID) {
		this.commentSvrID = commentSvrID;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getToNickName() {
		return toNickName;
	}
	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImCommentRequestDto [commentTime=");
		builder.append(commentTime);
		builder.append(", content=");
		builder.append(content);
		builder.append(", isRead=");
		builder.append(isRead);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append(", toUserName=");
		builder.append(toUserName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", commentSvrID=");
		builder.append(commentSvrID);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
