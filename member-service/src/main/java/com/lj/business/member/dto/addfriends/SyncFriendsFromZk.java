/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

import java.io.Serializable;
import java.util.Arrays;


/**
 * 
 * 
 * 类说明：同步微信客户参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月22日
 */
public class SyncFriendsFromZk implements Serializable {

	private static final long serialVersionUID = 6366901743968475121L;

	/**
	 * 终端微信
	 */
	private String noWxGm;
	/**
	 * 客户列表,非空
	 */
	private SyncFriendsDetail[] friendsList;

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/**
	 * @return the friendsList
	 */
	public SyncFriendsDetail[] getFriendsList() {
		return friendsList;
	}

	/**
	 * @param friendsList the friendsList to set
	 */
	public void setFriendsList(SyncFriendsDetail[] friendsList) {
		this.friendsList = friendsList;
	}

	@Override
	public String toString() {
		return "SyncFriendsFromZk [noWxGm=" + noWxGm + ", friendsList=" + Arrays.toString(friendsList) + "]";
	}

}
