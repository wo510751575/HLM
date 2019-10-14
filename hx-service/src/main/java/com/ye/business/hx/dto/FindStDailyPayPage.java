package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindStDailyPayPage extends PageParamEntity { 
	/** 参数 */
	private StDailyPayDto param;

	public StDailyPayDto getParam() {
		return param;
	}

	public void setParam(StDailyPayDto param) {
		this.param = param;
	}

}
