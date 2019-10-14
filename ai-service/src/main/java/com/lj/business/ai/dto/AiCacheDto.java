package com.lj.business.ai.dto;

import java.io.Serializable;

public class AiCacheDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2639707908972631422L;
	/**
	 * 会话ID
	 */
	private String sessionId = null;
	/**
	 * 上次匹配的预设问题CODE
	 */
	private String problemCode = null;
	/**
	 * 客户问题CODE
	 */
	private String customProblemCode = null;
	/**
	 * 匹配次数
	 */
	private Integer currentCount = 0;

	/**
	 * 处理导购
	 */
	private String guidMemberNo;
	
	
	private String memberNo;
	
	

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	public String getCustomProblemCode() {
		return customProblemCode;
	}

	public void setCustomProblemCode(String customProblemCode) {
		this.customProblemCode = customProblemCode;
	}

	public String getGuidMemberNo() {
		return guidMemberNo;
	}

	public void setGuidMemberNo(String guidMemberNo) {
		this.guidMemberNo = guidMemberNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AiCacheDto [sessionId=");
		builder.append(sessionId);
		builder.append(", problemCode=");
		builder.append(problemCode);
		builder.append(", customProblemCode=");
		builder.append(customProblemCode);
		builder.append(", currentCount=");
		builder.append(currentCount);
		builder.append(", guidMemberNo=");
		builder.append(guidMemberNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append("]");
		return builder.toString();
	}

}
