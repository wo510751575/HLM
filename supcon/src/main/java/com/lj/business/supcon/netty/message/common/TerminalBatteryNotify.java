/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.message.common;

import com.lj.business.supcon.netty.message.BaseRequest;

/**
 * 
 * 
 * 类说明：中控客户端当前电量上报
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月8日
 */
public class TerminalBatteryNotify extends BaseRequest {

	private static final long serialVersionUID = 8033209254447708407L;
	
	/**
	 * 手机当前电量
	 * 百分比，如batteryLevel =20表示当前手机只剩下20%的电量
	 */
	private Integer batteryLevel;

	/**
	 * @return the batteryLevel
	 */
	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * @param batteryLevel the batteryLevel to set
	 */
	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalBatteryNotify [batteryLevel=");
		builder.append(batteryLevel);
		builder.append("]");
		return builder.toString();
	}
	
}
