/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.redpacket;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：查询红包
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月18日
 */
public class FindRedPacketMessage implements Serializable {

	private static final long serialVersionUID = 667530593684184734L;

	/**
	 * 发红包记录CODE，非空
	 */
	private String rpCode;
	
	/**
	 * 终端微信，非空
	 */
	private String noWxShop;

	/**
	 * @return the rpCode
	 */
	public String getRpCode() {
		return rpCode;
	}

	/**
	 * @param rpCode the rpCode to set
	 */
	public void setRpCode(String rpCode) {
		this.rpCode = rpCode;
	}

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
		builder.append("FindRedPacketMessage [rpCode=");
		builder.append(rpCode);
		builder.append(", noWxShop=");
		builder.append(noWxShop);
		builder.append("]");
		return builder.toString();
	}
	
}
