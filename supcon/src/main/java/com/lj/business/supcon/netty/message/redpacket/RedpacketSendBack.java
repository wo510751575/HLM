/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.redpacket;

import com.lj.business.supcon.netty.message.BaseDto;

/**
 * 
 * 类说明：红包超时已退回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月31日
 */
public class RedpacketSendBack extends BaseDto {

	private static final long serialVersionUID = -4537020104002209558L;
	
	/**
	 * 微信红包订单编号
	 */
	private String orderNo;

	/**
	 * 发送红包时的msgId
	 */
	private String msgId;
	
	/**
	 * 客户编号
	 */
	private String memberNo;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RedpacketSendBack [orderNo=");
		builder.append(orderNo);
		builder.append("]");
		return builder.toString();
	}
	
}
