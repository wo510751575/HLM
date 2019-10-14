/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import java.io.Serializable;

/**
 * 
 * 类说明：查询客户最大版本数
 *  
 * 
 * <p>
 * 详细描述：
 * 1、导购编号不为空时，则查询导购所属客户的最大版本号
 * 2、导购编号为空且导购微信号不为空时，则查询中控手机登录微信所属客户的最大版本号
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月27日
 */
public class FindMaxVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -837577739738391707L;
	
	/**
	 * 商户编号，非空 
	 */
	private String merchantNo;
	
	/**
	 * 导购编号，导购编号与导购微信号不能同时为空
	 */
	private String memberNoGm;
	
	/**
	 * 导购微信号，导购编号与导购微信号不能同时为空
	 */
	private String noWxGm;

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMaxVersion [merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}
	
}
