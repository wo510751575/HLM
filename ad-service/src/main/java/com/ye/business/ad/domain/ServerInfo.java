package com.ye.business.ad.domain;

import java.util.Date;

public class ServerInfo {
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
	 * 产品名称 .
	 */
	private String serverName;

	/**
	 * 价格（分为单位） .
	 */
	private Long price;

	/**
	 * 原价（分为单位） .
	 */
	private Long originalPrice;

	/**
	 * 状态（use 启用 unuse 禁用） .
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
	 * 产品说明 .
	 */
	private String ctx;

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
	 * 产品名称 .
	 *
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * 产品名称 .
	 *
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName == null ? null : serverName.trim();
	}

	/**
	 * 价格（分为单位） .
	 *
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * 价格（分为单位） .
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
	 * 状态（use 启用 unuse 禁用） .
	 *
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态（use 启用 unuse 禁用） .
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
	 * 产品说明 .
	 *
	 */
	public String getCtx() {
		return ctx;
	}

	/**
	 * 产品说明 .
	 *
	 */
	public void setCtx(String ctx) {
		this.ctx = ctx == null ? null : ctx.trim();
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerInfo [code=").append(code);
		builder.append(",merchantNo=").append(merchantNo);
		builder.append(",merchantName=").append(merchantName);
		builder.append(",serverName=").append(serverName);
		builder.append(",price=").append(price);
		builder.append(",originalPrice=").append(originalPrice);
		builder.append(",status=").append(status);
		builder.append(",updateId=").append(updateId);
		builder.append(",updateDate=").append(updateDate);
		builder.append(",createId=").append(createId);
		builder.append(",createDate=").append(createDate);
		builder.append(",ctx=").append(ctx);
		builder.append("]");
		return builder.toString();
	}
}