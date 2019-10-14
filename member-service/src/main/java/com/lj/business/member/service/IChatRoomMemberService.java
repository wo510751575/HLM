package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.ChatRoomMember;
import com.lj.business.member.dto.chatroom.AddChatRoomMember;
import com.lj.business.member.dto.chatroom.AddChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.DelChatRoomMember;
import com.lj.business.member.dto.chatroom.DelChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMemberReturn;


/**
 * 
 * 
 * 类说明：群成员表接口类
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
public interface IChatRoomMemberService {
	
	/**
	 * 
	 *
	 * 方法说明：添加群成员表信息
	 *
	 * @param addChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public AddChatRoomMemberReturn addChatRoomMember(AddChatRoomMember addChatRoomMember) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找群成员表信息
	 *
	 * @param findChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public FindChatRoomMemberReturn findChatRoomMember(FindChatRoomMember findChatRoomMember) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除群成员表信息
	 *
	 * @param delChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public DelChatRoomMemberReturn delChatRoomMember(DelChatRoomMember delChatRoomMember) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改群成员表信息
	 *
	 * @param updateChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public UpdateChatRoomMemberReturn updateChatRoomMember(UpdateChatRoomMember updateChatRoomMember)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查找群成员表信息
	 *
	 * @param findChatRoomMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public Page<FindChatRoomMemberPageReturn> findChatRoomMemberPage(FindChatRoomMemberPage findChatRoomMemberPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找群成员表信息
	 *
	 * @param findChatRoomMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	public List<FindChatRoomMemberPageReturn> findChatRoomMemberList(FindChatRoomMemberPage findChatRoomMemberPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查询群聊下指定微信的群成员信息<br>
	 * <font color="red">注意：如果群成员信息不存在，则会向中控请求同步群信息</font>
	 *
	 * @param roomCode		群code
	 * @param userName		群成员微信号
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	public FindChatRoomMemberReturn findChatRoomMemberByNoWx(String roomCode, String userName) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除群成员表信息根据群Code
	 *
	 * @param delChatRoomMemberByRoomCode
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2018年10月25日
	 *
	 */
	public DelChatRoomMemberReturn delChatRoomMemberByCond(DelChatRoomMember delChatRoomMember) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：更新群成员表信息根据群Code
	 *
	 * @param delChatRoomMemberByRoomCode
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2018年10月25日
	 *
	 */
	public UpdateChatRoomMemberReturn updateChatRoomMemberByRoomCode(UpdateChatRoomMember updateChatRoomMember)throws TsfaServiceException;
	
	/**
	 * 同步群主头像
	 * @param updateChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 */
	public UpdateChatRoomMemberReturn synChatRoomOwnerHeadUrl(UpdateChatRoomMember updateChatRoomMember)throws TsfaServiceException;
	
	/**
	 * 获取群成员昵称以‘,’分隔，排除群主
	 * @param roomCode
	 * @param noWxGm
	 * @return
	 */
	String getNickNameByRoomCode(String roomCode,String noWxGm) throws TsfaServiceException;
	/**
	 * 同步群成员信息
	 * @param updateChatRoomMember
	 * @return
	 * @throws TsfaServiceException
	 */
	public UpdateChatRoomMemberReturn synChatRoomMember(UpdateChatRoomMember updateChatRoomMember)throws TsfaServiceException;
	
	/**
	 * 查询群聊下指定微信的群成员信息<br>
	 * @param roomCode
	 * @param userName
	 * @return
	 * @throws TsfaServiceException
	 */
	public FindChatRoomMemberReturn findChatRoomMemberByNoWxSingle(String roomCode, String userName) throws TsfaServiceException;
	/**
	 * 更新成员信息根据条件
	 * @param record
	 * @return
	 * @throws TsfaServiceException
	 */
	public int updateByCond(UpdateChatRoomMember updateChatRoomMember) throws TsfaServiceException;
	
	/**
	 * 取消认领
	 * @param updateChatRoomMember
	 * @return
	 */
	public int cancelClaimed(UpdateChatRoomMember updateChatRoomMember);

	/**
	 *@Desc 软删除
	 *@param updateChatRoomMember
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月6日下午4:56:12
	 */
	public void delete(UpdateChatRoomMember updateChatRoomMember);
}
