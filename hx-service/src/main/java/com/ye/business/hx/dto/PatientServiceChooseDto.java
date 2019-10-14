package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientServiceChooseDto implements Serializable { 

    /** CODE*/
    private String code;

    /** 患者服务CODE*/
    private String patientReservationCode;

    /** 项目CODE*/
    private String projectCode;

    /** 项目名称*/
    private String projectName;

    /** 服务项目属性CODE*/
    private String projectPropertyCode;

    /** 服务项目属性名称*/
    private String projectPropertyName;

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
    
    //////////////////////////////////
    /** 患者服务CODE list*/
    private List<String> patientReservationCodes;

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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectPropertyCode() {
        return projectPropertyCode;
    }

    public void setProjectPropertyCode(String projectPropertyCode) {
        this.projectPropertyCode = projectPropertyCode == null ? null : projectPropertyCode.trim();
    }

    public String getProjectPropertyName() {
        return projectPropertyName;
    }

    public void setProjectPropertyName(String projectPropertyName) {
        this.projectPropertyName = projectPropertyName == null ? null : projectPropertyName.trim();
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

	public List<String> getPatientReservationCodes() {
		return patientReservationCodes;
	}

	public void setPatientReservationCodes(List<String> patientReservationCodes) {
		this.patientReservationCodes = patientReservationCodes;
	}

	@Override
	public String toString() {
		return "PatientServiceChooseDto [code=" + code + ", patientReservationCode=" + patientReservationCode
				+ ", projectCode=" + projectCode + ", projectName=" + projectName + ", projectPropertyCode="
				+ projectPropertyCode + ", projectPropertyName=" + projectPropertyName + ", createDate=" + createDate
				+ ", createId=" + createId + ", remark=" + remark + ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + ", patientReservationCodes=" + patientReservationCodes + "]";
	}
	
}
