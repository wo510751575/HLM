package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindGuidSchedulePage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private GuidScheduleDto param;

	public GuidScheduleDto getParam() {
		return param;
	}

	public void setParam(GuidScheduleDto param) {
		this.param = param;
	}

	
	
}
