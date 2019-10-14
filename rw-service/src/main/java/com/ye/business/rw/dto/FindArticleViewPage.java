package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindArticleViewPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 945780265141565209L;
	/** 参数 */
	private ArticleViewDto param;

	public ArticleViewDto getParam() {
		return param;
	}

	public void setParam(ArticleViewDto param) {
		this.param = param;
	}

}
