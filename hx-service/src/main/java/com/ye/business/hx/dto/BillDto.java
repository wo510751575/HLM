package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BillDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** CODE*/
    private String code;
    /** 账单编号*/
    private String billNo;
    
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

    /** 账单类型(MX:明细，JY:简易）*/
    private String billType;

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
    
    /** 就诊时间*/
    private String clinicTimeStr;

    /** 首次收费时间*/
    private Date payTime;
    
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

    /** 备注1 医生*/
    private String remark1;

    /** 备注2 首次收费人名称*/
    private String remark2;

    /** 备注3 首次收费方式名称*/
    private String remark3;

    /** 备注4 预约号*/
    private String remark4;
    
    /** 账单详细*/
    private List<BillDetailDto> details;
    
	/** 账单操作 */
    private BillOperateDto billOperate;
   
    /** 查询 非该支付状态 */
    private String notPayStatus;
    
    /**预约编号，从今日工作跳转过来，带上预约code*/
    private String  patientServiceCode;
    
    /** 查询开始日期String：YYYY-MM-DD*/
    private String payStartDateStr;
    
    /** 查询结束日期String：YYYY-MM-DD*/
    private String payEndDateStr;
    
    /** 查询开始日期*/
    private Date payStartDate;
    
    /** 查询结束日期*/
    private Date payEndDate;
    
    /** 查询开始日期String：YYYY-MM-DD*/
    private String clinicStartDateStr;
    
    /** 查询结束日期String：YYYY-MM-DD*/
    private String clinicEndDateStr;
    
    /** 查询开始日期*/
    private Date clinicStartDate;
    
    /** 查询结束日期*/
    private Date clinicEndDate;
    
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

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
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

	public List<BillDetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<BillDetailDto> details) {
		this.details = details;
	}

	public BillOperateDto getBillOperate() {
		return billOperate;
	}

	public void setBillOperate(BillOperateDto billOperate) {
		this.billOperate = billOperate;
	}

	public String getNotPayStatus() {
		return notPayStatus;
	}

	public void setNotPayStatus(String notPayStatus) {
		this.notPayStatus = notPayStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getClinicTimeStr() {
		return clinicTimeStr;
	}

	public void setClinicTimeStr(String clinicTimeStr) {
		this.clinicTimeStr = clinicTimeStr;
	}

	@Override
	public String toString() {
		return "BillDto [code=" + code + ", billNo=" + billNo + ", patientNo=" + patientNo + ", patientName="
				+ patientName + ", medicalNo=" + medicalNo + ", shopNo=" + shopNo + ", shopName=" + shopName
				+ ", merchantNo=" + merchantNo + ", merchantName=" + merchantName + ", billType=" + billType
				+ ", originalAmount=" + originalAmount + ", reallyAmount=" + reallyAmount + ", discountNum="
				+ discountNum + ", payAmount=" + payAmount + ", debtAmount=" + debtAmount + ", rtAmount=" + rtAmount
				+ ", payStatus=" + payStatus + ", rtStatus=" + rtStatus + ", status=" + status + ", clinicTime="
				+ clinicTime + ", clinicTimeStr=" + clinicTimeStr + ", payTime=" + payTime + ", updateId=" + updateId
				+ ", updateDate=" + updateDate + ", createId=" + createId + ", createName=" + createName
				+ ", createDate=" + createDate + ", remark=" + remark + ", remark1=" + remark1 + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", remark4=" + remark4 + ", details=" + details + ", billOperate="
				+ billOperate + ", notPayStatus=" + notPayStatus + "]";
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getPatientServiceCode() {
		return patientServiceCode;
	}

	public void setPatientServiceCode(String patientServiceCode) {
		this.patientServiceCode = patientServiceCode;
	}

	public String getPayStartDateStr() {
		return payStartDateStr;
	}

	public void setPayStartDateStr(String payStartDateStr) {
		this.payStartDateStr = payStartDateStr;
	}

	public String getPayEndDateStr() {
		return payEndDateStr;
	}

	public void setPayEndDateStr(String payEndDateStr) {
		this.payEndDateStr = payEndDateStr;
	}

	public Date getPayStartDate() {
		return payStartDate;
	}

	public void setPayStartDate(Date payStartDate) {
		this.payStartDate = payStartDate;
	}

	public Date getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(Date payEndDate) {
		this.payEndDate = payEndDate;
	}

	public String getClinicStartDateStr() {
		return clinicStartDateStr;
	}

	public void setClinicStartDateStr(String clinicStartDateStr) {
		this.clinicStartDateStr = clinicStartDateStr;
	}

	public String getClinicEndDateStr() {
		return clinicEndDateStr;
	}

	public void setClinicEndDateStr(String clinicEndDateStr) {
		this.clinicEndDateStr = clinicEndDateStr;
	}

	public Date getClinicStartDate() {
		return clinicStartDate;
	}

	public void setClinicStartDate(Date clinicStartDate) {
		this.clinicStartDate = clinicStartDate;
	}

	public Date getClinicEndDate() {
		return clinicEndDate;
	}

	public void setClinicEndDate(Date clinicEndDate) {
		this.clinicEndDate = clinicEndDate;
	}
}
