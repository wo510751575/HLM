package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopFestivalPosterPage extends PageParamEntity { 
	/** 参数 */
	private ShopFestivalPosterDto param;

	public ShopFestivalPosterDto getParam() {
		return param;
	}

	public void setParam(ShopFestivalPosterDto param) {
		this.param = param;
	}

}
