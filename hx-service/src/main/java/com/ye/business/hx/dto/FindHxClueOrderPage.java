package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindHxClueOrderPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private HxClueOrderDto param;

	public HxClueOrderDto getParam() {
		return param;
	}

	public void setParam(HxClueOrderDto param) {
		this.param = param;
	}

}
