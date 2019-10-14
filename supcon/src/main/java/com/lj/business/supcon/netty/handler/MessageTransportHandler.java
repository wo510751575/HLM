package com.lj.business.supcon.netty.handler;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.member.dto.terminalimstatus.TerminalImLogoutRequest;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.common.SpringContext;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.SessionCloseReason;
import com.lj.business.supcon.netty.message.BaseDto;
import com.lj.business.supcon.netty.message.BaseResponse;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.common.CommonResponse;

/**
 * 
 * 
 * 类说明：消息处理器，所有消息报文都在此接收并转发到业务服务，同时发送消息报文给客户端
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
@Component
@Sharable	// 所有连接共享一个handler
public class MessageTransportHandler extends SimpleChannelInboundHandler<Message> {

	private final static Logger logger = LoggerFactory.getLogger(MessageTransportHandler.class);
	
	// 客户端超时次数
//	private static Map<ChannelHandlerContext,Integer> clientTimeoutMap = new ConcurrentHashMap<>();
	
	// 重连最大次数
//	private final int MAX_RECONNECT_TIMES = 10;
	
	@Resource
	private ServerManager serverManager;
	
	@Autowired
	private ITerminalImStatusService terminalImStatusService;
	
	/**
	 * 覆盖channelActive方法在channel被启用的时候触发（在建立连接的时候）
	 * 此处用handlerAdded方法是否更合适？
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if (!ChannelUtils.addSession(ctx.channel(), new IoSession(ctx.channel()))) {
			ctx.channel().close();
			logger.error("Duplicate session, IP=[{}], channelId=[{}]",ChannelUtils.getIp(ctx.channel()), ctx.channel().id());
		}
		logger.info("New connect active：{}, Total connect: {}", ctx.toString(), ChannelUtils.getConnectCount());
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message requestMessage) throws Exception {
		final Channel channel = ctx.channel();
		IoSession session = ChannelUtils.getSession(channel);
		logger.info("Received message[{} - {} - {}]: {}", session.getLoginAccountNo(), session.getIpAddr(), channel.id(), requestMessage);

		// 前置处理
		if(!preprocess(requestMessage, channel)) {
			return;
		}
		
		// 业务处理（XXX 高并发情况下，应使用线程池异步处理业务逻辑）
		Message responseMessage = this.process(requestMessage, channel);
		// 向客户端发送消息
		if(responseMessage != null) {
			serverManager.sendMessageNoCache(channel, responseMessage);
		}
		
		// 只要接受到数据包，则清空超时次数
//		clientTimeoutMap.remove(ctx);
	}
	
	/**
	 * 
	 *
	 * 方法说明：前置处理
	 *
	 * @param requestMessage
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月16日
	 *
	 */
	private boolean preprocess(Message requestMessage, Channel channel) {
		// 报文为空
		if(requestMessage == null) {
			return Boolean.FALSE;
		}
		
		// 除登录和通讯响应报文外，其他报文消息必须登录
		if(requestMessage.getCode() != MessageCode.LoginRequest && requestMessage.getCode() != MessageCode.CommonResponse) {
			// 会话不存在
			if(!ChannelUtils.validateSession(channel)) {
				logger.error("非法请求：session失效，请重新登录[{} - {}]", ChannelUtils.getIp(channel), channel.id());
//				logger.error("当前所有登录账号：{}", ChannelUtils.getAccount2SessionMap());
				ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.SESSION_INVAILD);
				CommonResponse commonResponse = new CommonResponse(requestMessage.getCode().getCode());
				commonResponse.setResult(Boolean.FALSE);
				commonResponse.setCode(responseCode.getCode());
				commonResponse.setMessage(responseCode.getMessage());
				Message responseMessage = new Message(MessageCode.CommonResponse, requestMessage.getMsgId(), commonResponse.toJson());
				serverManager.sendMessageNoCache(channel, responseMessage);	// 没有缓存报文消息
//				this.sendFailMessage(requestMessage, ErrorCode.SESSION_INVAILD, channel);
				
				// 主动关闭链接
				/*if(channel.isActive()) {
					channel.close();
				}*/
				return Boolean.FALSE;
			}
		}
		
		// 通讯响应报文无需响应通讯结果
		if(requestMessage.getCode() != MessageCode.CommonResponse/* && requestMessage.getCode() != MessageCode.HeartBeatRequest*/) {
			// 先缓存消息再给回执 TODO
			
			Message message = new Message(MessageCode.CommonResponse, requestMessage.getMsgId(), new CommonResponse(requestMessage.getCode().getCode()).toJson());
			serverManager.sendMessageNoCache(channel, message);
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * 
	 *
	 * 方法说明：业务处理，异常信息尽量在服务实现类里处理，特别是中控客户端有微信分身的情况下，此处无法识别中控客户端是以哪个绑定微信发出的报文
	 *
	 * @param requestMessage
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Message process(Message requestMessage, Channel channel) {
		short messageCode = requestMessage.getCode().getCode();	// 消息编码
		BaseDto request = null;
		if(requestMessage.getCode().isJsonCoverToObject()) {	// 将消息体从JSON格式字符串转换为指定对象
			Class requestObjectClass = requestMessage.getCode().getMessageClass();
			request = (BaseDto) JsonUtils.objectFromJson(requestMessage.getBody(), requestObjectClass);
		}
		
		// 根据消息编码,从spring容器中获取对应的业务处理服务实例
		IService service = SpringContext.getBean(MessageCode.getServiceClassByCode(messageCode));
		
		BaseResponse response = null;	// 业务响应结果参数
		try {
			service.process(request, requestMessage, channel);
			return null;
		} catch(IllegalArgumentException e) {
			logger.error("参数异常", e);
			response = ResponseUtils.generateFailureResponse(e);
		} catch(TsfaServiceException e) {
			logger.error("业务异常", e);
			response = ResponseUtils.generateFailureResponse(e);
		} catch(Exception e){
			logger.error("系统异常", e);
			response = ResponseUtils.generateFailureResponse(e);
		}
		
		if(response != null && MessageCode.exist(requestMessage.getCode().getRefCode())) {
			logger.info("Response Business Result: {}", response);
			MessageCode responseCode = MessageCode.getMessageCodeByCode(requestMessage.getCode().getRefCode());
			return new Message(responseCode, requestMessage.getMsgId(), response.toJson());
		}
		return null;
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送业务处理失败消息
	 *
	 * @param requestMessage
	 * @param errorCode
	 * @param channel
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月20日
	 *
	 */
	/*private void sendFailMessage(Message requestMessage, String errorCode, Channel channel) {
		if(MessageCode.exist(requestMessage.getCode().getRefCode())) {
			MessageCode responseMessageCode = MessageCode.getMessageCodeByCode(requestMessage.getCode().getRefCode());
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(errorCode);
			BaseResponse response = ResponseUtils.generateResponse(Boolean.FALSE, responseCode.getCode(), responseCode.getMessage());
			Message responseMessage = new Message(responseMessageCode, requestMessage.getMsgId(), response.toJson());
			serverManager.sendMessageNoCache(channel, responseMessage);	// 没有缓存报文消息
		}
	}*/
	
	/**
	 * 或使用handlerRemoved？
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final Channel channel = ctx.channel();
		IoSession session = ChannelUtils.getSession(channel);
		if(session.isLogin()) {
			logger.error("Connect inactive: " + session);
			this.logout(session, channel, ctx, SessionCloseReason.NORMAL);	// 正常登出
		}
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof IOException) {	// IO异常直接登出，关闭链接
			Channel channel = ctx.channel();
			logger.error("Client connect [{} - {}] exception: [{}]", ChannelUtils.getIp(channel), channel.id(), cause.toString());
			IoSession session = ChannelUtils.getSession(channel);
			this.logout(session, channel, ctx, SessionCloseReason.EXCEPTION);	// 异常登出
		} else {	// 业务异常已经在process方法里处理
			logger.error("Business exception: ", cause);
		}
	}
	
	/**
	 * 清除多余链接
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			switch (e.state()) {
			case READER_IDLE:	// 客户端读超时
				Channel channel = ctx.channel();
				IoSession session = ChannelUtils.getSession(channel);
				String remarkString = session != null ? session.toString() : "";
				logger.info("Client connect read timeout, close connect： {}", remarkString);
				this.logout(session, channel, ctx, SessionCloseReason.OVER_TIME);	// 超时登出
				break;
			/*case WRITER_IDLE:	// 客户端写超时
				channel = ctx.channel();
				session = ChannelUtils.getSession(channel);
				remarkString = session != null ? session.toString() : "";
				logger.info("Client connect write timeout, close connect： {}", remarkString);
				this.logout(session, channel, ctx, SessionCloseReason.OVER_TIME);	// 超时登出
				break;*/

			default:
				break;
			}
			/*if (e.state() == IdleState.READER_IDLE) {
				final Channel channel = ctx.channel();
				IoSession session = ChannelUtils.getSession(channel);
				String remarkString = session != null ? session.toString() : "";
				logger.info(remarkString + " read timeout...");
				this.logout(session, channel, ctx);
				int overtimeTimes = clientTimeoutMap.getOrDefault(ctx, 0);
				if(overtimeTimes < MAX_RECONNECT_TIMES){	// 未超过重连次数,则发送心跳包重连
					logger.info(remarkString + " reconnected...");
					addOverTimes(ctx);
					HeartBeatRequest heartBeatRequest = new HeartBeatRequest();
					heartBeatRequest.setTimestamp(System.currentTimeMillis());
					Message message = new Message(MessageCode.HeartBeatRequest, heartBeatRequest.toJson());
					serverManager.sendMessage(ctx.channel(), message);
				}else{	// 超过重连次数
					logger.info(remarkString + " reconnect times more than limit: " + MAX_RECONNECT_TIMES);
					this.logout(session, channel, ctx);
				}
			} */
		}
	}
	
	/*private void addOverTimes(ChannelHandlerContext ctx){
		int oldTimes = 0;
		if(clientTimeoutMap.containsKey(ctx)){
			oldTimes = clientTimeoutMap.get(ctx);
		}
		clientTimeoutMap.put(ctx, (int)(oldTimes+1));
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：登出、关闭链接
	 *
	 * @param session
	 * @param channel
	 * @param ctx
	 * @param closeReason
	 * @author 曾垂瑜 CreateDate: 2017年11月8日
	 *
	 */
	private void logout(IoSession session, Channel channel, ChannelHandlerContext ctx, SessionCloseReason closeReason) {
//		if(session.isLogin()) {	// 终端登出
		if(session.getConnectSource() != null) {
			try {
				TerminalImLogoutRequest terminalImLogoutRequest = new TerminalImLogoutRequest();
				terminalImLogoutRequest.setMemberNoGm(session.getMemberNoGm());
				terminalImLogoutRequest.setType(session.getConnectSource().name());
				terminalImLogoutRequest.setTerminalCode(session.getTerminalCode());
				terminalImStatusService.logout(terminalImLogoutRequest);
			} catch(Exception e) {
				logger.error("客户端记录登出日志失败", e);
			}
		}
//		}
		serverManager.logout(channel, closeReason);	// 注销通道，包括session
		if(channel.isActive()) {
			ctx.close();
		}
	}

}
