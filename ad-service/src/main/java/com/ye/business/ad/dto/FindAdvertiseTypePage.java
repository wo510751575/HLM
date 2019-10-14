package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAdvertiseTypePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6881437997790616870L;
	/** 参数 */
	private AdvertiseTypeDto param;

	public AdvertiseTypeDto getParam() {
		return param;
	}

	public void setParam(AdvertiseTypeDto param) {
		this.param = param;
	}

}
