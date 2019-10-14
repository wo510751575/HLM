package com.ye.business.hx.domain;

import java.util.Date;

public class PatientService {
    /** CODE*/
    private String code;

    /** 患者编号：FK-患者记录code*/
    private String patientNo;

    /** 患者名称*/
    private String patientName;

    /** 手机号*/
    private String mobile;

    /** 患者类型：普通：PT；待定；LS*/
    private String patientType;

    /** 病历编号*/
    private String medicalNo;

    /** 咨询时间*/
    private Date advisoryDate;

    /** 咨询师编号：FK*/
    private String advisoryNo;

    /** 咨询师名称*/
    private String advisoryName;

    /** 门诊编号：FK*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号：FK*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 预约时间*/
    private Date reservationDate;

    /** 预约时长（分钟）：如15分钟*/
    private Integer reservationDateLen;

    /** 预约医生编号：FK*/
    private String reservationDoctorNo;

    /** 预约医生名称*/
    private String reservationDoctorName;

    /** 预约类型：预约：RESERVATION；挂号：REGISTERED*/
    private String reservationType;

    /** 挂号时间*/
    private Date registeredDate;

    /** 挂号医生编号：FK*/
    private String registeredDoctorNo;

    /** 挂号医生名称*/
    private String registeredDoctorName;

    /** 助手编号：FK*/
    private String assistantNo;

    /** 助手名称*/
    private String assistantName;

    /** 就诊状态：未确认：UNCONFIRMED；治疗中：TREATMENT；取消：CANCEL；治疗完成：FINISHED*/
    private String vistitingStatus;

    /** 就诊类型：  初诊：NEWDIAGNOSIS；复诊：REVIEW*/
    private String visitingType;

    /** 接诊时间*/
    private Date visitingDate;
    
    /** 咨询师接诊时间*/
    private Date visitingAdvisoryDate;

    /** 分诊时间*/
    private Date triageDate;

    /** 复诊预约时间*/
    private Date reviewReservationDate;

    /** 治疗完成时间*/
    private Date finishedDate;
    
    /** 编辑病历时间*/
    private Date medicalDate;

    /** 创建时间*/
    private Date createDate;

    /** 创建人*/
    private String createId;

    /** 创建人名称*/
    private String createName;

    /** 备注*/
    private String remark;

    /** 备注*/
    private String remark2;

    /** 备注*/
    private String remark3;

    /** 备注：账单编号*/
    private String remark4;

    /** 更新人*/
    private String updateId;

    /** 更新人*/
    private String updateName;

    /** 更新时间*/
    private Date updateDate;
    
    /** 客户确认状态：UNCONFIRM 未确认，CONFIRM 确认 CANCEL取消*/
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType == null ? null : patientType.trim();
    }

    public String getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(String medicalNo) {
        this.medicalNo = medicalNo == null ? null : medicalNo.trim();
    }

    public Date getAdvisoryDate() {
		return advisoryDate;
	}

	public void setAdvisoryDate(Date advisoryDate) {
		this.advisoryDate = advisoryDate;
	}

	public String getAdvisoryNo() {
        return advisoryNo;
    }

    public void setAdvisoryNo(String advisoryNo) {
        this.advisoryNo = advisoryNo == null ? null : advisoryNo.trim();
    }

    public String getAdvisoryName() {
        return advisoryName;
    }

    public void setAdvisoryName(String advisoryName) {
        this.advisoryName = advisoryName == null ? null : advisoryName.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getReservationDateLen() {
        return reservationDateLen;
    }

    public void setReservationDateLen(Integer reservationDateLen) {
        this.reservationDateLen = reservationDateLen;
    }

    public String getReservationDoctorNo() {
        return reservationDoctorNo;
    }

    public void setReservationDoctorNo(String reservationDoctorNo) {
        this.reservationDoctorNo = reservationDoctorNo == null ? null : reservationDoctorNo.trim();
    }

    public String getReservationDoctorName() {
        return reservationDoctorName;
    }

    public void setReservationDoctorName(String reservationDoctorName) {
        this.reservationDoctorName = reservationDoctorName == null ? null : reservationDoctorName.trim();
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType == null ? null : reservationType.trim();
    }
    
    public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
    public String getRegisteredDoctorNo() {
        return registeredDoctorNo;
    }

	public void setRegisteredDoctorNo(String registeredDoctorNo) {
        this.registeredDoctorNo = registeredDoctorNo == null ? null : registeredDoctorNo.trim();
    }

    public String getRegisteredDoctorName() {
        return registeredDoctorName;
    }

    public void setRegisteredDoctorName(String registeredDoctorName) {
        this.registeredDoctorName = registeredDoctorName == null ? null : registeredDoctorName.trim();
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

    public String getVistitingStatus() {
        return vistitingStatus;
    }

    public void setVistitingStatus(String vistitingStatus) {
        this.vistitingStatus = vistitingStatus == null ? null : vistitingStatus.trim();
    }

    public String getVisitingType() {
        return visitingType;
    }

    public void setVisitingType(String visitingType) {
        this.visitingType = visitingType == null ? null : visitingType.trim();
    }

    public Date getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(Date visitingDate) {
        this.visitingDate = visitingDate;
    }

    public Date getVisitingAdvisoryDate() {
		return visitingAdvisoryDate;
	}

	public void setVisitingAdvisoryDate(Date visitingAdvisoryDate) {
		this.visitingAdvisoryDate = visitingAdvisoryDate;
	}

	public Date getTriageDate() {
        return triageDate;
    }

    public void setTriageDate(Date triageDate) {
        this.triageDate = triageDate;
    }

    public Date getReviewReservationDate() {
        return reviewReservationDate;
    }

    public void setReviewReservationDate(Date reviewReservationDate) {
        this.reviewReservationDate = reviewReservationDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Date getMedicalDate() {
		return medicalDate;
	}

	public void setMedicalDate(Date medicalDate) {
		this.medicalDate = medicalDate;
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