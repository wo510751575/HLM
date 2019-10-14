package com.lj.business.weixin.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WxJobInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7813089134489534257L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 任务名称 .
	 */
	private String jobName;

	/**
	 * 任务优先级 .
	 */
	private String jobLevel;

	/**
	 * 任务状态 .
	 */
	private String jobStatus;

	/**
	 * 延时时间 .
	 */
	private Integer jobDelayTime;
	
	
	private Integer delayTimes;

	/**
	 * 执行时间
	 */
	private Date executeTime;

	/**
	 * 执行类型
	 */
	private String executeType;

	/**
	 * 任务类型 .
	 */
	private String jobType;

	/**
	 * 任务执行Service .
	 */
	private String jobService;

	/**
	 * 任务创建时间 .
	 */
	private Date createDate;

	/**
	 * 任务更新时间 .
	 */
	private Date updateDate;

	/**
	 * 任务创建人 .
	 */
	private String createUser;

	private List<WxJobRedPackInfoDto> wxJobRedPackInfoDtoList;

	private List<WxRedpackDetailInfoDto> noWx;

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
		this.code = code;
	}

	/**
	 * 任务名称 .
	 *
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 任务名称 .
	 *
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 任务优先级 .
	 *
	 */
	public String getJobLevel() {
		return jobLevel;
	}

	/**
	 * 任务优先级 .
	 *
	 */
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	/**
	 * 任务状态 .
	 *
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * 任务状态 .
	 *
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Integer getJobDelayTime() {
		return jobDelayTime;
	}

	public void setJobDelayTime(Integer jobDelayTime) {
		this.jobDelayTime = jobDelayTime;
	}

	/**
	 * 任务类型 .
	 *
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * 任务类型 .
	 *
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * 任务执行Service .
	 *
	 */
	public String getJobService() {
		return jobService;
	}

	/**
	 * 任务执行Service .
	 *
	 */
	public void setJobService(String jobService) {
		this.jobService = jobService;
	}

	/**
	 * 任务创建时间 .
	 *
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 任务创建时间 .
	 *
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 任务更新时间 .
	 *
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 任务更新时间 .
	 *
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 任务创建人 .
	 *
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 任务创建人 .
	 *
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<WxJobRedPackInfoDto> getWxJobRedPackInfoDtoList() {
		return wxJobRedPackInfoDtoList;
	}

	public void setWxJobRedPackInfoDtoList(
			List<WxJobRedPackInfoDto> wxJobRedPackInfoDtoList) {
		this.wxJobRedPackInfoDtoList = wxJobRedPackInfoDtoList;
	}

	public List<WxRedpackDetailInfoDto> getNoWx() {
		return noWx;
	}

	public void setNoWx(List<WxRedpackDetailInfoDto> noWx) {
		this.noWx = noWx;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxJobInfoDto [code=");
		builder.append(code);
		builder.append(", jobName=");
		builder.append(jobName);
		builder.append(", jobLevel=");
		builder.append(jobLevel);
		builder.append(", jobStatus=");
		builder.append(jobStatus);
		builder.append(", jobDelayTime=");
		builder.append(jobDelayTime);
		builder.append(", delayTimes=");
		builder.append(delayTimes);
		builder.append(", executeTime=");
		builder.append(executeTime);
		builder.append(", executeType=");
		builder.append(executeType);
		builder.append(", jobType=");
		builder.append(jobType);
		builder.append(", jobService=");
		builder.append(jobService);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", createUser=");
		builder.append(createUser);
		builder.append(", wxJobRedPackInfoDtoList=");
		builder.append(wxJobRedPackInfoDtoList);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append("]");
		return builder.toString();
	}

	public Integer getDelayTimes() {
		return delayTimes;
	}

	public void setDelayTimes(Integer delayTimes) {
		this.delayTimes = delayTimes;
	}
}
