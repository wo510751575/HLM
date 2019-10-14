package com.lj.business.cm.dto.vrMaterialTypeCategory;

import java.io.Serializable;

public class FindVrMaterialTypeCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198012568176923401L; 
	
	/**
	 * code
	 */
	private String code;
	
	private String typeCode;
	

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
