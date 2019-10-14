/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：返回认领客户结果参数
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
public class MemberClaimResponse extends BaseResponse {

	private static final long serialVersionUID = 8528781814750089151L;
	
	/**
	 * 认领code，非空
	 */
	private String mbrCode;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
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

	/**
	 * @return the mbrCode
	 */
	public String getMbrCode() {
		return mbrCode;
	}

	/**
	 * @param mbrCode the mbrCode to set
	 */
	public void setMbrCode(String mbrCode) {
		this.mbrCode = mbrCode;
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
		builder.append("MemberClaimResponse [");
		builder.append(super.toString());
		builder.append(", mbrCode=");
		builder.append(mbrCode);
		builder.append(", memberNo=");
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
