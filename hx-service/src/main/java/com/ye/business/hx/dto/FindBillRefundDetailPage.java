package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillRefundDetailPage extends PageParamEntity { 
	/** 参数 */
	private BillRefundDetailDto param;

	public BillRefundDetailDto getParam() {
		return param;
	}

	public void setParam(BillRefundDetailDto param) {
		this.param = param;
	}

}
