package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindSetFriendNumPage extends PageParamEntity { 
	/** 参数 */
	private SetFriendNumDto param;
	public SetFriendNumDto getParam() { 
	return param;} 
	public void setParam(SetFriendNumDto param) { 
	this.param = param;} 

}
