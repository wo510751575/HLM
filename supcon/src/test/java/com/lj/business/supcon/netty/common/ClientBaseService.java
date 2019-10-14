package com.lj.business.supcon.netty.common;

import io.netty.channel.Channel;

import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.message.Message;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月30日
 */
public enum ClientBaseService {

	INSTANCE;

	/** 通信会话 */
	private IoSession session;

	public void registerSession(Channel channel) {
		this.session = new IoSession(channel);
	}

	public void sendServerRequest(Message request){
		this.session.getChannel().writeAndFlush(request);
	}

	/**
	 * 是否已连上服务器
	 * @return
	 */
	public boolean isConnectedSever() {
		return this.session != null;
	}


}
