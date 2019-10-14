package com.lj.business.supcon.netty.message.common;

import com.lj.business.supcon.netty.message.BaseDto;

public class SendSetUpUserToGm extends BaseDto {

	private static final long serialVersionUID = 8974674687146294041L;

	/**
	 * 客户编号，非空
	 */
	private String topid;
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 客户微信
	 */
	private String noWx;
	

	/**
	 * 设置 1:开启   0 取消
	 */
	private String setType;



	public String getTopid() {
		return topid;
	}

	public void setTopid(String topid) {
		this.topid = topid;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	public String getSetType() {
		return setType;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}
}
