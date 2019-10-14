/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lj.base.core.encryption.Base64;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.FindMemberLoginInfoReturn;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.chatroom.DelChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.im.FindPersonMemberByChat;
import com.lj.business.member.dto.im.FindPersonMemberByChatReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.emus.EquipmentType;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMemberLoginInfoService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.common.WeixinXMLParser;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.ChatInfoResponse;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.supcon.utils.WxPwdEncryptUtils;
import com.lj.business.weixin.domain.ImChatInfo;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.AddImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfo;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfoReturn;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.dto.publicaccount.AddWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccount;
import com.lj.business.weixin.dto.publicaccount.FindWxPublicAccountReturn;
import com.lj.business.weixin.dto.smallprogram.AddWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgram;
import com.lj.business.weixin.dto.smallprogram.FindWxSmallProgramReturn;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.RedPackTypeEnum;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.emus.SenderSyncStatus;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IWxPublicAccountService;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;
import com.lj.business.weixin.service.IWxSmallProgramService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;
import com.lj.distributecache.IDistributeCache;
import com.lj.messagecenter.emus.SendType;
import com.lj.messagecenter.msg.dto.AddNotifyInfo;
import com.lj.messagecenter.msg.service.INotifyService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：发送聊天记录实现类
 *  
 * 
 * <p>
 * 详细描述：聊天记录的处理应优化为通过消息队列MQ异步处理 XXX
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月24日
 */
@Service
public class SendChatInfoService implements IService<ChatInfoRequest> {

	private static Logger logger = LoggerFactory.getLogger(SendChatInfoService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Resource
	private IGuidMemberService guidMemberService;
	@Resource
	private IMemberLoginInfoService memberLoginInfoService;
	@Resource
	private INotifyService notifyService;
	@Resource
	private IWxPublicAccountService wxPublicAccountService;
	@Resource
	private IWxSmallProgramService wxSmallProgramService;
    @Resource
    private IShopTerminalService shopTerminalService;
    @Autowired
    private IChatRoomService chatRoomService;
    @Autowired
    private IWxChatRoomService wxChatRoomService;
    @Autowired
	private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
	private IPersonMemberImService personMemberImService;
    @Autowired
	private IPersonMemberService personMemberService;
    @Autowired
    private IGmAssistantShopService gmAssistantShopService;
    @Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Resource
	private IProblemService problemService;
    @Resource 
	private IDistributeCache distributeCache;
    @Autowired
    private IChatRoomMemberService chatRoomMemberService;
    @Resource
    private IWxRedpackDetailInfoService wxRedpackDetailInfoService;
//    @Resource
//    private ICommonService  commonService;

	@Override
	public void process(ChatInfoRequest request, Message message, Channel channel) {
		IoSession session = ChannelUtils.getSession(channel);// 会话
		ConnectSource connectSource = session.getConnectSource();// 连接来源,聊天记录发送客户端
		logger.info(connectSource + " - " + request);
		
		try {
			AssertUtils.notNull(request, "参数为空");
			AssertUtils.notNullAndEmpty(request.getNoWxGm(), "导购微信号不能为空");
			AssertUtils.notNullAndEmpty(request.getNoWx(), "客户微信号不能为空");
			AssertUtils.notNullAndEmpty(request.getType(), "消息类型不能为空");
			
			IoSession sessionRec = null; 		// 接收方session
			String reciveLoingAccountNo = null;	// 接收方登录账号
			
			//**************在同步历史聊天记录,不用做任何业务,别变更代码位置(start) ********************
			if(connectSource == ConnectSource.ZK) {
	            if(null != request.getOldChat() && request.getOldChat() == 1) {
	            	addOldChatInfo( request,message.getMsgId());
	            	return;
		        }
			}
	        //*******************在同步历史聊天记录(end) ****************************
			
			//************** App发送红包（转账）（start）*************************************
			if(connectSource == ConnectSource.GM) {
				if(request.getType().equals(ChatInfoType.S_REDPACKET.getCode()) || request.getType().equals(ChatInfoType.TRANSFER.getCode())) {
					sendRedPacket(request,message, session);
					return;
				}
			}
			//************** 发送红包系统消息（end）*************************************
			
			//************** 收到或收取红包（转账）系统消息（start）*************************************
			if(connectSource == ConnectSource.ZK) {
				//1.收到红包或转账
				if(request.getType().equals(ChatInfoType.S_GETREDPACKET.getCode()) || request.getType().equals(ChatInfoType.S_GETTRANSFER.getCode()) ){
					//只保存聊天和推送信息，不保存红包记录，因为不知道金额（转账可以）
					addRedPacket(request);
					return;
				}
				//2.收取红包或转账
				if(request.getType().equals(ChatInfoType.L_REDPACKET.getCode()) || request.getType().equals(ChatInfoType.L_TRANSFER.getCode())) {
					//第一步收到红包/转账入库
					String msgId = GUID.generateByUUID();
					saveRedPacket(request, msgId);
					//第二步收到做红包处理
					addRedPacketSendMessage(request,msgId);
					return;
				}
			}
			//************** 收取红包系统消息（end）*************************************

			//************** 对方收取红包（转账）系统消息（start）*************************************
			if(connectSource == ConnectSource.ZK) {
				if(request.getType().equals(ChatInfoType.R_GETREDPACKET.getCode()) || request.getType().equals(ChatInfoType.R_TRANSFER.getCode())) {
					//直接返回不做任何处理
					return;
				}
			}
			//************** 对方收取红包系统消息（end）*************************************
			
			// 导购客户端给客户发聊天消息
			if(connectSource == ConnectSource.GM) {
				AssertUtils.notNullAndEmpty(request.getMemberNoGm(), "导购编号不能为空");
				AssertUtils.notNullAndEmpty(request.getNoWxGm(), "导购微信号不能为空");
				AssertUtils.notAllNullAndEmpty(request.getMemberNo(), request.getGroupUserName(), "客户编号和群发送人不能同时为空");
				
				// 如果来源为导购,获取中控session
				reciveLoingAccountNo = request.getNoWxGm();
				
				sessionRec = ChannelUtils.getSession(reciveLoingAccountNo);
				
				// 中控离线,则不发送记录,则数据库也不保存该条聊天记录
				if(!ChannelUtils.isLogin(sessionRec)) {
					logger.error("中控客户端[{}]已离线，不能发送聊天记录", reciveLoingAccountNo);
					ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.ZKCLIENT_OFFLINE);
					ChatInfoResponse response = new ChatInfoResponse();
					response.setMsgId(message.getMsgId());
					response.setResult(Boolean.FALSE);
//					response.setResend(Boolean.FALSE);	// 不能重发
					response.setCode(responseCode.getCode());
					response.setMessage(responseCode.getMessage());
					Message failMessage = new Message(MessageCode.ChatInfoResponse, GUID.generateByUUID(), response.toJson());
					
				    
					serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, failMessage);
					return;
				}
			} else {	// 客户发给导购
				AssertUtils.notNullAndEmpty(request.getCreateTime(), "聊天时间不能为空");
				
				// 中控发的图片消息，content重置为null（实际上传内容：[收到一张图片]）
				if(ChatInfoType.IMG.getCode().equals(request.getType())) {
//					request.setContent(null);	
				} else if(ChatInfoType.CARD.getCode().equals(request.getType())) {	// 微信名片
					this.processCard(request);
				} else if(ChatInfoType.SMALL_PROGRAM.getCode().equals(request.getType())) {	// 小程序
					this.processSmallProgram(request);
				}
			}
			
			// 保存聊天记录
			AddImChatInfo addImChatInfo=new AddImChatInfo();
			addImChatInfo.setResend(request.isResend());
			addImChatInfo.setMemberNoGm(request.getMemberNoGm());
			addImChatInfo.setNoWxGm(request.getNoWxGm());
			addImChatInfo.setMemberNo(request.getMemberNo());
			addImChatInfo.setNoWx(request.getNoWx());
			addImChatInfo.setAlias(request.getAlias());
			addImChatInfo.setResourcesPath(request.getResources());
			/**
			 * 优惠券分享没有资源图片
			 * 兼容
			 */
			if(request.getType().equals(ChatInfoType.SHARE.getCode())
					&& StringUtils.isEmpty(request.getResources())
					&& request.getShareUrl().contains("oms-web/a/cp/h5coupon")) {
				String	couponUrlLogo = localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "logo", "couponLogoUrl");
				addImChatInfo.setResourcesPath(couponUrlLogo);
			}
			addImChatInfo.setShareTitle(request.getShareTitle());
			addImChatInfo.setShareDes(request.getShareDes());
			addImChatInfo.setShareUrl(request.getShareUrl());
			addImChatInfo.setContent(request.getContent());
			addImChatInfo.setCode(message.getMsgId());
			addImChatInfo.setType(request.getType());
			addImChatInfo.setImei(session.getTerminalCode());
			if (ConnectSource.GM == connectSource) {	// 如果来源为导购,导购手机发送聊天记录
				addImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
				addImChatInfo.setMsgSource(MessageSource.GM.getCode());
				addImChatInfo.setThirdReadFlag(ReadFlag.YES.getCode());//标记为已读
				/**
				 * 并且把原来客户发过来的也标记为已读
				 */
				updateThirdHaveRead(request.getNoWxGm(),request.getMemberNo(),request.getMemberNoGm());
				
				// 发送图片表情，则从content中解析出图片地址
				if(ChatInfoType.IMGBROW.getCode().equals(request.getType()) && StringUtils.isNotEmpty(request.getContent())) {
					Map<String, String> map = JsonUtils.mapFromJson(request.getContent());
					if(map.containsKey("url")) {
						addImChatInfo.setResourcesPath(map.get("url"));
					}
				}
			}else{	// 如果来源为中控,中控手机发送聊天记录
				addImChatInfo.setSenderFlag(SenderFlag.ZK.getCode());
				addImChatInfo.setMsgSource(MessageSource.ZK.getCode());
				addImChatInfo.setChatTime(new Date(request.getCreateTime()));
			}
			
		
			request.setSenderFlag(addImChatInfo.getSenderFlag());		// 发送人标识
			
			/**
			 * 群聊，补充相关信息 
			 * 群聊系统消息时，groupUserName 为空
			 */
			FindChatRoomReturn chatRoomReturn = null;
			if((StringUtils.isNotEmpty(request.getGroupUserName()) 
					|| request.getType().equals(ChatInfoType.SYSTEM.getCode())) 
					&& request.getNoWx().endsWith("@chatroom")) {	
				//群聊转发给群聊创建导购
				//获取群聊信息,此时memberNo是群聊code
				FindChatRoom findChatRoom=  new FindChatRoom();
				findChatRoom.setNoWxZk(request.getNoWxGm());
				findChatRoom.setChatRoomName(request.getNoWx());
				chatRoomReturn=  chatRoomService.findChatRoomBySelective(findChatRoom);
				if(chatRoomIsNull(chatRoomReturn, request, message, sessionRec, connectSource, channel)) {
					logger.warn("群聊为空，不保存聊天记录，立即同步群聊并通知导购！");
					return;
				}
				//设置免打扰
				request.setNoDisturb(chatRoomReturn.getNoDisturb());
				addImChatInfo.setMemberNoGm(chatRoomReturn.getMemberNoGm());
				addImChatInfo.setChatroomType(ChatRoomType.ROOM.getCode());
				addImChatInfo.setChatroomNoWx(request.getGroupUserName());
			}
			
		
			AddImChatInfoReturn addImChatInfoReturn = imChatInfoService.addImChatInfo(addImChatInfo);
			logger.info("已保存聊天记录：" + addImChatInfo.getCode());
			
			// 如果消息已发送成功,则不再往接收端发送聊天消息
			if(request.isResend() && MessageStatus.SUCCESS.getCode().equals(addImChatInfoReturn.getStatus())) {
				logger.info("聊天记录[{}]已由服务器向接收客户端发送成功,无需重复发送", addImChatInfo.getCode());
				return;
			}	
			
			// 从中控端由客户端发给导购的聊天记录，如果根据客户端微信没有找到客户信息，则不转发到导购客户端
			if(addImChatInfoReturn.isTempBool()) {
				logger.info("微信好友没有找到对应客户信息，已保存聊天记录{}", addImChatInfo.getCode());
				return;
			}
		
			// 往接收方客户端发送聊天信息
			request.setMemberNoGm(addImChatInfoReturn.getMemberNoGm());
			request.setMemberNo(addImChatInfoReturn.getMemberNo());
			request.setCreateTime(addImChatInfoReturn.getCreateDate().getTime());
			
			logger.info("消息构建MemberNoGm："+ request.getMemberNoGm());
			logger.info("消息构建MemberNo："+ request.getMemberNo());
			// 如果来源为中控,获取导购session
			if (ConnectSource.ZK == connectSource) {
				
				
				//群聊转发给群聊创建导购
				if(addImChatInfo.getChatroomType()==ChatRoomType.ROOM.getCode()  && null !=chatRoomReturn){
					
					final String NoWxZk = addImChatInfo.getNoWxGm();
					final String ChatRoomName = addImChatInfo.getNoWx();
					//群聊头像没有，立即同步群聊信息
					if(StringUtils.isEmpty(chatRoomReturn.getHeadUrl())){
						taskExecutor.execute(new Runnable() {
							@Override
							public void run() {
								SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
								syncChatRoomMessage.setNoWxZk(NoWxZk);
								syncChatRoomMessage.setChatRoomName(ChatRoomName);
								syncChatRoomMessage.setNowSync(false);
								wxChatRoomService.sendSyncChatRoom(syncChatRoomMessage);
							}
						});
					}
					//增加群聊返回
					request.setRoomNickName(chatRoomReturn.getRoomNickName());
					request.setHeadUrl(chatRoomReturn.getHeadUrl());
					
					//获取群成员信息
					if(StringUtils.isNotEmpty(request.getGroupUserName())) {
						FindChatRoomMemberReturn chatRoomMemberReturn= chatRoomMemberService.findChatRoomMemberByNoWx(chatRoomReturn.getCode(), request.getGroupUserName());
						if(null !=chatRoomMemberReturn){
							request.setMemberHeadUrl(chatRoomMemberReturn.getHeadUrl());
							request.setMemberNickName(chatRoomMemberReturn.getNickName());
						}
					}
					
					request.setMemberNoGm(chatRoomReturn.getMemberNoGm());
				}
				
				//导购存在则转发-未认领的群不转发
				if(StringUtils.isEmpty(request.getMemberNoGm())) {
					return;
				}
				this.sendZkToGm(request, message, request.getNoWxGm(), request.getMemberNoGm());
			} else {	// 导购发给中控
				this.sendGmToZk(request, message, sessionRec, reciveLoingAccountNo);
			}
			
			
			//******************* 查询是否需要自动回复  (start)**************************
			logger.info("查询是否需要自动回复 ");
			if(session.getConnectSource() == ConnectSource.GM)  {
				logger.info("非导购接收");
			}
			
			if(ConnectSource.ZK == connectSource)  {
			
				try {
					
					if(request.getMemberNo() == null) {
						logger.error("request.getMemberNo() 为空");
					}
					/**
					 * 只有单聊才自动回复
					 * update by 段志鹏
					 * update date 2018-12-15
					 */
					if(addImChatInfo.getChatroomType()==ChatRoomType.PERSONAL.getCode() 
							&& StringUtils.isNotEmpty(request.getMemberNo())
							&& StringUtils.isNotEmpty(request.getMemberNoGm())) {
						//获取最后一条聊天内容,如果 非空&是文本&客户发送 ,则开始匹配
						FindAutoAnswerChatInfo findAutoAnswerChatInfo=new FindAutoAnswerChatInfo();
						findAutoAnswerChatInfo.setMemberNo(request.getMemberNo());
						findAutoAnswerChatInfo.setMemberNoGm(request.getMemberNoGm());
						findAutoAnswerChatInfo.setNoWxGm(request.getNoWxGm());
						//查询该导购是否开启自动回复
						FindAutoAnswerChatInfoReturn chatInfo = imChatInfoService.getLastChatInfo(findAutoAnswerChatInfo);
						/**
						 * 群聊时request.getMemberNoGm()&request.getMemberNo()均为空
						 * 此处报空指针
						 */
						String answer=problemService.getAutoContent(chatInfo.getContent(), request.getMemberNoGm(), request.getMemberNo() );
						logger.info("自动回复内容：" +answer);
						
		                if(answer != null && !answer.equals("") && !answer.equals("null")) {
							

			        		taskExecutor.execute(new Runnable() {
								@Override
								public void run() {
									 int num = (int) (Math.random() * 5 + 1);
									 try {
										Thread.sleep(num*1000);
										SendImChatInfo sendImChatInfo = new SendImChatInfo();			
										sendImChatInfo.setNoWxGm(request.getNoWxGm());
										sendImChatInfo.setMemberNo(request.getMemberNo());
										sendImChatInfo.setContent(answer);
										sendImChatInfo.setType(ChatInfoType.TEXT.getCode());//文本
						        		sendImChatInfo.setSenderFlag(1);
						        		sendImChatInfo.setMsgSource(MessageSource.SA.getCode());
						        		sendImChatInfo.setChatAssistantCode(request.getMemberNoGm());	// 导购助手编号
						        		sendImChatInfo.setChatAssistantName("系统");	// 导购助手名称
										imChatInfoService.sendChat(sendImChatInfo); 
									}catch(Exception e) {
										logger.error("自动回复内容出错：" +e);
									}
								}
							});
			        	 	 
		                }
					}
				} catch (Exception e) {
					logger.error("自动回复错误 e={}",e);
				}
			}
			//******************* 查询是否需要自动回复 （end） **************************
		}catch(TsfaServiceException e) {
			logger.error("保存聊天记录失败", e);
			ChatInfoResponse response = new ChatInfoResponse();
			response.setMsgId(message.getMsgId());
			response.setResult(Boolean.FALSE);
//			response.setResend(Boolean.TRUE);	// 可以重发
			response.setCode(e.getExceptionCode());
			response.setMessage(e.getExceptionInfo());
			Message failMessage = new Message(MessageCode.ChatInfoResponse, GUID.generateByUUID(), response.toJson());
			if(connectSource == ConnectSource.ZK) {
				serverManager.sendMessageAndCache(request.getNoWxGm(), channel, failMessage);	// XXX 此处导购微信号应由中控上传
			} else {
				serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, failMessage);
			}
		}catch(Exception e) {
			logger.error("保存聊天记录失败", e);
			ResponseCode responseCode = ResponseUtils.getErrorResponse(e);
			ChatInfoResponse response = new ChatInfoResponse();
			response.setMsgId(message.getMsgId());
			response.setResult(Boolean.FALSE);
//			response.setResend(Boolean.TRUE);	// 可以重发
			response.setCode(responseCode.getCode());
			response.setMessage(responseCode.getMessage());
			Message failMessage = new Message(MessageCode.ChatInfoResponse, GUID.generateByUUID(), response.toJson());
			if(connectSource == ConnectSource.ZK) {
				serverManager.sendMessageAndCache(request.getNoWxGm(), channel, failMessage);	// XXX 此处导购微信号应由中控上传
			} else {
				serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, failMessage);
			}
		}
	}

	
	/**
	 * 并且把原来客户发过来的也标记为已读
	 */
	private void updateThirdHaveRead(String noWxGm,String memberNo,String memberNoGm) {
		try {
			final String wx =noWxGm;
			final String no =memberNo;
			final String noGm =memberNoGm;
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb = new UpdateThirdHaveReadFromWeb();
					updateThirdHaveReadFromWeb.setMemberNo(no);
					updateThirdHaveReadFromWeb.setNoWxGm(wx);
					updateThirdHaveReadFromWeb.setMemberNoGm(noGm);
					imChatInfoService.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
				}
			});
		} catch (Exception e) {
			logger.error("标记为已读错误 e={}",e);
		}
	}
	
	
	
	
	/**
	 * 收到1个红包或转账保存记录并转发系统消息告诉导购
	 * @param request
	 */
	private void addRedPacket(ChatInfoRequest request) {
		

		FindPersonMemberReturn pm = personMemberService.findPersonMemberByNoWxAndShopWx(request.getNoWx(),request.getNoWxGm());

		Map<String,String> content = Maps.newHashMap();
		
	    String addType = "";
		//转账
		if(request.getType().equals(ChatInfoType.S_GETTRANSFER.getCode())){
		
			content.put("code", "");
			content.put("amt", "");
			if(request.getShareDes() != null && !request.getShareDes().equals("")) {
				content.put("amt", Long.valueOf(request.getShareDes())+"");
			}
			content.put("des", "恭喜发财,大吉大利");
			if(request.getShareTitle() != null && !request.getShareTitle().equals("")) {
				content.put("des", request.getShareTitle());
			}
			
	        content.put("type",ChatInfoType.S_GETTRANSFER.getCode());
	        addType =  ChatInfoType.TRANSFER.getCode();

		}
		//红包
		if(request.getType().equals(ChatInfoType.S_GETREDPACKET.getCode())) {
			content.put("code", "");
			content.put("amt", "");
			if(request.getShareDes() != null && !request.getShareDes().equals("")) {
				content.put("amt", Long.valueOf(request.getShareDes())+"");
			}
			content.put("des", "恭喜发财,大吉大利");
			if(request.getShareTitle() != null && !request.getShareTitle().equals("")) {
				content.put("des", request.getShareTitle());
			}
	        content.put("type",ChatInfoType.S_GETREDPACKET.getCode());
	        addType = ChatInfoType.S_REDPACKET.getCode();
		}
		
		//入库设定状态，以便发送离线消息
		Integer senderSyncStatus=0;
		String status ="1";
		String msgId = GUID.generateByUUID();
		addImChatInfo(msgId, request.getNoWxGm(), pm.getMemberNo(), JsonUtils.jsonFromObject(content) ,
				addType, SenderFlag.ZK.getCode(), senderSyncStatus, status);

		if(pm != null && pm.getMemberNoGm() !=null) {
			//转发给导购消息
			// 往导购客户端发送聊天信息
			ChatInfoRequest chatRequest = new ChatInfoRequest();
			chatRequest.setMemberNoGm(pm.getMemberNoGm());
			chatRequest.setNoWxGm(request.getNoWxGm());
			chatRequest.setMemberNo(pm.getMemberNo());
			chatRequest.setNoWx(request.getNoWx());
			chatRequest.setAlias(request.getAlias());
			chatRequest.setSenderFlag(SenderFlag.ZK.getCode());
			
			//if(request.getType().equals(ChatInfoType.S_GETREDPACKET.getCode()) || request.getType().equals(ChatInfoType.S_GETTRANSFER.getCode()) ){

			//移动端APP非轮询，所以只要两种状态足够
			//红包
			if(request.getType().equals(ChatInfoType.S_GETREDPACKET.getCode())) {
			    chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
			    chatRequest.setContent(request.getContent());
			    if(request.getShareTitle()!=null && !request.getShareTitle().equals("")) {
			    	content.put("des",request.getShareTitle());
			    }
			    chatRequest.setContent(JsonUtils.jsonFromObject(content));
			}
			//转账
			if(request.getType().equals(ChatInfoType.S_GETTRANSFER.getCode())) {
				chatRequest.setType(ChatInfoType.TRANSFER.getCode());
				if(request.getShareTitle()!=null && !request.getShareTitle().equals("")) {
			    	content.put("des",request.getShareTitle());
			    }
				chatRequest.setContent(JsonUtils.jsonFromObject(content));
			}
			
		
			
			//chatRequest.setContent(request.getContent());
			chatRequest.setCreateTime(new Date().getTime());
	
	
			Message systemMessage = new  Message(MessageCode.ChatInfoRequest, msgId,chatRequest.toJson());
			this.sendZkToGm(chatRequest, systemMessage, request.getNoWxGm(), pm.getMemberNoGm());		
		
		}
		

	}
	
	
	
	/**
	 * 保存1个红包或转账保存记录并转发系统消息告诉导购
	 * @param request
	 */
	private void saveRedPacket(ChatInfoRequest request, String msgId) {
		
		
		
		WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
		wxRedpackDetailInfoDto.setAmount(Long.valueOf(request.getShareDes()));
		wxRedpackDetailInfoDto.setCode(GUID.generateCode());
		wxRedpackDetailInfoDto.setContent(request.getContent());
		if(request.getShareTitle() != null && !request.getShareTitle().equals("")) {
			wxRedpackDetailInfoDto.setContent(request.getShareTitle());
		}
		
		wxRedpackDetailInfoDto.setNoWx(request.getNoWx());
		wxRedpackDetailInfoDto.setNoWxShop(request.getNoWxGm());
		wxRedpackDetailInfoDto.setCreateDate(new Date());
		
		FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(request.getNoWxGm());

		FindPersonMemberReturn pm = personMemberService.findPersonMemberByNoWxAndShopWx(request.getNoWx(),request.getNoWxGm());
		if(pm != null) {
		   wxRedpackDetailInfoDto.setMemberNo(pm.getMemberNo());
		   wxRedpackDetailInfoDto.setMemberNoGm(pm.getMemberNoGm());
		}
		
		if(shopTerminalReturn !=null) {
			wxRedpackDetailInfoDto.setMerchantNo(shopTerminalReturn.getMerchantNo());
		}
		wxRedpackDetailInfoDto.setReceiveDate(new Date(request.getCreateTime()));
		//8收取客户红包，9收取客户转账等
		if(request.getType().equals(ChatInfoType.L_REDPACKET.getCode()) ) {
			wxRedpackDetailInfoDto.setType(8);
		}
		if(request.getType().equals(ChatInfoType.L_TRANSFER.getCode()) ) {
			wxRedpackDetailInfoDto.setType(9);
		}
		

		
		wxRedpackDetailInfoDto.setStatus("2");//成功
		wxRedpackDetailInfoDto.setSendDate(new Date(request.getCreateTime()));
		wxRedpackDetailInfoService.addWxRedpackDetailInfo(wxRedpackDetailInfoDto);

		Map<String,String> content = Maps.newHashMap();
		content.put("code", "");
		content.put("amt",wxRedpackDetailInfoDto.getAmount()+"");
		//content.put("des",wxRedpackDetailInfoDto.getContent());
		
		String addType = "";
		if(pm != null && pm.getMemberNoGm() !=null) {
			//转发给导购消息
			// 往导购客户端发送聊天信息
			ChatInfoRequest chatRequest = new ChatInfoRequest();
			chatRequest.setMemberNoGm(pm.getMemberNoGm());
			chatRequest.setNoWxGm(request.getNoWxGm());
			chatRequest.setMemberNo(pm.getMemberNo());
			chatRequest.setNoWx(request.getNoWx());
			chatRequest.setAlias(request.getAlias());
			chatRequest.setSenderFlag(SenderFlag.ZK.getCode());
			
			
			//移动端APP非轮询，所以只要两种状态足够
			//红包
			if(request.getType().equals(ChatInfoType.L_REDPACKET.getCode())) {
			    chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
			    content.put("type",ChatInfoType.L_REDPACKET.getCode());
			    addType = chatRequest.getType();
			}
			//转账
			if(request.getType().equals(ChatInfoType.L_TRANSFER.getCode())) {
				chatRequest.setType(ChatInfoType.TRANSFER.getCode());
				content.put("type",ChatInfoType.L_TRANSFER.getCode());
			    addType = chatRequest.getType();
			}
			content.put("des", request.getContent());
			chatRequest.setContent(JsonUtils.jsonFromObject(content));
			chatRequest.setCreateTime(new Date().getTime());
	
		}
		
		
		//入库设定状态，以便发送离线消息
		Integer senderSyncStatus=0;
		String status ="2";
		
		
		addImChatInfo(msgId, request.getNoWxGm(), pm.getMemberNo(), JsonUtils.jsonFromObject(content) , addType, SenderFlag.GM.getCode(), senderSyncStatus, status);

	}
	
	
	/**
	 * 收取对方红包或转账保存记录并转发系统消息告诉导购
	 * @param request
	 */
	private void addRedPacketSendMessage(ChatInfoRequest request, String msgId) {
		FindPersonMemberReturn pm = personMemberService.findPersonMemberByNoWxAndShopWx(request.getNoWx(),request.getNoWxGm());

		if(pm != null && pm.getMemberNoGm() !=null) {
			//转发给导购消息
			// 往导购客户端发送聊天信息
			ChatInfoRequest chatRequest = new ChatInfoRequest();
			chatRequest.setMemberNoGm(pm.getMemberNoGm());
			chatRequest.setNoWxGm(request.getNoWxGm());
			chatRequest.setMemberNo(pm.getMemberNo());
			chatRequest.setNoWx(request.getNoWx());
			chatRequest.setAlias(request.getAlias());
			chatRequest.setSenderFlag(SenderFlag.ZK.getCode());
			
			
			//红包（不处理），zk已代发
			if(request.getType().equals(ChatInfoType.L_REDPACKET.getCode())) {
				chatRequest.setType(ChatInfoType.SYSTEM.getCode());
				chatRequest.setContent("你已领取一个红包");
			}
			//转账
			if(request.getType().equals(ChatInfoType.L_TRANSFER.getCode())) {
				chatRequest.setType(ChatInfoType.TRANSFER.getCode());
				chatRequest.setSenderFlag(SenderFlag.GM.getCode());
				Map<String,String> content = Maps.newHashMap();
				content.put("code", "");
				content.put("amt",request.getShareDes()+"");
				content.put("des","已收钱");
				content.put("type",ChatInfoType.TRANSFER.getCode());
				content.put("flag", "true");//领取标识
				chatRequest.setContent(JsonUtils.jsonFromObject(content));
				chatRequest.setCreateTime(new Date().getTime());
				
				
				Message systemMessage = new  Message(MessageCode.ChatInfoRequest, msgId,chatRequest.toJson());
				this.sendZkToGm(chatRequest, systemMessage, request.getNoWxGm(), pm.getMemberNoGm());		
			
			}
			
			/**
			Map<String,String> content = Maps.newHashMap();
			content.put("code", "");
			content.put("amt",request.getShareDes());
			content.put("des",request.getContent());
			
			//移动端APP非轮询，所以只要两种状态足够
			//红包
			if(request.getType().equals(ChatInfoType.L_REDPACKET.getCode())) {
			    chatRequest.setType(ChatInfoType.S_REDPACKET.getCode());
			    content.put("type",ChatInfoType.S_REDPACKET.getCode());
			}
			//转账
			if(request.getType().equals(ChatInfoType.L_TRANSFER.getCode())) {
				chatRequest.setType(ChatInfoType.TRANSFER.getCode());
				content.put("type",ChatInfoType.TRANSFER.getCode());
			}
			
			chatRequest.setContent(JsonUtils.jsonFromObject(content));**/
			//chatRequest.setContent(request.getContent());
			
		}
	}

	
	

	
	/**
	 * 发送红包或转账保存记录并转发系统消息告诉zk
	 * @param request
	 */
	private void sendRedPacket(ChatInfoRequest request,Message message, IoSession session) {
		if(request.getAmount() == null) {
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "金额不能为空！");
		}
		//元转分
		BigDecimal amount = request.getAmount().multiply(new BigDecimal("100"));
		
		int i =  amount.toString().indexOf(".");
		String amountStr = amount.toString();
		if(i > 0) {
			amountStr = amount.toString().substring(0, i);
		}
		WxRedpackDetailInfoDto wxRedpackDetailInfoDto = new WxRedpackDetailInfoDto();
		wxRedpackDetailInfoDto.setAmount(Long.valueOf(amountStr));
	
		if(request.getPwd() == null || request.getPwd().equals("")) {
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "支付密码不能为空！");
		}
		FindPersonMember findPersonMember = new FindPersonMember();
		findPersonMember.setMemberNo(request.getMemberNo());
		findPersonMember.setMemberNoGm(request.getMemberNoGm());
		findPersonMember.setShopWx(request.getNoWxGm());
		FindPersonMemberReturn personMemberBase = personMemberService.findPersonMemberByMemberNoAndGM(findPersonMember);
		if(personMemberBase == null) {
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "该客户未认领！");
		}
		
		if(! request.getNoWx().endsWith("@chatroom")) {
			FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(request.getNoWxGm());
			AssertUtils.notNull(findShopTerminalReturn,"终端不存在!");
			AssertUtils.notNullAndEmpty(findShopTerminalReturn.getWxPwd(),"终端未维护支付密码!");
			String oldPwd = WxPwdEncryptUtils.encrypt(request.getPwd(), findShopTerminalReturn.getWorkKey());
			if(!oldPwd.equals(findShopTerminalReturn.getWxPwd())) {
				throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "支付密码错误！");

			}
			
			if(!ChannelUtils.isLogin(session)) {
				throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "中控已离线！");
			}
//			if(!commonService.getClientStatus(request.getNoWxGm())) {
//				throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "中控已离线！");
//
//			}
			wxRedpackDetailInfoDto.setNoWx(request.getNoWx());
			wxRedpackDetailInfoDto.setNoWxShop(request.getNoWxGm());
			if(request.getContent() == null || request.getContent().equals("")) {
				wxRedpackDetailInfoDto.setContent("恭喜发财，大吉大利");
			}else {
			    wxRedpackDetailInfoDto.setContent(request.getContent());
			}
			wxRedpackDetailInfoDto.setMemberNo(request.getMemberNo());
			wxRedpackDetailInfoDto.setMemberNoGm(request.getMemberNoGm());
			
			if(personMemberBase.getMemberName() == null ) {
				wxRedpackDetailInfoDto.setMemberName(request.getMemberNickName());
			}else {
				wxRedpackDetailInfoDto.setMemberName(personMemberBase.getMemberName());
			}
			
			wxRedpackDetailInfoDto.setMemberNameGm(personMemberBase.getMemberNameGm());
			wxRedpackDetailInfoDto.setMerchantNo(personMemberBase.getMerchantNo());
			wxRedpackDetailInfoDto.setMsgId(message.getMsgId());
			//发送源：0 或null为系统， 1 APP,  2.WEB 
			String source = "";
			String messageType = "";
			if(request.getType().equals(ChatInfoType.TRANSFER.getCode())) {
				wxRedpackDetailInfoDto.setType(Integer.valueOf(RedPackTypeEnum.TRANS.getType()));//发送转账
				source = "1"; //发送源：0 或null为系统， 1 APP,  2.WEB 
				messageType = "2";//messageType:2 为转账
				wxRedpackDetailInfoService.sendWxRedpackByIm(wxRedpackDetailInfoDto, messageType, source); 
			}
			
			if(request.getType().equals(ChatInfoType.S_REDPACKET.getCode())) {
				source = "1"; //发送源：0 或null为系统， 1 APP,  2.WEB 
				messageType = "1";//messageType:1 为红包
				wxRedpackDetailInfoDto.setType(Integer.valueOf(RedPackTypeEnum.ADD.getType()));//发送红包
				wxRedpackDetailInfoService.sendWxRedpackByIm(wxRedpackDetailInfoDto, messageType, source); 
			}
			
	
			
		}else {
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "群聊不支持！");	
		}
		
	
	}
	
	/**
	 * 同步历史聊天记录
	 * @param request
	 */
	private void addOldChatInfo(ChatInfoRequest request,String code) {
		
		ImChatInfo imChatInfo = new ImChatInfo();
		imChatInfo.setCode(code);
        imChatInfo.setNoWxGm(request.getNoWxGm());
        imChatInfo.setNoWx(request.getNoWx());
        imChatInfo.setAlias(request.getAlias());
        imChatInfo.setSenderFlag(request.getSenderFlag());
        imChatInfo.setType(request.getType());
        imChatInfo.setStatus("2");
        imChatInfo.setChatTime(new Date(request.getCreateTime()));
        imChatInfo.setReceivedTime(new Date(request.getCreateTime()));
        imChatInfo.setContent(request.getContent());
        imChatInfo.setThirdReadFlag(ReadFlag.YES.getCode());
    	imChatInfo.setSenderSyncStatus(SenderSyncStatus.YES.getCode()); // 中控客户端发送消息,不用通过同步方式发送到导购客户端
    	imChatInfo.setCreateDate(new Date());
        imChatInfo.setMsgSource(MessageSource.ZK.getCode());
        imChatInfo.setRemark("同步聊天记录");
    	// 中控发的图片消息，content重置为null（实际上传内容：[收到一张图片]）
		if(ChatInfoType.IMG.getCode().equals(request.getType())) {
			request.setContent(null);
		} else if(ChatInfoType.CARD.getCode().equals(request.getType())) {	// 微信名片
			this.processCard(request);
		} else if(ChatInfoType.SMALL_PROGRAM.getCode().equals(request.getType())) {	// 小程序
			this.processSmallProgram(request);
			
		}
		//分享
		imChatInfo.setResourcesPath(request.getResources());
		/**
		 * 优惠券分享没有资源图片
		 * 兼容
		 */
		if(request.getType().equals(ChatInfoType.SHARE.getCode())
				&& StringUtils.isEmpty(request.getResources())
				&& request.getShareUrl().contains("oms-web/a/cp/h5coupon")) {
			String	couponUrlLogo = localCacheSystemParams.getSystemParam(SystemAliasName.api.name(), "logo", "couponLogoUrl");
			imChatInfo.setResourcesPath(couponUrlLogo);
		}
		imChatInfo.setShareTitle(request.getShareTitle());
		imChatInfo.setShareDes(request.getShareDes());
		imChatInfo.setShareUrl(request.getShareUrl());
		
         //单聊
		if(!request.getNoWx().endsWith("@chatroom")) {
			imChatInfo.setChatroomType(1);
//			FindPersonMemberBaseReturn person =  personMemberBaseService.findMemberBaseByNoWxOrAlias(request.getNoWx(), null);
			FindPersonMemberByChat findPersonMemberByChat = new FindPersonMemberByChat();
            findPersonMemberByChat.setNoWxGm(request.getNoWxGm());
            findPersonMemberByChat.setNoWx(request.getNoWx());
            FindPersonMemberByChatReturn findPersonMemberByChatReturn = personMemberImService.findPersonMemberByChat(findPersonMemberByChat);
	        //非群聊
	        if(findPersonMemberByChatReturn != null) {
	        	imChatInfo.setMemberNoGm(findPersonMemberByChatReturn.getMemberNoGm());
				imChatInfo.setMemberNameGm(findPersonMemberByChatReturn.getMemberNameGm());
				imChatInfo.setMemberNo(findPersonMemberByChatReturn.getMemberNo());
				imChatInfo.setMemberName(findPersonMemberByChatReturn.getMemberName());
	        }
		}else {
			//群聊转发给群聊创建导购
			//获取群聊信息,此时memberNo是群聊code
			FindChatRoom findChatRoom=  new FindChatRoom();
			findChatRoom.setNoWxZk(request.getNoWxGm());
			findChatRoom.setChatRoomName(request.getNoWx());
			FindChatRoomReturn chatRoomReturn=  chatRoomService.findChatRoomBySelective(findChatRoom);
			if(chatRoomReturn != null) {
				imChatInfo.setMemberNoGm(chatRoomReturn.getMemberNoGm());
				imChatInfo.setMemberNameGm(chatRoomReturn.getMemberNameGm());
				imChatInfo.setMemberNo(chatRoomReturn.getCode());
				imChatInfo.setMemberName(chatRoomReturn.getRoomNickName());
			}
			imChatInfo.setChatroomType(ChatRoomType.ROOM.getCode());
			imChatInfo.setChatroomNoWx(request.getGroupUserName());
		}
        
		FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(imChatInfo.getNoWxGm());
    	if(shopTerminalReturn != null) {
            imChatInfo.setMerchantNo(shopTerminalReturn.getMerchantNo());
            imChatInfo.setMerchantName(shopTerminalReturn.getMerchantName());
    	}
       
        imChatInfoService.addImOldChatInfo(imChatInfo);
    	return;
	}

	/**
	 * 群聊为空，同步并通知导购
	 * @param chatRoomReturn
	 * @param request
	 * @param message
	 * @param session
	 * @param connectSource
	 * @return
	 */
	private boolean chatRoomIsNull(FindChatRoomReturn chatRoomReturn,ChatInfoRequest request, Message message,IoSession session,ConnectSource connectSource,Channel channel) {
		logger.info("chatRoomIsNull request = {}",request);
		/**
		 * 群聊不存在，修改返回信息
		 */
		if(null ==chatRoomReturn) {	
			/**
			 * 立即同步该群
			 */
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
					syncChatRoomMessage.setNoWxZk(request.getNoWxGm());
					syncChatRoomMessage.setChatRoomName(request.getNoWx());
					syncChatRoomMessage.setNowSync(true);
					wxChatRoomService.sendSyncChatRoom(syncChatRoomMessage);
				}
			});
			if(connectSource == ConnectSource.GM) {
				ChatInfoResponse response = new ChatInfoResponse();
				response.setMsgId(message.getMsgId());
				response.setResult(Boolean.FALSE);
				response.setCode("CHAT_ROOM_IS_NULL");
				response.setMessage("此群已被解散！");
				Message failMessage = new Message(MessageCode.ChatInfoResponse, GUID.generateByUUID(), response.toJson());
				serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, failMessage);
			}
			return true;
		}else {
			/**
			 * 如果当前消息为10000系统修改群名称消息，则修改群名称，并返回已修改过的真实群名称
			 */
			if(request.getType().equals(ChatInfoType.SYSTEM.getCode()) && StringUtils.isNotEmpty(request.getContent())) {
				if(request.getContent().startsWith("你修改群名为")) {
					String newName =request.getContent().replaceAll("”", "").replaceAll("“","").replaceAll("你修改群名为", "");
					UpdateChatRoom updateChatRoom = new UpdateChatRoom();
					updateChatRoom.setCode(chatRoomReturn.getCode());
					updateChatRoom.setRoomNickName(newName);
					chatRoomService.updateChatRoom(updateChatRoom);
					chatRoomReturn.setRoomNickName(newName);
				}
				//群已移除，直接删除该群
				if(request.getContent().equals("该群已被移除")) {
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							DelChatRoom delChatRoom = new DelChatRoom();
							delChatRoom.setCode(chatRoomReturn.getCode());
							chatRoomService.delChatRoom(delChatRoom);
							DelChatRoomMember delChatRoomMember = new DelChatRoomMember();
							delChatRoomMember.setRoomCode(chatRoomReturn.getCode());
							chatRoomMemberService.delChatRoomMemberByCond(delChatRoomMember);
						}
					});
				}
			}
			
			//导购发的消息-判断是否是自己认领的群，不是则提示
			if(connectSource == ConnectSource.GM) {
				if(!request.getMemberNoGm().equals(chatRoomReturn.getMemberNoGm())) {
					throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "该群你未认领！");
				}
			} 
		}
		return false;
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
	public void sendZkToGm(ChatInfoRequest request, Message message, String noWxGm, String memberNoGm) {
		// 导购客户端登录，还需将不支持的消息类型转换为文本消息
		// TODO 以后导购客户端有新版支持新消息类型，还需做进一步兼容
		this.buildChatByUnsupport(request);	
		
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
					//serverManager.sendMessageAndCache(cacheAccountNo, channel, chatInfoRequestMessage);
					logger.info("导购客户端{}未登录，不通过Netty向其发送聊天记录：{}",memberNoGm, chatInfoRequestMessage.getMsgId());
					this.pushChatInfo(request);
				}
			}
	}
	
	
	/**
	 *
	 * 方法说明：导购客户端发给中控客户端
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
	private void sendGmToZk(ChatInfoRequest request, Message message, IoSession sessionRec, String reciveLoingAccountNo) {
		Message chatInfoRequestMessage = new Message(MessageCode.ChatInfoRequest, message.getMsgId(), request.toJson());
		
		// 当客户端登录时发送，未登录时当做离线记录不发送
		if (ChannelUtils.isLogin(sessionRec)) {
			serverManager.sendMessageNoCache(sessionRec.getChannel(), chatInfoRequestMessage);
			logger.info("已向中控{}发送聊天记录：{}", reciveLoingAccountNo, chatInfoRequestMessage.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不通过Netty向其发送聊天记录：{}", reciveLoingAccountNo, chatInfoRequestMessage.getMsgId());
		}
	}


	/**
	 * 添加聊天消息-不发送
	 * @param noWxGm
	 * @param memberNo
	 * @param content
	 */
	private void addImChatInfo( String noWxGm,  String memberNo, String content, String type,Integer senderFlag) {
		try {
			//发送消息
			
			AddImChatInfo addImChatInfo = new AddImChatInfo();
			addImChatInfo.setCode(GUID.generateCode());
			addImChatInfo.setSenderFlag(senderFlag);
			addImChatInfo.setNoWxGm(noWxGm);
			addImChatInfo.setMemberNo(memberNo);
			addImChatInfo.setType(type);
			addImChatInfo.setContent(content);
			addImChatInfo.setMsgSource(MessageSource.GM.getCode());
			addImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
			addImChatInfo.setChatroomNoWx(noWxGm);
			addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
			addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
			logger.info("发送系统消息：{}", addImChatInfo);
			imChatInfoService.addImChatInfo(addImChatInfo);//0 为非历史聊天记录
			
		} catch(Exception e) {
			logger.error("发送链接失败", e);
		}
	}
	
	
	/**
	 * 添加聊天消息-不发送
	 * @param noWxGm
	 * @param memberNo
	 * @param content
	 * @param senderSyncStatus
	 * @param status
	 */
	private void addImChatInfo(String code, String noWxGm,  String memberNo, String content, 
			String type,Integer senderFlag, Integer senderSyncStatus, String status) {
		try {
			//发送消息
			
			AddImChatInfo addImChatInfo = new AddImChatInfo();
			addImChatInfo.setCode(code);
			addImChatInfo.setSenderFlag(senderFlag);
			addImChatInfo.setNoWxGm(noWxGm);
			addImChatInfo.setMemberNo(memberNo);
			addImChatInfo.setType(type);
			addImChatInfo.setContent(content);
			addImChatInfo.setMsgSource(MessageSource.GM.getCode());
			addImChatInfo.setChatroomType(ChatRoomType.PERSONAL.getCode());
			addImChatInfo.setChatroomNoWx(noWxGm);
			addImChatInfo.setStatus(status);
			addImChatInfo.setSenderSyncStatus(senderSyncStatus);
			addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
			logger.info("发送系统消息：{}", addImChatInfo);
			imChatInfoService.addImChatInfo(addImChatInfo);//0 为非历史聊天记录
			
		} catch(Exception e) {
			logger.error("发送链接失败", e);
		}
	}
	
	/**
	 *
	 * 方法说明：保存小程序
	 *
	 * @param request
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 * 
	 */
	private void processSmallProgram(ChatInfoRequest request) {
		// {"appId":"wxde8ac0a21135c07d","appServiceType":0,"description":"享美食，爱玩乐，看电影","fwH":0,"iconUrl":"":"http://mmbiz.qpic.cn/mmbiz_png/nGDT6vKQ2Nfxibw0Y34kicUykImTzNUtTb4ITaNK4h7nicLeEaVvnB9flUQvpaM5XiaCibVxzPH82RbZYqbezIZxIBQ/0?wx_fmt\u003dpng","j","juq":0,"juu":false,"pagePath":"index/pages/mt/mt/mt.html","s","scene":1007,"sourceDisplayName":"美团丨外卖美食电影酒店门票健身","title":"美团","type":2,"url":"":"https://mp.weixin.qq.com/mp/waerrpage?appid\u003dwxde8ac0a21135c07d\u0026amp;type\u003dupgrade\u0026amp;upgradetype\u003d3#wechat_redirect","u","userName":"gh_870576f3c6f9@app","version":150,"withShareTicket":false}
		Map<String, String> paramMap = JsonUtils.mapFromJson(request.getContent());
		String wxParamBase64 = paramMap.get(Constants.WX_PARAM_KEY);	// 微信上传参数base64后保存在wxParam字段里
		if(StringUtils.isNotEmpty(wxParamBase64)) {	// 微信上传参数原样保存在wxParam字段里
			String wxParam = StringUtils.newStringUtf8(Base64.decode2(wxParamBase64));	// 先解码
			logger.debug("微信小程序解码后： {}", wxParam);
			Map<String, String> spParamMap = JsonUtils.mapFromJson(wxParam); 
			String appId = spParamMap.get("appId");
			paramMap.put("appId", appId);
			String pagePath = spParamMap.get("pagePath");
			String resources = spParamMap.get("iconUrl");		// 小程序logo
			request.setResources(resources);
			String shareTitle = spParamMap.get("sourceDisplayName");	// 小程序标题
			if(StringUtils.isEmpty(shareTitle)) {				// 标题为空，则取title
				shareTitle = spParamMap.get("title");
			}
			request.setShareTitle(shareTitle);
			String shareDes = spParamMap.get("description");	// 小程序描述
			if(StringUtils.isNotEmpty(shareDes)) {
				request.setShareDes(shareDes);
			} else {
				request.setShareDes(shareTitle);
			}
			String shareUrl = request.getShareUrl();			// 小程序大图（中控未解析则传空值）
			request.setShareUrl(shareUrl);
			
			FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(request.getNoWxGm());
			FindWxSmallProgram findWxSmallProgram = new FindWxSmallProgram();
			findWxSmallProgram.setNoWxZk(request.getNoWxGm());
			findWxSmallProgram.setSpAppid(appId);
			findWxSmallProgram.setSpPagePath(pagePath);
//			findWxSmallProgram.setShopNo(shopTerminalReturn.getShopNo());
			FindWxSmallProgramReturn findWxSmallProgramReturn = wxSmallProgramService.findByAppidAndNoWxZk(findWxSmallProgram);
			if(findWxSmallProgramReturn == null) {
				// 保存小程序
				AddWxSmallProgram addWxSmallProgram = new AddWxSmallProgram();
				addWxSmallProgram.setNoWxZk(request.getNoWxGm());
				addWxSmallProgram.setSpAppid(appId);
				addWxSmallProgram.setType("UNKNOWN");	// 默认为未分类
				addWxSmallProgram.setSpName(request.getShareTitle());
				addWxSmallProgram.setSpLogo(request.getResources());
				addWxSmallProgram.setSpDesc(request.getShareDes());
				addWxSmallProgram.setSpUrl(request.getShareUrl());
				addWxSmallProgram.setSpPagePath(pagePath);
				addWxSmallProgram.setWxParam(wxParamBase64);
				addWxSmallProgram.setStatus(1);
//				addWxSmallProgram.setShopNo(shopTerminalReturn.getShopNo());
//				addWxSmallProgram.setShopName(shopTerminalReturn.getShopName());
				addWxSmallProgram.setMerchantNo(shopTerminalReturn.getMerchantNo());
				addWxSmallProgram.setMerchantName(shopTerminalReturn.getMerchantName());
				addWxSmallProgram.setCreateDate(new Date());
				wxSmallProgramService.addWxSmallProgram(addWxSmallProgram);
				logger.info("已保存小程序信息: {}", addWxSmallProgram);
			} else {
				// 暂不做更新操作
			}
			
			request.setContent(JsonUtils.jsonFromObject(paramMap));	// 重新生成content
		}
	}


	/**
	 *
	 * 方法说明：处理名片类型的聊天消息
	 *
	 * @param request
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 * 
	 */
	private void processCard(ChatInfoRequest request) {
		// 公众号：<msg username="gh_875b74ad7518" nickname="深圳潮生活" alias="SZLife0755" fullpy="深圳潮生活" shortpy="SZCSH" imagestatus="3" scene="17" province="广东" city="深圳" sign="135万人的深圳潮生活。在这里你可以遍尝深圳的美食；结交新朋友；知道很多你不知道的深圳事……" percard="0" sex="0" certflag="8" certinfo="135万人的深圳潮生活。在这里你可以遍尝深圳的美食；结交新朋友；知道很多你不知道的深圳事……" certinfoext="" brandIconUrl="l="http://mmbiz.qpic.cn/mmbiz_png/HXWl5oShr4tn4y816AZkzQObCGibFIXbSQVHlSmAT1WlvFQJ6kwVrqZEFXZbT9fhLFNoAlmFsI88RAePkR3qiaJg/0?wx_fmt=png" br" brandHomeUrl="" brandSubscriptConfigUrl="" brandFlags="" regionCode="CN_Guangdong_Shenzhen"/>
		// 个人名片：<msg username="wxid_y167na5yqv8e52" nickname="阿K" alias="wzk1405010509" fullpy="阿K" shortpy="AK" imagestatus="0" scene="17" province="贵州" city="贵阳" sign="就像一件俗气的衣裳" percard="1" sex="1" certflag="0" certinfo="" certinfoext="" brandIconUrl="" brandHomeUrl="" brandSubscriptConfigUrl="" brandFlags="" regionCode="CN_Guizhou_Guiyang"/>
		Map<String, String> paramMap = JsonUtils.mapFromJson(request.getContent());
		String wxParamBase64 = paramMap.get(Constants.WX_PARAM_KEY);	// 微信上传参数base64后保存在wxParam字段里
		if(StringUtils.isNotEmpty(wxParamBase64)) {	// 名片的所有参数是xml格式
			String wxParam = StringUtils.newStringUtf8(Base64.decode2(wxParamBase64));	// 先解码再按xml格式进行解析
			logger.debug("微信名片解码后： {}", wxParam);
			String certflag = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_CERTFLAG);
			paramMap.put("certflag", certflag);	// 保存名片类型
			String username = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_USERNAME);
			paramMap.put("username", username);
			String usernamev2 = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_ANTISPAMTICKET);	// 识别个人名片并添加好友时需要该参数
			paramMap.put("usernamev2", usernamev2);
			String alias = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_ALIAS);
			paramMap.put("alias", alias);
			String nickname = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_NICKNAME);
			request.setShareTitle(nickname);
			if("0".equals(certflag)) {	// 个人名片
				String smallheadimgurl = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_SMALLHEADIMGURL);
				if(StringUtils.isNotEmpty(smallheadimgurl)) {	// 优先使用小头像
					request.setResources(smallheadimgurl);
				} else {	// 小头像为空时，获取大头像
					request.setResources(WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_BIGHEADIMGURL));
				}
				String sign = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_SIGN);
				request.setShareDes(sign);
			} else {	// 公众号名片
				String brandIconUrl = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_BRANDICONURL);
				request.setResources(brandIconUrl);
				String certinfo = WeixinXMLParser.getPropertyValue(wxParam, WeixinXMLParser.REGEX_CARD_CERTINFO);
				request.setShareDes(certinfo);
				
				FindShopTerminalReturn shopTerminalReturn = shopTerminalService.findShopTerminalNormal(request.getNoWxGm());
				FindWxPublicAccount findWxPublicAccount = new FindWxPublicAccount();
				findWxPublicAccount.setNoWxZk(request.getNoWxGm());
				findWxPublicAccount.setPaUsername(username);
//				findWxPublicAccount.setShopNo(shopTerminalReturn.getShopNo());
				findWxPublicAccount.setMerchantNo(shopTerminalReturn.getMerchantNo());
				FindWxPublicAccountReturn findWxPublicAccountReturn = wxPublicAccountService.findByUsernameAndNoWxZk(findWxPublicAccount);
				if(findWxPublicAccountReturn == null) {
					// 保存公众号
					AddWxPublicAccount addWxPublicAccount = new AddWxPublicAccount();
					addWxPublicAccount.setNoWxZk(request.getNoWxGm());
					addWxPublicAccount.setPaUsername(username);
					addWxPublicAccount.setPaAlias(alias);
					addWxPublicAccount.setPaName(nickname);
					addWxPublicAccount.setPaLogo(brandIconUrl);
					addWxPublicAccount.setPaDesc(certinfo);
					addWxPublicAccount.setPaCertflag(certflag);
					addWxPublicAccount.setWxParam(wxParamBase64);	// 保存报文原文，即base64解码前的wxParam
					addWxPublicAccount.setStatus(1);
//					addWxPublicAccount.setShopNo(shopTerminalReturn.getShopNo());
//					addWxPublicAccount.setShopName(shopTerminalReturn.getShopName());
					addWxPublicAccount.setMerchantNo(shopTerminalReturn.getMerchantNo());
					addWxPublicAccount.setMerchantName(shopTerminalReturn.getMerchantName());
					addWxPublicAccount.setCreateDate(new Date());
					wxPublicAccountService.addWxPublicAccount(addWxPublicAccount);
					logger.info("已保存公众号信息: {}", addWxPublicAccount);
				} else {
					// 暂不做更新操作
				}
			}
			
			request.setContent(JsonUtils.jsonFromObject(paramMap));	// 重新生成content
		} else {	// 格式不正确
			// TODO
		}
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：导购离线且导购上次登录设备为iOS，则通过极光推送提醒导购
	 *
	 * @param request
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月17日
	 *
	 */
	public void pushChatInfo(ChatInfoRequest request) {
//		logger.info("notify: " + request.toString());
		// 没有导购编号
		if(StringUtils.isEmpty(request.getMemberNoGm())) {
			return;
		}
		
		try {
			logger.info("导购未登录，转为极光推送提醒导购");
			// 查询导购最后一次登录信息
			FindMemberLoginInfoReturn findMemberLoginInfoReturn = memberLoginInfoService.findLastMemberLoginInfo(request.getMemberNoGm());
			if(findMemberLoginInfoReturn == null) {
				return;
			}
			
			// 如果导购最后一次登录使用的设备是iPhone，才提醒导购
			if(EquipmentType.IPHONE.name().equals(findMemberLoginInfoReturn.getEquipment()) && StringUtils.isNotEmpty(findMemberLoginInfoReturn.getMac())) {
				// 查询导购信息
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(request.getMemberNoGm());
				FindGuidMemberReturn guidMember = guidMemberService.findGuidMember(findGuidMember);
				
				AddNotifyInfo addNotifyInfo = new AddNotifyInfo();
				addNotifyInfo.setMerchantNo(guidMember.getMerchantNo());
				addNotifyInfo.setMemberNo(guidMember.getMemberNo());
				addNotifyInfo.setMemberName(guidMember.getMemberName());
				addNotifyInfo.setMemberType("GUID");
				addNotifyInfo.setSendType(SendType.SINGLE.name());
				addNotifyInfo.setSysType("IOS");
//				addNotifyInfo.setMobile(guidMember.getMobile());
				addNotifyInfo.setMobile(findMemberLoginInfoReturn.getMac());
				addNotifyInfo.setContent(buildPushJGContent(request));
				logger.info("向导购推送消息提醒：{}", addNotifyInfo);
				notifyService.sendMsgInfo(addNotifyInfo);
			}
		} catch(Exception e) {
			logger.error("通过极光推送提醒导购新聊天消息失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：重新构建导购客户端不支持的消息类型，兼容后发送一条文本消息
	 *
	 * @param request
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	private void buildChatByUnsupport(ChatInfoRequest request) {
		if(StringUtils.isNotEmpty(request.getShareTitle())) {
			request.setShareTitle(StringUtils.converJson(request.getShareTitle()));
		}
		if(StringUtils.isNotEmpty(request.getShareDes())) {
			request.setShareDes(StringUtils.converJson(request.getShareDes()));
		}
//		String content = null;
//		if(ChatInfoType.CARD.getCode().equals(request.getType())) {
//			content = "[收到一张微信名片：" + request.getShareTitle() + "]";
//		} else {
			return;
//		}
//		request.setType(ChatInfoType.TEXT.getCode());
//		request.setContent(content);
//		request.setResources(null);
//		request.setShareTitle(null);
//		request.setShareDes(null);
//		request.setShareUrl(null);
//		logger.info("导购客户端不支持的消息类型，转换为文本消息发送：{}", request);
	}
	
	/**
	 * 
	 *
	 * 方法说明：构建极光推送内容
	 *
	 * @param request
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	private String buildPushJGContent(ChatInfoRequest request) {
		String content = null;
		if(ChatInfoType.TEXT.getCode().equals(request.getType())) {
			content = request.getContent();
		} else if(ChatInfoType.IMG.getCode().equals(request.getType())) {
			content = "[图片]";
		} else if(ChatInfoType.VOICE.getCode().equals(request.getType())) {
			content = "[语音]";
		} else if(ChatInfoType.CARD.getCode().equals(request.getType())) {
			content = "[微信名片]";
		} else if(ChatInfoType.VIDEO.getCode().equals(request.getType())) {
			content = "[视频]";
		} else if(ChatInfoType.IMGBROW.getCode().equals(request.getType())) {
			content = "[图片表情]";
		} else if(ChatInfoType.S_REDPACKET.getCode().equals(request.getType())) {
			content = "[收到一个红包]";
		} else if(ChatInfoType.SMALL_PROGRAM.getCode().equals(request.getType())) {
			content = "[收到一个小程序]";
		} else if(StringUtils.isNotEmpty(request.getShareTitle())) {
			content = "[链接]：" + request.getShareTitle();
		} else {
			content = "[收到一条消息]";
		}
		logger.info(content);
		return content;
	}
	
	
	public static void main(String[] args) {
//		String jsonString = "<?xml version=\"1.0\"?><msg bigheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/nPkibeUU1EE9bPJ27t5jBAv8RibibT0QIU6XU6iclA0t5KbuwSwer8iaJZ79G2f6icaQ8Yyq14azHX2zjx2aoRzkyrTQ/0\" smallheadimgurl=\"http://wx.qlogo.cn/mmhead/ver_1/nPkibeUU1EE9bPJ27t5jBAv8RibibT0QIU6XU6iclA0t5KbuwSwer8iaJZ79G2f6icaQ8Yyq14azHX2zjx2aoRzkyrTQ/96\" username=\"v1_0b8888117115d7eb46c4f9b48b410affd80839469c43d87ee19d523bf589e6069003ad62ca8917b63d5dc5f59a7f7b4e@stranger\" nickname=\"安家欧洲城店-黑夜\" fullpy=\"anjiaouzhouchengdianheiye\" shortpy=\"\" alias=\"\" imagestatus=\"3\" scene=\"17\" province=\"广东\" city=\"中国\" sign=\"\" sex=\"1\" certflag=\"0\" certinfo=\"\" brandIconUrl=\"\" brandHomeUrl=\"\" brandSubscriptConfigUrl=\"\" brandFlags=\"0\" regionCode=\"CN_Guangdong_Shenzhen\" antispamticket=\"v2_3780b609601580207bf9e13a3900b221ec3f409add232035b1e9a6dd8f4ee1dbbe196e2cb5fb84f02332f18e96a5d096@stranger\" />";
//		Map<String, String> paramMap = new HashMap<String, String>();
//		String certflag = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_CERTFLAG);
//		paramMap.put("certflag", certflag);	// 保存名片类型
//		String username = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_USERNAME);
//		paramMap.put("username", username);
//		String alias = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_ALIAS);
//		paramMap.put("alias", alias);
//		String brandIconUrl = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_NICKNAME);
//		paramMap.put("brandIconUrl", brandIconUrl);
//		String nickname = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_NICKNAME);
//		paramMap.put("nickname", nickname);
//		String sign = WeixinXMLParser.getPropertyValue(jsonString, WeixinXMLParser.REGEX_CARD_SIGN);
//		paramMap.put("sign", sign);
//		System.out.println(paramMap.toString());
		String base64 = "eyJhcHBJZCI6Ind4ZWJmNzczNjkxOTA0ZWVlOSIsImFwcFNlcnZpY2VUeXBlIjowLCJkZXNjcmlwdGlvbiI6Ilx1MDAyNnF1b3Q75oiR5Y+R546w5LiA5Liq5Lmw55Sf6bKc55qE5aW95Zyw5pa577yMMuWwj+aXtuWwseiDvemAgeWIsO+8gVx1MDAyNnF1b3Q7IiwiZndIIjowLCJpY29uVXJsIjoiaHR0cDovL21tYml6LnFwaWMuY24vbW1iaXpfcG5nL2duclBLbUFUQ0t2bks4M3pMc0ZEVk43THE1eXVZTm53M1F3bkJQQ2xQYjh4TkhuUHNTV1NTd0ppYlZzbXdHaWFYcUpMdnJ6VFQwR3NySWZBcTR1dzRWeUEvMD93eF9mbXRcdTAwM2RwbmciLCJqdXEiOjAsImp1dSI6ZmFsc2UsInBhZ2VQYXRoIjoicGFnZXMvaW5kZXgvaW5kZXguaHRtbD9hbGRfc2hhcmVfc3JjXHUwMDNkMTUzNDEyMTczMjgwNjY5Mjg2ODQiLCJzY2VuZSI6MTAwNywic291cmNlRGlzcGxheU5hbWUiOiLmr4/ml6XkvJjpspwiLCJ0aXRsZSI6Iuavj+aXpeS8mOmynCIsInR5cGUiOjMsInVybCI6Imh0dHBzOi8vbXAud2VpeGluLnFxLmNvbS9tcC93YWVycnBhZ2U/YXBwaWRcdTAwM2R3eGViZjc3MzY5MTkwNGVlZTlcdTAwMjZhbXA7dHlwZVx1MDAzZHVwZ3JhZGVcdTAwMjZhbXA7dXBncmFkZXR5cGVcdTAwM2QzI3dlY2hhdF9yZWRpcmVjdCIsInVzZXJOYW1lIjoiZ2hfMDVjODVhNTNjN2VlQGFwcCIsInZlcnNpb24iOjEzMiwid2l0aFNoYXJlVGlja2V0IjpmYWxzZX0=";
		String wxParam = StringUtils.newStringUtf8(Base64.decode2(base64));
		System.out.println(wxParam);
	}

}
