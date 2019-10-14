package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAdvertiseShowPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4266071183461309472L;
	/** 参数 */
	private AdvertiseShowDto param;

	public AdvertiseShowDto getParam() {
		return param;
	}

	public void setParam(AdvertiseShowDto param) {
		this.param = param;
	}

}
