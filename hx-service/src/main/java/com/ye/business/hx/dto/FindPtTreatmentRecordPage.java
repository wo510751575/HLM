package com.ye.business.hx.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindPtTreatmentRecordPage extends PageParamEntity { 
	/** 参数 */
	private PtTreatmentRecordDto param;

	public PtTreatmentRecordDto getParam() {
		return param;
	}

	public void setParam(PtTreatmentRecordDto param) {
		this.param = param;
	}

}
