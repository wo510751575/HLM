package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillOperatePage extends PageParamEntity { 
	/** 参数 */
	private BillOperateDto param;

	public BillOperateDto getParam() {
		return param;
	}

	public void setParam(BillOperateDto param) {
		this.param = param;
	}

}
