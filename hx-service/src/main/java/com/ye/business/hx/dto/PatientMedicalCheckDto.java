package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientMedicalCheckDto implements Serializable { 

    /**
     * CODE .
     */
    private String code;

    /**
     * 病历CODE：FK .
     */
    private String medicalCode;

    /**
     * 口腔检查：口腔检查 .
     */
    private String checkOralRemark;

    /**
     * 口腔检查：辅助检查 .
     */
    private String checkAuxiliaryRemark;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建人 .
     */
    private String createName;

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
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新人 .
     */
    private String updateName;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 口腔检查：牙位 .
     */
    private String dentalPosition;

    /**
     * 口腔检查：牙面 .
     */
    private String dentalSurface;

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
     * 病历CODE：FK .
     *
     */
    public String getMedicalCode() {
        return medicalCode;
    }

    /**
     * 病历CODE：FK .
     *
     */
    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode == null ? null : medicalCode.trim();
    }

    /**
     * 口腔检查：口腔检查 .
     *
     */
    public String getCheckOralRemark() {
        return checkOralRemark;
    }

    /**
     * 口腔检查：口腔检查 .
     *
     */
    public void setCheckOralRemark(String checkOralRemark) {
        this.checkOralRemark = checkOralRemark == null ? null : checkOralRemark.trim();
    }

    /**
     * 口腔检查：辅助检查 .
     *
     */
    public String getCheckAuxiliaryRemark() {
        return checkAuxiliaryRemark;
    }

    /**
     * 口腔检查：辅助检查 .
     *
     */
    public void setCheckAuxiliaryRemark(String checkAuxiliaryRemark) {
        this.checkAuxiliaryRemark = checkAuxiliaryRemark == null ? null : checkAuxiliaryRemark.trim();
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
     * 创建人 .
     *
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
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
     * 更新人 .
     *
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    /**
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 口腔检查：牙位 .
     *
     */
    public String getDentalPosition() {
        return dentalPosition;
    }

    /**
     * 口腔检查：牙位 .
     *
     */
    public void setDentalPosition(String dentalPosition) {
        this.dentalPosition = dentalPosition == null ? null : dentalPosition.trim();
    }

    /**
     * 口腔检查：牙面 .
     *
     */
    public String getDentalSurface() {
        return dentalSurface;
    }

    /**
     * 口腔检查：牙面 .
     *
     */
    public void setDentalSurface(String dentalSurface) {
        this.dentalSurface = dentalSurface == null ? null : dentalSurface.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientMedicalCheck [code=").append(code);
        builder.append(",medicalCode=").append(medicalCode); 
        builder.append(",checkOralRemark=").append(checkOralRemark); 
        builder.append(",checkAuxiliaryRemark=").append(checkAuxiliaryRemark); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",createId=").append(createId); 
        builder.append(",createName=").append(createName); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateName=").append(updateName); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",dentalPosition=").append(dentalPosition); 
        builder.append(",dentalSurface=").append(dentalSurface); 
        builder.append("]");
        return builder.toString();
    }
}
