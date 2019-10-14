package com.lj.business.member.dto.guidCardCustomer;

import java.io.Serializable;

public class FindGuidCardCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569946232675993657L; 

	/**
	 * code .
	 */
	private String code;
	
	/**
     * 导购名片编号 .
     */
    private String guidCardCode;

    /**
     * 客户OPEN_ID .
     */
    private String openId;

    /**
     * 类型 .
     */
    private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	/**
     * 导购名片编号 .
     *
     */
    public String getGuidCardCode() {
        return guidCardCode;
    }

    /**
     * 导购名片编号 .
     *
     */
    public void setGuidCardCode(String guidCardCode) {
        this.guidCardCode = guidCardCode == null ? null : guidCardCode.trim();
    }

    /**
     * 客户OPEN_ID .
     *
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 客户OPEN_ID .
     *
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 类型 .
     *
     */
    public String getType() {
        return type;
    }

    /**
     * 类型 .
     *
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	@Override
	public String toString() {
		return "FindGuidCardCustomer [code=" + code + ", guidCardCode="
				+ guidCardCode + ", openId=" + openId + ", type=" + type + "]";
	}
}
