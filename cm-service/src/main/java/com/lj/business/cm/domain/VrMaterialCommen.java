package com.lj.business.cm.domain;

import java.util.Date;

public class VrMaterialCommen {
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
     * 素材维度             商户：MERCHANT             终端：SHOP .
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
	 * 显示渠道
	 */
	private String showChannel;
	
    public String getShowChannel() {
		return showChannel;
	}

	public void setShowChannel(String showChannel) {
		this.showChannel = showChannel;
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
     * 客户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 客户编号 .
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
     * 标题 .
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题 .
     *
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 内容 .
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     *
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 简介 .
     *
     */
    public String getBrief() {
        return brief;
    }

    /**
     * 简介 .
     *
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址（多个时以逗号分隔） .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 素材维度             商户：MERCHANT             终端：SHOP .
     *
     */
    public String getDimensionSt() {
        return dimensionSt;
    }

    /**
     * 素材维度             商户：MERCHANT             终端：SHOP .
     *
     */
    public void setDimensionSt(String dimensionSt) {
        this.dimensionSt = dimensionSt == null ? null : dimensionSt.trim();
    }

    /**
     * 网址链接 .
     *
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 网址链接 .
     *
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 回应数量 .
     *
     */
    public Integer getRespondNum() {
        return respondNum;
    }

    /**
     * 回应数量 .
     *
     */
    public void setRespondNum(Integer respondNum) {
        this.respondNum = respondNum;
    }

    /**
     * 终端类型 .
     *
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * 终端类型 .
     *
     */
    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
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
     * 备注 .
     *
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * 备注 .
     *
     */
    public String getRemark4() {
        return remark4;
    }

    /**
     * 备注 .
     *
     */
    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VrMaterialCommen [code=");
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
		builder.append(", showChannel=");
		builder.append(showChannel);
		builder.append("]");
		return builder.toString();
	}

}