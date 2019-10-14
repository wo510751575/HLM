/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.sign;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：签到
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
public class SignRequest extends BaseDto {

	private static final long serialVersionUID = -8443885865162683929L;

	/**
	 * 签到令牌,非空
	 */
	private String token;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignRequest [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
	
}
