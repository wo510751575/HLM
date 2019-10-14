package com.lj.business.supcon.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;

import com.lj.business.supcon.netty.common.ClientBaseService;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.common.CommonResponse;

public class ClientTransportHandler extends SimpleChannelInboundHandler<Message>{


	public ClientTransportHandler(){

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx){
		//注册session
		ClientBaseService.INSTANCE.registerSession(ctx.channel());
		
//		ClientBaseService.INSTANCE.sendServerRequest(request);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Message responseMessage) throws Exception{
		// 此处读取报文,同时处理业务逻辑,包括业务异常
		System.out.println("Received message, content is: " + responseMessage);
		
		// 报文为空
		if(responseMessage == null) {
			return;
		}
		
		// 响应通讯结果，如果是通讯响应报文则无需再次响应
		if(responseMessage.getCode() != MessageCode.CommonResponse) {
			Message message = new Message(MessageCode.CommonResponse, responseMessage.getMsgId(), new CommonResponse(responseMessage.getCode().getCode()).toJson());
			ClientBaseService.INSTANCE.sendServerRequest(message);
		}
				
//		PacketManager.INSTANCE.execPacket(packet);
	}
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final Channel channel = ctx.channel();
		System.out.println(channel + " connect closed...");
		ctx.close();
	}
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("业务逻辑出错" + cause);	// TODO
		Channel channel = ctx.channel();
		if (cause instanceof IOException && channel.isActive()) {
			System.out.println("Client connect exception" + channel.remoteAddress());
			ctx.close();
		}
	}

}
