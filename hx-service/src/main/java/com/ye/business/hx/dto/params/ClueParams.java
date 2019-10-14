package com.ye.business.hx.dto.params;

/**
 * 线索参数
 * 
 * @author bobo
 *
 */
public class ClueParams extends LoginInfo {
	/**
	 * 预约项目
	 */
	private String project;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 地区
	 */
	private String addr;
	/**
	 * 用户类型
	 */
	private String userType;

	/**
	 * 线索code
	 */
	private String clueCode;
	/**
	 * 店铺服务code
	 */
	private String serverCode;

	/**
	 * 诊所名称
	 */
	private String clinicName;

	/**
	 * 工单号
	 */
	private String orderno;

	/**
	 * 订单类型
	 */
	private String type;

	private String date;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = "".equals(project) ? null : project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = "".equals(status) ? null : status;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = "".equals(addr) ? null : addr;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = "".equals(userType) ? null : userType;
	}

	public String getClueCode() {
		return clueCode;
	}

	public void setClueCode(String clueCode) {
		this.clueCode = "".equals(clueCode) ? null : clueCode;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = "".equals(serverCode) ? null : serverCode;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = "".equals(clinicName) ? null : clinicName;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = "".equals(orderno) ? null : orderno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = "".equals(type) ? null : type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = "".equals(date) ? null : date;
	}
}
