package com.lj.business.cm.dto.introduceMaterialProduct;

import java.io.Serializable;

public class FindIntroduceMaterialProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403982586965095531L; 

	/**
	 * code .
	 */
	private String code;
	
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
		return "FindIntroduceMaterialProduct [code=" + code + ", materialCode="
				+ materialCode + "]";
	}
}
