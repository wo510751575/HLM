package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindDiagnosisPage extends PageParamEntity { 
	/** 参数 */
	private DiagnosisDto param;
	public DiagnosisDto getParam() { 
	return param;} 
	public void setParam(DiagnosisDto param) { 
	this.param = param;} 

}
