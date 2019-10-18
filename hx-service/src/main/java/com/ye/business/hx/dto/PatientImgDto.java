package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientImgDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 患者code .
     */
    private String patientCode;

    /**
     * 图片地址 .
     */
    private String imgUrl;

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
     * 更新日期 .
     */
    private Date updateDate;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 影像类型code关联影像类型表 .
     */
    private String imgTypeCode;

    /**
     * 预约时间 .
     */
    private Date reservationDate;

    /**
     * 是否删除:0-是;1-否 .
     */
    private String isDelete;

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
     * 患者code .
     *
     */
    public String getPatientCode() {
        return patientCode;
    }

    /**
     * 患者code .
     *
     */
    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode == null ? null : patientCode.trim();
    }

    /**
     * 图片地址 .
     *
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 图片地址 .
     *
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
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
     * 更新日期 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新日期 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 影像类型code关联影像类型表 .
     *
     */
    public String getImgTypeCode() {
        return imgTypeCode;
    }

    /**
     * 影像类型code关联影像类型表 .
     *
     */
    public void setImgTypeCode(String imgTypeCode) {
        this.imgTypeCode = imgTypeCode == null ? null : imgTypeCode.trim();
    }

    /**
     * 预约时间 .
     *
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * 预约时间 .
     *
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
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
        builder.append("PatientImg [code=").append(code);
        builder.append(",patientCode=").append(patientCode); 
        builder.append(",imgUrl=").append(imgUrl); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",imgTypeCode=").append(imgTypeCode); 
        builder.append(",reservationDate=").append(reservationDate); 
        builder.append(",isDelete=").append(isDelete); 
        builder.append("]");
        return builder.toString();
    }
}
