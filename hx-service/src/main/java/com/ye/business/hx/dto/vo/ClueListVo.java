package com.ye.business.hx.dto.vo;

import com.ye.business.hx.dto.HxClueDto;

/**
 * 线索列表 返回数据
 * 
 * @author bobo
 *
 */
public class ClueListVo extends HxClueDto {
	
	/**
	 * 用户类型名称
	 */
	private String userTypeName;
	/**
	 * 诊所
	 */
	private String clinicName;
	
	/**
	 * 订单类型
	 */
	private String orderType;

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
