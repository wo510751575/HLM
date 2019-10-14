package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAdvertiseViewPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4266071183461309472L;
	/** 参数 */
	private AdvertiseViewDto param;

	public AdvertiseViewDto getParam() {
		return param;
	}

	public void setParam(AdvertiseViewDto param) {
		this.param = param;
	}

}
