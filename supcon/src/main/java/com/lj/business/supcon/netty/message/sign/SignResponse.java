/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.sign;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：签到结果
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月25日
 */
public class SignResponse extends BaseResponse {

	private static final long serialVersionUID = 4694731893051014105L;

	/**
	 * 密钥密文,签到成功时返回
	 */
	private String encrypt;
	
	/**
	 * 服务器当前时间戳,签到成功时返回
	 */
	private Long timestamp;
	/**
	 * 返回token
	 */
	private String token;
	/**
	 * @return the encrypt
	 */
	public String getEncrypt() {
		return encrypt;
	}

	/**
	 * @param encrypt the encrypt to set
	 */
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "SignResponse [encrypt=" + encrypt + ", timestamp=" + timestamp + ", token=" + token + "]";
	}

}
