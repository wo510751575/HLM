package com.ye.business.hx.domain;

import java.util.Date;

public class PatientMedical {
    /** CODE*/
    private String code;

    /** 患者服务CODE：FK*/
    private String patientReservationCode;

    /** 患者编号：FK*/
    private String patientNo;

    /** 患者名称*/
    private String patientName;

    /** 医生编号：FK*/
    private String doctorNo;

    /** 医生名称*/
    private String doctorName;

    /** 助手编号：FK*/
    private String assistantNo;

    /** 助手名称*/
    private String assistantName;

    /** 接诊时间*/
    private Date visitingDate;

    /** 就诊类型：  初诊：NEWDIAGNOSIS；复诊：REVIEW*/
    private String visitingType;

    /** 主诉及病史：主诉*/
    private String mainRemark;

    /** 主诉及病史：现病史*/
    private String mainCurrentRemark;

    /** 主诉及病史：既往史*/
    private String mainPastRemark;

    /** 口腔检查：口腔检查*/
    private String checkOralRemark;

    /** 口腔检查：辅助检查*/
    private String checkAuxiliaryRemark;

    /** 诊断与治疗计划：诊断*/
    private String planDiagnosisRemark;

    /** 诊断与治疗计划：治疗计划*/
    private String planTreatmentRemark;

    /** 处置与医嘱：处置*/
    private String dmDisposalRemark;

    /** 处置与医嘱：医嘱*/
    private String dmMedicalRemark;

    /** 其他：标签*/
    private String otherLabelRemark;

    /** 其他：备注*/
    private String otherRemark;

    /** 创建时间*/
    private Date createDate;

    /** 创建人*/
    private String createId;

    /** 创建人*/
    private String createName;

    /** 备注*/
    private String remark;

    /** 备注*/
    private String remark2;

    /** 备注*/
    private String remark3;

    /** 备注*/
    private String remark4;

    /** 更新人*/
    private String updateId;

    /** 更新人*/
    private String updateName;

    /** 更新时间*/
    private Date updateDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPatientReservationCode() {
        return patientReservationCode;
    }

    public void setPatientReservationCode(String patientReservationCode) {
        this.patientReservationCode = patientReservationCode == null ? null : patientReservationCode.trim();
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo == null ? null : patientNo.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo == null ? null : doctorNo.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getAssistantNo() {
        return assistantNo;
    }

    public void setAssistantNo(String assistantNo) {
        this.assistantNo = assistantNo == null ? null : assistantNo.trim();
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName == null ? null : assistantName.trim();
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getVisitingType() {
        return visitingType;
    }

    public void setVisitingType(String visitingType) {
        this.visitingType = visitingType == null ? null : visitingType.trim();
    }

    public String getMainRemark() {
        return mainRemark;
    }

    public void setMainRemark(String mainRemark) {
        this.mainRemark = mainRemark == null ? null : mainRemark.trim();
    }

    public String getMainCurrentRemark() {
        return mainCurrentRemark;
    }

    public void setMainCurrentRemark(String mainCurrentRemark) {
        this.mainCurrentRemark = mainCurrentRemark == null ? null : mainCurrentRemark.trim();
    }

    public String getMainPastRemark() {
        return mainPastRemark;
    }

    public void setMainPastRemark(String mainPastRemark) {
        this.mainPastRemark = mainPastRemark == null ? null : mainPastRemark.trim();
    }

    public String getCheckOralRemark() {
        return checkOralRemark;
    }

    public void setCheckOralRemark(String checkOralRemark) {
        this.checkOralRemark = checkOralRemark == null ? null : checkOralRemark.trim();
    }

    public String getCheckAuxiliaryRemark() {
        return checkAuxiliaryRemark;
    }

    public void setCheckAuxiliaryRemark(String checkAuxiliaryRemark) {
        this.checkAuxiliaryRemark = checkAuxiliaryRemark == null ? null : checkAuxiliaryRemark.trim();
    }

    public String getPlanDiagnosisRemark() {
        return planDiagnosisRemark;
    }

    public void setPlanDiagnosisRemark(String planDiagnosisRemark) {
        this.planDiagnosisRemark = planDiagnosisRemark == null ? null : planDiagnosisRemark.trim();
    }

    public String getPlanTreatmentRemark() {
        return planTreatmentRemark;
    }

    public void setPlanTreatmentRemark(String planTreatmentRemark) {
        this.planTreatmentRemark = planTreatmentRemark == null ? null : planTreatmentRemark.trim();
    }

    public String getDmDisposalRemark() {
        return dmDisposalRemark;
    }

    public void setDmDisposalRemark(String dmDisposalRemark) {
        this.dmDisposalRemark = dmDisposalRemark == null ? null : dmDisposalRemark.trim();
    }

    public String getDmMedicalRemark() {
        return dmMedicalRemark;
    }

    public void setDmMedicalRemark(String dmMedicalRemark) {
        this.dmMedicalRemark = dmMedicalRemark == null ? null : dmMedicalRemark.trim();
    }

    public String getOtherLabelRemark() {
        return otherLabelRemark;
    }

    public void setOtherLabelRemark(String otherLabelRemark) {
        this.otherLabelRemark = otherLabelRemark == null ? null : otherLabelRemark.trim();
    }

    public String getOtherRemark() {
        return otherRemark;
    }

    public void setOtherRemark(String otherRemark) {
        this.otherRemark = otherRemark == null ? null : otherRemark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}