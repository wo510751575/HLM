package com.lj.business.member.dto.chatroom;

import java.io.Serializable;

public class CreateChatRoom implements Serializable { 

	private static final long serialVersionUID = 4436118662711381278L;

	/**
	 * 中控微信，非空
	 */
	private String noWxZk;
	
	//群组code
	private String pmCode;
	
    //群组名称
	private String pmName;



	/**
     * 导购编号，非空 .
     */
    private String memberNoGm;
	/**
	 * 群昵称
	 */
	private String roomNickName;
	
	/**
	 * 群成员微信
	 */
	private String userNames;
	
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

	public String getNoWxZk() {
		return noWxZk;
	}

	public void setNoWxZk(String noWxZk) {
		this.noWxZk = noWxZk;
	}


	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(String roomNickName) {
		this.roomNickName = roomNickName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	
	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateChatRoom [noWxZk=");
		builder.append(noWxZk);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", roomNickName=");
		builder.append(roomNickName);
		builder.append(", userNames=");
		builder.append(userNames);
		builder.append("]");
		return builder.toString();
	}

}
