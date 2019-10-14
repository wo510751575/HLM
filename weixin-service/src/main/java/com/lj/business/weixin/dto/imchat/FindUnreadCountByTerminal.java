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
public class FindUnreadCountByTerminal implements Serializable {

	private static final long serialVersionUID = 8412631979393049702L;

	/**
	 * 商户编号，非空
	 */
	private String merchantNo;
	
	/**
	 * 终端微信号列表，非空
	 */
	private List<String> noWxList;
	
	/**
	 * 聊天时间，不包括
	 */
	private Date chatTimeBegin;

	private List<String> memberNoGms;
	
	private String noWxGm;
	
	public String getNoWxGm() {
		return noWxGm;
	}

	public List<String> getMemberNoGms() {
		return memberNoGms;
	}


	public void setMemberNoGms(List<String> memberNoGms) {
		this.memberNoGms = memberNoGms;
	}


	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

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
	 * @return the noWxList
	 */
	public List<String> getNoWxList() {
		return noWxList;
	}

	/**
	 * @param noWxList the noWxList to set
	 */
	public void setNoWxList(List<String> noWxList) {
		this.noWxList = noWxList;
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
		return "FindUnreadCountByTerminal [merchantNo=" + merchantNo + ", noWxList=" + noWxList + ", chatTimeBegin="
				+ chatTimeBegin + ", memberNoGms=" + memberNoGms + ", noWxGm=" + noWxGm + "]";
	}


}
