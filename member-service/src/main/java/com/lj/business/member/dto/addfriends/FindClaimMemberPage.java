/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

import com.lj.base.core.pagination.PageParamEntity;

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
 * CreateDate: 2017年11月23日
 */

public class FindClaimMemberPage extends PageParamEntity {
	private static final long serialVersionUID = 2637291007591484782L;
	
	/**
	 * 导购编号，非空
	 */
	private String memberNoGm;
	
	/**
	 * 认领标识：true已认领，false未认领
	 */
	private Boolean flag;
	/**
	 * 手机号
	 */
	private String mobile;
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

	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindClaimMemberPage [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}
	
}
