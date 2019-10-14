package com.ye.business.ad.domain;

import java.util.Date;

public class RwOrder {
	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 门诊编号 .
	 */
	private String shopNo;

	/**
	 * 门诊名称 .
	 */
	private String shopName;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;

	/**
	 * 订单编号 .
	 */
	private String orderNo;

	/**
	 * 订单类型（SERVER:服务） .
	 */
	private String orderType;

	/**
	 * 服务CODE .
	 */
	private String serveCode;

	/**
	 * 服务名称 .
	 */
	private String serveName;

	/**
	 * 下单人编号 .
	 */
	private String memberNoGuid;

	/**
	 * 下单人姓名 .
	 */
	private String memberNameGuid;

	/**
	 * 下单人电话 .
	 */
	private String mobile;

	/**
	 * 支付流水编号 .
	 */
	private String serialNum;

	/**
	 * 付款方式(WX:微信，ALI:支付宝，BANK:银行转账) .
	 */
	private String payType;

	/**
	 * 付款金额（分为单位） .
	 */
	private Long amount;

	/**
	 * 付款时间 .
	 */
	private Date payTime;

	/**
	 * 支付凭证,多张逗号分隔 .
	 */
	private String payCert;

	/**
	 * 审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝) .
	 */
	private String status;

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

	/**
	 * 备注 .
	 */
	private String remark;

	/**
	 * 备注1 .
	 */
	private String remark2;

	/**
	 * 备注2 .
	 */
	private String remark3;
	
	/**
     * 登录名
     */
    private String loginName;
    
    

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * CODE .
	 *
	 */
	public String getCode() {
		return code;
	}

	/**
	 * CODE .
	 *
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * 门诊编号 .
	 *
	 */
	public String getShopNo() {
		return shopNo;
	}

	/**
	 * 门诊编号 .
	 *
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo == null ? null : shopNo.trim();
	}

	/**
	 * 门诊名称 .
	 *
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * 门诊名称 .
	 *
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	/**
	 * 商户编号 .
	 *
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * 商户编号 .
	 *
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo == null ? null : merchantNo.trim();
	}

	/**
	 * 商户名称 .
	 *
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 商户名称 .
	 *
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	/**
	 * 订单编号 .
	 *
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单编号 .
	 *
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	/**
	 * 订单类型（SERVER:服务） .
	 *
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 订单类型（SERVER:服务） .
	 *
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType == null ? null : orderType.trim();
	}

	/**
	 * 服务CODE .
	 *
	 */
	public String getServeCode() {
		return serveCode;
	}

	/**
	 * 服务CODE .
	 *
	 */
	public void setServeCode(String serveCode) {
		this.serveCode = serveCode == null ? null : serveCode.trim();
	}

	/**
	 * 服务名称 .
	 *
	 */
	public String getServeName() {
		return serveName;
	}

	/**
	 * 服务名称 .
	 *
	 */
	public void setServeName(String serveName) {
		this.serveName = serveName == null ? null : serveName.trim();
	}

	/**
	 * 下单人编号 .
	 *
	 */
	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	/**
	 * 下单人编号 .
	 *
	 */
	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
	}

	/**
	 * 下单人姓名 .
	 *
	 */
	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	/**
	 * 下单人姓名 .
	 *
	 */
	public void setMemberNameGuid(String memberNameGuid) {
		this.memberNameGuid = memberNameGuid == null ? null : memberNameGuid.trim();
	}

	/**
	 * 下单人电话 .
	 *
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 下单人电话 .
	 *
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * 支付流水编号 .
	 *
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * 支付流水编号 .
	 *
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum == null ? null : serialNum.trim();
	}

	/**
	 * 付款方式(WX:微信，ALI:支付宝，BANK:银行转账) .
	 *
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 付款方式(WX:微信，ALI:支付宝，BANK:银行转账) .
	 *
	 */
	public void setPayType(String payType) {
		this.payType = payType == null ? null : payType.trim();
	}

	/**
	 * 付款金额（分为单位） .
	 *
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * 付款金额（分为单位） .
	 *
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * 付款时间 .
	 *
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 付款时间 .
	 *
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 支付凭证,多张逗号分隔 .
	 *
	 */
	public String getPayCert() {
		return payCert;
	}

	/**
	 * 支付凭证,多张逗号分隔 .
	 *
	 */
	public void setPayCert(String payCert) {
		this.payCert = payCert == null ? null : payCert.trim();
	}

	/**
	 * 审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝) .
	 *
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 审核状态(WAIT:待审，PASS:通过，UNPASS：拒绝) .
	 *
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 更新人 .
	 *
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 更新人 .
	 *
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	/**
	 * 更新时间 .
	 *
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新时间 .
	 *
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 创建人 .
	 *
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * 创建人 .
	 *
	 */
	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	/**
	 * 创建时间 .
	 *
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建时间 .
	 *
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 备注 .
	 *
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注 .
	 *
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 备注1 .
	 *
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * 备注1 .
	 *
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2 == null ? null : remark2.trim();
	}

	/**
	 * 备注2 .
	 *
	 */
	public String getRemark3() {
		return remark3;
	}

	/**
	 * 备注2 .
	 *
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3 == null ? null : remark3.trim();
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RwOrder [code=").append(code);
		builder.append(",shopNo=").append(shopNo);
		builder.append(",shopName=").append(shopName);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",merchantName=").append(merchantName);
		builder.append(",orderNo=").append(orderNo);
		builder.append(",orderType=").append(orderType);
		builder.append(",serveCode=").append(serveCode);
		builder.append(",serveName=").append(serveName);
		builder.append(",memberNoGuid=").append(memberNoGuid);
		builder.append(",memberNameGuid=").append(memberNameGuid);
		builder.append(",mobile=").append(mobile);
		builder.append(",serialNum=").append(serialNum);
		builder.append(",payType=").append(payType);
		builder.append(",amount=").append(amount);
		builder.append(",payTime=").append(payTime);
		builder.append(",payCert=").append(payCert);
		builder.append(",status=").append(status);
		builder.append(",updateId=").append(updateId);
		builder.append(",updateDate=").append(updateDate);
		builder.append(",createId=").append(createId);
		builder.append(",createDate=").append(createDate);
		builder.append(",remark=").append(remark);
		builder.append(",remark2=").append(remark2);
		builder.append(",remark3=").append(remark3);
		builder.append("]");
		return builder.toString();
	}
}