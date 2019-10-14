package com.lj.business.supcon.netty.service.friends;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.friends.SendFriendsPicDownResult;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.ImFriendsPicDownCallBackDto;

import io.netty.channel.Channel;

@Service
public class SendFriendsPicDownResultService implements IService<SendFriendsPicDownResult>  {

	private static Logger LOG = LoggerFactory.getLogger(SendFriendsPicDownResultService.class);
	
	@Resource
	IImFriendsCallBackFacade friendsCallBackFacade;
	@Resource
	IImFriendsInfoService imFriendsInfoService;
	@Resource
	private ServerManager serverManager;
	
	@Override
	public void process(SendFriendsPicDownResult request, Message message,Channel channel) {
		LOG.info("SendFriendsPicDownResultService.process -------------- >{}",request);
		if(request.isResult()){
			LOG.info(" img url download success  , new image url :{}",request.getImgUrl());
			ImFriendsPicDownCallBackDto imFriendsPicDownCallBackDto = new ImFriendsPicDownCallBackDto();
			imFriendsPicDownCallBackDto.setFriendsId(request.getFriendsId());
			imFriendsPicDownCallBackDto.setImgStatus(request.getImgStatus());
			imFriendsPicDownCallBackDto.setImgUrls(request.getImgUrl());
			imFriendsPicDownCallBackDto.setNoWx(request.getNoWx());
			friendsCallBackFacade.toFriendsPicDownCallBack(imFriendsPicDownCallBackDto);
		}else {
			/**
			 * 只通知给请求的导购
			 */
			ImFriendsInfoDto imFriendsInfoDto  = imFriendsInfoService.getImFriendsInfoByFriendsId(request.getFriendsId(), request.getNoWx());
			if(StringUtils.isNotEmpty(imFriendsInfoDto.getMemberNoGm())) {
				IoSession sessionRec = ChannelUtils.getSession(imFriendsInfoDto.getMemberNoGm());
				if(sessionRec!=null && sessionRec.isLogin()){
					Message gmessage = new  Message();
					gmessage.setBody(request.toJson());
					gmessage.setCode(MessageCode.SendFriendsPicNotify);
					gmessage.setMsgId(GUID.generateByUUID());
					serverManager.sendMessageNoCache(sessionRec.getChannel(), gmessage);
					LOG.info("sendPicUrlDownLoad notify success :{}",request);
				}else {
					LOG.info("sendPicUrlDownLoad notify fail  , gm is not online :{}",request);
				}
			}
		}
		
		
	}

}
