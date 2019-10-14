package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindArticleSharePage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 945780265141565209L;
	/** 参数 */
	private ArticleShareDto param;

	public ArticleShareDto getParam() {
		return param;
	}

	public void setParam(ArticleShareDto param) {
		this.param = param;
	}

}
