package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCarouselViewPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4266071183461309472L;
	/** 参数 */
	private CarouselViewDto param;

	public CarouselViewDto getParam() {
		return param;
	}

	public void setParam(CarouselViewDto param) {
		this.param = param;
	}

}
