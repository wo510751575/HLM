/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.shopterminal;

import java.io.Serializable;

/**
 * 
 * 类说明：签到返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月26日
 */
public class TerminalSignReturn implements Serializable {

	private static final long serialVersionUID = -9208433939571581811L;
	
	/**
	 * 密钥密文
	 */
	private String encrypt;
	
	/**
	 * 签到时间戳
	 */
	private Long timestamp;
	/**
	 * 增加token返回
	 */
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	@Override
	public String toString() {
		return "TerminalSignReturn [encrypt=" + encrypt + ", timestamp=" + timestamp + ", token=" + token + "]";
	}

}
