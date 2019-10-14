/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import com.lj.business.supcon.dto.chatroom.AddChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DelChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DismissChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;

/**
 * 
 * 类说明：微信群服务接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年9月10日
 */
public interface IWxChatRoomService {

	/**
	 * 
	 *
	 * 方法说明：请求中控同步群信息
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月10日
	 * @return TODO
	 *
	 */
	public boolean sendSyncChatRoom(SyncChatRoomMessage syncChatRoomMessage);
	
	/**
	 * 
	 *
	 * 方法说明：创建群聊
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 段志鹏 CreateDate: 2018年10月25日
	 * @return TODO
	 *
	 */
	public boolean sendCreateChatRoom(CreateChatRoomMessage createChatRoomMessage);
	/**
	 *
	 * 方法说明：添加群聊成员
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 * @return TODO
	 *
	 */
	public boolean sendAddChatRoom(AddChatRoomMessage addChatRoomMessage);
	/**
	 *
	 * 方法说明：删除群聊成员
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 * @return TODO
	 *
	 */
	public boolean sendDelChatRoom(DelChatRoomMessage delChatRoomMessage);
	
	/**
	 *
	 * 方法说明：解散群聊
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 * @return TODO
	 *
	 */
	public boolean sendDismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage);
}
