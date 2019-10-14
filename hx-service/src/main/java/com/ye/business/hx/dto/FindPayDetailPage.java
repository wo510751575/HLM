package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPayDetailPage extends PageParamEntity { 
	/** 参数 */
	private PayDetailDto param;

	public PayDetailDto getParam() {
		return param;
	}

	public void setParam(PayDetailDto param) {
		this.param = param;
	}

}
