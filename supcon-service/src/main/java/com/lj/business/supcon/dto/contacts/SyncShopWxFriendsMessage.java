/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

/**
 * 
 * 类说明：发送中控同步终端微信通讯录入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月12日
 */
public class SyncShopWxFriendsMessage implements Serializable {

	private static final long serialVersionUID = 6194413100374551407L;

	/**
	 * 终端微信号，非空
	 */
	private String noWxShop;

	/**
	 * @return the noWxShop
	 */
	public String getNoWxShop() {
		return noWxShop;
	}

	/**
	 * @param noWxShop the noWxShop to set
	 */
	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncShopWxFriendsMessage [noWxShop=");
		builder.append(noWxShop);
		builder.append("]");
		return builder.toString();
	}
	
}
