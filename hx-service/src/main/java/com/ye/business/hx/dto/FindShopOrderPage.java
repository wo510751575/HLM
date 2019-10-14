package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopOrderPage extends PageParamEntity { 
	/** 参数 */
	private ShopOrderDto param;

	public ShopOrderDto getParam() {
		return param;
	}

	public void setParam(ShopOrderDto param) {
		this.param = param;
	}

}
