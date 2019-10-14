package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindServerInfoPage extends PageParamEntity { 
	/** 参数 */
	private ServerInfoDto param;

	public ServerInfoDto getParam() {
		return param;
	}

	public void setParam(ServerInfoDto param) {
		this.param = param;
	}

}
