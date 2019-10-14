package com.lj.business.weixin.dto.imchat;

import java.io.Serializable;

public class UpdateImChatInfoReceived implements Serializable { 

	private static final long serialVersionUID = -3730607000786134229L;

	/**
     * 微信ID .
     */
    private String msgId;

    /**
     * 接收端标识：1导购、2客户
     */
    private Integer receivedFlag;
    
    /**
     * 消息状态：true接收成功、false接收失败 .
     */
    private boolean success = Boolean.TRUE;
    
    /**
     * 发送失败编码
     */
    private String errorCode;
    
    /**
     * 发送失败描述
     */
    private String errorMessage;

	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the receivedFlag
	 */
	public Integer getReceivedFlag() {
		return receivedFlag;
	}

	/**
	 * @param receivedFlag the receivedFlag to set
	 */
	public void setReceivedFlag(Integer receivedFlag) {
		this.receivedFlag = receivedFlag;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateImChatInfoReceived [msgId=");
		builder.append(msgId);
		builder.append(", receivedFlag=");
		builder.append(receivedFlag);
		builder.append(", success=");
		builder.append(success);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}
}
