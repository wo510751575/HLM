package com.lj.business.cm.dto.introduceMaterialProduct;

import java.io.Serializable;
import java.util.Date;

public class AddIntroduceMaterialProduct implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 6933274166282137860L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 个人介绍素材编号 .
     */
    private String materialCode;

    /**
     * 产品名称 .
     */
    private String bomName;

    /**
     * 产品图片 .
     */
    private String bomAddress;
    
    /**
     * 排序 .
     */
    private String sort;

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

	public String getBomName() {
		return bomName;
	}

	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	public String getBomAddress() {
		return bomAddress;
	}

	public void setBomAddress(String bomAddress) {
		this.bomAddress = bomAddress;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
		return "AddIntroduceMaterialProduct [code=" + code + ", materialCode="
				+ materialCode + ", bomName=" + bomName + ", bomAddress="
				+ bomAddress + ", sort=" + sort + ", createId=" + createId
				+ ", createDate=" + createDate + ", remark=" + remark
				+ ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + "]";
	}
}
