package com.ye.business.hx.dto.vo;

import java.io.Serializable;

/**
 * 店铺购买服务列表
 * 
 * @author bobo
 *
 */
public class shopServerListVo implements Serializable {
	private String code;
	private String serverName;
	private String unuserNum;
	private String isShop;
	private String clinicName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getUnuserNum() {
		return unuserNum;
	}

	public void setUnuserNum(String unuserNum) {
		this.unuserNum = unuserNum;
	}

	public String getIsShop() {
		return isShop;
	}

	public void setIsShop(String isShop) {
		this.isShop = isShop;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
}
