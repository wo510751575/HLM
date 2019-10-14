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
 * 
 * 类说明：查询红包返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月18日
 */
public class FindRedpacketResponse extends BaseResponse {

	private static final long serialVersionUID = 6736102958542793037L;

	/**
	 * 发红包记录CODE
	 */
	private String rpCode;
	
	/**
	 * 红包状态：
	 * 0-未发送
	 * 1-发送中
	 * 2-发送成功
	 * 3-发送失败
	 * 4-已领取
	 * 5-已退回
	 * 99-没有该红包记录
	 */
	private String status;
	
	/**
	 * 微信红包订单编号
	 */
	private String orderNo;
	
	/**
	 * 发送失败错误编码
	 */
	private String failCode;
	
	/**
	 * 发送失败错误描述
	 */
	private String failMsg;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the failCode
	 */
	public String getFailCode() {
		return failCode;
	}

	/**
	 * @param failCode the failCode to set
	 */
	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	/**
	 * @return the failMsg
	 */
	public String getFailMsg() {
		return failMsg;
	}

	/**
	 * @param failMsg the failMsg to set
	 */
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindRedpacketResponse [");
		builder.append(super.toString());
		builder.append(", rpCode=");
		builder.append(rpCode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", failCode=");
		builder.append(failCode);
		builder.append(", failMsg=");
		builder.append(failMsg);
		builder.append("]");
		return builder.toString();
	}

}
