/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto;

import java.util.Collection;
import java.util.Date;

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
 * CreateDate: 2018年7月17日
 */
public class FindUnchatMemberPage extends PageParamEntity {

	private static final long serialVersionUID = -1597074525773805412L;
	
	/**
	 * 客户姓名
	 */
	private String memberName;
	
	/**
	 * 手机号
	 */
	private String mobile;		
	
	/**
	 * 客户来源
	 */
	private String memberSrc;	
	
	/**
	 * 注册时间-从
	 */
	private Date registerBeginTime;
	
	/**
	 * 注册时间-到
	 */
	private Date registerEndTime;

	
	/**
	 * 商户编号
	 */
	private String merchantNo;
	
	/**
	 * 聊天记录小于等于leChatCount
	 */
	private Integer leChatCount;
	
	/**
	 * 聊天时间 - 从
	 */
	private Date chatBeginTime;
	
	/**
	 * 聊天时间 - 到
	 */
	private Date chatEndTime;
	
	/**
	 * 聊天时间标识
	 */
	private Integer flag;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the memberSrc
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * @param memberSrc the memberSrc to set
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
	 * @return the registerBeginTime
	 */
	public Date getRegisterBeginTime() {
		return registerBeginTime;
	}

	/**
	 * @param registerBeginTime the registerBeginTime to set
	 */
	public void setRegisterBeginTime(Date registerBeginTime) {
		this.registerBeginTime = registerBeginTime;
	}

	/**
	 * @return the registerEndTime
	 */
	public Date getRegisterEndTime() {
		return registerEndTime;
	}

	/**
	 * @param registerEndTime the registerEndTime to set
	 */
	public void setRegisterEndTime(Date registerEndTime) {
		this.registerEndTime = registerEndTime;
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
	 * @return the leChatCount
	 */
	public Integer getLeChatCount() {
		return leChatCount;
	}

	/**
	 * @param leChatCount the leChatCount to set
	 */
	public void setLeChatCount(Integer leChatCount) {
		this.leChatCount = leChatCount;
	}

	/**
	 * @return the chatBeginTime
	 */
	public Date getChatBeginTime() {
		return chatBeginTime;
	}

	/**
	 * @param chatBeginTime the chatBeginTime to set
	 */
	public void setChatBeginTime(Date chatBeginTime) {
		this.chatBeginTime = chatBeginTime;
	}

	/**
	 * @return the chatEndTime
	 */
	public Date getChatEndTime() {
		return chatEndTime;
	}

	/**
	 * @param chatEndTime the chatEndTime to set
	 */
	public void setChatEndTime(Date chatEndTime) {
		this.chatEndTime = chatEndTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnchatMemberPage [memberName=");
		builder.append(memberName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", registerBeginTime=");
		builder.append(registerBeginTime);
		builder.append(", registerEndTime=");
		builder.append(registerEndTime);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", leChatCount=");
		builder.append(leChatCount);
		builder.append(", chatBeginTime=");
		builder.append(chatBeginTime);
		builder.append(", chatEndTime=");
		builder.append(chatEndTime);
		builder.append(", flag=");
		builder.append(flag);
		builder.append("]");
		return builder.toString();
	}


}
