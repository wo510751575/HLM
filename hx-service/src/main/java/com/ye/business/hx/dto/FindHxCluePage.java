package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindHxCluePage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private HxClueDto param;

	public HxClueDto getParam() {
		return param;
	}

	public void setParam(HxClueDto param) {
		this.param = param;
	}

	
}
