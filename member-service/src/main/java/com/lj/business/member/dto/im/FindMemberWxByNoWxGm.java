/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * 类说明：查询绑定导购微信号所有客户的微信列表-入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月16日
 */
public class FindMemberWxByNoWxGm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743812906070945129L;

	/**
	 * 终端编号，非空
	 */
	
	
	/**
	 * 导购微信号列表，非空
	 */
	private String[] noWxGms;


	/**
	 * @return the noWxGms
	 */
	public String[] getNoWxGms() {
		return noWxGms;
	}

	/**
	 * @param noWxGms the noWxGms to set
	 */
	public void setNoWxGms(String[] noWxGms) {
		this.noWxGms = noWxGms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMemberWxByNoWxGm [noWxGms=");
		builder.append(Arrays.toString(noWxGms));
		builder.append("]");
		return builder.toString();
	}

}
