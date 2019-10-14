package com.lj.business.cm.dto.introduceMaterialProduct;

import java.io.Serializable;

public class DelIntroduceMaterialProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9192374283031978671L;
	
	/**
	 * code .
	 */
	private String code;
	
	/**
     * 个人介绍素材编号 .
     */
    private String materialCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Override
	public String toString() {
		return "DelIntroduceMaterialProduct [code=" + code + ", materialCode="
				+ materialCode + "]";
	}
	
}
