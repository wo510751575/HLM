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
 * 类说明：设置终端微信支付密码
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
public class UpdateTerminalWxPwd implements Serializable {

	private static final long serialVersionUID = 1940739712210992931L;

	/**
	 * 设置微信支付密码
	 */
	private String code;
	
	/**
	 * 密码明文
	 */
	private String pwd;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateTerminalWxPwd [code=");
		builder.append(code);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append("]");
		return builder.toString();
	}
}
