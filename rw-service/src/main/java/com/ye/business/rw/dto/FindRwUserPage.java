package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindRwUserPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7894483856877086973L;
	/** 参数 */
	private RwUserDto param;

	public RwUserDto getParam() {
		return param;
	}

	public void setParam(RwUserDto param) {
		this.param = param;
	}

}
