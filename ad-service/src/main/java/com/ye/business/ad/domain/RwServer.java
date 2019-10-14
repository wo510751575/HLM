package com.ye.business.ad.domain;

import java.util.Date;

public class RwServer {
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
	 * 员工编号 .
	 */
	private String memberNoGuid;

	/**
	 * 员工姓名 .
	 */
	private String memberNameGuid;

	/**
	 * 下单人电话 .
	 */
	private String mobile;

	/**
	 * 服务code .
	 */
	private String serverCode;

	/**
	 * 服务名称 .
	 */
	private String serverName;

	/**
	 * 价格 .
	 */
	private Long price;

	/**
	 * 原价（分为单位） .
	 */
	private Long originalPrice;

	/**
	 * 订单号 .
	 */
	private String orderNo;

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
	 * 员工编号 .
	 *
	 */
	public String getMemberNoGuid() {
		return memberNoGuid;
	}

	/**
	 * 员工编号 .
	 *
	 */
	public void setMemberNoGuid(String memberNoGuid) {
		this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
	}

	/**
	 * 员工姓名 .
	 *
	 */
	public String getMemberNameGuid() {
		return memberNameGuid;
	}

	/**
	 * 员工姓名 .
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
	 * 服务code .
	 *
	 */
	public String getServerCode() {
		return serverCode;
	}

	/**
	 * 服务code .
	 *
	 */
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode == null ? null : serverCode.trim();
	}

	/**
	 * 服务名称 .
	 *
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * 服务名称 .
	 *
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName == null ? null : serverName.trim();
	}

	/**
	 * 价格 .
	 *
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * 价格 .
	 *
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * 原价（分为单位） .
	 *
	 */
	public Long getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * 原价（分为单位） .
	 *
	 */
	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * 订单号 .
	 *
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 订单号 .
	 *
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
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
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RwServer [code=").append(code);
		builder.append(",shopNo=").append(shopNo);
		builder.append(",shopName=").append(shopName);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",merchantName=").append(merchantName);
		builder.append(",memberNoGuid=").append(memberNoGuid);
		builder.append(",memberNameGuid=").append(memberNameGuid);
		builder.append(",mobile=").append(mobile);
		builder.append(",serverCode=").append(serverCode);
		builder.append(",serverName=").append(serverName);
		builder.append(",price=").append(price);
		builder.append(",originalPrice=").append(originalPrice);
		builder.append(",orderNo=").append(orderNo);
		builder.append(",updateId=").append(updateId);
		builder.append(",updateDate=").append(updateDate);
		builder.append(",createId=").append(createId);
		builder.append(",createDate=").append(createDate);
		builder.append("]");
		return builder.toString();
	}
}