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
 * 类说明：签到
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
public class TerminalSign implements Serializable {

	private static final long serialVersionUID = -9208433939571581811L;
	
	/**
	 * 微信号，非空
	 */
	private String noWx;

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalSign [noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}

}
