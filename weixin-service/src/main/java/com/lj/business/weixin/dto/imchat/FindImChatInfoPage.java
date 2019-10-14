package com.lj.business.weixin.dto.imchat;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindImChatInfoPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54770060518688204L; 

	/**
     * CODE .
     */
    private String code;
    /**
     * 导购编号 .
     */
    private String memberNoGm;
    /**
     * 客户编号 .
     */
    private String memberNo;
    /**
     * 导购微信号 .
     */
    private String noWxGm;
    /**
     * 状态 .
     */
	private String status;
	/**
     * 发送人标识：1导购发送、2客户发送
              .
     */
    private Integer senderFlag;
    
    /**
     * 聊天时间 - 从
     */
    private Date chatTimeBegin;
    /**
     * 聊天时间 - 到
     */
    private Date chatTimeEnd;
    /**
     * 客户微信号 .
     */
    private String noWx;
    /**
     *  '消息类型：1文本、3图片、34语音、43视频、42名片、47图片表情、48定位 49分享、50视频聊天、10000系统信息'.
     */
    private String type;
    
    private String chatroomType;

	/** * 商户编号 . */
	private String merchantNo;
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
    List<String> memberNos;
    
    /**
     * 小于状态
     */
    private Integer leStatus;
    
	public Integer getLeStatus() {
		return leStatus;
	}

	public void setLeStatus(Integer leStatus) {
		this.leStatus = leStatus;
	}

	public String getChatroomType() {
		return chatroomType;
	}

	public void setChatroomType(String chatroomType) {
		this.chatroomType = chatroomType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the chatTimeEnd
	 */
	public Date getChatTimeEnd() {
		return chatTimeEnd;
	}

	/**
	 * @param chatTimeEnd the chatTimeEnd to set
	 */
	public void setChatTimeEnd(Date chatTimeEnd) {
		this.chatTimeEnd = chatTimeEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getSenderFlag() {
		return senderFlag;
	}

	public void setSenderFlag(Integer senderFlag) {
		this.senderFlag = senderFlag;
	}

	/**
	 * @return the chatTimeBegin
	 */
	public Date getChatTimeBegin() {
		return chatTimeBegin;
	}

	/**
	 * @param chatTimeBegin the chatTimeBegin to set
	 */
	public void setChatTimeBegin(Date chatTimeBegin) {
		this.chatTimeBegin = chatTimeBegin;
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
	
	

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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

	public List<String> getMemberNos() {
		return memberNos;
	}

	public void setMemberNos(List<String> memberNos) {
		this.memberNos = memberNos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImChatInfoPage [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", status=");
		builder.append(status);
		builder.append(", senderFlag=");
		builder.append(senderFlag);
		builder.append(", chatTimeBegin=");
		builder.append(chatTimeBegin);
		builder.append(", chatTimeEnd=");
		builder.append(chatTimeEnd);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
