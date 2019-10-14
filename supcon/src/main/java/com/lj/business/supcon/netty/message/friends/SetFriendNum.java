/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 设置好友添加数量
 * @author wo510
 *
 */
public class SetFriendNum extends BaseDto {

	private static final long serialVersionUID = 1239571030988049419L;
	
	/**
	 * false关闭、true打开
	 */
	private boolean uploadFriends;

	/**
	 * @return the uploadFriends
	 */
	public boolean isUploadFriends() {
		return uploadFriends;
	}

	/**
	 * @param uploadFriends the uploadFriends to set
	 */
	public void setUploadFriends(boolean uploadFriends) {
		this.uploadFriends = uploadFriends;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadFriendsFlagCommand [uploadFriends=");
		builder.append(uploadFriends);
		builder.append("]");
		return builder.toString();
	}
}
