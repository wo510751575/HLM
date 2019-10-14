package com.lj.business.cm.dto.vrMaterialCommenCategory;

import java.io.Serializable;

public class FindVrMaterialCommenCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255498248514033744L; 
	
	/**
	 * code
	 */
	private String code ;
	
	/**
	 * 素材code
	 */
	private String materialCode;
	/**
	 * 类型code
	 */
	private String materialTypeCode;
	


	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
