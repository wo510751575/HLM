/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：发送朋友圈评论
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月21日
 */
public class SendFriendsComment extends BaseDto {

	private static final long serialVersionUID = -3329225473725208624L;
	
	/**
	 * 微信朋友圈ID
	 */
	private String friendsId;
	
	/**
	 * 评论CODE,服务器评论CODE
	 */
	private String code;

	/**
	 * 被评论朋友的微信号
	 */
	private String noWx;
	
	/**
	 * 被评论朋友的微信昵称
	 */
	private String nickName;
	
	/**
	 * 回复人微信号
	 */
	private String toNoWx;
	
	/**
	 * 回复人微信昵称
	 */
	private String toNickName;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 时间戳，单位：毫秒
	 */
	private Long timestamp;
	
	
	private String commentSvrId;
	
	

	 

	public String getCommentSvrId() {
		return commentSvrId;
	}

	public void setCommentSvrId(String commentSvrId) {
		this.commentSvrId = commentSvrId;
	}

	/**
	 * @return the friendsId
	 */
	public String getFriendsId() {
		return friendsId;
	}

	/**
	 * @param friendsId the friendsId to set
	 */
	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the toNoWx
	 */
	public String getToNoWx() {
		return toNoWx;
	}

	/**
	 * @param toNoWx the toNoWx to set
	 */
	public void setToNoWx(String toNoWx) {
		this.toNoWx = toNoWx;
	}

	/**
	 * @return the toNickName
	 */
	public String getToNickName() {
		return toNickName;
	}

	/**
	 * @param toNickName the toNickName to set
	 */
	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendFriendsComment [friendsId=");
		builder.append(friendsId);
		builder.append(", code=");
		builder.append(code);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", toNoWx=");
		builder.append(toNoWx);
		builder.append(", toNickName=");
		builder.append(toNickName);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", commentSvrId=");
		builder.append(commentSvrId);
		builder.append("]");
		return builder.toString();
	}
	
}
