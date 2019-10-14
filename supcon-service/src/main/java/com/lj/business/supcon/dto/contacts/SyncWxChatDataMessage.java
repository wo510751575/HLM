package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

/**
 * 同步历史聊天记录
 * @author 1234
 *
 */
public class SyncWxChatDataMessage implements Serializable {

	private static final long serialVersionUID = 6194413100374551408L;
	
	/**
	 * 终端微信
	 */
	private String noWxGm;
	
	/**
	 * 最早一条聊天记录
	 */
	private String chatTime;
	
	public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SyncWxChatDataMessage [noWxGm=");
		builder.append(noWxGm);
		builder.append(",chatTime=");
		builder.append(chatTime);
		builder.append("]");
		return builder.toString();
	}
	
}
