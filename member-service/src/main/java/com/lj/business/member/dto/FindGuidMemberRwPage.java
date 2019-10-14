package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindGuidMemberRwPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1952921563960338545L;
	/** 参数 */
	private GuidMemberRwDto param;

	public GuidMemberRwDto getParam() {
		return param;
	}

	public void setParam(GuidMemberRwDto param) {
		this.param = param;
	}

}
