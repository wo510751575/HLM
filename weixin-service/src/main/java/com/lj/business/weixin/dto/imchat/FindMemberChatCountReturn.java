package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class FindMemberChatCountReturn implements Serializable {

	private static final long serialVersionUID = 3621278569380736496L;

	/**
	 * 客户编号 .
	 */
	private String memberNo;

	/**
	 * 导购编号
	 */
	private String memberNoGm;

    /**
     * 聊天记录数
     */
    private int chatCount;

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
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the chatCount
	 */
	public int getChatCount() {
		return chatCount;
	}

	/**
	 * @param chatCount the chatCount to set
	 */
	public void setChatCount(int chatCount) {
		this.chatCount = chatCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindMemberChatCountReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", chatCount=");
		builder.append(chatCount);
		builder.append("]");
		return builder.toString();
	}

}
