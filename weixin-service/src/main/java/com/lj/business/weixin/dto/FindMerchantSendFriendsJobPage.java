package com.lj.business.weixin.dto;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

public class FindMerchantSendFriendsJobPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 参数 */
	private MerchantSendFriendsJobDto param;

	public MerchantSendFriendsJobDto getParam() {
		return param;
	}

	public void setParam(MerchantSendFriendsJobDto param) {
		this.param = param;
	}

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

	@Override
	public String toString() {
		return "FindMerchantSendFriendsJobPage [param=" + param + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}	
	
}
