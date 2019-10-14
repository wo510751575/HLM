package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalDmPage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private PatientMedicalDmDto param;
	public PatientMedicalDmDto getParam() { 
	return param;} 
	public void setParam(PatientMedicalDmDto param) { 
	this.param = param;} 
	public FindPatientMedicalDmPage() { 
	super(); 
 }	public FindPatientMedicalDmPage(PatientMedicalDmDto param) { 
	super(); 
 	this.param = param; 
 }
}
