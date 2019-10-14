/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * CreateDate: 2017年12月4日
 */
public class FindUnreadCountByMember implements Serializable {

	private static final long serialVersionUID = 8412631979393049702L;

	/**
	 * 终端微信，非空
	 */
	private String noWxShop;
	
	/**
	 * 客户列表，非空
	 */
	private List<String> memberNoList;
	
	/**
	 * 聊天时间，不包括
	 */
	private Date chatTimeBegin;
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the noWxShop
	 */
	public String getNoWxShop() {
		return noWxShop;
	}

	/**
	 * @param noWxShop the noWxShop to set
	 */
	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}

	/**
	 * @return the memberNoList
	 */
	public List<String> getMemberNoList() {
		return memberNoList;
	}

	/**
	 * @param memberNoList the memberNoList to set
	 */
	public void setMemberNoList(List<String> memberNoList) {
		this.memberNoList = memberNoList;
	}

	/**
	 * @return the chatTimeBegin
	 */
	public Date getChatTimeBegin() {
		return chatTimeBegin;
	}

	/**
	 * @param chatTimeBegin the chatTimeBegin to set
	 */
	public void setChatTimeBegin(Date chatTimeBegin) {
		this.chatTimeBegin = chatTimeBegin;
	}

	@Override
	public String toString() {
		return "FindUnreadCountByMember [noWxShop=" + noWxShop + ", memberNoList=" + memberNoList + ", chatTimeBegin="
				+ chatTimeBegin + ", memberNoGm=" + memberNoGm + "]";
	}

}
