package com.lj.business.member.dto.guidCardCustomer;

import java.io.Serializable;
import java.util.Date;

public class UpdateGuidCardCustomer implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -5964180567916519413L;

	/**
     * CODE .
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

    /**
     * 创建时间 .
     */
    private Date createDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UpdateGuidCardCustomer [code=" + code + ", guidCardCode="
				+ guidCardCode + ", openId=" + openId + ", type=" + type
				+ ", createDate=" + createDate + "]";
	}
}
