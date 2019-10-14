/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.redpacket;

import com.lj.business.supcon.netty.message.BaseResponse;

/**
 * 
 * 类说明：发红包结果返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月25日
 */
public class SendRedpacketResult extends BaseResponse {

	private static final long serialVersionUID = 1774406691650063445L;

	/**
	 * 发红包记录CODE
	 */
	private String rpCode;
	
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
	 * @return the rpCode
	 */
	public String getRpCode() {
		return rpCode;
	}

	/**
	 * @param rpCode the rpCode to set
	 */
	public void setRpCode(String rpCode) {
		this.rpCode = rpCode;
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
		builder.append("SendRedpacketResult [");
		builder.append(super.toString());
		builder.append(", rpCode=");
		builder.append(rpCode);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append("]");
		return builder.toString();
	}
	
}
