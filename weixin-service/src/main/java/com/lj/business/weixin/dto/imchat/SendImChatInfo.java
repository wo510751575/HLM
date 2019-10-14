/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.util.GUID;

/**
 * 
 * 类说明：发送IM聊天记录
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月25日
 */
public class SendImChatInfo implements Serializable {

	private static final long serialVersionUID = -4531737022138844346L;
	
	/**
	 * 消息ID,为空则由系统自动生成，32位UUID
	 * {@link GUID#generateByUUID()}
	 */
	private String msgId;

	/**
	 * 标识该聊天记录是否为重新发送，默认为false不是重发
	 */
	private boolean resend = Boolean.FALSE;
	
	/**
	 * 发送人标识，非空：1导购发送、2客户发送
	 */
	private Integer senderFlag = 1;
	
	/**
	 * 发给客户（即senderFlag=1）时是否允许中控客户端离线发送，默认为不允许
	 */
	private boolean allowZkOffline = Boolean.FALSE;

	/**
	 * 导购微信，单聊时非空
	 */
	private String noWxGm;
	/**
	 * 导购编号，单聊时非空
	 */
	private String memberNoGm;
	/**
	 * 客户编号，导购客户端非空，群聊时表示群记录code
	 */
	private String memberNo;
	
	/**
     * 消息类型 1文本 3图片 34语音 43视频 48定位 49分享 50视频聊天 10000系统信息（添加好友）318767153系统安全提示
     * 非空
     */
    private String type;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 资源路径：语音、图片、视频 .
     */
    private String resources;
    
    /**
     * 分享标题
     */
    private String shareTitle;
    
    /**
     * 分享描述
     */
    private String shareDes;
    
    /**
     * 分享链接
     */
    private String shareUrl;
    
    /**
     * 群聊类型：1单聊、2群聊
     */
    private Integer chatroomType = 1;
    
    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;
    
    /**
     * 消息来源，非空
     */
    private Integer msgSource;
    
    /**
     * 聊天时间，为空时将默认为服务器时间
     */
    private Date chatTime;
    
    /**
     * 手机串号     */
    private String imei;

    /**
     * 聊天助手编号,msgSource=3时不为空 .
     */
    private String chatAssistantCode;

    /**
     * 聊天助手名称,msgSource=3时不为空 .
     */
    private String chatAssistantName;
    


	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the resend
	 */
	public boolean isResend() {
		return resend;
	}

	/**
	 * @return the allowZkOffline
	 */
	public boolean isAllowZkOffline() {
		return allowZkOffline;
	}

	/**
	 * @param allowZkOffline the allowZkOffline to set
	 */
	public void setAllowZkOffline(boolean allowZkOffline) {
		this.allowZkOffline = allowZkOffline;
	}

	/**
	 * @param resend the resend to set
	 */
	public void setResend(boolean resend) {
		this.resend = resend;
	}

	/**
	 * @return the senderFlag
	 */
	public Integer getSenderFlag() {
		return senderFlag;
	}

	/**
	 * @param senderFlag the senderFlag to set
	 */
	public void setSenderFlag(Integer senderFlag) {
		this.senderFlag = senderFlag;
	}

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the resources
	 */
	public String getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * @return the shareTitle
	 */
	public String getShareTitle() {
		return shareTitle;
	}

	/**
	 * @param shareTitle the shareTitle to set
	 */
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	/**
	 * @return the shareDes
	 */
	public String getShareDes() {
		return shareDes;
	}

	/**
	 * @param shareDes the shareDes to set
	 */
	public void setShareDes(String shareDes) {
		this.shareDes = shareDes;
	}

	/**
	 * @return the shareUrl
	 */
	public String getShareUrl() {
		return shareUrl;
	}

	/**
	 * @param shareUrl the shareUrl to set
	 */
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	/**
	 * @return the chatroomType
	 */
	public Integer getChatroomType() {
		return chatroomType;
	}

	/**
	 * @param chatroomType the chatroomType to set
	 */
	public void setChatroomType(Integer chatroomType) {
		this.chatroomType = chatroomType;
	}

	/**
	 * @return the chatroomNoWx
	 */
	public String getChatroomNoWx() {
		return chatroomNoWx;
	}

	/**
	 * @param chatroomNoWx the chatroomNoWx to set
	 */
	public void setChatroomNoWx(String chatroomNoWx) {
		this.chatroomNoWx = chatroomNoWx;
	}

	/**
	 * @return the msgSource
	 */
	public Integer getMsgSource() {
		return msgSource;
	}

	/**
	 * @param msgSource the msgSource to set
	 */
	public void setMsgSource(Integer msgSource) {
		this.msgSource = msgSource;
	}

	/**
	 * @return the chatTime
	 */
	public Date getChatTime() {
		return chatTime;
	}

	/**
	 * @param chatTime the chatTime to set
	 */
	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the chatAssistantCode
	 */
	public String getChatAssistantCode() {
		return chatAssistantCode;
	}

	/**
	 * @param chatAssistantCode the chatAssistantCode to set
	 */
	public void setChatAssistantCode(String chatAssistantCode) {
		this.chatAssistantCode = chatAssistantCode;
	}

	/**
	 * @return the chatAssistantName
	 */
	public String getChatAssistantName() {
		return chatAssistantName;
	}

	/**
	 * @param chatAssistantName the chatAssistantName to set
	 */
	public void setChatAssistantName(String chatAssistantName) {
		this.chatAssistantName = chatAssistantName;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		return "SendImChatInfo [msgId=" + msgId + ", resend=" + resend + ", senderFlag=" + senderFlag
				+ ", allowZkOffline=" + allowZkOffline + ", noWxGm=" + noWxGm + ", memberNoGm=" + memberNoGm
				+ ", memberNo=" + memberNo + ", type=" + type + ", content=" + content + ", resources=" + resources
				+ ", shareTitle=" + shareTitle + ", shareDes=" + shareDes + ", shareUrl=" + shareUrl + ", chatroomType="
				+ chatroomType + ", chatroomNoWx=" + chatroomNoWx + ", msgSource=" + msgSource + ", chatTime="
				+ chatTime + ", imei=" + imei + ", chatAssistantCode=" + chatAssistantCode + ", chatAssistantName="
				+ chatAssistantName + "]";
	}
}
