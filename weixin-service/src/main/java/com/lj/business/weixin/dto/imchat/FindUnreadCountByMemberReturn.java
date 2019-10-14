/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.util.DateUtils;

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
public class FindUnreadCountByMemberReturn implements Serializable {

	private static final long serialVersionUID = -5041204686991643116L;

	/**
	 * 客户编号
	 */
	private String memberNo;

	/**
	 * 未读聊天记录数
	 */
	private int unreadCount;
	
	/**
	 * 最早未读聊天记录的时间
	 */
	private Date chatTimeBegin;
	
	/**
	 * 取上一秒
	 */
	public Date getChatTimeBegin() {
		return chatTimeBegin == null ? null : DateUtils.addSeconds(chatTimeBegin, -1);
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
	 * @return the unreadCount
	 */
	public int getUnreadCount() {
		return unreadCount;
	}

	/**
	 * @param unreadCount the unreadCount to set
	 */
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}

	/**
	 * @param chatTimeBegin the chatTimeBegin to set
	 */
	public void setChatTimeBegin(Date chatTimeBegin) {
		this.chatTimeBegin = chatTimeBegin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindUnreadCountByMemberReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", unreadCount=");
		builder.append(unreadCount);
		builder.append(", chatTimeBegin=");
		builder.append(chatTimeBegin);
		builder.append("]");
		return builder.toString();
	}

}
