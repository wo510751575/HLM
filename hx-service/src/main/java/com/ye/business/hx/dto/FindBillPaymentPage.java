package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillPaymentPage extends PageParamEntity { 
	/** 参数 */
	private BillPaymentDto param;

	public BillPaymentDto getParam() {
		return param;
	}

	public void setParam(BillPaymentDto param) {
		this.param = param;
	}

}
