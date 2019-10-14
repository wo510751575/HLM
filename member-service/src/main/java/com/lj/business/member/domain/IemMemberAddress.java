package com.lj.business.member.domain;

import java.util.Date;

public class IemMemberAddress {
    /**
     * CODE .
     */
    private String code;

    /**
     * 会员编号 .
     */
    private String memberNo;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 收件人 .
     */
    private String reciverName;

    /**
     * 联系手机 .
     */
    private String reciverMobile;

    /**
     * 邮编 .
     */
    private String reciverZip;

    /**
     * 完整地址（省市区详细地址全部包含） .
     */
    private String addrInfo;

    /**
     * 是否默认 Y:是 N:否 .
     */
    private String isDefault;

    /**
     * 省编号 .
     */
    private String provinceCode;

    /**
     * 省名称 .
     */
    private String provinceName;

    /**
     * 市编号 .
     */
    private String cityCode;

    /**
     * 市名称 .
     */
    private String cityName;

    /**
     * 县编号 .
     */
    private String areaCode;

    /**
     * 县名称 .
     */
    private String areaName;

    /**
     * 详细地址 .
     */
    private String addrDetail;

    /**
     * 是否删除（N未删除 Y删除） .
     */
    private String delFlag;

    /**
     * 微软CRM地址标识：0否、1是 .
     */
    private Integer crmAddrFlag;

    /**
     * 微软CRM地址编号 .
     */
    private String crmAddrNo;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新时间 .
     */
    private Date updateDate;

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
     * 会员编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 会员编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
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
     * 收件人 .
     *
     */
    public String getReciverName() {
        return reciverName;
    }

    /**
     * 收件人 .
     *
     */
    public void setReciverName(String reciverName) {
        this.reciverName = reciverName == null ? null : reciverName.trim();
    }

    /**
     * 联系手机 .
     *
     */
    public String getReciverMobile() {
        return reciverMobile;
    }

    /**
     * 联系手机 .
     *
     */
    public void setReciverMobile(String reciverMobile) {
        this.reciverMobile = reciverMobile == null ? null : reciverMobile.trim();
    }

    /**
     * 邮编 .
     *
     */
    public String getReciverZip() {
        return reciverZip;
    }

    /**
     * 邮编 .
     *
     */
    public void setReciverZip(String reciverZip) {
        this.reciverZip = reciverZip == null ? null : reciverZip.trim();
    }

    /**
     * 完整地址（省市区详细地址全部包含） .
     *
     */
    public String getAddrInfo() {
        return addrInfo;
    }

    /**
     * 完整地址（省市区详细地址全部包含） .
     *
     */
    public void setAddrInfo(String addrInfo) {
        this.addrInfo = addrInfo == null ? null : addrInfo.trim();
    }

    /**
     * 是否默认 Y:是 N:否 .
     *
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 是否默认 Y:是 N:否 .
     *
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    /**
     * 省编号 .
     *
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省编号 .
     *
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 省名称 .
     *
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 省名称 .
     *
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 市编号 .
     *
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 市编号 .
     *
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 市名称 .
     *
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 市名称 .
     *
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 县编号 .
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 县编号 .
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 县名称 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 县名称 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 详细地址 .
     *
     */
    public String getAddrDetail() {
        return addrDetail;
    }

    /**
     * 详细地址 .
     *
     */
    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail == null ? null : addrDetail.trim();
    }

    /**
     * 是否删除（N未删除 Y删除） .
     *
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 是否删除（N未删除 Y删除） .
     *
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * 微软CRM地址标识：0否、1是 .
     *
     */
    public Integer getCrmAddrFlag() {
        return crmAddrFlag;
    }

    /**
     * 微软CRM地址标识：0否、1是 .
     *
     */
    public void setCrmAddrFlag(Integer crmAddrFlag) {
        this.crmAddrFlag = crmAddrFlag;
    }

    /**
     * 微软CRM地址编号 .
     *
     */
    public String getCrmAddrNo() {
        return crmAddrNo;
    }

    /**
     * 微软CRM地址编号 .
     *
     */
    public void setCrmAddrNo(String crmAddrNo) {
        this.crmAddrNo = crmAddrNo == null ? null : crmAddrNo.trim();
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
        builder.append("IemMemberAddress [code=").append(code);
        builder.append(",memberNo=").append(memberNo); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",reciverName=").append(reciverName); 
        builder.append(",reciverMobile=").append(reciverMobile); 
        builder.append(",reciverZip=").append(reciverZip); 
        builder.append(",addrInfo=").append(addrInfo); 
        builder.append(",isDefault=").append(isDefault); 
        builder.append(",provinceCode=").append(provinceCode); 
        builder.append(",provinceName=").append(provinceName); 
        builder.append(",cityCode=").append(cityCode); 
        builder.append(",cityName=").append(cityName); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",addrDetail=").append(addrDetail); 
        builder.append(",delFlag=").append(delFlag); 
        builder.append(",crmAddrFlag=").append(crmAddrFlag); 
        builder.append(",crmAddrNo=").append(crmAddrNo); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}