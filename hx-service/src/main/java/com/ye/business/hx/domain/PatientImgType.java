package com.ye.business.hx.domain;

import java.util.Date;

public class PatientImgType {
    /**
     * code .
     */
    private String code;

    /**
     * 名称 .
     */
    private String name;

    /**
     * 创建日期 .
     */
    private Date createDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 备注 .
     */
    private String remark;

    /**
     *  .
     */
    private String remark2;

    /**
     *  .
     */
    private String remark3;

    /**
     *  .
     */
    private String remark4;

    /**
     * 是否删除:0-是;1-否 .
     */
    private String isDelete;

    /**
     * code .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * code .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 创建日期 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     *  .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     *  .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     *  .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     *  .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     *  .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    /**
     * 是否删除:0-是;1-否 .
     *
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除:0-是;1-否 .
     *
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientImgType [code=").append(code);
        builder.append(",name=").append(name); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",isDelete=").append(isDelete); 
        builder.append("]");
        return builder.toString();
    }
}