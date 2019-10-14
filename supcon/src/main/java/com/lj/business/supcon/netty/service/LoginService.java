package com.lj.business.supcon.netty.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.im.FindMaxVersion;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.shopterminal.TerminalSign;
import com.lj.business.member.dto.shopterminal.TerminalSignReturn;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginRequest;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginResponse;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.chat.OfflineChatInfo;
import com.lj.business.supcon.netty.message.login.LoginRequest;
import com.lj.business.supcon.netty.message.login.LoginResult;
import com.lj.business.supcon.netty.message.sign.SignResponse;
import com.lj.business.supcon.service.impl.TokenService;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoGroup;
import com.lj.business.weixin.dto.imchat.FindOfflineChatInfoReturn;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImEmojiPackageService;
import com.lj.distributecache.IDistributeCache;

import io.netty.channel.Channel;

/**
 * 
 * 
 * 类说明：IM登录实现类
 * 
 * <p>
 * 详细描述：
 * <br>1、登录身份验证
 * <br>2、登录账户与连接session绑定：导购客户端使用导购编号，中控客户端使用手机串号
 * <br>3、登录成功，则返回客户基本信息：当前所属客户最大版本号（用于客户端增量更新）等等
 * <br>4、异步发送客户端未接收的报文消息（聊天记录等除外）
 * <br>5、
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
@Service
public class LoginService implements IService<LoginRequest> {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Resource 
	private IDistributeCache distributeCache;
	
	@Resource
	private IGuidMemberService guidMemberService;
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Resource
	private IPersonMemberService personMemberService;
	
	@Resource
	private IPersonMemberImService personMemberImService;
	
//	@Autowired
//	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private IImEmojiPackageService imEmojiPackageService;
	@Autowired
	private ITerminalImStatusService terminalImStatusService;
	@Resource
	private TokenService tokenService;
	@Autowired
	private IShopTerminalService shopTerminalService;
	
	
	@Override
	public void process(LoginRequest request, Message message, Channel channel) {
		logger.info("登录报文 request={}",request);
		AssertUtils.notNull(request, "参数为空");
		AssertUtils.notNull(request.getToken(), "访问令牌为空");
		AssertUtils.notNull(request.getType(), "登录类型为空");
		final ConnectSource connectSource = ConnectSource.valueOf(request.getType());
		if (ConnectSource.GM == connectSource) {
			AssertUtils.notNull(request.getNoWx(), "微信号为空");
		} else {
			AssertUtils.notNull(request.getUsernameWx(), "微信号为空");
		}
		
		// token校验
		MemberLoginCache memberCache = null;
		try {
			memberCache = tokenService.parseMember(request.getToken(), Token.TOKEN_TIMEOUT_SECONDS);
		} catch(TsfaServiceException e) {
			logger.error(e.getExceptionInfo(), e);
			ResponseCode responseCode = ResponseUtils.getErrorResponse(e);
			LoginResult loginResult = new LoginResult();
			loginResult.setResult(Boolean.FALSE);
			loginResult.setCode(responseCode.getCode());
			loginResult.setMessage(responseCode.getMessage());
			Message loginResultMessage = new Message(MessageCode.LoginResult, loginResult.toJson());
			serverManager.sendMessageNoCache(channel, loginResultMessage);	// 登录结果报文不做缓存
			return;
		}
		
		// 请求终端登录服务
		TerminalImLoginRequest tidLoginRequest = new TerminalImLoginRequest();
		if (ConnectSource.GM == connectSource) {
			tidLoginRequest.setMemberNoGm(memberCache.getCode());
		} else {
			tidLoginRequest.setTerminalCode(memberCache.getCode());
		}
		tidLoginRequest.setType(request.getType());
		tidLoginRequest.setVersionCode(request.getVersionCode());
		tidLoginRequest.setVersionName(request.getVersionName());
		tidLoginRequest.setWxVersionCode(request.getWxVersionCode());
		tidLoginRequest.setWxVersionName(request.getWxVersionName());
		tidLoginRequest.setUsernameWx(request.getUsernameWx());
		tidLoginRequest.setNoWx(request.getNoWx());
		TerminalImLoginResponse tidLoginResponse = terminalImStatusService.login(tidLoginRequest);
		FindGuidMemberReturn findGuidMemberReturn = tidLoginResponse.getFindGuidMemberReturn();
		FindShopTerminalReturn findShopTerminalReturn= tidLoginResponse.getFindShopTerminalReturn();
		
		// 登录账号
		/** 导购端登录是：导购编号 - memberNoGm/
		 * 	中控端登录是：终端号 - terminalCode.
		 *  */
		String loginAccountNo = memberCache.getCode();
		logger.info("Terminal[{}] login response: {}", loginAccountNo, tidLoginResponse);
		
		// 获取客户端上次登录登出时间，并以此时间查询客户端离线聊天记录(先获取上次登录登出时间再注册会话，要不然获取的就是本次登录的时间了)
//		ClientLoginStatus loginStatus = serverManager.getLastStatus(loginAccountNo);
		Date lastLoginTime = null;	// 最后一次登录登出时间
//		if(loginStatus != null) {
//			lastLoginTime = loginStatus.getLastTime();
//		}

		String noWx;
		IoSession session = null;
		if (ConnectSource.GM == connectSource) {	//导购当前切换的微信号
			noWx = request.getNoWx();
		} else {
			noWx = findShopTerminalReturn.getNoWx();
			// 注册会话：登录账户与session绑定、映射
			  
		}
		// 注册会话：登录账户与session绑定、映射
		session = serverManager.login(tidLoginResponse, connectSource, noWx, channel);
		final IoSession  iosession = session;
		// 从缓存中获取当前登录账户未发送的消息记录(不包括聊天记录等)
		final List<Message> messageList = serverManager.getCacheBySession(iosession);
		
		// 客户版本号，导购所属客户最大版本号
		FindMaxVersion findMaxVersion = new FindMaxVersion();
		findMaxVersion.setNoWxGm(noWx);
		if (ConnectSource.GM == connectSource) {
			findMaxVersion.setMerchantNo(findGuidMemberReturn.getMerchantNo());
		}else{
			findMaxVersion.setMerchantNo(findShopTerminalReturn.getMerchantNo());
		}
		long memberVersion = personMemberImService.findMaxVersion(findMaxVersion);
		
		// 表情包版本号(迁移至app登录返回)
		long emojiVersion = imEmojiPackageService.findMaxVersion();
		
		// 登录成功，向客户端发送登录成功消息
		LoginResult loginResult = new LoginResult();
		loginResult.setMemberVersion(memberVersion);			// 客户最大版本号，用于增量下载通讯录
		loginResult.setEmojiVersion(emojiVersion); 				// 表情包最大版本号，用于增量下载包情
		loginResult.setTimestamp(System.currentTimeMillis());	// 服务器当前时间戳
		logger.info("Terminal[{}] login success, result object: {}", loginAccountNo, loginResult);
		Message loginResultMessage = new Message(MessageCode.LoginResult, loginResult.toJson());
		serverManager.sendMessageNoCache(channel, loginResultMessage);	// 登录结果报文不做缓存
		
		// 异步发送登录账户未发送的报文消息记录
		if(messageList != null && messageList.size() > 0) {
			logger.info("当前登录账户[ {} ]有[ {} ]条未发送报文消息，即将发送给{}", loginAccountNo, messageList.size(), connectSource.getName());
			for(Message msg : messageList) {
				// 离线聊天记录不通过netty发送给客户端，而是由客户端主动拉取
				if(msg.getCode() != MessageCode.ChatInfoRequest) {
					try {
						serverManager.sendMessageNoCache(iosession.getChannel(), msg);	// 报文消息不需要再做一次缓存
					} catch (Exception e) {
						logger.error("向" + connectSource.getName() + "发送离线消息报文[" + msg + "]失败", e);
					}
				}
			}
		}
		
		// 客户端有未接收的离线聊天记录，重新向客户端发送
		this.sendOfflineChat(lastLoginTime, iosession);
		
		if (ConnectSource.ZK == connectSource) {
			try {
				TerminalSign terminalSign = new TerminalSign();
				terminalSign.setNoWx(noWx);
				TerminalSignReturn terminalSignReturn = shopTerminalService.sign(terminalSign);
				logger.info("终端{}签到成功：{}", terminalSign.getNoWx(), terminalSignReturn);

				// 返回签到结果
				SignResponse signResponse = new SignResponse();
				signResponse.setEncrypt(terminalSignReturn.getEncrypt());
				signResponse.setTimestamp(terminalSignReturn.getTimestamp());
				signResponse.setToken(terminalSignReturn.getToken());
				Message responseMessage = new Message(MessageCode.SignResponse, signResponse.toJson());
				serverManager.sendMessageNoCache(channel, responseMessage);
				logger.info("已向{}中控客户端下发签到命令：{}", noWx, message);
			} catch (Exception e) {
				logger.error("下发签到结果失败 e={}",e);
			}
		}
	}

	/**
	 * 
	 *
	 * 方法说明：发送离线聊天记录
	 *
	 * @param lastLoginTime
	 * @param session
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月8日
	 *
	 */
	private void sendOfflineChat(Date lastLoginTime, IoSession session) {
		// 查询离线聊天记录
		FindOfflineChatInfo findOfflineChatInfo = new FindOfflineChatInfo();
		findOfflineChatInfo.setLastLoginTime(lastLoginTime);
		FindOfflineChatInfoReturn findOfflineChatInfoReturn = null;
		if (ConnectSource.GM == session.getConnectSource()) {	// 如果来源为导购，则查询客户发给导购的离线聊天记录（即未发送记录）
			findOfflineChatInfo.setMemberNoGm(session.getMemberNoGm());
			findOfflineChatInfo.setNoWxGm(session.getNoWxGm());
			findOfflineChatInfo.setClientFlag(SenderFlag.GM.getCode());	// 导购客户端
			findOfflineChatInfoReturn = imChatInfoService.findOfflineChatInfo(findOfflineChatInfo);
		}else{	// 如果来源为中控，则查询导购发送客户的离线聊天记录（即未发送记录）
			findOfflineChatInfoReturn = new FindOfflineChatInfoReturn();
			findOfflineChatInfo.setClientFlag(SenderFlag.ZK.getCode());	// 中控客户端，则按其绑定的微信号查询
			for(String noWxGm : session.getZkNoWxList()) {	// 循环查询所有绑定微信的离线聊天记录
				findOfflineChatInfo.setNoWxGm(noWxGm);	
				findOfflineChatInfoReturn.setGroupList(new ArrayList<FindOfflineChatInfoGroup>());
				FindOfflineChatInfoReturn offlineChatReturn = imChatInfoService.findOfflineChatInfo(findOfflineChatInfo);
				if(offlineChatReturn != null && offlineChatReturn.getTotal() > 0) {
					findOfflineChatInfoReturn.setTotal(findOfflineChatInfoReturn.getTotal() + offlineChatReturn.getTotal());
					findOfflineChatInfoReturn.getGroupList().addAll(offlineChatReturn.getGroupList());
				}
			}
		}
		
		// 将离线聊天记录的最早时间和最晚时间缓存起来，客户端接到离线消息后响应通讯报文，从缓存中获取并更新这些离线聊天记录为已发送
		if(findOfflineChatInfoReturn != null && findOfflineChatInfoReturn.getTotal() > 0) {
			logger.info("当前登录账户[ {} ]有[ {} ]条未发送离线聊天记录，即将异步发送给{}", session.getLoginAccountNo(), findOfflineChatInfoReturn.getTotal(), session.getConnectSource().getName());
			
			ReceivedOfflineChatInfo receivedOfflineChatInfo = new ReceivedOfflineChatInfo();
			receivedOfflineChatInfo.setClientFlag(findOfflineChatInfo.getClientFlag());
			receivedOfflineChatInfo.setNoWxGm(session.getNoWxGm());
			if(session.getConnectSource() == ConnectSource.GM) {
				receivedOfflineChatInfo.setMemberNoGm(session.getMemberNoGm());
			}
//			Date begin = null, end = null;
			//按客户分批推送
			for (FindOfflineChatInfoGroup group : findOfflineChatInfoReturn.getGroupList()) {
//				if(begin == null) {
//					begin = group.getChatList().get(0).getChatTime();// 此客户离线记录的最早时间-获取第一条获取一次就好
//				}
//				end = group.getChatList().get(group.getChatList().size() - 1).getChatTime();// 此客户离线记录的最晚时间
				
				// 导购客户端登录，还需将不支持的消息类型转换为文本消息
				// TODO 以后导购客户端有新版支持新消息类型，还需做进一步兼容
				if(session.getConnectSource() == ConnectSource.GM) {
					this.buildChatByUnsupport(group);
				}
				
				//离线推送报文组装
				OfflineChatInfo offlineChatInfo = new OfflineChatInfo();
				offlineChatInfo.setTotal(group.getChatList().size());
				List<FindOfflineChatInfoGroup> groupList=new ArrayList<FindOfflineChatInfoGroup>();
				groupList.add(group);
				offlineChatInfo.setGroupList(groupList);
				Message offlineMessage = new Message(MessageCode.OfflineChatInfo, offlineChatInfo.toJson());
				serverManager.sendMessageNoCache(session.getChannel(), offlineMessage);	// 不缓存该消息
				
			}
			distributeCache.set(Constants.OFFLINE_CHAT_CACHE_PREFIX , JsonUtils.jsonFromObject_LongToString(receivedOfflineChatInfo));
//			receivedOfflineChatInfo.setChatTimeBegin(begin);
//			receivedOfflineChatInfo.setChatTimeEnd(end);
			
//			OfflineChatInfo offlineChatInfo = new OfflineChatInfo();
//			offlineChatInfo.setTotal(findOfflineChatInfoReturn.getTotal());
//			offlineChatInfo.setGroupList(findOfflineChatInfoReturn.getGroupList());
//			Message offlineMessage = new Message(MessageCode.OfflineChatInfo, offlineChatInfo.toJson());
//			distributeCache.set(Constants.OFFLINE_CHAT_CACHE_PREFIX + offlineMessage.getMsgId(), JsonUtils.jsonFromObject_LongToString(receivedOfflineChatInfo));
//			serverManager.sendMessageNoCache(session.getChannel(), offlineMessage);	// 不缓存该消息
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：重新构建导购客户端不支持的消息类型，兼容后发送一条文本消息
	 *
	 * @param group
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月5日
	 *
	 */
	private void buildChatByUnsupport(FindOfflineChatInfoGroup group) {
//		String content = null;
//		for (FindOfflineChatInfoDetail chat : group.getChatList()) {
//			if(ChatInfoType.CARD.getCode().equals(chat.getType())) {
//				content = "[收到一张微信名片：" + chat.getShareTitle() + "]";
//			} else {
				return;
//			}
//			chat.setType(ChatInfoType.TEXT.getCode());
//			chat.setContent(content);
//			chat.setResources(null);
//			chat.setShareTitle(null);
//			chat.setShareDes(null);
//			chat.setShareUrl(null);
//			logger.info("导购客户端不支持的消息类型，转换为文本消息发送：{}", chat);
//		}
	}
}
