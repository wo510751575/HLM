package com.lj.business.cm.dto.introduceMaterialProduct;

import java.io.Serializable;

public class FindIntroduceMaterialProductPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3761516575870741460L; 

	/**
	 * code .
	 */
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DelIntroduceMaterialProduct [code=" + code + "]";
	}
}
