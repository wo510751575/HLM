package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

public class FindChatTimesByGmAndMemberNo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2743874240844010342L;
    
    /**
     * 导购号.
     */
    private String memberNoGm;
    
    /**
     * 客户号.
     */
    private String memberNo;
    
    /**
     * 聊天时间-从
     */
    private Date chatBeginTime;
    
    /**
     * 聊天时间-到
     */
    private Date chatEndTime;

    public String getMemberNoGm() {
        return memberNoGm;
    }

    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Date getChatBeginTime() {
        return chatBeginTime;
    }

    public void setChatBeginTime(Date chatBeginTime) {
        this.chatBeginTime = chatBeginTime;
    }

    public Date getChatEndTime() {
        return chatEndTime;
    }

    public void setChatEndTime(Date chatEndTime) {
        this.chatEndTime = chatEndTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindChatTimesByGmAndMemberNo [memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", chatBeginTime=");
        builder.append(chatBeginTime);
        builder.append(", chatEndTime=");
        builder.append(chatEndTime);
        builder.append("]");
        return builder.toString();
    }

}
