package com.lj.business.cf.dto.clientFollowSummary;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.io.Serializable;

/**
 * The Class FindClientFollowSummary.
 */
public class FindClientFollowRemark implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 721644291634634543L;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 客户编号
	 */
	private String memberNo;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindClientFollowRemark [remark=");
		builder.append(remark);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}
	
}
