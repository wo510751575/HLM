package com.lj.business.cm.domain;

import java.util.Date;

public class VrMaterialCommenCategory {
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
     * 素材中心CODE .
     *
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * 素材中心CODE .
     *
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    /**
     * 类型CODE .
     *
     */
    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    /**
     * 类型CODE .
     *
     */
    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode == null ? null : materialTypeCode.trim();
    }

    /**
     * 类型名称 .
     *
     */
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    /**
     * 类型名称 .
     *
     */
    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName == null ? null : materialTypeName.trim();
    }

    /**
     * 类型分类CODE .
     *
     */
    public String getTypeCategoryCode() {
        return typeCategoryCode;
    }

    /**
     * 类型分类CODE .
     *
     */
    public void setTypeCategoryCode(String typeCategoryCode) {
        this.typeCategoryCode = typeCategoryCode == null ? null : typeCategoryCode.trim();
    }

    /**
     * 类型分类名称 .
     *
     */
    public String getTypeCategoryName() {
        return typeCategoryName;
    }

    /**
     * 类型分类名称 .
     *
     */
    public void setTypeCategoryName(String typeCategoryName) {
        this.typeCategoryName = typeCategoryName == null ? null : typeCategoryName.trim();
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VrMaterialCommenCategory [code=").append(code);
        builder.append(",materialCode=").append(materialCode); 
        builder.append(",materialTypeCode=").append(materialTypeCode); 
        builder.append(",materialTypeName=").append(materialTypeName); 
        builder.append(",typeCategoryCode=").append(typeCategoryCode); 
        builder.append(",typeCategoryName=").append(typeCategoryName); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}