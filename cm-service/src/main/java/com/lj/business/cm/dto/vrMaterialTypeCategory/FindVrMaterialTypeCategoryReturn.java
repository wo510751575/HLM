package com.lj.business.cm.dto.vrMaterialTypeCategory;

import java.io.Serializable;
import java.util.Date;

public class FindVrMaterialTypeCategoryReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -4019560749185507619L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 素材类型CODE .
     */
    private String typeCode;

    /**
     * 分类名称 .
     */
    private String categoryName;

    /**
     * 显示序号 .
     */
    private Integer showIndex;

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
    
    private  FindVrMaterialTypeCategoryReturn[] list;
    
    

	public FindVrMaterialTypeCategoryReturn[] getList() {
		return list;
	}

	public void setList(FindVrMaterialTypeCategoryReturn[] list) {
		this.list = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
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
		return "FindVrMaterialTypeCategoryReturn [code=" + code + ", typeCode="
				+ typeCode + ", categoryName=" + categoryName + ", showIndex="
				+ showIndex + ", createId=" + createId + ", createDate="
				+ createDate + ", remark=" + remark + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4 + "]";
	}
    
    
}
