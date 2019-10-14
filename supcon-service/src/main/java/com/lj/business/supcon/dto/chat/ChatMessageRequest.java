/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.dto.chat;

import java.io.Serializable;

/**
 * 
 * 类说明：发送聊天消息入参
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月24日
 */
public class ChatMessageRequest implements Serializable {

	private static final long serialVersionUID = 6102300621404165235L;
    
    /**
     * 发送人标识，非空：1导购发送、2客户发送
     */
    private Integer senderFlag;
    
    /**
     * 接收人标识，非空：1导购接收、2客户接收
     */
    private Integer receiveFlag;
    
	/**
	 * 标识该聊天记录是否为重新发送，默认为false不是重发
	 */
	private boolean resend = Boolean.FALSE;
	
	/**
	 * 消息ID，非空
	 */
	private String msgId;

	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 导购微信号，导购客户端非空
	 */
	private String noWxGm;

	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户微信号，非空
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 群聊发送人微信
	 */
	private String groupUserName;

	/**
     * 消息类型 1文本 3图片 34语音 43视频 48定位 49分享 50视频聊天 10000系统信息（添加好友）318767153系统安全提示 494小程序
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
     * 发送时间，非空
     */
    private Long createTime;
    /**
	 * 群头像
	 */
	private String headUrl;
	
	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	/**
	 * 发送客户昵称
	 */
	private String memberNickName;
	/**
	 * 发送客户头像
	 */
	private String memberHeadUrl;
	/**
     * 免打扰，0：未开启，1：开启
     * 默认未开启
     */
    private Integer noDisturb=0;
    
    public Integer getNoDisturb() {
		return noDisturb;
	}

	public void setNoDisturb(Integer noDisturb) {
		this.noDisturb = noDisturb;
	}
	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}

	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
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
	 * @return the receiveFlag
	 */
	public Integer getReceiveFlag() {
		return receiveFlag;
	}

	/**
	 * @param receiveFlag the receiveFlag to set
	 */
	public void setReceiveFlag(Integer receiveFlag) {
		this.receiveFlag = receiveFlag;
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
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the groupUserName
	 */
	public String getGroupUserName() {
		return groupUserName;
	}

	/**
	 * @param groupUserName the groupUserName to set
	 */
	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
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
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChatMessageRequest [senderFlag=");
		builder.append(senderFlag);
		builder.append(", receiveFlag=");
		builder.append(receiveFlag);
		builder.append(", resend=");
		builder.append(resend);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", groupUserName=");
		builder.append(groupUserName);
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
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", memberNickName=");
		builder.append(memberNickName);
		builder.append("]");
		return builder.toString();
	}

}
