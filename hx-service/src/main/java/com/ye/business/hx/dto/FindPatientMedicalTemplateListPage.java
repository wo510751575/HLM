package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPatientMedicalTemplateListPage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private PatientMedicalTemplateListDto param;
	public PatientMedicalTemplateListDto getParam() { 
	return param;} 
	public void setParam(PatientMedicalTemplateListDto param) { 
	this.param = param;} 
	public FindPatientMedicalTemplateListPage() { 
	super(); 
 }	public FindPatientMedicalTemplateListPage(PatientMedicalTemplateListDto param) { 
	super(); 
 	this.param = param; 
 }
}
