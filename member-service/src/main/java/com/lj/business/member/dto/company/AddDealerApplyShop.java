package com.lj.business.member.dto.company;

import java.io.Serializable;

public class AddDealerApplyShop implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2897985535031494889L;

    /**
     * 终端代码
     */
    private String shopNoMerchant;

    /**
     * 终端名称
     */
    

    /**
     * 省CODE
     */
    private String provinceCode;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市CODE
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区CODE
     */
    private String areaCode;

    /**
     * 区名称
     */
    private String areaName;

    /**
     * 详细地址
     */
    private String detailAddr;

    /**
     * LOGO图片
     */
    private String logoAddr;

    /**
     * 经营范围
     */
    private String bizScope;

    /**
     * 店长姓名
     */
    private String shopManagerName;

    /**
     * 店长手机号
     */
    private String shopManagerMobile;

    /**
     * 商户编号
     */
    private String merchantNo;

    public String getShopNoMerchant() {
        return shopNoMerchant;
    }

    public void setShopNoMerchant(String shopNoMerchant) {
        this.shopNoMerchant = shopNoMerchant;
    }


    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getLogoAddr() {
        return logoAddr;
    }

    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr;
    }

    public String getBizScope() {
        return bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope;
    }

    public String getShopManagerName() {
        return shopManagerName;
    }

    public void setShopManagerName(String shopManagerName) {
        this.shopManagerName = shopManagerName;
    }

    public String getShopManagerMobile() {
        return shopManagerMobile;
    }

    public void setShopManagerMobile(String shopManagerMobile) {
        this.shopManagerMobile = shopManagerMobile;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddDealerApplyShop [shopNoMerchant=");
        builder.append(shopNoMerchant);
        builder.append(", provinceCode=");
        builder.append(provinceCode);
        builder.append(", provinceName=");
        builder.append(provinceName);
        builder.append(", cityCode=");
        builder.append(cityCode);
        builder.append(", cityName=");
        builder.append(cityName);
        builder.append(", areaCode=");
        builder.append(areaCode);
        builder.append(", areaName=");
        builder.append(areaName);
        builder.append(", detailAddr=");
        builder.append(detailAddr);
        builder.append(", logoAddr=");
        builder.append(logoAddr);
        builder.append(", bizScope=");
        builder.append(bizScope);
        builder.append(", shopManagerName=");
        builder.append(shopManagerName);
        builder.append(", shopManagerMobile=");
        builder.append(shopManagerMobile);
        builder.append(", merchantNo=");
        builder.append(merchantNo);
        builder.append("]");
        return builder.toString();
    }
    
}
