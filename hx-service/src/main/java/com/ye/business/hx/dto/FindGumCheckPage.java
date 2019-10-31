package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindGumCheckPage extends PageParamEntity { 
	/** 参数 */
	private GumCheckDto param;
	public GumCheckDto getParam() { 
	return param;} 
	public void setParam(GumCheckDto param) { 
	this.param = param;} 

}
