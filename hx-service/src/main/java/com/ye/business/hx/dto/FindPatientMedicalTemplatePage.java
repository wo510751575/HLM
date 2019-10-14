package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalTemplatePage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private PatientMedicalTemplateDto param;
	public PatientMedicalTemplateDto getParam() { 
	return param;} 
	public void setParam(PatientMedicalTemplateDto param) { 
	this.param = param;} 
	public FindPatientMedicalTemplatePage() { 
	super(); 
 }	public FindPatientMedicalTemplatePage(PatientMedicalTemplateDto param) { 
	super(); 
 	this.param = param; 
 }
}
