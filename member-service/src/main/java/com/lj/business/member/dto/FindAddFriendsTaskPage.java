package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindAddFriendsTaskPage extends PageParamEntity {
	/** 参数 */
	private AddFriendsTaskDto param;

	public AddFriendsTaskDto getParam() {
		return param;
	}

	public void setParam(AddFriendsTaskDto param) {
		this.param = param;
	}

}
