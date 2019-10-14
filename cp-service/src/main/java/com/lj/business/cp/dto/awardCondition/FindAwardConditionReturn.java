package com.lj.business.cp.dto.awardCondition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FindAwardConditionReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2542102403567212699L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;

	/**
	 * 分店编号 .
	 */
	

	/**
	 * 分店名称 .
	 */
	

	/**
	 * 维度编号 .
	 */
	private String veidooNo;

	/**
	 * 维度上限 .
	 */
	private BigDecimal veidooUp;

	/**
	 * 维度下限 .
	 */
	private BigDecimal veidooDown;

	/**
	 * 活动开始时间 .
	 */
	private Date beginDate;

	/**
	 * 活动结束时间 .
	 */
	private Date endDate;

	/**
	 * 活动描述 .
	 */
	private String activityDesc;

	/**
	 * 更新人 .
	 */
	private String updateId;

	/**
	 * 更新时间 .
	 */
	private Date updateDate;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public String getVeidooNo() {
		return veidooNo;
	}

	public void setVeidooNo(String veidooNo) {
		this.veidooNo = veidooNo;
	}

	public BigDecimal getVeidooUp() {
		return veidooUp;
	}

	public void setVeidooUp(BigDecimal veidooUp) {
		this.veidooUp = veidooUp;
	}

	public BigDecimal getVeidooDown() {
		return veidooDown;
	}

	public void setVeidooDown(BigDecimal veidooDown) {
		this.veidooDown = veidooDown;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
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
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
