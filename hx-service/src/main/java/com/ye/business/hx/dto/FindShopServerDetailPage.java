package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopServerDetailPage extends PageParamEntity { 
	/** 参数 */
	private ShopServerDetailDto param;

	public ShopServerDetailDto getParam() {
		return param;
	}

	public void setParam(ShopServerDetailDto param) {
		this.param = param;
	}

}
