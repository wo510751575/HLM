/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty;

import io.netty.channel.Channel;

import com.lj.business.supcon.netty.message.BaseDto;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;

/**
 * 
 * 类说明：业务处理接口,所有业务服务都应实现此接口
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
public interface IService<T extends BaseDto> {

	/**
	 * 
	 *
	 * 方法说明：业务处理
	 *
	 * @param request			消息体转换后的对象实体（如果不转换，则为null, 参考方法：{@link MessageCode#isJsonCoverToObject()}）
	 * @param requestMessage	请求报文 {@link com.lj.business.zhongkong.netty.message.Message}
	 * @param channel			当前连接通道 {@link io.netty.channel.Channel}
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月10日
	 *
	 */
	public void process(T request, Message message, Channel channel);
}
