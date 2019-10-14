package com.lj.business.weixin.dto;

import java.io.Serializable;

public class UpdateRedpackDetailByPoll implements Serializable { 

	private static final long serialVersionUID = -8809804198765868391L;

	/**
     *  code,非空.
     */
    private String code;

    /**
     * 微信红包ID .
     */
    private String redPackId;

    /**
	 * 红包状态,非空：
	 * 0-未发送
	 * 1-发送中
	 * 2-发送成功
	 * 3-发送失败
	 * 4-已领取
	 * 5-已退回
	 * 99-没有该红包记录
	 */
    private String status;
	
	/**
	 * 发送失败错误编码
	 */
	private String failCode;
	
	/**
	 * 发送失败错误描述
	 */
	private String failMsg;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the redPackId
	 */
	public String getRedPackId() {
		return redPackId;
	}

	/**
	 * @param redPackId the redPackId to set
	 */
	public void setRedPackId(String redPackId) {
		this.redPackId = redPackId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the failCode
	 */
	public String getFailCode() {
		return failCode;
	}

	/**
	 * @param failCode the failCode to set
	 */
	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	/**
	 * @return the failMsg
	 */
	public String getFailMsg() {
		return failMsg;
	}

	/**
	 * @param failMsg the failMsg to set
	 */
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateRedpackDetailByPoll [code=");
		builder.append(code);
		builder.append(", redPackId=");
		builder.append(redPackId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", failCode=");
		builder.append(failCode);
		builder.append(", failMsg=");
		builder.append(failMsg);
		builder.append("]");
		return builder.toString();
	}

}
