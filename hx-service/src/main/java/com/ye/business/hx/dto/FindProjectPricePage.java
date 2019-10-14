package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindProjectPricePage extends PageParamEntity { 
	/** 参数 */
	private ProjectPriceDto param;

	public ProjectPriceDto getParam() {
		return param;
	}

	public void setParam(ProjectPriceDto param) {
		this.param = param;
	}

}
