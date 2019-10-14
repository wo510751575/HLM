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
 * CreateDate: 2017年11月14日
 */
public class FindGuidByShopAndMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583301892228606748L;

	/**
	 * 分店编号，非空
	 */
	private String shopWx;
	
	/**
	 * 客户编号，非空
	 */
	private String memberNo;


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

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGuidByShopAndMember [shopWx=");
		builder.append(shopWx);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}

}
