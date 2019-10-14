package com.lj.business.cm.dto.vrMaterialCommenCategory;

import java.io.Serializable;
import java.util.Date;

public class UpdateVrMaterialCommenCategory implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8926106982464427315L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 素材中心CODE .
     */
    private String materialCode;

    /**
     * 类型CODE .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;

    /**
     * 类型分类CODE .
     */
    private String typeCategoryCode;

    /**
     * 类型分类名称 .
     */
    private String typeCategoryName;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;
    
    /**
     * 分类code数组
     */
    private String[] codes;
    
    


	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

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

	public String getTypeCategoryCode() {
		return typeCategoryCode;
	}

	public void setTypeCategoryCode(String typeCategoryCode) {
		this.typeCategoryCode = typeCategoryCode;
	}

	public String getTypeCategoryName() {
		return typeCategoryName;
	}

	public void setTypeCategoryName(String typeCategoryName) {
		this.typeCategoryName = typeCategoryName;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	@Override
	public String toString() {
		return "UpdateVrMaterialCommenCategory [code=" + code
				+ ", materialCode=" + materialCode + ", materialTypeCode="
				+ materialTypeCode + ", materialTypeName=" + materialTypeName
				+ ", typeCategoryCode=" + typeCategoryCode
				+ ", typeCategoryName=" + typeCategoryName + ", createId="
				+ createId + ", createDate=" + createDate + ", remark="
				+ remark + ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + "]";
	}
    
    
}
