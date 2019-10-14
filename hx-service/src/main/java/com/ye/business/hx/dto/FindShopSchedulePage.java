package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopSchedulePage extends PageParamEntity { 
	/** 参数 */
	private ShopScheduleDto param;

	public ShopScheduleDto getParam() {
		return param;
	}

	public void setParam(ShopScheduleDto param) {
		this.param = param;
	}

}
