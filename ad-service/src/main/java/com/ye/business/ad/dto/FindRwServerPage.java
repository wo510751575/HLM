package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindRwServerPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8802207383842607996L;
	/** 参数 */
	private RwServerDto param;

	public RwServerDto getParam() {
		return param;
	}

	public void setParam(RwServerDto param) {
		this.param = param;
	}

}
