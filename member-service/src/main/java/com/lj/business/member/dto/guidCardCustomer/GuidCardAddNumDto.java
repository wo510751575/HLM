package com.lj.business.member.dto.guidCardCustomer;

import java.io.Serializable;

public class GuidCardAddNumDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 6033509982147767475L;

    /**
     * 导购名片编号 .
     */
    private String guidCardCode;

    /**
     * 类型 .
     */
    private String type;
    
    /**
     * openId签名 .
     */
    private String signature;

	public String getGuidCardCode() {
		return guidCardCode;
	}

	public void setGuidCardCode(String guidCardCode) {
		this.guidCardCode = guidCardCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "GuidCardAddNumDto [guidCardCode=" + guidCardCode + ", type="
				+ type + ", signature=" + signature + "]";
	}

}
