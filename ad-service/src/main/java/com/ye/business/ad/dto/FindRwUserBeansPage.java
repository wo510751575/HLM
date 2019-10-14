package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindRwUserBeansPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2425534203726486028L;
	/** 参数 */
	private RwUserBeansDto param;

	public RwUserBeansDto getParam() {
		return param;
	}

	public void setParam(RwUserBeansDto param) {
		this.param = param;
	}

}
