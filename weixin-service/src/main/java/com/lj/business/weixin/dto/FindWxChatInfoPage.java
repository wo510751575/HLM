package com.lj.business.weixin.dto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity; 

/**
 * 
 * 
 * 类说明：聊天信息查询dto
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年7月20日
 */
public class FindWxChatInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145026781282640123L; 

    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNo;
    /**
     *导购编号(转义)
     */
    private String memberNoGm;
    /**
     * 导购姓名 .
     */
    private String memberName;

    /**
     * 消息ID服务器 .
     */
    private String msgsvrid;

    /**
     * 消息类型 1文本 3图片 34语音 43视频 48定位 49分享 50视频聊天 100系统信息（添加好友）318767153系统安全提示
              .
     */
    private String type;

    /**
     * 发送人标识
             1自己发的 0对方发的
              .
     */
    private String issend;

    /**
     * 微信号 .
     */
    private String talker;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 聊天时间 .
     */
    private Date chatDate;
    
    /**
     *  时间戳
     */
    private String timestamp;

    /**
     * 资源路径，
             语音、图片、视频 .
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
     * 创建时间 .
     */
    private String shareUrl;

    /**
     * 消息状态 本人（1 未发送 2发送成功） 对方待定（3, 4） .
     */
    private String status;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;


	/** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;
	/**
	 * 微信号
	 */
	private String noWx;
	
	
	

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMsgsvrid() {
		return msgsvrid;
	}

	public void setMsgsvrid(String msgsvrid) {
		this.msgsvrid = msgsvrid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssend() {
		return issend;
	}

	public void setIssend(String issend) {
		this.issend = issend;
	}

	public String getTalker() {
		return talker;
	}

	public void setTalker(String talker) {
		this.talker = talker;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "FindWxChatInfoPage [code=" + code + ", memberNo=" + memberNo
				+ ", memberNoGm=" + memberNoGm + ", memberName=" + memberName
				+ ", msgsvrid=" + msgsvrid + ", type=" + type + ", issend="
				+ issend + ", talker=" + talker + ", content=" + content
				+ ", chatDate=" + chatDate + ", timestamp=" + timestamp
				+ ", resourcesPath=" + resourcesPath + ", shareTitle="
				+ shareTitle + ", shareDes=" + shareDes + ", shareUrl="
				+ shareUrl + ", status=" + status + ", createDate="
				+ createDate + ", remark=" + remark + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", noWx=" + noWx + "]";
	}
	
	
	
	
}
