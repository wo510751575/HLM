package com.lj.business.member.service;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.ChatRoomPm;
import com.lj.business.member.dto.chatroom.AddChatRoom;
import com.lj.business.member.dto.chatroom.AddChatRoomReturn;
import com.lj.business.member.dto.chatroom.CreateChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoomReturn;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.chatroom.UpdateChatRoomReturn;


/**
 * 
 * 
 * 类说明：群信息表接口类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
public interface IChatRoomService {
	
	
	/**
	 * 创建群组
	 * @param room
	 */
	public void createRoomGroup(ChatRoom room);
	
	/**
	 * 方法说明：用户设置取消置顶
	 * @param personMemberBase
	 */
	public void updateCancleSetUpUser(ChatRoom updateChatRoom);

	/**
	 * 方法说明： 设置用户置顶
	 * @param personMemberBase
	 */
	public void updateSetUpUser(ChatRoom updateChatRoom);
	
	/**
	 * 
	 *
	 * 方法说明：添加群信息表信息
	 *
	 * @param addChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddChatRoomReturn addChatRoom(AddChatRoom addChatRoom) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找群信息表信息
	 *
	 * @param findChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindChatRoomReturn findChatRoom(FindChatRoom findChatRoom) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找群信息表信息
	 *
	 * @param findChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindChatRoomReturn findChatRoomBySelective(FindChatRoom findChatRoom) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除群信息表信息
	 *
	 * @param delChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelChatRoomReturn delChatRoom(DelChatRoom delChatRoom) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改群信息表信息
	 *
	 * @param updateChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateChatRoomReturn updateChatRoom(UpdateChatRoom updateChatRoom)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找群信息表信息
	 *
	 * @param findChatRoomPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindChatRoomPageReturn> findChatRoomPage(FindChatRoomPage findChatRoomPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：中控上传群信息
	 *
	 * @param paramJson
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月8日
	 *
	 */
	public void syncChatRoomFromZk(String paramJson) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找群信息表信息且当群信息不存在时初始化
	 *
	 * @param findChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindChatRoomReturn findChatRoomAndInitWhileNon(FindChatRoom findChatRoom) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：创建群聊并返回code
	 *
	 * @param findChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public String createChatRoom(CreateChatRoom createChatRoom) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：删除群信息表根据群名ID
	 *
	 * @param delChatRoom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏CreateDate: 2018年10月29日
	 *
	 */
	public DelChatRoomReturn delChatRoomByRoomName(String roomName,String noWxGm) throws TsfaServiceException;
	
	/**
	 * 查找列表
	 * @param findChatRoomPage
	 * @return
	 * @throws TsfaServiceException
	 */
	List<FindChatRoomPageReturn> findChatRooms(FindChatRoomPage findChatRoomPage)throws TsfaServiceException;
	
	/**
	 * 取消认领
	 * @param updateChatRoom
	 * @return
	 * @throws TsfaServiceException
	 */
	public UpdateChatRoomReturn cancelClaimed(UpdateChatRoom updateChatRoom)throws TsfaServiceException;
	
	/**
	 * 查询导购所有群组
	 * @param chatroompm
	 * @return
	 */
	public List<ChatRoomPm> selectRoomGroup(ChatRoomPm chatroompm);

	/**
	 * 
	 *@Desc 查询是否置顶
	 *@param roomCode
	 *@return
	 *@return String
	 *@author 贾光朝
	 *@createDate 2019年4月11日下午2:40:25
	 */
	public String selectSetUp(String roomCode);

	/**
	 *@Desc 软删除
	 *@param updateChatRoom
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午4:38:40
	 */
	public void delete(UpdateChatRoom updateChatRoom);
	
	/**
	 * 方法说明： 设置免打扰
	 */
	public void updateNoDisturb(UpdateChatRoom updateChatRoom);
}

