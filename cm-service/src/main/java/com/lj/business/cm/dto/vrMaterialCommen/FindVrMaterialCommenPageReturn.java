package com.lj.business.cm.dto.vrMaterialCommen;

import java.io.Serializable;

public class FindVrMaterialCommenPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -846780019778768275L; 

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
	 * 显示渠道
	 */
	private String showChannel;

    /**
     * 素材中心code
     */
    private String materialCode;
    /**
     * 备注
     */
    private String remark;
    
    
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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

	public String getShowChannel() {
		return showChannel;
	}

	public void setShowChannel(String showChannel) {
		this.showChannel = showChannel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindVrMaterialCommenPageReturn [code=");
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
		builder.append(", showChannel=");
		builder.append(showChannel);
		builder.append(", materialCode=");
		builder.append(materialCode);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}

}
