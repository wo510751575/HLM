package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCarouselPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4663641518880490333L;
	/** 参数 */
	private CarouselDto param;

	public CarouselDto getParam() {
		return param;
	}

	public void setParam(CarouselDto param) {
		this.param = param;
	}

}
