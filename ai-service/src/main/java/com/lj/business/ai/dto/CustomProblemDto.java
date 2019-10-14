package com.lj.business.ai.dto;

import java.io.Serializable;
import java.util.Date;

public class CustomProblemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8983354207564089058L;

	private String code;

	private String merchantNo;

	private String memberName;

	private String memberNo;

	private String guidMemberNo;

	private String guidMemberName;

	private String customProblem;

	private String customProblemWord;

	private String isTargetAnswer;

	private String merchantProblemCode;

	private String merchantAnswer;

	private String sessionId;

	private Integer sessionsSeq;

	private String status;

	private String remark;

	private Date createDate;

	private Date updateDate;

	public Integer getSessionsSeq() {
		return sessionsSeq;
	}

	public void setSessionsSeq(Integer sessionsSeq) {
		this.sessionsSeq = sessionsSeq;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getCustomProblem() {
		return customProblem;
	}

	public void setCustomProblem(String customProblem) {
		this.customProblem = customProblem;
	}

	public String getCustomProblemWord() {
		return customProblemWord;
	}

	public void setCustomProblemWord(String customProblemWord) {
		this.customProblemWord = customProblemWord;
	}

	public String getIsTargetAnswer() {
		return isTargetAnswer;
	}

	public void setIsTargetAnswer(String isTargetAnswer) {
		this.isTargetAnswer = isTargetAnswer;
	}

	public String getMerchantProblemCode() {
		return merchantProblemCode;
	}

	public void setMerchantProblemCode(String merchantProblemCode) {
		this.merchantProblemCode = merchantProblemCode;
	}

	public String getMerchantAnswer() {
		return merchantAnswer;
	}

	public void setMerchantAnswer(String merchantAnswer) {
		this.merchantAnswer = merchantAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getGuidMemberNo() {
		return guidMemberNo;
	}

	public void setGuidMemberNo(String guidMemberNo) {
		this.guidMemberNo = guidMemberNo;
	}

	public String getGuidMemberName() {
		return guidMemberName;
	}

	public void setGuidMemberName(String guidMemberName) {
		this.guidMemberName = guidMemberName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomProblemDto [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", guidMemberNo=");
		builder.append(guidMemberNo);
		builder.append(", guidMemberName=");
		builder.append(guidMemberName);
		builder.append(", customProblem=");
		builder.append(customProblem);
		builder.append(", customProblemWord=");
		builder.append(customProblemWord);
		builder.append(", isTargetAnswer=");
		builder.append(isTargetAnswer);
		builder.append(", merchantProblemCode=");
		builder.append(merchantProblemCode);
		builder.append(", merchantAnswer=");
		builder.append(merchantAnswer);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", sessionsSeq=");
		builder.append(sessionsSeq);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}
}
