/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import com.lj.business.supcon.dto.chat.CancelChatMessage;
import com.lj.business.supcon.dto.chat.ChatMessageRequest;
import com.lj.business.supcon.dto.chat.ChatMessageResponse;
import com.lj.business.supcon.dto.chat.FindChatImageMessage;
import com.lj.business.supcon.dto.chat.UploadChatFileMessage;
import com.lj.business.supcon.dto.chat.UploadChatVideoMessage;

/**
 * 
 * 类说明：IM聊天消息服务接口定义
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月15日
 */
public interface IChatService {
	
	/**
	 * 
	 *
	 * 方法说明：发送聊天消息
	 * 1、接收客户端离线时不发送，并返回客户端已离线标识
	 *
	 * @param chatMessageRequest
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月24日
	 * @return TODO
	 *
	 */
	public ChatMessageResponse sendChatMessage(ChatMessageRequest chatMessageRequest);
	
	/**
	 * 
	 *
	 * 方法说明：请求中控获取图片消息图片（获取大图、中图）
	 *
	 * @param findChatImageMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月31日
	 *
	 */
	public void sendFindChatImageMessage(FindChatImageMessage findChatImageMessage);
	
	/**
	 * 
	 *
	 * 方法说明：请求中控获取视频消息视频文件
	 *
	 * @param uploadChatVideoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	public void sendUploadChatVideoMessage(UploadChatVideoMessage uploadChatVideoMessage);
	
	/**
	 * 
	 *
	 * 方法说明：请求中控撤回聊天消息
	 *
	 * @param cancelChatMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月30日
	 *
	 */
	public void sendCancelChatMessage(CancelChatMessage cancelChatMessage);
	
	/**
	 * 方法说明：请求下载文件
	 */
	public void requestZkUploadChatFile(UploadChatFileMessage uploadChatFileMessage);
}
