package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillDetailPage extends PageParamEntity { 
	/** 参数 */
	private BillDetailDto param;

	public BillDetailDto getParam() {
		return param;
	}

	public void setParam(BillDetailDto param) {
		this.param = param;
	}

}
