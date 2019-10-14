package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindGmLabelPage extends PageParamEntity {
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private GmLabelDto param;

	public GmLabelDto getParam() {
		return param;
	}

	public void setParam(GmLabelDto param) {
		this.param = param;
	}

	public FindGmLabelPage() {
		super();
	}

	public FindGmLabelPage(GmLabelDto param) {
		super();
		this.param = param;
	}
}
