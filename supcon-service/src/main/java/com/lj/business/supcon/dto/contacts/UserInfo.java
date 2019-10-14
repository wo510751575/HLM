package com.lj.business.supcon.dto.contacts;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = -7392045532342137682L;
	/**
	 * 手机号
	 * 
	 * @return
	 */
	private String mobile;

	/**
	 * 验证话术
	 */
	private String validation;
	/**
	 * 客户昵称备注
	 * 
	 * @return
	 */
	private String nickRemark;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getNickRemark() {
		return nickRemark;
	}
	public void setNickRemark(String nickRemark) {
		this.nickRemark = nickRemark;
	}
	@Override
	public String toString() {
		return "UserInfo [mobile=" + mobile + ", validation=" + validation + ", nickRemark=" + nickRemark + "]";
	}
	
}
