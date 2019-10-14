package com.lj.business.cf.dto.messagePush;

import java.io.Serializable;

public class MessagePushCodeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752028751068271360L;
	
	private String code;
	
	private String pushDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	@Override
	public String toString() {
		return "MessagePushCodeDto [code=" + code + ", pushDate=" + pushDate
				+ "]";
	}

}
