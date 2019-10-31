package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientSymptomPage extends PageParamEntity { 
	/** 参数 */
	private PatientSymptomDto param;
	public PatientSymptomDto getParam() { 
	return param;} 
	public void setParam(PatientSymptomDto param) { 
	this.param = param;} 

}
