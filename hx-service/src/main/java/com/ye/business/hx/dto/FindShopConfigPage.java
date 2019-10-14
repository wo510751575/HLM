package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindShopConfigPage extends PageParamEntity { 
	/** 参数 */
	private ShopConfigDto param;

	private String parentCode;
	
	public ShopConfigDto getParam() {
		return param;
	}

	public void setParam(ShopConfigDto param) {
		this.param = param;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	
}
