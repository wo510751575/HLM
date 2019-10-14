/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.redpacket;

import com.lj.business.supcon.netty.message.BaseDto;

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
public class FindRedpacketRequest extends BaseDto {

	private static final long serialVersionUID = -8347778595319652344L;
	
	/**
	 * 发红包记录CODE
	 */
	private String rpCode;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindRedpacketRequest [rpCode=");
		builder.append(rpCode);
		builder.append("]");
		return builder.toString();
	}
	
}
