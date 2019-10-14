package com.lj.business.member.dto.chatroom;

import java.io.Serializable;
import java.util.List;

public class SyncChatRoom implements Serializable { 

	private static final long serialVersionUID = 4436118662711381278L;

	/**
     * 群名 .
     */
    private String chatRoomName;

    /**
     * 群昵称 .
     */
    private String roomNickName;

    /**
     * 群主用户名 .
     */
    private String roomOwner;

    /**
     * 群头像 .
     */
    private String headUrl;

    /**
     * 群成员列表
     */
    private List<SyncChatRoomMember> memberList;
    /**
	 * 导购编号
	 */
	private String memberNoGm;
	
	private String pmCode;
	
	private String pmName;

	
	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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
	 * @return the roomNickName
	 */
	public String getRoomNickName() {
		return roomNickName;
	}

	/**
	 * @param roomNickName the roomNickName to set
	 */
	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
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
	 * @return the headUrl
	 */
	public String getHeadUrl() {
		return headUrl;
	}

	/**
	 * @param headUrl the headUrl to set
	 */
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	/**
	 * @return the memberList
	 */
	public List<SyncChatRoomMember> getMemberList() {
		return memberList;
	}

	/**
	 * @param memberList the memberList to set
	 */
	public void setMemberList(List<SyncChatRoomMember> memberList) {
		this.memberList = memberList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncChatRoom [chatRoomName=");
		builder.append(chatRoomName);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", roomOwner=");
		builder.append(roomOwner);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", memberList=");
		builder.append(memberList);
		builder.append("]");
		return builder.toString();
	}

}
