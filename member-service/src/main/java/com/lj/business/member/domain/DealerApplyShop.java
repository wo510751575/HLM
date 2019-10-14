package com.lj.business.member.domain;

import java.util.Date;

public class DealerApplyShop {
    /**
     * CODE
     */
    private String code;

    /**
     * 经销商编号
     */
    private String dealerNo;

    /**
     * 终端代码
     */
    private String shopNoMerchant;

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
     * 店长微信号(暂未使用)
     */
    private String shopManagerWxNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 备注3
     */
    private String remark3;

    /**
     * 备注4
     */
    private String remark4;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDealerNo() {
        return dealerNo;
    }

    public void setDealerNo(String dealerNo) {
        this.dealerNo = dealerNo == null ? null : dealerNo.trim();
    }


    public String getShopNoMerchant() {
        return shopNoMerchant;
    }

    public void setShopNoMerchant(String shopNoMerchant) {
        this.shopNoMerchant = shopNoMerchant == null ? null : shopNoMerchant.trim();
    }


    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public String getLogoAddr() {
        return logoAddr;
    }

    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    public String getBizScope() {
        return bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope == null ? null : bizScope.trim();
    }

    public String getShopManagerName() {
        return shopManagerName;
    }

    public void setShopManagerName(String shopManagerName) {
        this.shopManagerName = shopManagerName == null ? null : shopManagerName.trim();
    }

    public String getShopManagerMobile() {
        return shopManagerMobile;
    }

    public void setShopManagerMobile(String shopManagerMobile) {
        this.shopManagerMobile = shopManagerMobile == null ? null : shopManagerMobile.trim();
    }

    public String getShopManagerWxNo() {
        return shopManagerWxNo;
    }

    public void setShopManagerWxNo(String shopManagerWxNo) {
        this.shopManagerWxNo = shopManagerWxNo == null ? null : shopManagerWxNo.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4 == null ? null : remark4.trim();
    }
}