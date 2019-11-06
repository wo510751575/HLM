package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindFaceCheckPage extends PageParamEntity { 
	/** 参数 */
	private FaceCheckDto param;
	public FaceCheckDto getParam() { 
	return param;} 
	public void setParam(FaceCheckDto param) { 
	this.param = param;} 

}
