package com.lj.business.member.dto.chatroom;

import com.lj.base.core.pagination.PageParamEntity;

public class FindChatRoomMemberPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001501638916123497L; 

    /**
     * 群CODE .
     */
    private String roomCode;

    /**
     * 中控微信号 .
     */
    private String noWxZk;

    /**
     * 群名 .
     */
    private String chatRoomName;

    /**
     * 群成员微信号 .
     */
    private String userName;

    /**
     * 群成员昵称 .
     */
    private String nickName;

    /**
     * 状态：0初始化、1有效、2无效（删除） .
     */
    private Integer status;

    /**
     * 终端编号 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

	/**
	 * @return the roomCode
	 */
	public String getRoomCode() {
		return roomCode;
	}

	/**
	 * @param roomCode the roomCode to set
	 */
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * @return the shopNo
	 */

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
		builder.append("FindChatRoomMemberPage [roomCode=");
		builder.append(roomCode);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}
