package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientImgPage extends PageParamEntity { 
	/** 参数 */
	private PatientImgDto param;
	public PatientImgDto getParam() { 
	return param;} 
	public void setParam(PatientImgDto param) { 
	this.param = param;} 

}
