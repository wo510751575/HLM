package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindMyCollectionPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private MyCollectionDto param;

	public MyCollectionDto getParam() {
		return param;
	}

	public void setParam(MyCollectionDto param) {
		this.param = param;
	}

}
