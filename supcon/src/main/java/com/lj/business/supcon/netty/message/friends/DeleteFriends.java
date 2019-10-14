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
 * 类说明：删除朋友圈
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年7月29日
 */
public class DeleteFriends extends BaseDto {

	private static final long serialVersionUID = 6378573821378564615L;

	/**
	 * 微信朋友圈ID
	 */
	private String friendsId;
	
	/**
	 * 朋友圈CODE，服务器朋友圈CODE
	 */
	private String code;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeleteFriends [friendsId=");
		builder.append(friendsId);
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	
}
