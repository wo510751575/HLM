package com.ye.business.hx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BillRefundDto implements Serializable {
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

    /** 门诊编号*/
    private String shopNo;

    /** 门诊名称*/
    private String shopName;

    /** 商户编号*/
    private String merchantNo;

    /** 商户名称*/
    private String merchantName;

    /** 患者编号*/
    private String patientNo;

    /** 患者名称*/
    private String patientName;

    /** 患者病历号*/
    private String medicalNo;

    /** 支付方式*/
    private String payType;

    /** 支付方式描述*/
    private String payTypeName;

    /** 退款金额(分为单位）*/
    private Long rtAmount;

    /** 退款人编号*/
    private String refundGdNo;

    /** 退款人姓名*/
    private String refundGdName;

    /** 退费时间*/
    private Date refundTime;
    /** 退费时间*/
    private String refundTimeStr;

    /** 状态（UNCHECK待审批，UNPAY待退费，CHKREFUS:已拒绝，FINISH已退费）*/
    private String refundStatus;

    /** 有效状态(NORMAL 正常，CANCEL：作废）*/
    private String status;

    /** 退款类型（ALL:整单退，ITEM：按项目数量退，PART:退一部分金额）*/
    private String refundType;

    /** 更新人*/
    private String updateId;

    /** 更新时间*/
    private Date updateDate;

    /** 创建人*/
    private String createId;

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

    /** 退款项目List */
    private List<BillRefundDetailDto> details;
    /** 操作人编号*/
    private String memberNoGuid;
    /** 操作人姓名*/
    private String memberNameGuid;
    /** 支付方式及其金额（格式：PAY_TYPE1:支付金额2,PAY_TYPE1:支付金额2）*/
    private String payRemark;
    /** SVAE:仅保存，（PAY收费，DEBT：收欠费，REFUNDAPLLY退款申请） */
    private String optType;
   
    /** 在退款时，账单的应收金额（分为单位）*/
    private Long reallyAmount;
 
    /** 账单可退总额(账单收费总额-账单已退总额）*/
    private Long billPayAmount;
    
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    public Long getRtAmount() {
        return rtAmount;
    }

    public void setRtAmount(Long rtAmount) {
        this.rtAmount = rtAmount;
    }

    public String getRefundGdNo() {
        return refundGdNo;
    }

    public void setRefundGdNo(String refundGdNo) {
        this.refundGdNo = refundGdNo == null ? null : refundGdNo.trim();
    }

    public String getRefundGdName() {
        return refundGdName;
    }

    public void setRefundGdName(String refundGdName) {
        this.refundGdName = refundGdName == null ? null : refundGdName.trim();
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
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

	public List<BillRefundDetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<BillRefundDetailDto> details) {
		this.details = details;
	}

	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid;
	}

	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid;
	}

	public String getPayRemark() {
		return payRemark;
	}

	public void setPayRemark(String payRemark) {
		this.payRemark = payRemark;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getRefundTimeStr() {
		return refundTimeStr;
	}

	public void setRefundTimeStr(String refundTimeStr) {
		this.refundTimeStr = refundTimeStr;
	}

	public Long getReallyAmount() {
		return reallyAmount;
	}

	public void setReallyAmount(Long reallyAmount) {
		this.reallyAmount = reallyAmount;
	}

	public Long getBillPayAmount() {
		return billPayAmount;
	}

	public void setBillPayAmount(Long billPayAmount) {
		this.billPayAmount = billPayAmount;
	}

	@Override
	public String toString() {
		return "BillRefundDto [code=" + code + ", billCode=" + billCode + ", operateCode=" + operateCode + ", shopNo="
				+ shopNo + ", shopName=" + shopName + ", merchantNo=" + merchantNo + ", merchantName=" + merchantName
				+ ", patientNo=" + patientNo + ", patientName=" + patientName + ", medicalNo=" + medicalNo
				+ ", payType=" + payType + ", payTypeName=" + payTypeName + ", rtAmount=" + rtAmount + ", refundGdNo="
				+ refundGdNo + ", refundGdName=" + refundGdName + ", refundTime=" + refundTime + ", refundTimeStr="
				+ refundTimeStr + ", refundStatus=" + refundStatus + ", status=" + status + ", refundType=" + refundType
				+ ", updateId=" + updateId + ", updateDate=" + updateDate + ", createId=" + createId + ", createDate="
				+ createDate + ", remark=" + remark + ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3="
				+ remark3 + ", remark4=" + remark4 + ", details=" + details + ", memberNoGuid=" + memberNoGuid
				+ ", memberNameGuid=" + memberNameGuid + ", payRemark=" + payRemark + ", optType=" + optType
				+ ", reallyAmount=" + reallyAmount + ", billPayAmount=" + billPayAmount + "]";
	}
    
    
}
