package com.lj.business.supcon.netty.message.contacts;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 向中控發送同步歷史聊天記錄
 * @author 1234
 *
 */
public class SyncWxChatDataRequest extends BaseDto {
	private static final long serialVersionUID = 2774407018826731749L;
	
	/**
	 * 微信号
	 */
	private String noWxGm;
	
	/**
	 * 最早的一条聊天记录
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
		builder.append("SyncWxChatDataRequest [noWxGm=");
		builder.append(noWxGm);
		builder.append(",chatTime=");
		builder.append(chatTime);
		builder.append("]");
		return builder.toString();
	}
}
