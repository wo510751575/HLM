/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.utils;

import com.lj.base.core.encryption.AES;
import com.lj.base.core.encryption.MD5;

/**
 * 
 * 类说明：微信密码加密工具类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月27日
 */
public class WxPwdEncryptUtils {
	
	private static final int KEY_SIZE = 16;	// AES密钥长度（128位加密算法）

	/**
	 * 
	 *
	 * 方法说明：加密生成保存到数据库的密码密文
	 *
	 * @param wxPwd		密码原文
	 * @param workKey	工作密钥
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public static String encrypt(String wxPwd, String workKey) {
		return AES.encrypt(wxPwd, workKey.substring(0, KEY_SIZE));
	}
	
	/**
	 * 
	 *
	 * 方法说明：解密保存在数据库中的密码密文
	 *
	 * @param wxPwd		密码原文
	 * @param workKey	工作密钥
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public static String decrypt(String wxPwd, String workKey) {
		return AES.decrypt(wxPwd, workKey.substring(0, KEY_SIZE));
	}
	
	/**
	 * 
	 *
	 * 方法说明：加密签到返回的密码密文
	 *
	 * @param wxPwd		密码原文
	 * @param workKey	工作密钥
	 * @param timestamp	签到时间戳
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public static String encryptBySign(String wxPwd, String workKey, Long timestamp) {
		// AES加密密钥 = MD5(工作密钥，时间戳)
		return AES.encrypt(wxPwd, MD5.encryptByMD5(workKey, String.valueOf(timestamp)).substring(0, KEY_SIZE));
	}
	
	/**
	 * 
	 *
	 * 方法说明：解密签到返回的密码密文
	 *
	 * @param wxPwd		密码密文
	 * @param workKey	工作密钥
	 * @param timestamp	签到时间戳
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月27日
	 *
	 */
	public static String decryptBySign(String wxPwd, String workKey, Long timestamp) {
		// AES解密密钥 = MD5(工作密钥，时间戳)
		return AES.decrypt(wxPwd, MD5.encryptByMD5(workKey, String.valueOf(timestamp)).substring(0, KEY_SIZE));
	}
	
	public static void main(String[] args) {
		System.out.println(WxPwdEncryptUtils.decrypt("WH4E6eEw0nLDEzBsUJXKZA==", "ae74d9b56e5e4a41bc7d82a8c1ca13b9"));
	}
}
