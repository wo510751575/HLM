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
import com.lj.business.supcon.netty.message.redpacket.SendRedpacketResult;
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
import com.lj.distributecache.RedisCacheConfigFromCC;
import com.lj.distributelock.RedisLock;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：发红包结果返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月26日
 */
@Service
public class SendRedpacketResultService implements IService<SendRedpacketResult> {

	private static Logger logger = LoggerFactory.getLogger(SendRedpacketResultService.class);
	
	
	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService ;
	
    @Autowired
    private IGmAssistantShopService gmAssistantShopService;
    
	@Resource
	IPersonMemberService personMemberService;
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	IImChatInfoService imChatInfoService;
	
	@Resource
	SendChatInfoService sendChatInfoService;
	
	@Resource
	RedisCacheConfigFromCC redisCacheConfigFromCC;
	
	@Resource
	RedisLock redisLock;
	
	@Override
	public void process(SendRedpacketResult request, Message message, Channel channel) {
		logger.info("发红包结果返回：{}", request);
		WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
		wxRedpackDetailInfoDto.setCode(request.getRpCode());
		wxRedpackDetailInfoDto = wxRedpackDetailInfoService.findWxRedpackDetailInfo(wxRedpackDetailInfoDto);
		AssertUtils.notNull(wxRedpackDetailInfoDto,"红包不存在");
		try{
			if(request.isResult()){
				
				String msgId = GUID.generateByUUID();
				IoSession ioSession = ChannelUtils.getSession(channel);
				logger.info(" redpack code :{}  send succees , order_id :{}",request.getCode(),request.getOrderNo());
				wxRedpackDetailInfoDto.setStatus(RedPackStatus.SUCCESS.getStatus());
				wxRedpackDetailInfoDto.setReceiveDate(new Date());
				wxRedpackDetailInfoDto.setRedPackId(request.getOrderNo());
				SendChatToGm  sendChatToGm = new SendChatToGm();
				sendChatToGm.setNoWxGm(ioSession.getNoWxGm());
				sendChatToGm.setMsgId(message.getMsgId());
				sendChatToGm.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
				sendChatToGm.setMemberNoGm(wxRedpackDetailInfoDto.getMemberNoGm());
				sendChatToGm.setType(ChatInfoType.S_REDPACKET.getCode());//默认
				//1发送普通红包
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.NORMAL.getType()))) {
					sendChatToGm.setType(ChatInfoType.S_REDPACKET.getCode());
				}
				//2发送加好友红包
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.ADD.getType()))) {
					sendChatToGm.setType(ChatInfoType.S_REDPACKET.getCode());
				}
				//10发送转账 
				if(wxRedpackDetailInfoDto.getType() !=null 
						&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.TRANS.getType()))) {
					sendChatToGm.setType(ChatInfoType.TRANSFER.getCode());
				}
		
				
				
				sendChatToGm.setSenderFlag(1);
				sendChatToGm.setMsgSource(MessageSource.SA.getCode());
				Map<String,String> content = Maps.newHashMap();
				content.put("code", wxRedpackDetailInfoDto.getMsgId());
				content.put("type",sendChatToGm.getType());
				content.put("amt",wxRedpackDetailInfoDto.getAmount()+"");
				content.put("des",wxRedpackDetailInfoDto.getContent());
				sendChatToGm.setContent(JsonUtils.jsonFromObject(content));
				if (StringUtils.isNotEmpty(wxRedpackDetailInfoDto.getMemberNoGm())) {
					AddImChatInfo addImChatInfo = new AddImChatInfo();
					addImChatInfo.setCode(msgId);
					addImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
					addImChatInfo.setNoWxGm(sendChatToGm.getNoWxGm());
					addImChatInfo.setMemberNo(sendChatToGm.getMemberNo());
					addImChatInfo.setType(sendChatToGm.getType());	
					addImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
					addImChatInfo.setContent(sendChatToGm.getContent());
					
					
					//非App 发送红包或转账
					if(!wxRedpackDetailInfoDto.getSource().equals("1")) {
						//SENDER_SYNC_STATUS '发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端',
						addImChatInfo.setSenderSyncStatus(0);
						//STATUS '消息状态：1 未发送、2发送成功、3发送失败',
						//addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
					}
					addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
					addImChatInfo.setMsgSource(MessageSource.SA.getCode());
					
					addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
					imChatInfoService.addImChatInfo(addImChatInfo);
				    logger.info(" --------  add im chat success  ----- >{}",addImChatInfo);
                }
				
				FindPersonMemberReturn pm = personMemberService.findPersonMemberByNoWxAndShopWx(wxRedpackDetailInfoDto.getNoWx(),wxRedpackDetailInfoDto.getNoWxShop());
                //发送源不为app 才需要同步
				if(pm != null && pm.getMemberNoGm() !=null && !wxRedpackDetailInfoDto.getSource().equals("1")) {
					//转发给导购消息
					// 往导购客户端发送聊天信息
					ChatInfoRequest chatRequest = new ChatInfoRequest();
					chatRequest.setMemberNoGm(pm.getMemberNoGm());
					chatRequest.setNoWxGm(wxRedpackDetailInfoDto.getNoWxShop());
					chatRequest.setMemberNo(pm.getMemberNo());
					chatRequest.setNoWx(wxRedpackDetailInfoDto.getNoWx());
					//chatRequest.setAlias(request.getAlias());
					chatRequest.setSenderFlag(SenderFlag.GM.getCode());
					
					
					Map<String,String> chatcontent = Maps.newHashMap();
					chatcontent.put("code", wxRedpackDetailInfoDto.getMsgId());
					chatcontent.put("amt",wxRedpackDetailInfoDto.getAmount()+"");
					
					
					//移动端APP非轮询，所以只要两种状态足够
				    //1红包
					if(wxRedpackDetailInfoDto.getType() !=null 
							&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.NORMAL.getType()))) {
						//对方收取红包
						chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
						chatcontent.put("type",ChatInfoType.R_GETREDPACKET.getCode());
					}
					//2红包
					if(wxRedpackDetailInfoDto.getType() !=null 
							&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.ADD.getType()))) {
						//对方收取红包
						chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
						chatcontent.put("type",ChatInfoType.R_GETREDPACKET.getCode());
						
					}
					//10转账 
					if(wxRedpackDetailInfoDto.getType() !=null 
							&& wxRedpackDetailInfoDto.getType().equals(Integer.valueOf(RedPackTypeEnum.TRANS.getType()))) {
						//对方收取转账
						chatRequest.setType(ChatInfoType.TRANSFER.getCode());
						chatcontent.put("type",ChatInfoType.R_TRANSFER.getCode());

					}
					chatcontent.put("des", wxRedpackDetailInfoDto.getContent());
					sendChatToGm.setContent(JsonUtils.jsonFromObject(chatcontent));
					chatRequest.setContent(sendChatToGm.getContent());
					chatRequest.setCreateTime(new Date().getTime());

					Message systemMessage = new  Message(MessageCode.ChatInfoRequest, msgId,chatRequest.toJson());
					this.sendZkToGm(chatRequest, systemMessage, wxRedpackDetailInfoDto.getNoWxShop(), pm.getMemberNoGm());		
				
				}
				wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
			}else {
				logger.info(" redpack code :{}  send fail ",request.getCode());
				wxRedpackDetailInfoDto.setStatus(RedPackStatus.FAIL.getStatus());
				wxRedpackDetailInfoDto.setErrorMsg(request.getMessage());
				wxRedpackDetailInfoDto.setReceiveDate(new Date());
				//转发失败原因给导购
				if(wxRedpackDetailInfoDto.getMsgId() != null) {
					request.setOrderNo(wxRedpackDetailInfoDto.getMsgId());
					request.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
					request.setMsgId(wxRedpackDetailInfoDto.getMsgId());
					Message systemMessage = new  Message(MessageCode.SendRedpacketResult,GUID.generateByUUID(),request.toJson());
	
					IoSession session = ChannelUtils.getSession(wxRedpackDetailInfoDto.getMemberNoGm());
					if (ChannelUtils.isLogin(session)) {	// 导购端已连接并登录，则发送信息
						serverManager.sendMessageNoCache(session.getChannel(), systemMessage);	// 不缓存报文消息
						logger.info("已向中导购客户端发送错误：" + message);
					}
				}
				wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
			}
			
		}catch(Exception e){
			logger.error("SendRedpacketResultService.process -------->error :{}  ",wxRedpackDetailInfoDto,e);
		}finally{
			boolean  releaseLock = redisLock.releaseLock(wxRedpackDetailInfoDto.getNoWxShop()+"_RED_PACK_QUEUE");
			logger.info( "  redis lock release result :{}" , releaseLock);
		}
		
		logger.info("发红包结果返回处理完成：{}", request);

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
