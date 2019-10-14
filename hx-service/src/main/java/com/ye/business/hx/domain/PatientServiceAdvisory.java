package com.ye.business.hx.domain;

import java.util.Date;

public class PatientServiceAdvisory {
    /** CODE*/
    private String code;

    /** 患者服务CODE：FK*/
    private String patientReservationCode;

    /** 患者编号：FK*/
    private String patientNo;

    /** 患者名称*/
    private String patientName;

    /** 接诊时间*/
    private Date visitingDate;

    /** 项目CODE用逗号分隔*/
    private String projectCodes;

    /** 项目服务名称，多个用逗号分隔*/
    private String projectNames;

    /** 咨询内容*/
    private String advisoryContent;

    /** 意向说明*/
    private String explainRemark;

    /** 成交意愿：高：HIGH；中：CENTRAL；低：LOW*/
    private String want;

    /** 创建时间*/
    private Date createDate;

    /** 创建人*/
    private String createId;

    /** 备注*/
    private String remark;

    /** 备注*/
    private String remark2;

    /** 备注*/
    private String remark3;

    /** 备注*/
    private String remark4;

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

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public String getProjectCodes() {
        return projectCodes;
    }

    public void setProjectCodes(String projectCodes) {
        this.projectCodes = projectCodes == null ? null : projectCodes.trim();
    }

    public String getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String projectNames) {
        this.projectNames = projectNames == null ? null : projectNames.trim();
    }

    public String getAdvisoryContent() {
        return advisoryContent;
    }

    public void setAdvisoryContent(String advisoryContent) {
        this.advisoryContent = advisoryContent == null ? null : advisoryContent.trim();
    }

    public String getExplainRemark() {
        return explainRemark;
    }

    public void setExplainRemark(String explainRemark) {
        this.explainRemark = explainRemark == null ? null : explainRemark.trim();
    }

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want == null ? null : want.trim();
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
}