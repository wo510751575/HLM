package com.lj.business.api.dto.tmall;

import java.io.Serializable;

public class TmallBonusH5Dto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801848380744423913L;
	/**
	 * openId
	 */
	private String openId;
	/**
	 * 终端微信
	 */
	private String noWxGm;
	/**
	 * 客户微信
	 */
	private String noWx;
	/**
	 * 天猫订单号
	 */
	private String orderNo;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNoWx() {
		return noWx;
	}
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNoWxGm() {
		return noWxGm;
	}
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TmallBonusH5Dto [openId=");
		builder.append(openId);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append("]");
		return builder.toString();
	}
}
