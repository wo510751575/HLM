/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

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
 * CreateDate: 2018年1月24日
 */
public class UnclaimedFriendsByShop implements Serializable {

	private static final long serialVersionUID = 5006985815761789996L;

	
	/**
	 * 未认领终端微信数量
	 */
	private int wxCount;
	
	/**
	 * 未认领客户数量
	 */
	private int unclaimedCount;

	/**
	 * @return the wxCount
	 */
	public int getWxCount() {
		return wxCount;
	}

	/**
	 * @param wxCount the wxCount to set
	 */
	public void setWxCount(int wxCount) {
		this.wxCount = wxCount;
	}

	/**
	 * @return the unclaimedCount
	 */
	public int getUnclaimedCount() {
		return unclaimedCount;
	}

	/**
	 * @param unclaimedCount the unclaimedCount to set
	 */
	public void setUnclaimedCount(int unclaimedCount) {
		this.unclaimedCount = unclaimedCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnclaimedFriendsByShop [wxCount=");
		builder.append(wxCount);
		builder.append(", unclaimedCount=");
		builder.append(unclaimedCount);
		builder.append("]");
		return builder.toString();
	}

}
