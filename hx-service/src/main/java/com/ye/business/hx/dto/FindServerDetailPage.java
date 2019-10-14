package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindServerDetailPage extends PageParamEntity { 
	/** 参数 */
	private ServerDetailDto param;

	public ServerDetailDto getParam() {
		return param;
	}

	public void setParam(ServerDetailDto param) {
		this.param = param;
	}

}
