package com.lj.business.cm.dto.guidIntroduceMaterial;

import java.io.Serializable;

public class DelGuidIntroduceMaterial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4998537755902766943L; 

	/**
	 * 个人素材介绍编号 .
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
		return "DelGuidIntroduceMaterial [code=" + code + "]";
	}
}
