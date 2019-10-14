package com.ye.business.rw.dto;

import java.io.Serializable;
import java.util.Date;

public class ArticleComplaintDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911566782588534125L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 文章CODE .
	 */
	private String articleCode;

	/**
	 * 文章标题 .
	 */
	private String title;

	/**
	 * 投诉内容 .
	 */
	private String complaintContent;

	/**
	 * 投诉证据，截图，多个请使用英文逗号分割 .
	 */
	private String complaintEvidence;

	/**
	 * 投诉人名称 .
	 */
	private String complaintUsername;

	/**
	 * 投诉人联系方式 .
	 */
	private String complaintMobile;

	/**
	 * 审核人ID .
	 */
	private String auditorCode;

	/**
	 * 审核人名称 .
	 */
	private String auditorName;

	/**
	 * 审核时间 .
	 */
	private Date auditorDate;

	/**
	 * 审核状态：noverify-未审核；verify-已处理； .
	 */
	private String auditorStatus;

	/**
	 * 处理结果：normal-正常；forbid-禁用； .
	 */
	private String auditorResult;

	/**
	 * 处理内容 .
	 */
	private String auditorContent;

	/**
	 * 备注 .
	 */
	private String remark;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	/**
	 * 更新人ID .
	 */
	private String updateId;
	
	private String beginDate;
	
	private String endDate;

	/**
	 * CODE .
	 *
	 */
	public String getCode() {
		return code;
	}

	/**
	 * CODE .
	 *
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * 文章CODE .
	 *
	 */
	public String getArticleCode() {
		return articleCode;
	}

	/**
	 * 文章CODE .
	 *
	 */
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode == null ? null : articleCode.trim();
	}

	/**
	 * 文章标题 .
	 *
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 文章标题 .
	 *
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 投诉内容 .
	 *
	 */
	public String getComplaintContent() {
		return complaintContent;
	}

	/**
	 * 投诉内容 .
	 *
	 */
	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent == null ? null : complaintContent.trim();
	}

	/**
	 * 投诉证据，截图，多个请使用英文逗号分割 .
	 *
	 */
	public String getComplaintEvidence() {
		return complaintEvidence;
	}

	/**
	 * 投诉证据，截图，多个请使用英文逗号分割 .
	 *
	 */
	public void setComplaintEvidence(String complaintEvidence) {
		this.complaintEvidence = complaintEvidence == null ? null : complaintEvidence.trim();
	}

	/**
	 * 投诉人名称 .
	 *
	 */
	public String getComplaintUsername() {
		return complaintUsername;
	}

	/**
	 * 投诉人名称 .
	 *
	 */
	public void setComplaintUsername(String complaintUsername) {
		this.complaintUsername = complaintUsername == null ? null : complaintUsername.trim();
	}

	/**
	 * 投诉人联系方式 .
	 *
	 */
	public String getComplaintMobile() {
		return complaintMobile;
	}

	/**
	 * 投诉人联系方式 .
	 *
	 */
	public void setComplaintMobile(String complaintMobile) {
		this.complaintMobile = complaintMobile == null ? null : complaintMobile.trim();
	}

	/**
	 * 审核人ID .
	 *
	 */
	public String getAuditorCode() {
		return auditorCode;
	}

	/**
	 * 审核人ID .
	 *
	 */
	public void setAuditorCode(String auditorCode) {
		this.auditorCode = auditorCode == null ? null : auditorCode.trim();
	}

	/**
	 * 审核人名称 .
	 *
	 */
	public String getAuditorName() {
		return auditorName;
	}

	/**
	 * 审核人名称 .
	 *
	 */
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName == null ? null : auditorName.trim();
	}

	/**
	 * 审核时间 .
	 *
	 */
	public Date getAuditorDate() {
		return auditorDate;
	}

	/**
	 * 审核时间 .
	 *
	 */
	public void setAuditorDate(Date auditorDate) {
		this.auditorDate = auditorDate;
	}

	/**
	 * 审核状态：noverify-未审核；verify-已处理； .
	 *
	 */
	public String getAuditorStatus() {
		return auditorStatus;
	}

	/**
	 * 审核状态：noverify-未审核；verify-已处理； .
	 *
	 */
	public void setAuditorStatus(String auditorStatus) {
		this.auditorStatus = auditorStatus == null ? null : auditorStatus.trim();
	}

	/**
	 * 处理结果：normal-正常；forbid-禁用； .
	 *
	 */
	public String getAuditorResult() {
		return auditorResult;
	}

	/**
	 * 处理结果：normal-正常；forbid-禁用； .
	 *
	 */
	public void setAuditorResult(String auditorResult) {
		this.auditorResult = auditorResult == null ? null : auditorResult.trim();
	}

	/**
	 * 处理内容 .
	 *
	 */
	public String getAuditorContent() {
		return auditorContent;
	}

	/**
	 * 处理内容 .
	 *
	 */
	public void setAuditorContent(String auditorContent) {
		this.auditorContent = auditorContent == null ? null : auditorContent.trim();
	}

	/**
	 * 备注 .
	 *
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 创建人 .
	 *
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * 创建人 .
	 *
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * 创建时间 .
	 *
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建时间 .
	 *
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 更新人ID .
	 *
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 更新人ID .
	 *
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleComplaint [code=").append(code);
		builder.append(",articleCode=").append(articleCode);
		builder.append(",title=").append(title);
		builder.append(",complaintContent=").append(complaintContent);
		builder.append(",complaintEvidence=").append(complaintEvidence);
		builder.append(",complaintUsername=").append(complaintUsername);
		builder.append(",complaintMobile=").append(complaintMobile);
		builder.append(",auditorCode=").append(auditorCode);
		builder.append(",auditorName=").append(auditorName);
		builder.append(",auditorDate=").append(auditorDate);
		builder.append(",auditorStatus=").append(auditorStatus);
		builder.append(",auditorResult=").append(auditorResult);
		builder.append(",auditorContent=").append(auditorContent);
		builder.append(",remark=").append(remark);
		builder.append(",createId=").append(createId);
		builder.append(",createDate=").append(createDate);
		builder.append(",updateId=").append(updateId);
		builder.append("]");
		return builder.toString();
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
