package com.lj.business.cf.dto.clientFollowSummary;

import java.io.Serializable;
import java.util.Date;

public class UpdateClientFollowSummary implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1980960213993279273L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 跟踪编号 .
     */
    private String cfNo;

    /**
     * 跟踪次数 .
     */
    private Integer followNum;

    /**
     * 跟踪状态
             正常：NORMAL
             成单：DEAL
             放弃：ABANDON .
     */
    private String status;

    /**
     * 订单金额 .
     */
    private Long orderAmount;
    
    /**
     * 所需产品编号
     */
    private String bomCode;
    
    /**
     * 所需产品名称
     */
    private String bomName;

    /**
     * 送货时间 .
     */
    private Date deliverTime;

    /**
     * 跟踪开始时间 .
     */
    private Date startDate;

    /**
     * 跟踪结束时间 .
     */
    private Date endDate;

    /**
     * 备注 .
     */
    private String remark;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 备注 .
     */
    private String remark4;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark2;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getCfNo() {
		return cfNo;
	}

	public void setCfNo(String cfNo) {
		this.cfNo = cfNo;
	}

	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getBomCode() {
		return bomCode;
	}

	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}

	public String getBomName() {
		return bomName;
	}

	public void setBomName(String bomName) {
		this.bomName = bomName;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Override
	public String toString() {
		return "UpdateClientFollowSummary [code=" + code + ", merchantNo="
				+ merchantNo + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", cfNo=" + cfNo + ", followNum=" + followNum
				+ ", status=" + status + ", orderAmount=" + orderAmount
				+ ", bomCode=" + bomCode + ", bomName=" + bomName
				+ ", deliverTime=" + deliverTime + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", remark=" + remark
				+ ", createDate=" + createDate + ", remark4=" + remark4
				+ ", remark3=" + remark3 + ", remark2=" + remark2 + "]";
	}
    
}
