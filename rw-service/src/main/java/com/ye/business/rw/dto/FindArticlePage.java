package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindArticlePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private ArticleDto param;

	public ArticleDto getParam() {
		return param;
	}

	public void setParam(ArticleDto param) {
		this.param = param;
	}

}
