package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.supcon.dto.friends.AddRedPackMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.redpacket.SendRedpacketRequest;
import com.lj.business.supcon.service.IRedPackFacade;

@Service
public class RedPackFacadeImpl implements IRedPackFacade {
	
	
	private static Logger LOG = LoggerFactory.getLogger(RedPackFacadeImpl.class);
	
	
	@Resource
	private ServerManager serverManager;
	

	@Override
	public void sendRedPackRequest(AddRedPackMessage redPackMessage) {
		LOG.info(" begin sendRedPackRequest  ---- >{}",redPackMessage);
		AssertUtils.notNull(redPackMessage);
		AssertUtils.notNullAndEmpty(redPackMessage.getNoWxShop(),"中控微信不能为空");
		AssertUtils.notNullAndEmpty(redPackMessage.getNoWx(),"客户微信不能为空");
		AssertUtils.notNullAndEmpty(redPackMessage.getRpCode(), "红包CODE不能为空");
		SendRedpacketRequest sendRedpacketRequest = new SendRedpacketRequest();
		sendRedpacketRequest.setAmt(redPackMessage.getAmt());
		sendRedpacketRequest.setDes(redPackMessage.getDes());
		sendRedpacketRequest.setNoWxShop(redPackMessage.getNoWxShop());
		sendRedpacketRequest.setNoWx(redPackMessage.getNoWx());
		sendRedpacketRequest.setRpCode(redPackMessage.getRpCode());
		sendRedpacketRequest.setType(redPackMessage.getType());
		sendRedpacketRequest.setNickName(redPackMessage.getNickName());
		
		if(redPackMessage.getMsgId() == null || redPackMessage.getMsgId().equals("")) {
			redPackMessage.setMsgId(GUID.generateByUUID());
		}
		IoSession ioSession = ChannelUtils.getZkSessionByNoWx(sendRedpacketRequest.getNoWxShop());	
		Message message = new  Message(MessageCode.SendRedpacketRequest, redPackMessage.getMsgId(),sendRedpacketRequest.toJson());
		if(ioSession!=null && ioSession.isLogin()){  
			serverManager.sendMessageAndCache(sendRedpacketRequest.getNoWx(), ioSession.getChannel(), message);
			LOG.info("  sendRedPackRequest success :{}",sendRedpacketRequest);
		}else {
			serverManager.cacheMsg(sendRedpacketRequest.getNoWx(), message);
			LOG.info("  sendRedPackRequest cache :{}",sendRedpacketRequest);

		}
		
		
	}

}
