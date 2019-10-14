/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月13日
 */
public class DistributionMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7858405934081567988L;

	/**
	 * 添加好友code
	 */
	private String code;
	
	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;

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
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DistributionMember [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}
	
}
