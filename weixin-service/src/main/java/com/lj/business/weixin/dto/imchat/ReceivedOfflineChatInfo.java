/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类说明：客户端已接收到服务器推送的离线聊天记录入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月4日
 */
public class ReceivedOfflineChatInfo implements Serializable {

	private static final long serialVersionUID = -2568069620051583762L;
	
    /**
     * 客户端标识：1导购客户端、2中控客户端，非空
     */
    private Integer clientFlag;

    /**
     * 导购编号，导购客户端时非空 .
     */
    private String memberNoGm;
    
    /**
     * 导购微信号，中控客户端时非空
     */
    private String noWxGm;
    
    /**
	 * 聊天时间 - 从
	 */
	private Date chatTimeBegin;
	
	/**
	 * 聊天时间 - 到
	 */
	private Date chatTimeEnd;

	/**
	 * @return the clientFlag
	 */
	public Integer getClientFlag() {
		return clientFlag;
	}

	/**
	 * @param clientFlag the clientFlag to set
	 */
	public void setClientFlag(Integer clientFlag) {
		this.clientFlag = clientFlag;
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

	/**
	 * @return the chatTimeEnd
	 */
	public Date getChatTimeEnd() {
		return chatTimeEnd;
	}

	/**
	 * @param chatTimeEnd the chatTimeEnd to set
	 */
	public void setChatTimeEnd(Date chatTimeEnd) {
		this.chatTimeEnd = chatTimeEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReceivedOfflineChatInfo [clientFlag=");
		builder.append(clientFlag);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", chatTimeBegin=");
		builder.append(chatTimeBegin);
		builder.append(", chatTimeEnd=");
		builder.append(chatTimeEnd);
		builder.append("]");
		return builder.toString();
	}

}
