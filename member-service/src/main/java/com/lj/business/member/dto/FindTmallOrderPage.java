package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindTmallOrderPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private TmallOrderDto param;

	public TmallOrderDto getParam() {
		return param;
	}

	public void setParam(TmallOrderDto param) {
		this.param = param;
	}

}
