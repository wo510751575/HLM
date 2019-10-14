package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientServicePage extends PageParamEntity { 
	/** 参数 */
	private PatientServiceDto param;

	public PatientServiceDto getParam() {
		return param;
	}

	public void setParam(PatientServiceDto param) {
		this.param = param;
	}

}
