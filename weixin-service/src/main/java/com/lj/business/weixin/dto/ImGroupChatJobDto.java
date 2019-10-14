package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;

public class ImGroupChatJobDto implements Serializable { 

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1416875328224929802L;

	/**
	 * 编号
	 */
    private String code;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 群发会员编号（,分隔）
     */
    private String memberNos;

    /**
     * 群发会员名称（,分隔）
     */
    private String memberNames;

    /**
     * 群发会员微信（,分隔）
     */
    private String memberNoWxs;

    /**
     * 导购编号
     */
    private String memberNoGm;

    /**
     * 导购名称
     */
    private String memberNameGm;

    /**
     * 导购微信号
     */
    private String noWxGm;

    /**
     * 消息类型：1文本 2链接 3图片 4公众号 5小程序
     */
    private String type;

    /**
     * 消息状态：1 未执行、2执行中、3已完成
     */
    private String status;

    /**
     * 内容
     */
    private String content;
    
    /**
     * 资源路径：语音、图片、视频
     */
    private String resourcesPath;

    /**
     * 群聊类型：1单聊、2群聊
     */
    private Integer chatroomType;

    /**
     * 群聊消息发送人微信
     */
    private String chatroomNoWx;

    /**
     * 聊天助手编号
     */
    private String chatAssistantCode;

    /**
     * 创建时间
     */
    private Date createDate;

    private String remark;

    private String remark2;

    private String remark3;

    private String remark4;

    /**
     * 1永不 2每小时 3每天 4每周 5每月
     */
    private String repetition;
    
    /**
     * 群发时间
     */
    private Date sendDate;
    
    //资源code,公众号,小程序,优惠券
    private String sourceCode;

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

	public String getMemberNos() {
		return memberNos;
	}

	public void setMemberNos(String memberNos) {
		this.memberNos = memberNos;
	}

	public String getMemberNames() {
		return memberNames;
	}

	public void setMemberNames(String memberNames) {
		this.memberNames = memberNames;
	}

	public String getMemberNoWxs() {
		return memberNoWxs;
	}

	public void setMemberNoWxs(String memberNoWxs) {
		this.memberNoWxs = memberNoWxs;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public Integer getChatroomType() {
		return chatroomType;
	}

	public void setChatroomType(Integer chatroomType) {
		this.chatroomType = chatroomType;
	}

	public String getChatroomNoWx() {
		return chatroomNoWx;
	}

	public void setChatroomNoWx(String chatroomNoWx) {
		this.chatroomNoWx = chatroomNoWx;
	}

	public String getChatAssistantCode() {
		return chatAssistantCode;
	}

	public void setChatAssistantCode(String chatAssistantCode) {
		this.chatAssistantCode = chatAssistantCode;
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


	public String getRepetition() {
		return repetition;
	}

	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

    
}
