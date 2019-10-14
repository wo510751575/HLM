/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.core.util.GUID;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.contacts.AddFriendsByPhoneMessage;
import com.lj.business.supcon.dto.contacts.UserInfo;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.service.IWxChatRoomService;

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
public class ContactsServiceImplTest extends SpringTestCase {

	@Autowired
	private IContactsService contactsService;
	
	@Test
	public void sendPhoneNumberToZKMessage() {
		AddFriendsByPhoneMessage addFriendsByPhoneMessage = new AddFriendsByPhoneMessage();
		addFriendsByPhoneMessage.setWxNoGm("Monster9402");
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo userInfo =new UserInfo();
		userInfo.setMobile("18670275128");
		userInfo.setNickRemark("nickRemark");
		userInfo.setValidation("validation");
		list.add(userInfo);
		userInfo =new UserInfo();
		userInfo.setMobile("18123954667");
		userInfo.setNickRemark("nickRemark");
		userInfo.setValidation("validation");
		list.add(userInfo);
		addFriendsByPhoneMessage.setList(list);
		contactsService.sendPhoneNumberToZKMessage(addFriendsByPhoneMessage);
	}
}
