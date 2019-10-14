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
 * 
 * 类说明：客户分配返回参数
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
public class DistributionMemberReturn implements Serializable {

	private static final long serialVersionUID = -7858405934081567988L;

	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 认领导购编号
	 */
	private String memberNoGm;

	/**
	 * 认领导购名称
	 */
	private String memberNameGm;
	
	/**
	 * 认领时间
	 */
	private Long claimTime;

	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
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

	/**
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * @return the claimTime
	 */
	public Long getClaimTime() {
		return claimTime;
	}

	/**
	 * @param claimTime the claimTime to set
	 */
	public void setClaimTime(Long claimTime) {
		this.claimTime = claimTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DistributionMemberReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", claimTime=");
		builder.append(claimTime);
		builder.append("]");
		return builder.toString();
	}

}
