package com.lj.business.ai.dto;

import java.io.Serializable;
import java.util.Date;

public class MerchantPreAnswerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3169058779946761138L;

	private String code;

	private String merchantNo;

	private String problemCode;

	private String answerContent;

	private String answerWord;
	
	private String answerType;

	private String status;

	private String remark;

	private Date createDate;

	private Date updateDate;

	private Integer answerTargetCount;

	private Integer answerScanCount;

	private String guidMemberNo;

	private String guidMemberName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerWord() {
		return answerWord;
	}

	public void setAnswerWord(String answerWord) {
		this.answerWord = answerWord;
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

	public Integer getAnswerTargetCount() {
		return answerTargetCount;
	}

	public void setAnswerTargetCount(Integer answerTargetCount) {
		this.answerTargetCount = answerTargetCount;
	}

	public Integer getAnswerScanCount() {
		return answerScanCount;
	}

	public void setAnswerScanCount(Integer answerScanCount) {
		this.answerScanCount = answerScanCount;
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

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerchantPreAnswerDto [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", problemCode=");
		builder.append(problemCode);
		builder.append(", answerContent=");
		builder.append(answerContent);
		builder.append(", answerWord=");
		builder.append(answerWord);
		builder.append(", answerType=");
		builder.append(answerType);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", answerTargetCount=");
		builder.append(answerTargetCount);
		builder.append(", answerScanCount=");
		builder.append(answerScanCount);
		builder.append(", guidMemberNo=");
		builder.append(guidMemberNo);
		builder.append(", guidMemberName=");
		builder.append(guidMemberName);
		builder.append("]");
		return builder.toString();
	}
}
