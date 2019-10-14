package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

public class ShopExtDto implements Serializable { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8015120824890642207L;

	/**
     *  .
     */
    private String code;

    /**
     *  .
     */
    private String memberNoMerchant;

    /**
     *  .
     */
    private String memberNameMerchant;

    /**
     * 终端编号 .
     */
    

    /**
     * 终端名称 .
     */
    

    /**
     * 终端代码(自定义) .
     */
    private String shopNoMerchant;

    /**
     * 店长/负责人 .
     */
    private String shopAdmin;

    /**
     * 终端/分公司地址 .
     */
    private String addrInfo;

    /**
     * 终端座机号 .
     */
    private String telephone;

    /**
     *  .
     */
//    private String mobile;

    /**
     * 终端照片 .
     */
    private String logoAddr;

    /**
     * 所属区域 .
     */
    private String areaName;

    /**
     * 终端/企业开业时间 .
     */
    private Date openDate;

    /**
     * 终端状态 
             OPENED已经开业
             SUSPEND暂停营业
             INDECORATION尚在装修  
              .
     */
    private String status;

    /**
     *  .
     */
    private Date createTime;

    /**
     *  .
     */
    private Date updateTime;

    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     *  .
     *
     */
    public String getMemberNoMerchant() {
        return memberNoMerchant;
    }

    /**
     *  .
     *
     */
    public void setMemberNoMerchant(String memberNoMerchant) {
        this.memberNoMerchant = memberNoMerchant == null ? null : memberNoMerchant.trim();
    }

    /**
     *  .
     *
     */
    public String getMemberNameMerchant() {
        return memberNameMerchant;
    }

    /**
     *  .
     *
     */
    public void setMemberNameMerchant(String memberNameMerchant) {
        this.memberNameMerchant = memberNameMerchant == null ? null : memberNameMerchant.trim();
    }

    /**
     * 终端代码(自定义) .
     *
     */
    public String getShopNoMerchant() {
        return shopNoMerchant;
    }

    /**
     * 终端代码(自定义) .
     *
     */
    public void setShopNoMerchant(String shopNoMerchant) {
        this.shopNoMerchant = shopNoMerchant == null ? null : shopNoMerchant.trim();
    }

    /**
     * 店长/负责人 .
     *
     */
    public String getShopAdmin() {
        return shopAdmin;
    }

    /**
     * 店长/负责人 .
     *
     */
    public void setShopAdmin(String shopAdmin) {
        this.shopAdmin = shopAdmin == null ? null : shopAdmin.trim();
    }

    /**
     * 终端/分公司地址 .
     *
     */
    public String getAddrInfo() {
        return addrInfo;
    }

    /**
     * 终端/分公司地址 .
     *
     */
    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    /**
     * 终端座机号 .
     *
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 终端座机号 .
     *
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     *  .
     *
     */
    public String getMobile() {
        return null;
    }

    /**
     *  .
     *
     */
    public void setMobile(String mobile) {
        
    }

    /**
     * 终端照片 .
     *
     */
    public String getLogoAddr() {
        return logoAddr;
    }

    /**
     * 终端照片 .
     *
     */
    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    /**
     * 所属区域 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 所属区域 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 终端/企业开业时间 .
     *
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * 终端/企业开业时间 .
     *
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 终端状态 
             OPENED已经开业
             SUSPEND暂停营业
             INDECORATION尚在装修  
              .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 终端状态 
             OPENED已经开业
             SUSPEND暂停营业
             INDECORATION尚在装修  
              .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *  .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *  .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *  .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *  .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopExt [code=").append(code);
        builder.append(",memberNoMerchant=").append(memberNoMerchant); 
        builder.append(",memberNameMerchant=").append(memberNameMerchant); 
        builder.append(",shopNoMerchant=").append(shopNoMerchant); 
        builder.append(",shopAdmin=").append(shopAdmin); 
        builder.append(",addrInfo=").append(addrInfo); 
        builder.append(",telephone=").append(telephone); 
        builder.append(",logoAddr=").append(logoAddr); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",openDate=").append(openDate); 
        builder.append(",status=").append(status); 
        builder.append(",createTime=").append(createTime); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append("]");
        return builder.toString();
    }
}
