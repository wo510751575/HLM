package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAdvertisePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8867586990314029936L;

	/** 参数 */
	private AdvertiseDto param;

	public AdvertiseDto getParam() {
		return param;
	}

	public void setParam(AdvertiseDto param) {
		this.param = param;
	}

}
