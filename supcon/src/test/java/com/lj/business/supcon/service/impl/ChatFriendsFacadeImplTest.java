/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.core.util.GUID;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;

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
 * CreateDate: 2017年12月25日
 */
public class ChatFriendsFacadeImplTest extends SpringTestCase {

	@Resource
	private IChatFriendsFacade chatFriendsFacade;
	
	@Test
	public void testSendFriendsInfo() {
		AddFriendsInfoMessage add = new AddFriendsInfoMessage();
		add.setFriendsCode(GUID.generateByUUID());
		add.setImei("866036033222088987");
		add.setNickName("Louise");
		add.setNoWxShop("fanfan558989");
		add.setContent("1111111111111111111123");
		add.setType("1");
		add.setMemberNoGm("71deb4191b764764a5927c7f93d5e142");
		chatFriendsFacade.sendFriendsInfo(add);
	}
}
