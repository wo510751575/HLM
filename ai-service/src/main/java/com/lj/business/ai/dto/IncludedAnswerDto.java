package com.lj.business.ai.dto;

import java.io.Serializable;

public class IncludedAnswerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 649807930958318562L;

	private String memberNo;

	private String memberName;

	private String guidMemberNo;

	private String guidMemberName;

	private String guidAnswer;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getGuidMemberName() {
		return guidMemberName;
	}

	public void setGuidMemberName(String guidMemberName) {
		this.guidMemberName = guidMemberName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getGuidMemberNo() {
		return guidMemberNo;
	}

	public void setGuidMemberNo(String guidMemberNo) {
		this.guidMemberNo = guidMemberNo;
	}

	public String getGuidAnswer() {
		return guidAnswer;
	}

	public void setGuidAnswer(String guidAnswer) {
		this.guidAnswer = guidAnswer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IncludedAnswerDto [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", guidMemberNo=");
		builder.append(guidMemberNo);
		builder.append(", guidMemberName=");
		builder.append(guidMemberName);
		builder.append(", guidAnswer=");
		builder.append(guidAnswer);
		builder.append("]");
		return builder.toString();
	}

}
