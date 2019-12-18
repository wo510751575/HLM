package com.ye.business.hx.domain;

import java.util.Date;

public class OrthodonticsProcessRecord {
    /**
     *  .
     */
    private String code;

    /**
     * 就诊时间 .
     */
    private Date createDate;

    /**
     * 患者编号 .
     */
    private String patientNo;

    /**
     * 经治医生 .
     */
    private String attendingDoctor;

    /**
     * 护士 .
     */
    private String nurse;

    /**
     * 诊所 .
     */
    private String clinic;

    /**
     * 创建人 .
     */
    private String createBy;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     *  .
     */
    private String remark;

    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 就诊时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 就诊时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 患者编号 .
     *
     */
    public String getPatientNo() {
        return patientNo;
    }

    /**
     * 患者编号 .
     *
     */
    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo == null ? null : patientNo.trim();
    }

    /**
     * 经治医生 .
     *
     */
    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    /**
     * 经治医生 .
     *
     */
    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor == null ? null : attendingDoctor.trim();
    }

    /**
     * 护士 .
     *
     */
    public String getNurse() {
        return nurse;
    }

    /**
     * 护士 .
     *
     */
    public void setNurse(String nurse) {
        this.nurse = nurse == null ? null : nurse.trim();
    }

    /**
     * 诊所 .
     *
     */
    public String getClinic() {
        return clinic;
    }

    /**
     * 诊所 .
     *
     */
    public void setClinic(String clinic) {
        this.clinic = clinic == null ? null : clinic.trim();
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
     *  .
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     *  .
     *
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrthodonticsProcessRecord [code=").append(code);
        builder.append(",createDate=").append(createDate); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",attendingDoctor=").append(attendingDoctor); 
        builder.append(",nurse=").append(nurse); 
        builder.append(",clinic=").append(clinic); 
        builder.append(",createBy=").append(createBy); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}