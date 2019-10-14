package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientServiceAdvisoryPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private PatientServiceAdvisoryDto param;

	public PatientServiceAdvisoryDto getParam() {
		return param;
	}

	public void setParam(PatientServiceAdvisoryDto param) {
		this.param = param;
	}

}
