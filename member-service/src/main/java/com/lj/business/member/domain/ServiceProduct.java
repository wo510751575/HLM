package com.lj.business.member.domain;

import java.util.Date;

public class ServiceProduct {
    /**
     * CODE .
     */
    private String code;

    /**
     * 产品名称 .
     */
    private String productName;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 关键字，多个用英文逗号隔开 .
     */
    private String keywords;

    /**
     * 描述 .
     */
    private String description;

    /**
     * 封面地址 .
     */
    private String coverAddr;

    /**
     * 照片地址 .
     */
    private String imgAddr;

    /**
     * 服务选择CODE .
     */
    private String serviceChooseCode;

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
     * 产品名称 .
     *
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 产品名称 .
     *
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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
     * 关键字，多个用英文逗号隔开 .
     *
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 关键字，多个用英文逗号隔开 .
     *
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * 描述 .
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述 .
     *
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 封面地址 .
     *
     */
    public String getCoverAddr() {
        return coverAddr;
    }

    /**
     * 封面地址 .
     *
     */
    public void setCoverAddr(String coverAddr) {
        this.coverAddr = coverAddr == null ? null : coverAddr.trim();
    }

    /**
     * 照片地址 .
     *
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 照片地址 .
     *
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }

    /**
     * 服务选择CODE .
     *
     */
    public String getServiceChooseCode() {
        return serviceChooseCode;
    }

    /**
     * 服务选择CODE .
     *
     */
    public void setServiceChooseCode(String serviceChooseCode) {
        this.serviceChooseCode = serviceChooseCode == null ? null : serviceChooseCode.trim();
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

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceProduct [code=").append(code);
        builder.append(",productName=").append(productName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",keywords=").append(keywords); 
        builder.append(",description=").append(description); 
        builder.append(",coverAddr=").append(coverAddr); 
        builder.append(",imgAddr=").append(imgAddr); 
        builder.append(",serviceChooseCode=").append(serviceChooseCode); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}