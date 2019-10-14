package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindGuidScheduleLogPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private GuidScheduleLogDto param;

	public GuidScheduleLogDto getParam() {
		return param;
	}

	public void setParam(GuidScheduleLogDto param) {
		this.param = param;
	}

	
}
