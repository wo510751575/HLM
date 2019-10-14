package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCarouselShowPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4266071183461309472L;
	/** 参数 */
	private CarouselShowDto param;

	public CarouselShowDto getParam() {
		return param;
	}

	public void setParam(CarouselShowDto param) {
		this.param = param;
	}

}
