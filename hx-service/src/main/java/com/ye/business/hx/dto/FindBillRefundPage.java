package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillRefundPage extends PageParamEntity { 
	/** 参数 */
	private BillRefundDto param;

	public BillRefundDto getParam() {
		return param;
	}

	public void setParam(BillRefundDto param) {
		this.param = param;
	}

}
