/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.friends;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：删除朋友圈结果返回
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
public class DeleteFriendsResult extends BaseResponse {

	private static final long serialVersionUID = 7755172127993154037L;

	/**
	 * 朋友圈CODE，服务器朋友圈CODE
	 */
	private String code;
	
	/**
	 * 微信朋友圈ID，删除成功后返回对应的微信朋友圈ID
	 */
	private String friendsId;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeleteFriendsResult [code=");
		builder.append(code);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append("]");
		return builder.toString();
	}
}
