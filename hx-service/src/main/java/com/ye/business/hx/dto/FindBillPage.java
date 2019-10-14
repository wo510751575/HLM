package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillPage extends PageParamEntity { 
	/** 参数 */
	private BillDto param;

	public BillDto getParam() {
		return param;
	}

	public void setParam(BillDto param) {
		this.param = param;
	}

}
