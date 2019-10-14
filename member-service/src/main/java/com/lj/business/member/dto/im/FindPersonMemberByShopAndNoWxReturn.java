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
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月12日
 */
public class FindPersonMemberByShopAndNoWxReturn implements Serializable {

	private static final long serialVersionUID = 5186905341369244970L;

	/**
	 * 客户编号，非空
	 */
	private String memberNo;
	
	/**
	 * 客户微信，非空
	 */
	private String noWx;
	
	/**
	 * 客户微信别名，非空
	 */
	private String noWxAlias;
	
	/**
	 * 客户头像地址
	 */
	private String headAddress;
	
	/**
	 * 所属导购编号
	 */
	private String memberNoGm;

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
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the noWxAlias
	 */
	public String getNoWxAlias() {
		return noWxAlias;
	}

	/**
	 * @param noWxAlias the noWxAlias to set
	 */
	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
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
		builder.append("FindPersonMemberByShopAndNoWxReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxAlias=");
		builder.append(noWxAlias);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append("]");
		return builder.toString();
	}

}
