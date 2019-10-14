package com.lj.business.weixin.dto;

import java.io.Serializable;

public class ImLikeRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -359780147050883875L;
	
	
	private String userName;
	
	private String nickName;
	
	private String isRead;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImLikeRequestDto [userName=");
		builder.append(userName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", isRead=");
		builder.append(isRead);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
