package com.lj.business.cm.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AddMaterialCommen.
 */
public class AddMaterialCommen implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6997633458976663565L;

	private Date startTime;
	private Date endTime;
	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 类型CODE .
	 */
	private String materialTypeCode;

	/**
	 * 类型名称 .
	 */
	private String materialTypeName;

	/**
	 * 标题 .
	 */
	private String title;

	/**
	 * 内容 .
	 */
	private String content;

	/**
	 * 简介 .
	 */
	private String brief;

	/**
	 * 图片地址 .
	 */
	private String imgAddr;

	/** 回应数量. */
	private int respondNum;

	/**
	 * 创建人 .
	 */
	private String createId;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	/**
	 * 商户编号 .
	 */
	private String merchantNo;

	/**
	 * 商户名称 .
	 */
	private String merchantName;

	/** 终端编号. */

	/** 终端名称. */

	/** 维护维度. */
	private String dimensionSt;

	/** 网址链接. */
	private String linkUrl;

	/** 终端类型. */
	private String shopType;

	/** 模版ID. */
	private String tempId;

	
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the 模版ID.
	 *
	 * @return the tempId
	 */
	public String getTempId() {
		return tempId;
	}

	/**
	 * Sets the 模版ID.
	 *
	 * @param tempId the tempId to set
	 */
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	/**
	 * Gets the 终端类型.
	 *
	 * @return the 终端类型
	 */
	public String getShopType() {
		return shopType;
	}

	/**
	 * Sets the 终端类型.
	 *
	 * @param shopType the new 终端类型
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	/**
	 * Gets the 网址链接.
	 *
	 * @return the 网址链接
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * Sets the 网址链接.
	 *
	 * @param linkUrl the new 网址链接
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}



	/**
	 * Gets the dimension st.
	 *
	 * @return the dimension st
	 */
	public String getDimensionSt() {
		return dimensionSt;
	}

	/**
	 * Sets the dimension st.
	 *
	 * @param dimensionSt the dimension st
	 */
	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 商户名称 .
	 *
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 类型CODE .
	 *
	 * @return the 类型CODE
	 */
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	/**
	 * Sets the 类型CODE .
	 *
	 * @param materialTypeCode the new 类型CODE
	 */
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	/**
	 * Gets the 类型名称 .
	 *
	 * @return the 类型名称
	 */
	public String getMaterialTypeName() {
		return materialTypeName;
	}

	/**
	 * Sets the 类型名称 .
	 *
	 * @param materialTypeName the new 类型名称
	 */
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	/**
	 * Gets the 标题 .
	 *
	 * @return the 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the 标题 .
	 *
	 * @param title the new 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the 内容 .
	 *
	 * @return the 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 内容 .
	 *
	 * @param content the new 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the 图片地址 .
	 *
	 * @return the 图片地址
	 */
	public String getImgAddr() {
		return imgAddr;
	}

	/**
	 * Sets the 图片地址 .
	 *
	 * @param imgAddr the new 图片地址
	 */
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	/**
	 * Gets the 回应数量 .
	 *
	 * @return the 回应数量
	 */
	public int getRespondNum() {
		return respondNum;
	}

	/**
	 * Sets the 回应数量 .
	 *
	 * @param respondNum the new 回应数量
	 */
	public void setRespondNum(int respondNum) {
		this.respondNum = respondNum;
	}

	/**
	 * Gets the 创建人 .
	 *
	 * @return the 创建人
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * Sets the 创建人 .
	 *
	 * @param createId the new 创建人
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the 创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the new 创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * Gets the 简介 .
	 *
	 * @return the 简介
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * Sets the 简介 .
	 *
	 * @param brief the new 简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddMaterialCommen [code=");
		builder.append(code);
		builder.append(", materialTypeCode=");
		builder.append(materialTypeCode);
		builder.append(", materialTypeName=");
		builder.append(materialTypeName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", brief=");
		builder.append(brief);
		builder.append(", imgAddr=");
		builder.append(imgAddr);
		builder.append(", respondNum=");
		builder.append(respondNum);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", dimensionSt=");
		builder.append(dimensionSt);
		builder.append(", linkUrl=");
		builder.append(linkUrl);
		builder.append(", shopType=");
		builder.append(shopType);
		builder.append(", tempId=");
		builder.append(tempId);
		builder.append("]");
		return builder.toString();
	}
}
