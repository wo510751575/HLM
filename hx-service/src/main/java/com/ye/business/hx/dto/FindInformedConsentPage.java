package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindInformedConsentPage extends PageParamEntity { 
	/** 参数 */
	private InformedConsentDto param;
	public InformedConsentDto getParam() { 
	return param;} 
	public void setParam(InformedConsentDto param) { 
	this.param = param;} 

}
