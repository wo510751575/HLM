package com.lj.business.cm.domain;

import java.util.Date;

public class IntroduceMaterialProduct {
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

    /**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 个人介绍素材编号 .
     *
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * 个人介绍素材编号 .
     *
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    /**
     * 产品名称 .
     *
     */
    public String getBomName() {
        return bomName;
    }

    /**
     * 产品名称 .
     *
     */
    public void setBomName(String bomName) {
        this.bomName = bomName == null ? null : bomName.trim();
    }

    /**
     * 产品图片 .
     *
     */
    public String getBomAddress() {
        return bomAddress;
    }

    /**
     * 产品图片 .
     *
     */
    public void setBomAddress(String bomAddress) {
        this.bomAddress = bomAddress == null ? null : bomAddress.trim();
    }

    public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 备注 .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    @Override
	public String toString() {
		return "IntroduceMaterialProduct [code=" + code + ", materialCode="
				+ materialCode + ", bomName=" + bomName + ", bomAddress="
				+ bomAddress + ", sort=" + sort + ", createId=" + createId
				+ ", createDate=" + createDate + ", remark=" + remark
				+ ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + "]";
	}
}