package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;
import java.util.Date;

public class AddImChatInfo implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -7772258958655132378L;

    /**
     * 消息ID，非空 .
     */
    private String code;
	
	/**
	 * 标识该聊天记录是否为重新发送，默认为false不是重发
	 */
	private boolean resend = Boolean.FALSE;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购微信 .
     */
    private String noWxGm;
    
    /**
     * 客户编号 .
     */
    private String memberNo;
    
    /**
     * 客户微信 .
     */
    private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;

    /**
     * 发送人标识：1导购发送、2客户发送
     */
    private Integer senderFlag;

    /**
     * 消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息 .
     */
    private String type;

    /**
     * 聊天时间 .
     */
    private Date chatTime;

    /**
     * 资源路径:语音、图片、视频 .
     */
    private String resourcesPath;

    /**
     * 图片地址 .
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
    private Integer chatroomType = 1;
    
    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;

    /**
     * 消息来源 .
     */
    private Integer msgSource;
    
    /**
     * 手机串号
     */
    private String imei;

    /**
     * 内容 .
     */
    private String content;
    /**
     * 发送状态
     */
    private String status;
    /**
     * 第三方已读标识：0未读、1已读 .
     */
    private Integer thirdReadFlag;
    
    /**
     * 发送人同步状态：0未同步、1已同步。从导购客户端、中控客户端（客户微信）外第三方发送给客户，则需将消息同步到导购客户端
     */
    private Integer senderSyncStatus;

	public Integer getSenderSyncStatus() {
		return senderSyncStatus;
	}

	public void setSenderSyncStatus(Integer senderSyncStatus) {
		this.senderSyncStatus = senderSyncStatus;
	}

	public Integer getThirdReadFlag() {
		return thirdReadFlag;
	}

	public void setThirdReadFlag(Integer thirdReadFlag) {
		this.thirdReadFlag = thirdReadFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMemberNoGm() {
		return memberNoGm;
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

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public Integer getSenderFlag() {
		return senderFlag;
	}

	public void setSenderFlag(Integer senderFlag) {
		this.senderFlag = senderFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareDes() {
		return shareDes;
	}

	public void setShareDes(String shareDes) {
		this.shareDes = shareDes;
	}

	public String getShareUrl() {
		return shareUrl;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddImChatInfo [code=");
		builder.append(code);
		builder.append(", resend=");
		builder.append(resend);
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
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", type=");
		builder.append(type);
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
		builder.append(", msgSource=");
		builder.append(msgSource);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}

}
