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
public class UpdateThirdHaveReadFromWeb implements Serializable {

	private static final long serialVersionUID = 1360332985297920752L;

	/**
	 * 电商模式暂时不用该字段
	 */
	private String memberNoGm;
	
	/**
	 * 客户编号（群聊时为群code），非空
	 */
	private String memberNo;
	
    /**
     * 聊天时间-从（包含）
     */
    private Date chatTimeBegin;
    
    /**
     * 聊天时间-到（包含）
     */
    private Date chatTimeEnd;
    
    /**
     * 默认为来自PC导购助手的请求，否则为来自客户端请求
     */
    private boolean webFlag = Boolean.TRUE;

    private String noWxGm;
    
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
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

	/**
	 * @return the webFlag
	 */
	public boolean isWebFlag() {
		return webFlag;
	}

	/**
	 * @param webFlag the webFlag to set
	 */
	public void setWebFlag(boolean webFlag) {
		this.webFlag = webFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateThirdHaveReadFromWeb [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", chatTimeBegin=");
		builder.append(chatTimeBegin);
		builder.append(", chatTimeEnd=");
		builder.append(chatTimeEnd);
		builder.append(", webFlag=");
		builder.append(webFlag);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append("]");
		return builder.toString();
	}

}
