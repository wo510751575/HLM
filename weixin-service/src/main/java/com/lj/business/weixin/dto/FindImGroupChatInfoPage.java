package com.lj.business.weixin.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindImGroupChatInfoPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private ImGroupChatInfoDto param;
	/** 开始时间. */
	private Date startTime;			
	
	/** 结束时间. */
	private Date endTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ImGroupChatInfoDto getParam() {
		return param;
	}

	public void setParam(ImGroupChatInfoDto param) {
		this.param = param;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImGroupChatInfoPage [param=");
		builder.append(param);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append("]");
		return builder.toString();
	}
}
