package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

import com.lj.base.core.pagination.PageParamEntity;

public class FindHistoryChatInfo extends PageParamEntity implements Serializable {

	
	private static final long serialVersionUID = 5069064010406196641L;
	
	/**
	 * 消息msgId
	 */
	private String msgId;

	/**
	 * 标识该聊天记录是否为重新发送，默认为false不是重发
	 */
	private boolean resend = Boolean.FALSE;
	
	/**
	 * 导购编号，导购客户端非空
	 */
	private String memberNoGm;
	
	/**
	 * 导购微信号，导购客户端非空
	 */
	private String noWxGm;

	/**
	 * 客户编号，导购客户端非空
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
     * 发送人标识：1导购发送、2客户发送
     */
    private Integer senderFlag;
	
	/**
     * 消息类型 1文本 3图片 34语音 43视频 48定位 49分享 50视频聊天 10000系统信息（添加好友）318767153系统安全提示
     * 300 - 发送图片原图
     * 494小程序
     * 非空
     */
    private String type;
    
    /**
     * 内容，非空
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
     * 创建时间
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
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 客户名称 .
     */
    private String memberName;
    
    /**手机号码 */
 	private String mobile;
 	
 	/** 终端微信头像*/
 	private String terminalHeadurl;
 	/**
     * 大图
     */
    private String bigImg;
    /**
     * 中图
     */
    private String midImg;
    
    
	public String getMidImg() {
		return midImg;
	}

	public void setMidImg(String midImg) {
		this.midImg = midImg;
	}
	public String getBigImg() {
		return bigImg;
	}

	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public boolean isResend() {
		return resend;
	}
	public void setResend(boolean resend) {
		this.resend = resend;
	}
	public String getMemberNoGm() {
		return memberNoGm;
	}
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}
	public String getNoWxGm() {
		return noWxGm;
	}
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getNoWx() {
		return noWx;
	}
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getGroupUserName() {
		return groupUserName;
	}
	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResources() {
		return resources;
	}
	public void setResources(String resources) {
		this.resources = resources;
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
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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
	public String getMemberHeadUrl() {
		return memberHeadUrl;
	}
	public void setMemberHeadUrl(String memberHeadUrl) {
		this.memberHeadUrl = memberHeadUrl;
	}
	public String getMemberNameGm() {
		return memberNameGm;
	}
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTerminalHeadurl() {
		return terminalHeadurl;
	}
	public void setTerminalHeadurl(String terminalHeadurl) {
		this.terminalHeadurl = terminalHeadurl;
	}
	
}
