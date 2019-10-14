package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindBeansOrderPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2374777691183238437L;
	/** 参数 */
	private BeansOrderDto param;

	public BeansOrderDto getParam() {
		return param;
	}

	public void setParam(BeansOrderDto param) {
		this.param = param;
	}

}
