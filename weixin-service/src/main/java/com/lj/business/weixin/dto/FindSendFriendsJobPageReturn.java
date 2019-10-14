package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
public class FindSendFriendsJobPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3237523980826266332L; 

	/**
     * CODE .
     */
    private String code;
    
    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 商户名称 .
     */
    private String merchantName;
    


    /**
     * 内容 .
     */
    private String content;

    /**
     * 图片地址 .
     */
    private String imgAddr;

    /**
     * 链接地址 .
     */
    private String linkUrl;

    /**
     * 是否自动评论 .
     */
    private Integer autoComment;

    /**
     * 自动评论内容 .
     */
    private String autoContent;

    /**
     * 发送微信 .
     */
    private String noWx;
    
    /**
     * 发送微信 .
     */
    private String noWxs;


	/**
     * 预定执行时间 .
     */
    private Date executeTime;


    /**
     * 实际执行时间
     */
    private Date realExecuteTime;
    
    /**
     * 朋友圈类型
     */
    private String type; // 1 图文 2 视频，3文章链接

	/**
     * 任务状态 .
     */
    private Integer jobState;

    /**
     * 已发送微信 .
     */
    private String sentNoWxs;

    /**
     * 创建人 .
     */
    private String createId;
    
    private String createName;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注2 .
     */
    private String remark2;

    /**
     * 备注3 .
     */
    private String remark3;

    /**
     * 备注4 .
     */
    private String remark4;

    private String createUserLevel;


	/**
     * 音乐或视频地址
     */
    private String resourcePath;
    /**
     * 商户发朋友圈Code
     */
    private String merchantJobCode;
    
    public String getMerchantJobCode() {
		return merchantJobCode;
	}

	public void setMerchantJobCode(String merchantJobCode) {
		this.merchantJobCode = merchantJobCode;
	}
    public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateUserLevel() {
		return createUserLevel;
	}

	public void setCreateUserLevel(String createUserLevel) {
		this.createUserLevel = createUserLevel;
	}
	
    public Date getRealExecuteTime() {
		return realExecuteTime;
	}

	public void setRealExecuteTime(Date realExecuteTime) {
		this.realExecuteTime = realExecuteTime;
	}
    
    public String getNoWxs() {
		return noWxs;
	}

	public void setNoWxs(String noWxs) {
		this.noWxs = noWxs;
	}
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getAutoComment() {
		return autoComment;
	}

	public void setAutoComment(Integer autoComment) {
		this.autoComment = autoComment;
	}

	public String getAutoContent() {
		return autoContent;
	}

	public void setAutoContent(String autoContent) {
		this.autoContent = autoContent;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}



	public Integer getJobState() {
		return jobState;
	}

	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}

	public String getSentNoWxs() {
		return sentNoWxs;
	}

	public void setSentNoWxs(String sentNoWxs) {
		this.sentNoWxs = sentNoWxs;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
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

	@Override
	public String toString() {
		return "FindSendFriendsJobPageReturn [code=" + code + ", merchantNo=" + merchantNo + ", merchantName="
				+ merchantName + ", content=" + content + ", imgAddr=" + imgAddr + ", linkUrl=" + linkUrl
				+ ", autoComment=" + autoComment + ", autoContent=" + autoContent + ", noWx=" + noWx + ", noWxs="
				+ noWxs + ", executeTime=" + executeTime + ", realExecuteTime=" + realExecuteTime + ", type=" + type
				+ ", jobState=" + jobState + ", sentNoWxs=" + sentNoWxs + ", createId=" + createId + ", createName="
				+ createName + ", createDate=" + createDate + ", remark=" + remark + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4 + ", createUserLevel=" + createUserLevel
				+ ", resourcePath=" + resourcePath + "]";
	}

}
