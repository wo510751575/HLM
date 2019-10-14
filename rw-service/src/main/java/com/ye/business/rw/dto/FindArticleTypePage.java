package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindArticleTypePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6881437997790616870L;
	/** 参数 */
	private ArticleTypeDto param;

	public ArticleTypeDto getParam() {
		return param;
	}

	public void setParam(ArticleTypeDto param) {
		this.param = param;
	}

}
