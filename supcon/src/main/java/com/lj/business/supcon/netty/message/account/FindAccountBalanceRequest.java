/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.account;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：查询余额
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
public class FindAccountBalanceRequest extends BaseDto {

	private static final long serialVersionUID = -6282876974217892984L;
	
	/**
	 * 终端微信
	 */
	private String noWx;

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAccountBalanceRequest [noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}

}
