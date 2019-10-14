/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty;

import java.net.InetSocketAddress;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.service.IChatService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.cc.enums.SystemAliasName;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 
 * 类说明：netty服务器
 *  
 * 
 * <p>
 * 详细描述：<br>
 * <br><b style="color: red;">XXX 待优化或者可优化点：</b>
 * <li>1、序列化，netty基于的是Java的原生序列化机制，性能效率并不高，可以考虑使用第三方框架，例如Kryo、Hessian</li>
 * <li>2、bossGroup和workerGroup线程数优化</li>
 * <li>3、handler业务逻辑通过线程池异步处理，不过业务线程池同样是把双刃剑，虽然将任务交给业务线程池异步执行降低了Netty的I/O线程的占用时间、减轻了压力，但同时业务线程池增加了线程上下文切换的次数</li>
 * <li>4、ExecutionHandler</li>
 * <li>5、TCP参数优化</li>
 * <li>6、JVM参数</li>
 * <li>7、Linux最大句柄数</li>
 * <li>8、失败重发机制，可靠性</li>
 * 
 * <p>
 * <br>参考文档：</br>
 * <li>1、http://www.infoq.com/cn/articles/netty-million-level-push-service-design-points</li>
 * <li>2、http://www.cnblogs.com/549294286/p/5177663.html</li>
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
@Component
public class NettyServer {

	private static Logger logger = LoggerFactory.getLogger(NettyServer.class);
	
	private static EventLoopGroup bossGroup = new NioEventLoopGroup(1);	//避免使用默认线程数参数
	private static EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());	// XXX 默认为cpu数，实际使用线程数会更好
	
	private int port=1215;	// 服务器端口
	
	@Resource
	private ServerChannelInitializer serverChannelInitializer;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
    @Resource
    private IChatService chatService;
//    @Resource
//    private IHashMap hashMap;
    @Resource
    IPersonMemberImService personMemberImService;
	@PostConstruct
	public void init() throws Exception {
		
		//初始化redis数据，将要缓存的数据加载到redis中
		initCache();

		// 初始化消息编码
		MessageCode.init();
		logger.info("已初始化消息编码");
		
		// 另起线程启动netty服务器，否则将占用主线程
		new Thread() {
			
			@Override
			public void run() {
				try{
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup,workerGroup)
					.channel(NioServerSocketChannel.class)
					
					/*
					 * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
					 * 如果未设置或所设置的值小于1，Java将使用默认值50
					 */
					.option(ChannelOption.SO_BACKLOG, 1024)
					
					/*
					 * Netty4使用内存池，重用缓冲区
					 * ByteBuf需要手动释放，有可能会造成内存泄漏，API： ReferenceCountUtil.release(buf);
					 */
					.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
					
					.childHandler(serverChannelInitializer);

					ChannelFuture f = b.bind(new InetSocketAddress(port)).sync();
					f.channel().closeFuture().sync();
					
					logger.info("中控服务器已启动，开始监听客户端请求......");
				} catch (Exception e) {
					logger.error("中控服务器启动错误", e);
				} finally {
					if (bossGroup != null) {
						bossGroup.shutdownGracefully();
					}
					if (workerGroup != null) {
						workerGroup.shutdownGracefully();
					}
				}
				
				// 优雅退出
				Runtime.getRuntime().addShutdownHook(new Thread() {
					@Override
					public void run() {
						if (bossGroup != null) {
							bossGroup.shutdownGracefully();
						}
						if (workerGroup != null) {
							workerGroup.shutdownGracefully();
						}
					}
				});
				
			}
		}.start();
	}

	/**
	 * 
	 *
	 * 方法说明：注销前关闭netty服务器
	 *
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月15日
	 *
	 */
	@PreDestroy
	public synchronized void close() {
		try{
			if (bossGroup != null) {
				bossGroup.shutdownGracefully();
			}
			if (workerGroup != null) {
				workerGroup.shutdownGracefully();
			}
		}catch(Exception e){
			logger.error("中控服务器关闭错误", e);
		}
	}
    /**
     * @author: dengxiudong
     * @title: NettyServer
     * @description: 初始化redis数据，将要缓存的数据加载到redis中
     * @param:
     * @return:
     * @throws:
     **/
    public void initCache() {
        try {
        	//不再缓存关系数据到redis，现有业务有转移之类，与原业务不一致 DZP 2019-03-14
//            personMemberImService.cacheAllGmMemberRelateInfo();
        	//获取服务器配置TCP端口
        	String nettyPort =  localCacheSystemParams.getSystemParam(SystemAliasName.ms.toString(), "nettyAddress","port");
    		if(StringUtils.isNotEmpty(nettyPort) && StringUtils.isNumeric(nettyPort)) {
    			port =Integer.valueOf(nettyPort);
    		}
            logger.info("初始化Redis缓存数据完成。");
        } catch (Exception e) {
            logger.error("缓存数据到Redis中失败，程序将直接从MySQL获取数据。", e);
        }
    }
}
