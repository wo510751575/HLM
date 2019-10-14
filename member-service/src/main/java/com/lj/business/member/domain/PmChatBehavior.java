package com.lj.business.member.domain;

import java.util.Date;

public class PmChatBehavior {

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
     * 终端微信
     */
    private String noWxGm;
    
    public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
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
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PmChatBehavior [memberNo=").append(memberNo); 
        builder.append(",memberNoGm=").append(memberNoGm); 
        builder.append(",chatTime=").append(chatTime); 
        builder.append(",thirdUnreadFlag=").append(thirdUnreadFlag); 
        builder.append("]");
        return builder.toString();
    }
}