/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.common;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.core.util.StringUtils;

/**
 * 
 * 类说明：报文编、解码工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月14日
 */
public class MessageUtils {

	private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);
	
	private final static String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 
	 *
	 * 方法说明：读指定长度的内容
	 *
	 * @param buf
	 * @param length
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月18日
	 *
	 */
	public static String readUTF8(ByteBuf buf, int length){
		byte[] content = new byte[length];
		buf.readBytes(content);
		try {
			return new String(content,DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			logger.error("读报文消息失败", e);
			return "";
		}

	}

	/**
	 * 
	 *
	 * 方法说明：读取内容,格式按：内容长度 + 内容
	 *
	 * @param buf
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月18日
	 *
	 */
	public static String readUTF8ByLength(ByteBuf buf){
		int strSize = buf.readInt();
		if(strSize <= 0) {
			return "";
		}
		return readUTF8(buf, strSize);
	}

	/**
	 * 
	 *
	 * 方法说明：写内容(不带内容长度)
	 *
	 * @param buf
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月18日
	 *
	 */
	public static void writeUTF8(ByteBuf buf, String message){
		byte[] content ;
		try {
			if(StringUtils.isNotEmpty(message)) {
				content = message.getBytes(DEFAULT_CHARSET);
				buf.writeBytes(content);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("写报文消息失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：写内容(带内容长度)
	 *
	 * @param buf
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月18日
	 *
	 */
	public static void writeUTF8ByLength(ByteBuf buf, String message){
		byte[] content ;
		try {
			if(StringUtils.isNotEmpty(message)) {
				content = message.getBytes(DEFAULT_CHARSET);
				buf.writeInt(content.length);
				buf.writeBytes(content);
			} else {
				buf.writeInt(0);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("写报文消息失败", e);
		}
	}
}
