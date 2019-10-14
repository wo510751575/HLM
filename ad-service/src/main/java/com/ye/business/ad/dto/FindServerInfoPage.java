package com.ye.business.ad.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindServerInfoPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5825252079941834613L;
	/** 参数 */
	private ServerInfoDto param;

	public ServerInfoDto getParam() {
		return param;
	}

	public void setParam(ServerInfoDto param) {
		this.param = param;
	}

}
