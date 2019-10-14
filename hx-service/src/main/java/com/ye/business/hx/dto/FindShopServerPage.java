package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopServerPage extends PageParamEntity { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private ShopServerDto param;

	public ShopServerDto getParam() {
		return param;
	}

	public void setParam(ShopServerDto param) {
		this.param = param;
	}

}
