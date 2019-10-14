package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindRwUserBeansChangePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5574580810731131124L;
	/** 参数 */
	private RwUserBeansChangeDto param;

	public RwUserBeansChangeDto getParam() {
		return param;
	}

	public void setParam(RwUserBeansChangeDto param) {
		this.param = param;
	}

}
