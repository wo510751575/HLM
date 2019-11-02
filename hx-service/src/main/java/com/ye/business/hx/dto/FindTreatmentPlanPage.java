package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindTreatmentPlanPage extends PageParamEntity { 
	/** 参数 */
	private TreatmentPlanDto param;
	public TreatmentPlanDto getParam() { 
	return param;} 
	public void setParam(TreatmentPlanDto param) { 
	this.param = param;} 

}
