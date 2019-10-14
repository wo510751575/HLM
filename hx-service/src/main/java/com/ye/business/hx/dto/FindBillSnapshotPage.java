package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindBillSnapshotPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private BillSnapshotDto param;

	public BillSnapshotDto getParam() {
		return param;
	}

	public void setParam(BillSnapshotDto param) {
		this.param = param;
	}

}
