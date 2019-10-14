/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.account;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：查询余额返回
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
public class FindAccountBalanceResponse extends BaseResponse {

	private static final long serialVersionUID = 4085243173424781502L;
	
	/**
	 * 终端微信
	 */
	private String noWx;

	/**
	 * 账户余额
	 */
	private Long balance;

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

	/**
	 * @return the balance
	 */
	public Long getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Long balance) {
		this.balance = balance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindAccountBalanceResponse [");
		builder.append(super.toString());
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", balance=");
		builder.append(balance);
		builder.append("]");
		return builder.toString();
	}

}
