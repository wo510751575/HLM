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
 * 朋友圈查询
 * 
 *
 * @author 彭俊霖
 * @CreateDate 2018年6月21日下午3:20:39
 */
public class FindSendFriendsJobPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145026781282640123L; 

    /**
     * CODE .
     */
    private String code;
    
    /**
               * 微信
     */
    private String noWxShop;
    
    private String noWxs;
    


	/**
                * 商户编号 .
     */
    private String merchantNo;
    
    /**
     * 开始创建时间
     */
    private String createDateBegin;
    
    /**
     * 结束创建时间
     */
    private String createDateEnd;
    
    
    /**
     * 任务状态
     */
    private String jobState;
    
    /**
     * 预定执行时间
     */
    private Date executeTime;
    
    
    /**
     * 真正执行时间
     */
    private Date realExecuteTime;
    


	/**
     * 朋友圈类型
     */
    private String type; // 1 文字 2 图文，3音乐文字 4视频文字
    
    /**
     * 多图片地址
     */
    private String imgAddrs;
    /**
     * 音乐或视频地址
     */
    private String resourcePath;
    
    private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNoWxs() {
		return noWxs;
	}

	public void setNoWxs(String noWxs) {
		this.noWxs = noWxs;
	}
    public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Date getRealExecuteTime() {
		return realExecuteTime;
	}

	public void setRealExecuteTime(Date realExecuteTime) {
		this.realExecuteTime = realExecuteTime;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgAddrs() {
		return imgAddrs;
	}

	public void setImgAddrs(String imgAddrs) {
		this.imgAddrs = imgAddrs;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getNoWxShop() {
		return noWxShop;
	}

	public void setNoWxShop(String noWxShop) {
		this.noWxShop = noWxShop;
	}
	


	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public String getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(String createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindSendFriendsJobPage [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", createDateBegin=");
		builder.append(createDateBegin);
		builder.append(", createDateEnd=");
		builder.append(createDateEnd);
		builder.append(", jobState=");
		builder.append(jobState);
		builder.append("]");
		return builder.toString();
	}

}
