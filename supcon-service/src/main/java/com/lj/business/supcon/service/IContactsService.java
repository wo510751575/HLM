/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import java.util.Map;

import com.lj.business.supcon.dto.contacts.AddFriendsByPhoneMessage;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.dto.contacts.ForcedLogoutMessage;
import com.lj.business.supcon.dto.contacts.MemberClaimMessage;
import com.lj.business.supcon.dto.contacts.NewFriendMessage;
import com.lj.business.supcon.dto.contacts.PushNotifyMessage;
import com.lj.business.supcon.dto.contacts.ScanAddFriendRequestDto;
import com.lj.business.supcon.dto.contacts.SyncShopWxFriendsMessage;
import com.lj.business.supcon.dto.contacts.SyncWxChatDataMessage;
import com.lj.business.supcon.dto.contacts.SyncWxInfoMessage;


/**
 * 
 * 类说明：IM通讯录好友相关接口定义
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月14日
 */
public interface IContactsService {

	/**
	 * 
	 *
	 * 方法说明：向客户端发送新的好友消息
	 *
	 * @param newFriendMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月14日
	 *
	 */
	public void sendNewFriendMessage(NewFriendMessage newFriendMessage);
	
	/**
	 * 
	 *
	 * 方法说明：向导购客户端发送客户认领通知消息
	 *
	 * @param memberClaimMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	public void sendMemberClaimNotifyMessage(MemberClaimMessage memberClaimMessage, String memberNo);
	
	/**
	 * 
	 *
	 * 方法说明：向客户端推送通知消息
	 *
	 * @param pushNotifyMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月20日
	 *
	 */
	public void sendPushNotifyMessage(PushNotifyMessage pushNotifyMessage);
	
	/**
	 * 方法说明：发送添加好友的请求
	 * @param findWxInfoRequestDto
	 * @param cachePre 添加途径的缓存KEY前缀
	 * @author 李端强 CreateDate: 2018年1月3日
	 *
	 */
	public Map<String, Object> sendAddNewFriendMessage(FindWxInfoRequestDto findWxInfoRequestDto,String cachePre);
	
	
	/**
	 * 方法说明：查询基础信息之后,发送添加好友的请求
	 * @param sendScanAddNewFriendMessage
	 * @param cachePre 添加途径的缓存KEY前缀
	 * @author 李端强 CreateDate: 2018年1月3日
	 */
	public boolean sendScanAddNewFriendMessage(ScanAddFriendRequestDto scanAddFriendRequestDto);
	
	/**
	 * 
	 *
	 * 方法说明：向导购客户端发出强制登出IM和APP的消息
	 *
	 * @param forcedLogoutMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月8日
	 *
	 */
	public void sendForcedLogoutMessage(ForcedLogoutMessage forcedLogoutMessage);
	
	/**
	 * 
	 *
	 * 方法说明：向中控客户端发送同步微信通讯录请求
	 *
	 * @param syncShopWxFriendsMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月12日
	 *
	 */
	public void sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage syncShopWxFriendsMessage);
	
	/**
	 * 
	 *
	 * 方法说明：向中控客户端发送同步微信历史聊天记录请求
	 *
	 * @param SyncWxChatDataMessage
	 *
	 * @author zlh CreateDate: 2018年1月12日
	 *
	 */
	public void sendSyncWxChatDataMessage(SyncWxChatDataMessage syncWxChatDataMessage);
	
	/**
	 * 
	 *
	 * 方法说明：向中控客户端发送同步客户微信基本信息的请求
	 *
	 * @param syncWxInfoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月13日
	 * @return 发送是否成功
	 *
	 */
	public boolean sendSyncWxInfoMessage(SyncWxInfoMessage syncWxInfoMessage);
	
	
	/**
	 * 方法说明：往中控推送手机号添加为微信好友
	 */
	public boolean sendPhoneNumberToZKMessage(AddFriendsByPhoneMessage addFriendsByPhoneMessage);
}
