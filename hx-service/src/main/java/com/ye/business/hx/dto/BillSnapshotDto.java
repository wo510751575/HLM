package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;

public class BillSnapshotDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** CODE*/
    private String code;

    /** 账单code*/
    private String billCode;

    /** 操作code*/
    private String operateCode;

    /** 患者编号*/
    private String patientNo;

    /** 患者名称*/
    private String patientName;

    /** 患者病历号*/
    private String medicalNo;

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 账单总金额（分为单位）*/
    private Long originalAmount;

    /** 账单折扣后实际应付金额（分为单位）*/
    private Long reallyAmount;

    /** 账单折扣(万分比，如8折则值为8000）*/
    private Integer discountNum;

    /** 已付总金额（分为单位）*/
    private Long payAmount;

    /** 欠费总金额（分为单位）*/
    private Long debtAmount;

    /** 退款总金额(分为单位）*/
    private Long rtAmount;

    /** 收费状态（UNPAY:待收费,FINISH已结清，ARREARAGE未结清）*/
    private String payStatus;

    /** 退费状态（RT:有退款，NO：无退款）*/
    private String rtStatus;

    /** 有效状态(NORMAL 正常，CANCEL：作废）*/
    private String status;

    /** 就诊时间*/
    private Date clinicTime;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

    /** 建单人名称*/
    private String createName;

    /** 创建时间*/
    private Date createDate;

    /** 备注*/
    private String remark;

    /** 备注1*/
    private String remark1;

    /** 备注2*/
    private String remark2;

    /** 备注3*/
    private String remark3;

    /** 备注4*/
    private String remark4;

    /** 快照时间*/
    private Date snapshotTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
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

    public String getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(String medicalNo) {
        this.medicalNo = medicalNo == null ? null : medicalNo.trim();
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

    public Long getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Long originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Long getReallyAmount() {
        return reallyAmount;
    }

    public void setReallyAmount(Long reallyAmount) {
        this.reallyAmount = reallyAmount;
    }

    public Integer getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(Integer discountNum) {
        this.discountNum = discountNum;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Long debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Long getRtAmount() {
        return rtAmount;
    }

    public void setRtAmount(Long rtAmount) {
        this.rtAmount = rtAmount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getRtStatus() {
        return rtStatus;
    }

    public void setRtStatus(String rtStatus) {
        this.rtStatus = rtStatus == null ? null : rtStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(Date clinicTime) {
        this.clinicTime = clinicTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
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

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

	@Override
	public String toString() {
		return "BillSnapshotDto [code=" + code + ", billCode=" + billCode + ", operateCode=" + operateCode
				+ ", patientNo=" + patientNo + ", patientName=" + patientName + ", medicalNo=" + medicalNo + ", shopNo="
				+ shopNo + ", shopName=" + shopName + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", originalAmount=" + originalAmount + ", reallyAmount=" + reallyAmount + ", discountNum="
				+ discountNum + ", payAmount=" + payAmount + ", debtAmount=" + debtAmount + ", rtAmount=" + rtAmount
				+ ", payStatus=" + payStatus + ", rtStatus=" + rtStatus + ", status=" + status + ", clinicTime="
				+ clinicTime + ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId=" + createId
				+ ", createName=" + createName + ", createDate=" + createDate + ", remark=" + remark + ", remark1="
				+ remark1 + ", remark2=" + remark2 + ", remark3=" + remark3 + ", remark4=" + remark4 + ", snapshotTime="
				+ snapshotTime + "]";
	}
    
    
}
