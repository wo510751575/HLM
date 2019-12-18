package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientTreatmentPlanPage extends PageParamEntity { 
	/** 参数 */
	private PatientTreatmentPlanDto param;
	public PatientTreatmentPlanDto getParam() { 
	return param;} 
	public void setParam(PatientTreatmentPlanDto param) { 
	this.param = param;} 

}
