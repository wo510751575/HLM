package com.lj.business.supcon.netty.service.friends;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDown;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDownResult;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.ImFriendsPicDownCallBackDto;

import io.netty.channel.Channel;

@Service
public class SendFriendsPicDownService implements IService<SendFriendsPicDown>  {

	private static Logger LOG = LoggerFactory.getLogger(SendFriendsPicDownService.class);
	
	@Resource
	IImFriendsCallBackFacade friendsCallBackFacade;
	@Resource
	IImFriendsInfoService imFriendsInfoService;
	@Resource
	private ServerManager serverManager;
	
	@Override
	public void process(SendFriendsPicDown request, Message message,Channel channel) {
		LOG.info("SendFriendsPicDownService.process -------------- >{}",request);
		AssertUtils.notNullAndEmpty(request.getFriendsId(),"朋友圈ID不能为空");
		IoSession ioSession = ChannelUtils.getSession(channel);
		
		ImFriendsInfoDto friendsInfoDto= imFriendsInfoService.getImFriendsInfoByFriendsId(request.getFriendsId(), ioSession.getNoWxGm());
		if(null == friendsInfoDto) {
			LOG.error("朋友圈不存在");
			return;
		}
		ImFriendsInfoDto updateDto = new ImFriendsInfoDto();
		updateDto.setCode(friendsInfoDto.getCode());
		updateDto.setMemberNoGm(ioSession.getMemberNoGm());
		imFriendsInfoService.updateImFriendsInfo(updateDto);
		
		IoSession zkSession = ChannelUtils.getZkSessionByGm(channel);
		message.setBody(request.toJson());
		message.setCode(MessageCode.SendFriendsPicDown);
		message.setMsgId(GUID.generateByUUID());
		serverManager.sendMessageAndCache(zkSession.getNoWxGm(), zkSession.getChannel(), message);
		
		
	}

}
