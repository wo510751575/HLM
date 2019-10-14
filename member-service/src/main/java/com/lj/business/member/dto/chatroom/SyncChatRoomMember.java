package com.lj.business.member.dto.chatroom;

import java.io.Serializable;

public class SyncChatRoomMember implements Serializable { 

	private static final long serialVersionUID = 3020224540413675039L;

    /**
     * 群成员微信号 .
     */
    private String userName;

    /**
     * 群成员昵称 .
     */
    private String nickName;

    /**
     * 群成员头像 .
     */
    private String headUrl;
    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;
    
    
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncChatRoomMember [userName=");
		builder.append(userName);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", headUrl=");
		builder.append(headUrl);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append("]");
		return builder.toString();
	}

}
