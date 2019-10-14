/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lj.base.core.util.GUID;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.friends.AddFriendsInfoMessage;
import com.lj.business.supcon.service.IChatFriendsFacade;
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
public class WxChatRoomServiceImplTest extends SpringTestCase {

	@Autowired
	private IWxChatRoomService wxChatRoomService;
	
	@Test
	public void sendCreateChatRoom() {
		CreateChatRoomMessage createChatRoomMessage = new CreateChatRoomMessage();
		createChatRoomMessage.setNoWxZk("ljkj8402");
		createChatRoomMessage.setUserNames("boer00001,KENNYvip6688,wxid_wv737da4uw1i21");
		for (int i = 0; i < 50; i++) {
			createChatRoomMessage.setRoomNickName("test"+i);
			wxChatRoomService.sendCreateChatRoom(createChatRoomMessage);
		}
	}
}
