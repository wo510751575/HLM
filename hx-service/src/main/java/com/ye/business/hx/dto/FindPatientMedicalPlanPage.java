package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalPlanPage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private PatientMedicalPlanDto param;
	public PatientMedicalPlanDto getParam() { 
	return param;} 
	public void setParam(PatientMedicalPlanDto param) { 
	this.param = param;} 
	public FindPatientMedicalPlanPage() { 
	super(); 
 }	public FindPatientMedicalPlanPage(PatientMedicalPlanDto param) { 
	super(); 
 	this.param = param; 
 }
}
