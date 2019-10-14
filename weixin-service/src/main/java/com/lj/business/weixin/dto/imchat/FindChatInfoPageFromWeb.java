/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.weixin.dto.imchat;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

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
public class FindChatInfoPageFromWeb extends PageParamEntity {

	private static final long serialVersionUID = 1360332985297920752L;
	
	private String code;
	private boolean isChatRoomFlag;
	private String memberNoGm;
	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public boolean isChatRoomFlag() {
		return isChatRoomFlag;
	}

	public void setChatRoomFlag(boolean isChatRoomFlag) {
		this.isChatRoomFlag = isChatRoomFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 客户编号，非空（memberNoGm为空时表示群聊，且客户编号为群记录的code）
	 */
	private String memberNo;
	
	/**
     * 第三方已读标识：0未读、1已读 .
     */
    private Integer thirdReadFlag;
    
    /**
     * 聊天时间-从,不包括
     */
    private Date chatTimeBegin;
    
    /**
     * 聊天时间-到,不包括
     */
    private Date chatTimeEnd;
    
    /**
     * 聊天日期
     */
    private Date chatDate;

    /**
     * 是否过滤发送失败的聊天记录，默认为过滤
     */
    private boolean filterFailChat = Boolean.TRUE;
    /**
     * 终端微信
     */
    private String noWxGm;
    
	public String getNoWxGm() {
		return noWxGm;
	}

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

	public Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}

	/**
	 * @return the filterFailChat
	 */
	public boolean getFilterFailChat() {
		return filterFailChat;
	}

	/**
	 * @param filterFailChat the filterFailChat to set
	 */
	public void setFilterFailChat(boolean filterFailChat) {
		this.filterFailChat = filterFailChat;
	}

	@Override
	public String toString() {
		return "FindChatInfoPageFromWeb [code=" + code + ", isChatRoomFlag=" + isChatRoomFlag + ", memberNo=" + memberNo
				+ ", thirdReadFlag=" + thirdReadFlag + ", chatTimeBegin=" + chatTimeBegin + ", chatTimeEnd="
				+ chatTimeEnd + ", chatDate=" + chatDate + ", filterFailChat=" + filterFailChat + ", noWxGm=" + noWxGm
				+ "]";
	}

}
