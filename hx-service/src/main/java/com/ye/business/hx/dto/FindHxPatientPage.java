package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindHxPatientPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private HxPatientDto param;

	public HxPatientDto getParam() {
		return param;
	}

	public void setParam(HxPatientDto param) {
		this.param = param;
	}

	
}
