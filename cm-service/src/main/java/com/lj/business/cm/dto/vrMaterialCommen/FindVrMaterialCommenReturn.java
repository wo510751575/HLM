package com.lj.business.cm.dto.vrMaterialCommen;

import java.io.Serializable;
import java.util.Date;

public class FindVrMaterialCommenReturn implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = -6709299665221821800L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 客户编号 .
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
     * 图片地址（多个时以逗号分隔） .
     */
    private String imgAddr;

    /**
     * 素材维度
             商户：MERCHANT
             终端：SHOP .
     */
    private String dimensionSt;

    /**
     * 网址链接 .
     */
    private String linkUrl;

    /**
     * 回应数量 .
     */
    private Integer respondNum;

    /**
     * 终端类型 .
     */
    private String shopType;

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
     * 备注 .
     */
    private String remark2;

    /**
     * 备注 .
     */
    private String remark3;

    /**
     * 备注 .
     */
    private String remark4;
    

    /**
     * 类型CODE .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;

    /**
     * 类型分类CODE .
     */
    private String typeCategoryCode;

    /**
     * 类型分类名称 .
     */
    private String typeCategoryName;
    
	/**
	 * 显示渠道
	 */
	private String showChannel;

	public String getShowChannel() {
		return showChannel;
	}

	public void setShowChannel(String showChannel) {
		this.showChannel = showChannel;
	}

	public String getMaterialTypeCode() {
		return materialTypeCode;
	}

	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getTypeCategoryCode() {
		return typeCategoryCode;
	}

	public void setTypeCategoryCode(String typeCategoryCode) {
		this.typeCategoryCode = typeCategoryCode;
	}

	public String getTypeCategoryName() {
		return typeCategoryName;
	}

	public void setTypeCategoryName(String typeCategoryName) {
		this.typeCategoryName = typeCategoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getRespondNum() {
		return respondNum;
	}

	public void setRespondNum(Integer respondNum) {
		this.respondNum = respondNum;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindVrMaterialCommenReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", brief=");
		builder.append(brief);
		builder.append(", imgAddr=");
		builder.append(imgAddr);
		builder.append(", dimensionSt=");
		builder.append(dimensionSt);
		builder.append(", linkUrl=");
		builder.append(linkUrl);
		builder.append(", respondNum=");
		builder.append(respondNum);
		builder.append(", shopType=");
		builder.append(shopType);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", remark2=");
		builder.append(remark2);
		builder.append(", remark3=");
		builder.append(remark3);
		builder.append(", remark4=");
		builder.append(remark4);
		builder.append(", materialTypeCode=");
		builder.append(materialTypeCode);
		builder.append(", materialTypeName=");
		builder.append(materialTypeName);
		builder.append(", typeCategoryCode=");
		builder.append(typeCategoryCode);
		builder.append(", typeCategoryName=");
		builder.append(typeCategoryName);
		builder.append(", showChannel=");
		builder.append(showChannel);
		builder.append("]");
		return builder.toString();
	}
    
}
