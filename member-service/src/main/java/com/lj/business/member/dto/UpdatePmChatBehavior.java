/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto;

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
 * CreateDate: 2017年12月29日
 */
public class UpdatePmChatBehavior implements Serializable {

	private static final long serialVersionUID = -664521455586063156L;

	/**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 最新聊天时间 .
     */
    private Date chatTime;
    
    /**
     * 第三方未读记录数标识：0没有未读消息、1有未读消息 .
     */
    private Integer thirdUnreadFlag;
    
    /**
     * 客户最新聊天时间
     */
    private Date memberChatTime;
    
    /**
     * 未回复客户标识
     */
    private Integer unrespFlag;
    /**
     * 终端微信
     */
    private String noWxGm;
    
    public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	
    public UpdatePmChatBehavior() {
    	super();
    }
    
    public UpdatePmChatBehavior(String memberNo,String memberNoGm, String noWxGm, Date chatTime, Integer thirdUnreadFlag) {
    	super();
    	this.memberNo = memberNo;
    	this.memberNoGm = memberNoGm;
    	this.noWxGm = noWxGm;
    	this.chatTime = chatTime;
    	this.thirdUnreadFlag = thirdUnreadFlag;
    }

    /**
     * 客户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 客户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 最新聊天时间 .
     *
     */
    public Date getChatTime() {
        return chatTime;
    }

    /**
     * 最新聊天时间 .
     *
     */
    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    /**
     * 第三方未读记录数标识：0没有未读消息、1有未读消息 .
     */
	public Integer getThirdUnreadFlag() {
		return thirdUnreadFlag;
	}

	/**
     * 第三方未读记录数标识：0没有未读消息、1有未读消息 .
     */
	public void setThirdUnreadFlag(Integer thirdUnreadFlag) {
		this.thirdUnreadFlag = thirdUnreadFlag;
	}

	/**
	 * @return the memberChatTime
	 */
	public Date getMemberChatTime() {
		return memberChatTime;
	}

	/**
	 * @param memberChatTime the memberChatTime to set
	 */
	public void setMemberChatTime(Date memberChatTime) {
		this.memberChatTime = memberChatTime;
	}

	/**
	 * @return the unrespFlag
	 */
	public Integer getUnrespFlag() {
		return unrespFlag;
	}

	/**
	 * @param unrespFlag the unrespFlag to set
	 */
	public void setUnrespFlag(Integer unrespFlag) {
		this.unrespFlag = unrespFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdatePmChatBehavior [memberNo=");
		builder.append(memberNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", chatTime=");
		builder.append(chatTime);
		builder.append(", thirdUnreadFlag=");
		builder.append(thirdUnreadFlag);
		builder.append(", memberChatTime=");
		builder.append(memberChatTime);
		builder.append(", unrespFlag=");
		builder.append(unrespFlag);
		builder.append("]");
		return builder.toString();
	}

}
