package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindDentitionCheckPage extends PageParamEntity { 
	/** 参数 */
	private DentitionCheckDto param;
	public DentitionCheckDto getParam() { 
	return param;} 
	public void setParam(DentitionCheckDto param) { 
	this.param = param;} 

}
