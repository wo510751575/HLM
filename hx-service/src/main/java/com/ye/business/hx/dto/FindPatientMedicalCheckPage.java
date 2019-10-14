package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalCheckPage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private PatientMedicalCheckDto param;
	public PatientMedicalCheckDto getParam() { 
	return param;} 
	public void setParam(PatientMedicalCheckDto param) { 
	this.param = param;} 
	public FindPatientMedicalCheckPage() { 
	super(); 
 }	public FindPatientMedicalCheckPage(PatientMedicalCheckDto param) { 
	super(); 
 	this.param = param; 
 }
}
