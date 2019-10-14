package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPayTypeDetailPage extends PageParamEntity { 
	/** 参数 */
	private PayTypeDetailDto param;

	public PayTypeDetailDto getParam() {
		return param;
	}

	public void setParam(PayTypeDetailDto param) {
		this.param = param;
	}

}
