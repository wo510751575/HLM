package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientServiceChoosePage extends PageParamEntity { 
	/** 参数 */
	private PatientServiceChooseDto param;

	public PatientServiceChooseDto getParam() {
		return param;
	}

	public void setParam(PatientServiceChooseDto param) {
		this.param = param;
	}

}
