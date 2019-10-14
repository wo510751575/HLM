/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lj.business.supcon.netty.codec.MessageDecoder;
import com.lj.business.supcon.netty.codec.MessageEncoder;
import com.lj.business.supcon.netty.handler.MessageTransportHandler;

/**
 * 
 * 类说明：初始化netty服务器参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月11日
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Resource
	private MessageTransportHandler messageTransportHandler;
	
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		// 当有操作超出指定空闲秒数时，便会触发{@link MessageTransportHandler#userEventTriggered}事件
		pipeline.addLast(new IdleStateHandler(180, 0, 0));	// 服务端设置读超时时间为180秒
		pipeline.addLast(new MessageDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
		pipeline.addLast(new LengthFieldPrepender(4));
		pipeline.addLast(new MessageEncoder());
		pipeline.addLast(messageTransportHandler);
//		pipeline.addLast(new MessageTransportHandler());
	}
}
