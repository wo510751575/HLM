/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

import com.lj.base.core.util.StringUtils;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月4日
 */
public class FindChatInfoPageFromWebReturn implements Serializable {

	private static final long serialVersionUID = 5890790590725263104L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 导购微信号 .
     */
    private String noWxGm;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 客户微信 .
     */
    private String noWx;

    /**
     * 客户微信别名 .
     */
    private String alias;

    /**
     * 发送人标识：1导购发送、2客户发送 .
     */
    private Integer senderFlag;

    /**
     * 发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端 .
     */
    private Integer senderSyncStatus;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 消息状态：1 未发送、2发送成功、3发送失败 .
     */
    private String status;

    /**
     * 聊天时间 .
     */
    private Date chatTime;

    /**
     * 资源路径：语音、图片、视频 .
     */
    private String resourcesPath;

    /**
     * 分享标题 .
     */
    private String shareTitle;

    /**
     * 分享描述 .
     */
    private String shareDes;

    /**
     * 分享链接 .
     */
    private String shareUrl;
    
    /**
     * 群聊类型：1单聊、2群聊
     */
    private Integer chatroomType;
    
    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 消息来源：1导购、2中控、3系统 .
     */
    private Integer msgSource;

    /**
     * 第三方已读标识：0未读、1已读 .
     */
    private Integer thirdReadFlag;

    /**
     * 手机串号 .
     */
    private String imei;
    
    /**
     * 发送失败编码
     */
    private String errorCode;
    
    /**
     * 发送失败描述
     */
    private String errorMessage;

    private String memberHeadUrl;
    
	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}

	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
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
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return StringUtils.toString(alias);
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
	 * @return the senderSyncStatus
	 */
	public Integer getSenderSyncStatus() {
		return senderSyncStatus;
	}

	/**
	 * @param senderSyncStatus the senderSyncStatus to set
	 */
	public void setSenderSyncStatus(Integer senderSyncStatus) {
		this.senderSyncStatus = senderSyncStatus;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return StringUtils.toString(type);
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return StringUtils.toString(status);
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the resourcesPath
	 */
	public String getResourcesPath() {
		return StringUtils.toString(resourcesPath);
	}

	/**
	 * @param resourcesPath the resourcesPath to set
	 */
	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	/**
	 * @return the shareTitle
	 */
	public String getShareTitle() {
		return StringUtils.toString(shareTitle);
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
		return StringUtils.toString(shareDes);
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
		return StringUtils.toString(shareUrl);
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
	 * @return the content
	 */
	public String getContent() {
		return StringUtils.toString(content);
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the thirdReadFlag
	 */
	public Integer getThirdReadFlag() {
		return thirdReadFlag;
	}

	/**
	 * @param thirdReadFlag the thirdReadFlag to set
	 */
	public void setThirdReadFlag(Integer thirdReadFlag) {
		this.thirdReadFlag = thirdReadFlag;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return StringUtils.toString(imei);
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return StringUtils.toString(errorCode);
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return StringUtils.toString(errorMessage);
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindChatInfoPageFromWebReturn [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", senderSyncStatus=");
		builder.append(senderSyncStatus);
		builder.append(", type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
		builder.append(", chatTime=");
		builder.append(chatTime);
		builder.append(", resourcesPath=");
		builder.append(resourcesPath);
		builder.append(", shareTitle=");
		builder.append(shareTitle);
		builder.append(", shareDes=");
		builder.append(shareDes);
		builder.append(", shareUrl=");
		builder.append(shareUrl);
		builder.append(", chatroomType=");
		builder.append(chatroomType);
		builder.append(", chatroomNoWx=");
		builder.append(chatroomNoWx);
		builder.append(", content=");
		builder.append(content);
		builder.append(", msgSource=");
		builder.append(msgSource);
		builder.append(", thirdReadFlag=");
		builder.append(thirdReadFlag);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}

}
