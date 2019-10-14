package com.lj.business.member.dto.chatroom;

import java.io.Serializable;

public class FindChatRoom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126223838069424961L; 

	private String code;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 群名 .
     */
    private String chatRoomName;

    /**
     * 群主用户名 .
     */
    private String roomOwner;

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     */
    private Integer status;

    /**
     * 商户编号 .
     */
    private String merchantNo;
    /**
     * 导购编号 .
     */
    private String memberNoGm;
    
    
    public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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
	 * @return the noWxZk
	 */
	public String getNoWxZk() {
		return noWxZk;
	}

	/**
	 * @param noWxZk the noWxZk to set
	 */
	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}

	/**
	 * @return the chatRoomName
	 */
	public String getChatRoomName() {
		return chatRoomName;
	}

	/**
	 * @param chatRoomName the chatRoomName to set
	 */
	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	/**
	 * @return the roomOwner
	 */
	public String getRoomOwner() {
		return roomOwner;
	}

	/**
	 * @param roomOwner the roomOwner to set
	 */
	public void setRoomOwner(String roomOwner) {
		this.roomOwner = roomOwner;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindChatRoom [code=");
		builder.append(code);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", roomOwner=");
		builder.append(roomOwner);
		builder.append(", status=");
		builder.append(status);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}

}
