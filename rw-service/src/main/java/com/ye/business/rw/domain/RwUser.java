package com.ye.business.rw.domain;

import java.util.Date;

public class RwUser {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGuid;

    /**
     * 导购姓名 .
     */
    private String memberNameGuid;

    /**
     * 分店编号 .
     */
    private String shopNo;

    /**
     * 分店名称 .
     */
    private String shopName;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 状态           NORMAL正常、          CANCEL注销。          FREEZE冻结          .
     */
    private String status;

    /**
     * 用户级别：1-普通用户；2-个体用户 .
     */
    private String userLevel;

    /**
     * 手机号 .
     */
    private String mobile;

    /**
     * 手机串号 .
     */
    private String imei;

    /**
     * 邮箱 .
     */
    private String email;

    /**
     * 昵称 .
     */
    private String nickName;

    /**
     * 区域CODE .
     */
    private String areaCode;

    /**
     * 区域名称 .
     */
    private String areaName;

    /**
     * 省CODE .
     */
    private String provinceCode;

    /**
     * 市CODE .
     */
    private String cityCode;

    /**
     * 市区CODE .
     */
    private String cityAreaCode;

    /**
     * 住址 .
     */
    private String address;

    /**
     * 年龄 .
     */
    private Integer age;

    /**
     * 性别:male,female .
     */
    private String gender;

    /**
     * LOGO图片 .
     */
    private String logoAddr;

    /**
     * 官方网站 .
     */
    private String websiteUrl;

    /**
     * 所在公司code .
     */
    private String companyId;

    /**
     * 所在公司 .
     */
    private String companyName;

    /**
     * 登录名 .
     */
    private String loginName;

    /**
     * 登录密码 .
     */
    private String pwd;

    /**
     * 加密机CODE .
     */
    private String encryptionCode;
    
    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 头像地址 .
     */
    private String headAddress;

    /**
     * 二维码地址 .
     */
    private String qcord;

    /**
     * 工作微信号 .
     */
    private String noWx;

    /**
     * 私人微信号 .
     */
    private String noWxPersonal;

    /**
     * 第三方登陆微信OpenID .
     */
    private String loginWxOpenid;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;

    /**
     * 更新人 .
     */
    private String updateId;

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
     * 导购编号 .
     *
     */
    public String getMemberNoGuid() {
        return memberNoGuid;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGuid(String memberNoGuid) {
        this.memberNoGuid = memberNoGuid == null ? null : memberNoGuid.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGuid() {
        return memberNameGuid;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGuid(String memberNameGuid) {
        this.memberNameGuid = memberNameGuid == null ? null : memberNameGuid.trim();
    }

    /**
     * 分店编号 .
     *
     */
    public String getShopNo() {
        return shopNo;
    }

    /**
     * 分店编号 .
     *
     */
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    /**
     * 分店名称 .
     *
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 分店名称 .
     *
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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
     * 状态           NORMAL正常、          CANCEL注销。          FREEZE冻结          .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态           NORMAL正常、          CANCEL注销。          FREEZE冻结          .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 用户级别：1-普通用户；2-个体用户 .
     *
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * 用户级别：1-普通用户；2-个体用户 .
     *
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    /**
     * 手机号 .
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号 .
     *
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 手机串号 .
     *
     */
    public String getImei() {
        return imei;
    }

    /**
     * 手机串号 .
     *
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * 邮箱 .
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱 .
     *
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 昵称 .
     *
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称 .
     *
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 区域CODE .
     *
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 区域CODE .
     *
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 区域名称 .
     *
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区域名称 .
     *
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 省CODE .
     *
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 省CODE .
     *
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 市CODE .
     *
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 市CODE .
     *
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 市区CODE .
     *
     */
    public String getCityAreaCode() {
        return cityAreaCode;
    }

    /**
     * 市区CODE .
     *
     */
    public void setCityAreaCode(String cityAreaCode) {
        this.cityAreaCode = cityAreaCode == null ? null : cityAreaCode.trim();
    }

    /**
     * 住址 .
     *
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址 .
     *
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 年龄 .
     *
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄 .
     *
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 性别:male,female .
     *
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别:male,female .
     *
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * LOGO图片 .
     *
     */
    public String getLogoAddr() {
        return logoAddr;
    }

    /**
     * LOGO图片 .
     *
     */
    public void setLogoAddr(String logoAddr) {
        this.logoAddr = logoAddr == null ? null : logoAddr.trim();
    }

    /**
     * 官方网站 .
     *
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 官方网站 .
     *
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    /**
     * 所在公司code .
     *
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 所在公司code .
     *
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 所在公司 .
     *
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 所在公司 .
     *
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 登录名 .
     *
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录名 .
     *
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 登录密码 .
     *
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 登录密码 .
     *
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * 加密机CODE .
     *
     */
    public String getEncryptionCode() {
        return encryptionCode;
    }

    /**
     * 加密机CODE .
     *
     */
    public void setEncryptionCode(String encryptionCode) {
        this.encryptionCode = encryptionCode == null ? null : encryptionCode.trim();
    }

    public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
     * 头像地址 .
     *
     */
    public String getHeadAddress() {
        return headAddress;
    }

    /**
     * 头像地址 .
     *
     */
    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress == null ? null : headAddress.trim();
    }

    /**
     * 二维码地址 .
     *
     */
    public String getQcord() {
        return qcord;
    }

    /**
     * 二维码地址 .
     *
     */
    public void setQcord(String qcord) {
        this.qcord = qcord == null ? null : qcord.trim();
    }

    /**
     * 工作微信号 .
     *
     */
    public String getNoWx() {
        return noWx;
    }

    /**
     * 工作微信号 .
     *
     */
    public void setNoWx(String noWx) {
        this.noWx = noWx == null ? null : noWx.trim();
    }

    /**
     * 私人微信号 .
     *
     */
    public String getNoWxPersonal() {
        return noWxPersonal;
    }

    /**
     * 私人微信号 .
     *
     */
    public void setNoWxPersonal(String noWxPersonal) {
        this.noWxPersonal = noWxPersonal == null ? null : noWxPersonal.trim();
    }

    /**
     * 第三方登陆微信OpenID .
     *
     */
    public String getLoginWxOpenid() {
        return loginWxOpenid;
    }

    /**
     * 第三方登陆微信OpenID .
     *
     */
    public void setLoginWxOpenid(String loginWxOpenid) {
        this.loginWxOpenid = loginWxOpenid == null ? null : loginWxOpenid.trim();
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
        builder.append("RwUser [code=").append(code);
        builder.append(",memberNoGuid=").append(memberNoGuid); 
        builder.append(",memberNameGuid=").append(memberNameGuid); 
        builder.append(",shopNo=").append(shopNo); 
        builder.append(",shopName=").append(shopName); 
        builder.append(",merchantNo=").append(merchantNo); 
        builder.append(",merchantName=").append(merchantName); 
        builder.append(",status=").append(status); 
        builder.append(",userLevel=").append(userLevel); 
        builder.append(",mobile=").append(mobile); 
        builder.append(",imei=").append(imei); 
        builder.append(",email=").append(email); 
        builder.append(",nickName=").append(nickName); 
        builder.append(",areaCode=").append(areaCode); 
        builder.append(",areaName=").append(areaName); 
        builder.append(",provinceCode=").append(provinceCode); 
        builder.append(",cityCode=").append(cityCode); 
        builder.append(",cityAreaCode=").append(cityAreaCode); 
        builder.append(",address=").append(address); 
        builder.append(",age=").append(age); 
        builder.append(",gender=").append(gender); 
        builder.append(",logoAddr=").append(logoAddr); 
        builder.append(",websiteUrl=").append(websiteUrl); 
        builder.append(",companyId=").append(companyId); 
        builder.append(",companyName=").append(companyName); 
        builder.append(",loginName=").append(loginName); 
        builder.append(",pwd=").append(pwd); 
        builder.append(",encryptionCode=").append(encryptionCode); 
        builder.append(",headAddress=").append(headAddress); 
        builder.append(",qcord=").append(qcord); 
        builder.append(",noWx=").append(noWx); 
        builder.append(",noWxPersonal=").append(noWxPersonal); 
        builder.append(",loginWxOpenid=").append(loginWxOpenid); 
        builder.append(",createId=").append(createId); 
        builder.append(",createDate=").append(createDate); 
        builder.append(",updateId=").append(updateId); 
        builder.append(",updateDate=").append(updateDate); 
        builder.append(",remark=").append(remark); 
        builder.append(",remark2=").append(remark2); 
        builder.append(",remark3=").append(remark3); 
        builder.append(",remark4=").append(remark4); 
        builder.append("]");
        return builder.toString();
    }
}