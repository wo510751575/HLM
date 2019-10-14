package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chatroom.GetChatRoomQRCodeRequest;
import com.lj.business.supcon.service.IChatRoomMessageService;

/**
 * 类说明:
 *@author 贾光朝
 *@createDate 2019年5月13日下午12:00:19
 */
@Service
public class ChatRoomMessageServiceImpl implements IChatRoomMessageService {

	private static Logger logger = LoggerFactory.getLogger(ChatRoomMessageServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Override
	public void requestChatRoomMessage(String memberNoGm, String noWx, String chatRoomName, String roomNickName) {
		logger.info("开始发送报文");
		Message message = this.buildChatMessage(chatRoomName,roomNickName);
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(noWx);
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("发送报文结束");
	}

	/**
	 *@Desc 
	 *@param chatRoomName
	 *@param roomNickName
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月13日下午12:06:20
	 */
	private Message buildChatMessage(String chatRoomName, String roomNickName) {
		GetChatRoomQRCodeRequest request = new GetChatRoomQRCodeRequest();
		/**
		 * type =1 获取群二维码
		 * type =2 修改群名称
		 */
		if(StringUtils.isNotEmpty(roomNickName)){
			request.setType(2);
		}else{
			request.setType(1);
		}
		request.setChatRoomName(chatRoomName);
		request.setRoomNickName(roomNickName);
		Message message = new Message(MessageCode.GetChatRoomQRCodeRequest, request.toJson());
		return message;
	}

}
