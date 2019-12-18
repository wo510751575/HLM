package com.ye.business.hx.domain;

public class PatientOrthodonticsPlan {
    /**
     *  .
     */
    private String code;

    /**
     * 主诉 .
     */
    private String chiefComplaint;

    /**
     * 问题,多个逗号分割 .
     */
    private String problem;

    /**
     * 矫正目标 .
     */
    private String target;

    /**
     * 牙型 .
     */
    private String toothType;

    /**
     * 牙骨 .
     */
    private String dentalBone;

    /**
     * 治疗计划,多个逗号分割 .
     */
    private String treatmentPlan;

    /**
     * 方案确认 .
     */
    private String sure;

    /**
     * 患者编号 .
     */
    private String patientNo;

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
     * 主诉 .
     *
     */
    public String getChiefComplaint() {
        return chiefComplaint;
    }

    /**
     * 主诉 .
     *
     */
    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint == null ? null : chiefComplaint.trim();
    }

    /**
     * 问题,多个逗号分割 .
     *
     */
    public String getProblem() {
        return problem;
    }

    /**
     * 问题,多个逗号分割 .
     *
     */
    public void setProblem(String problem) {
        this.problem = problem == null ? null : problem.trim();
    }

    /**
     * 矫正目标 .
     *
     */
    public String getTarget() {
        return target;
    }

    /**
     * 矫正目标 .
     *
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 牙型 .
     *
     */
    public String getToothType() {
        return toothType;
    }

    /**
     * 牙型 .
     *
     */
    public void setToothType(String toothType) {
        this.toothType = toothType == null ? null : toothType.trim();
    }

    /**
     * 牙骨 .
     *
     */
    public String getDentalBone() {
        return dentalBone;
    }

    /**
     * 牙骨 .
     *
     */
    public void setDentalBone(String dentalBone) {
        this.dentalBone = dentalBone == null ? null : dentalBone.trim();
    }

    /**
     * 治疗计划,多个逗号分割 .
     *
     */
    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    /**
     * 治疗计划,多个逗号分割 .
     *
     */
    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan == null ? null : treatmentPlan.trim();
    }

    /**
     * 方案确认 .
     *
     */
    public String getSure() {
        return sure;
    }

    /**
     * 方案确认 .
     *
     */
    public void setSure(String sure) {
        this.sure = sure == null ? null : sure.trim();
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
        builder.append("PatientOrthodonticsPlan [code=").append(code);
        builder.append(",chiefComplaint=").append(chiefComplaint); 
        builder.append(",problem=").append(problem); 
        builder.append(",target=").append(target); 
        builder.append(",toothType=").append(toothType); 
        builder.append(",dentalBone=").append(dentalBone); 
        builder.append(",treatmentPlan=").append(treatmentPlan); 
        builder.append(",sure=").append(sure); 
        builder.append(",patientNo=").append(patientNo); 
        builder.append(",remark=").append(remark); 
        builder.append("]");
        return builder.toString();
    }
}