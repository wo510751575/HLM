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
import com.lj.business.weixin.emus.MessageSource;

/**
 * 
 * 类说明：向导购发送聊天记录入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月30日
 */
public class SendChatToGm implements Serializable {

	private static final long serialVersionUID = 7274533503564662665L;

	/**
	 * 消息ID,为空则由系统自动生成，32位UUID
	 * {@link GUID#generateByUUID()}
	 */
	private String msgId;
	
	/**
	 * 终端微信
	 */
	private String noWxGm;

	/**
	 * 标识该聊天记录是否为重新发送，默认为false不是重发
	 */
	private boolean resend = Boolean.FALSE;
	
	/**
	 * 发送人标识，非空：1导购发送、2客户发送
	 */
	private Integer senderFlag = 1;
	
	/**
	 * 导购编号，导购客户端非空
	 */
	private String memberNoGm;
	
	/**
	 * 客户编号，导购客户端非空
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
     * 消息来源，非空{@link MessageSource}
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

    
    
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendChatToGm [msgId=");
		builder.append(msgId);
		builder.append(", resend=");
		builder.append(resend);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", type=");
		builder.append(type);
		builder.append(", content=");
		builder.append(content);
		builder.append(", resources=");
		builder.append(resources);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", shareDes=");
		builder.append(shareDes);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", msgSource=");
		builder.append(msgSource);
		builder.append(", chatTime=");
		builder.append(chatTime);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", chatAssistantCode=");
		builder.append(chatAssistantCode);
		builder.append(", chatAssistantName=");
		builder.append(chatAssistantName);
		builder.append("]");
		return builder.toString();
	}
    
}
