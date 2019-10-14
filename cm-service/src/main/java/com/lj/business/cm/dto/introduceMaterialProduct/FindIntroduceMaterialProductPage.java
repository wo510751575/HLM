package com.lj.business.cm.dto.introduceMaterialProduct;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindIntroduceMaterialProductPage extends PageParamEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3776936831176482874L; 

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
