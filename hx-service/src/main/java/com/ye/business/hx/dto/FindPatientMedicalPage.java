package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 参数 */
	private PatientMedicalDto param;

	public PatientMedicalDto getParam() {
		return param;
	}

	public void setParam(PatientMedicalDto param) {
		this.param = param;
	}

}
