package com.lj.business.member.dto.chatroom;

import java.io.Serializable;

public class DelChatRoomMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233302027440215575L; 

	/**
     * CODE .
     */
    private String code;
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

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getNoWxZk() {
		return noWxZk;
	}

	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}

	public String getChatRoomName() {
		return chatRoomName;
	}

	public void setChatRoomName(String chatRoomName) {
		this.chatRoomName = chatRoomName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DelChatRoomMember [code=");
		builder.append(code);
		builder.append(", roomCode=");
		builder.append(roomCode);
		builder.append(", noWxZk=");
		builder.append(noWxZk);
		builder.append(", chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}
}
