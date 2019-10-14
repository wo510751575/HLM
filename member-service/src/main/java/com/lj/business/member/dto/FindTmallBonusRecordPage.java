package com.lj.business.member.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindTmallBonusRecordPage extends PageParamEntity { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 参数 */
	private TmallBonusRecordDto param;
	/**
	 * 旺旺号精确匹配
	 */
	private String noWxEq;
	/**
	 * 订单号精确匹配
	 */
	private String orderNoEq;
	
	public String getOrderNoEq() {
		return orderNoEq;
	}

	public void setOrderNoEq(String orderNoEq) {
		this.orderNoEq = orderNoEq;
	}

	public String getNoWxEq() {
		return noWxEq;
	}

	public void setNoWxEq(String noWxEq) {
		this.noWxEq = noWxEq;
	}

	public TmallBonusRecordDto getParam() {
		return param;
	}

	public void setParam(TmallBonusRecordDto param) {
		this.param = param;
	}

}
