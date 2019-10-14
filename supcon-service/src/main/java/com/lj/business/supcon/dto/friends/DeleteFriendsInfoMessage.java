/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.friends;

import java.io.Serializable;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年7月30日
 */
public class DeleteFriendsInfoMessage implements Serializable {
	
	private static final long serialVersionUID = -7152171831856268782L;

	/**
	 * 中控微信号，非空
	 */
	private String noWx;
	
	/**
	 * 朋友圈code，非空
	 */
	private String friendsCode;
	
	/**
	 * 微信朋友圈id，非空
	 */
	private String friendsId;

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
	 * @return the friendsCode
	 */
	public String getFriendsCode() {
		return friendsCode;
	}

	/**
	 * @param friendsCode the friendsCode to set
	 */
	public void setFriendsCode(String friendsCode) {
		this.friendsCode = friendsCode;
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
		builder.append("DeleteFriendsInfoMessage [noWx=");
		builder.append(noWx);
		builder.append(", friendsCode=");
		builder.append(friendsCode);
		builder.append(", friendsId=");
		builder.append(friendsId);
		builder.append("]");
		return builder.toString();
	}
}
