package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindFestivalPosterPage extends PageParamEntity { 
	/** 参数 */
	private FestivalPosterDto param;

	public FestivalPosterDto getParam() {
		return param;
	}

	public void setParam(FestivalPosterDto param) {
		this.param = param;
	}

}
