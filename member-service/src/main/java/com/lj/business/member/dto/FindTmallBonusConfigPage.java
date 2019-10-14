package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindTmallBonusConfigPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private TmallBonusConfigDto param;

	public TmallBonusConfigDto getParam() {
		return param;
	}

	public void setParam(TmallBonusConfigDto param) {
		this.param = param;
	}

}
