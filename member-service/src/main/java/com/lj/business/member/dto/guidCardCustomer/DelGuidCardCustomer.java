package com.lj.business.member.dto.guidCardCustomer;

import java.io.Serializable;

public class DelGuidCardCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733207885464507569L; 

	/**
	 * code .
	 */
	private String Code;
	
	/**
     * 导购名片编号 .
     */
    private String guidCardCode;
    
    /**
     * 微信openId .
     */
    private String openId;

    /**
     * 类型 .
     */
    private String type;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getGuidCardCode() {
		return guidCardCode;
	}

	public void setGuidCardCode(String guidCardCode) {
		this.guidCardCode = guidCardCode;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DelGuidCardCustomer [Code=" + Code + ", guidCardCode="
				+ guidCardCode + ", openId=" + openId + ", type=" + type + "]";
	}
}
