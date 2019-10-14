package com.ye.business.rw.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindArticleComplaintPage extends PageParamEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7970476668953356631L;
	/** 参数 */
	private ArticleComplaintDto param;

	public ArticleComplaintDto getParam() {
		return param;
	}

	public void setParam(ArticleComplaintDto param) {
		this.param = param;
	}

}
