package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindRwOrderPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4122067379095694889L;
	/** 参数 */
	private RwOrderDto param;

	public RwOrderDto getParam() {
		return param;
	}

	public void setParam(RwOrderDto param) {
		this.param = param;
	}

}
