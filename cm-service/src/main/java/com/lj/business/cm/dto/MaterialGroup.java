package com.lj.business.cm.dto;

import java.io.Serializable;
import java.util.List;

public class MaterialGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5551251055806301728L;

	/**
     * 类型ID .
     */
    private String materialTypeCode;
    
    /**
     * 类型名称 .
     */
    private String materialTypeName;
    
    /**
     * 分组对象 .
     */
    private List<FindMaterialPageReturn> datas;

	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public List<FindMaterialPageReturn> getDatas() {
		return datas;
	}

	public void setDatas(List<FindMaterialPageReturn> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "MaterialCommenGroup [materialTypeCode=" + materialTypeCode
				+ ", materialTypeName=" + materialTypeName + ", datas=" + datas
				+ "]";
	}
}
