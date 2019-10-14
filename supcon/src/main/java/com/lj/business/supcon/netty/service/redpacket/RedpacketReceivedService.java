/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.redpacket;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.redpacket.RedpacketReceived;
import com.lj.business.supcon.netty.service.chat.SendChatInfoService;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.SendChatToGm;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.RedPackStatus;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：红包已领取
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月31日
 */
@Service
public class RedpacketReceivedService implements IService<RedpacketReceived> {

	private static Logger logger = LoggerFactory.getLogger(RedpacketReceivedService.class);
	
	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;

	@Resource
	private ServerManager serverManager;
	
	@Resource
	IImChatInfoService imChatInfoService;
	
	@Resource
	IPersonMemberService personMemberService;
	
    @Autowired
    private IGmAssistantShopService gmAssistantShopService;
    
    @Autowired
    private SendChatInfoService sendChatInfoService;
    
	@Override
	public void process(RedpacketReceived request, Message message, Channel channel) {
		 logger.info("RedpacketReceivedService.process ------------> {} ",request);
		 WxRedpackDetailInfoDto wxRedpackDetailInfoDto  = wxRedpackDetailInfoService.findWxRedpackDetailinfoByOrderNo(request.getOrderNo());
		
	
         String msgId = GUID.generateByUUID();
		
		 //对方（客户）领取 ，  中控领取在sendChatInfoService 操作		
		 if(wxRedpackDetailInfoDto != null && request.getOrderNo() != null) {
			 
			 AssertUtils.notNull(wxRedpackDetailInfoDto);
			 wxRedpackDetailInfoDto.setStatus(RedPackStatus.REVICE.getStatus());
			 wxRedpackDetailInfoDto.setReceiveDate(new Date());
			 wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
			 
			 IoSession ioSession = ChannelUtils.getSession(channel);
			 SendChatToGm  sendChatToGm = new SendChatToGm();
			 sendChatToGm.setNoWxGm(ioSession.getNoWxGm());
			 sendChatToGm.setMsgId(message.getMsgId());
			 sendChatToGm.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
			 sendChatToGm.setMemberNoGm(wxRedpackDetailInfoDto.getMemberNoGm());
		    //1红包
			if(wxRedpackDetailInfoDto.getType() !=null 
					&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.NORMAL.getType()))) {
				//对方收取红包
				sendChatToGm.setType(ChatInfoType.S_REDPACKET.getCode());
			}
			//2红包
			if(wxRedpackDetailInfoDto.getType() !=null 
					&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.ADD.getType()))) {
				//对方收取红包
				sendChatToGm.setType(ChatInfoType.S_REDPACKET.getCode());
			}
			//10转账 
			if(wxRedpackDetailInfoDto.getType() !=null 
					&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.TRANS.getType()))) {
				//对方收取转账
				sendChatToGm.setType(ChatInfoType.TRANSFER.getCode());
			}
	
			
			
			sendChatToGm.setSenderFlag(2);
			sendChatToGm.setMsgSource(MessageSource.SA.getCode());
			
			//String content = wxRedpackDetailInfoDto.getMemberName() + "已领取了你的红包";
			//addImChatInfo.setContent(JsonUtils.jsonFromObject(content));
			
			String code = "1";
			FindPersonMemberReturn pm = personMemberService.findPersonMemberByNoWxAndShopWx(wxRedpackDetailInfoDto.getNoWx(),wxRedpackDetailInfoDto.getNoWxShop());

			if(pm != null && pm.getMemberNoGm() !=null) {
				//转发给导购消息
				// 往导购客户端发送聊天信息
				ChatInfoRequest chatRequest = new ChatInfoRequest();
				chatRequest.setMemberNoGm(pm.getMemberNoGm());
				chatRequest.setNoWxGm(wxRedpackDetailInfoDto.getNoWxShop());
				chatRequest.setMemberNo(pm.getMemberNo());
				chatRequest.setNoWx(wxRedpackDetailInfoDto.getNoWx());
				//chatRequest.setAlias(request.getAlias());
				chatRequest.setSenderFlag(SenderFlag.ZK.getCode());
				
				
				Map<String,String> content = Maps.newHashMap();
				content.put("code", wxRedpackDetailInfoDto.getMsgId());
				content.put("amt",wxRedpackDetailInfoDto.getAmount()+"");
				
				
				//移动端APP非轮询，所以只要两种状态足够
			    //1红包
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.NORMAL.getType()))) {
					//对方收取红包
					chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
					content.put("type",ChatInfoType.R_GETREDPACKET.getCode());
					code = ChatInfoType.S_REDPACKET.getCode();
					content.put("des",pm.getMemberName() +"已领取红包");
				}
				//2红包
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.ADD.getType()))) {
					//对方收取红包
					chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
					content.put("type",ChatInfoType.R_GETREDPACKET.getCode());
					code = ChatInfoType.S_REDPACKET.getCode();
					content.put("des", pm.getMemberName() +"已领取红包");
				}
				//10转账 
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.TRANS.getType()))) {
					//对方收取转账
					chatRequest.setType(ChatInfoType.TRANSFER.getCode());
					content.put("type",ChatInfoType.R_TRANSFER.getCode());
					content.put("flag", "true");//领取标识
					code = ChatInfoType.TRANSFER.getCode();
					content.put("des", pm.getMemberName() +"已领取转账");
				}
				
				sendChatToGm.setContent(JsonUtils.jsonFromObject(content));
				chatRequest.setContent(sendChatToGm.getContent());
				chatRequest.setCreateTime(new Date().getTime());
		
		
			
			
			  if (StringUtils.isNotEmpty(wxRedpackDetailInfoDto.getMemberNoGm())) {
		    	AddImChatInfo addImChatInfo = new AddImChatInfo();
				addImChatInfo.setCode(msgId);
				addImChatInfo.setSenderFlag(SenderFlag.ZK.getCode());
				addImChatInfo.setNoWxGm(sendChatToGm.getNoWxGm());
				addImChatInfo.setMemberNo(sendChatToGm.getMemberNo());
				
				
				addImChatInfo.setType(code);	
				addImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
				
				addImChatInfo.setContent(sendChatToGm.getContent());
				addImChatInfo.setMsgSource(MessageSource.SA.getCode());
				if(!wxRedpackDetailInfoDto.getSource().equals("1")) {
					//SENDER_SYNC_STATUS '发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端',
					addImChatInfo.setSenderSyncStatus(0);
					addImChatInfo.setStatus(MessageStatus.OFFLINE.getCode());
				}else {
					//SENDER_SYNC_STATUS '发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端',
					addImChatInfo.setSenderSyncStatus(1);
					addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
				}
				
				addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
				
				imChatInfoService.addImChatInfo(addImChatInfo);
				
				Message systemMessage = new  Message(MessageCode.ChatInfoRequest,msgId,chatRequest.toJson());
				this.sendZkToGm(chatRequest, systemMessage, wxRedpackDetailInfoDto.getNoWxShop(), pm.getMemberNoGm());		
			
				
			    logger.info(" --------  add im chat success  ----- >{}",addImChatInfo);
            }
		 }
			
		 }
		    logger.info("RedpacketReceivedService.process----------->{}",wxRedpackDetailInfoDto);
	}

	
	/**
	 *
	 * 方法说明：中控客户端发给导购客户端
	 *
	 * @param request
	 * @param message
	 * @param connectSource
	 * @param sessionRec
	 * @param reciveLoingAccountNo
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 * 
	 */
	private void sendZkToGm(ChatInfoRequest request, Message message, String noWxGm, String memberNoGm) {
		// 导购客户端登录，还需将不支持的消息类型转换为文本消息
		// TODO 以后导购客户端有新版支持新消息类型，还需做进一步兼容

		
		//************************** 广播信息给员工 *************************
			FindGmAssistantShopReturn findGmAssistantShopReturn = gmAssistantShopService.findGmByWxAndNo(noWxGm, memberNoGm);
			if(findGmAssistantShopReturn !=null ) {
				IoSession sessionRec = ChannelUtils.getSession(memberNoGm);
				Message chatInfoRequestMessage = new Message(MessageCode.ChatInfoRequest, message.getMsgId(), request.toJson());
				// 当客户端登录时发送，未登录时当做离线记录不发送
				if (ChannelUtils.isLogin(sessionRec)) {
					/**
					 * 如果导购切换登录的微信和当前终端微信一致才推送
					 */
					String noWx =sessionRec.getNoWxGm();
					if(StringUtils.isNotEmpty(noWx) && noWx.equals(noWxGm)) {
						serverManager.sendMessageNoCache(sessionRec.getChannel(), chatInfoRequestMessage);
						logger.info("已向导购{}发送聊天记录：{}", memberNoGm, chatInfoRequestMessage.getMsgId());
					}
				} else {
					logger.info("导购客户端{}未登录，不通过Netty向其发送聊天记录：{}",memberNoGm, chatInfoRequestMessage.getMsgId());
					sendChatInfoService.pushChatInfo(request);
				}
			}
	}

}
