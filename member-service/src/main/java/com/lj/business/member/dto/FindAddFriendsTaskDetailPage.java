package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindAddFriendsTaskDetailPage extends PageParamEntity { 
	/** 参数 */
	private AddFriendsTaskDetailDto param;
	public AddFriendsTaskDetailDto getParam() { 
	return param;} 
	public void setParam(AddFriendsTaskDetailDto param) { 
	this.param = param;} 

}
