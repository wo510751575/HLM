/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.login;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：导购登录结果报文参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月23日
 */
public class LoginResult extends BaseResponse {

	private static final long serialVersionUID = 5729470213594618342L;
	
	/**
	 * 客户版本号,导购所属客户最大版本号
	 */
	private long memberVersion;
	
	/**
	 * 平台表情包最大版本号
	 */
	private long emojiVersion;
	
	/**
	 * 服务器当前时间戳
	 */
	private long timestamp;

	/**
	 * @return the memberVersion
	 */
	public long getMemberVersion() {
		return memberVersion;
	}

	/**
	 * @param memberVersion the memberVersion to set
	 */
	public void setMemberVersion(long memberVersion) {
		this.memberVersion = memberVersion;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the emojiVersion
	 */
	public long getEmojiVersion() {
		return emojiVersion;
	}

	/**
	 * @param emojiVersion the emojiVersion to set
	 */
	public void setEmojiVersion(long emojiVersion) {
		this.emojiVersion = emojiVersion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginResult [");
		builder.append(super.toString());
		builder.append(", memberVersion=");
		builder.append(memberVersion);
		builder.append(", emojiVersion=");
		builder.append(emojiVersion);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
