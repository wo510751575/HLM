package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindToothCheckPage extends PageParamEntity { 
	/** 参数 */
	private ToothCheckDto param;
	public ToothCheckDto getParam() { 
	return param;} 
	public void setParam(ToothCheckDto param) { 
	this.param = param;} 

}
