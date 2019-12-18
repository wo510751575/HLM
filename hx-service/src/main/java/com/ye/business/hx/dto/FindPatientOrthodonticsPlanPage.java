package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientOrthodonticsPlanPage extends PageParamEntity { 
	/** 参数 */
	private PatientOrthodonticsPlanDto param;
	public PatientOrthodonticsPlanDto getParam() { 
	return param;} 
	public void setParam(PatientOrthodonticsPlanDto param) { 
	this.param = param;} 

}
