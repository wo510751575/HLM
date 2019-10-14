package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdatePersonMemberBase.
 */
public class UpdatePersonMemberBase implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2411583531647762356L;

	   /**
     * CODE .
     */
    private String code;

    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 客户名称 .
     */
    private String memberName;

    /**
     * 状态 
             NORMAL正常
             CANCEL注销
             FREEZE冻结
              .
     */
    private String status;

    /**
     * 证件类型 .
     */
    private String certTypeCode;

    /**
     * 证件号码 .
     */
    private String certNo;

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
     * 职业 .
     */
    private String job;

    /**
     * 住址 .
     */
    private String address;

    /**
     * 年龄 .
     */
    private Integer age;

    /**
     * 性别:
             男：male
             女：female
             未知：unknown .
     */
    private String sex;

    /**
     * 实名认证标识 N未实名认证  Y实名认证 .
     */
    private String nameAuthFlag;

    /**
     * 登录密码 .
     */
    private String pwd;

    /**
     * 加密机CODE .
     */
    private String encryptionCode;

    /**
     * 客户来源 .
     */
    private String memberSrc;

    /**
     * 微信公众号OPENID .
     */
    private String openIdGzhWx;

    /**
     * 微信小程序OPENID .
     */
    private String openIdXcxWx;

    /**
     * 微信号 .
     */
    private String noWx;
    
    /**
     * 微信号别名 .
     */
    private String noWxAlias;

    /**
     * 昵称_微信 .
     */
    private String nickNameWx;

    /**
     * 城市_微信 .
     */
    private String cityWx;

    /**
     * 国家 .
     */
    private String countryWx;

    /**
     * 省_微信 .
     */
    private String provinceWx;

    /**
     * 头像地址 .
     */
    private String headAddress;

    /**
     * 关注时间 .
     */
    private Date subsribeTime;

    /**
     * 家庭组CODE .
     */
    private String familyCode;

    /**
     * 客户家庭组名称 .
     */
    private String familyName;

    /**
     * 兴趣 .
     */
    private String interest;

    /** 星座. */
    private String constellation;
    
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
     * 出生日期 .
     */
    private Date birthday;

    /**
     * 资料完善度 .
     */
    private Double ratioClientInfo;

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
     * 微信OPENID,存储用于对外接口返回
     */
    private String wxOpenId;
    
    /**
     * QQ号
     */
    private String noQQ;
    /** 旺旺账号*/
    private String noWw;
    /**
     * 订单号
     */
    private String orderNo;
    
    /**
     * 首次添加手机号码时间
     */
    private Date addMobileDate;
    
    
	public Date getAddMobileDate() {
		return addMobileDate;
	}

	public void setAddMobileDate(Date addMobileDate) {
		this.addMobileDate = addMobileDate;
	}

    
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw;
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
	 * Gets the 客户编号 .
	 *
	 * @return the 客户编号 
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 客户编号 .
	 *
	 * @param memberNo the new 客户编号 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 客户名称 .
	 *
	 * @return the 客户名称 
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 客户名称 .
	 *
	 * @param memberName the new 客户名称 
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 状态 NORMAL正常 CANCEL注销 FREEZE冻结 .
	 *
	 * @return the 状态 NORMAL正常 CANCEL注销 FREEZE冻结 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the 状态 NORMAL正常 CANCEL注销 FREEZE冻结 .
	 *
	 * @param status the new 状态 NORMAL正常 CANCEL注销 FREEZE冻结 
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the 证件类型 .
	 *
	 * @return the 证件类型 
	 */
	public String getCertTypeCode() {
		return certTypeCode;
	}

	/**
	 * Sets the 证件类型 .
	 *
	 * @param certTypeCode the new 证件类型 
	 */
	public void setCertTypeCode(String certTypeCode) {
		this.certTypeCode = certTypeCode;
	}

	/**
	 * Gets the 证件号码 .
	 *
	 * @return the 证件号码 
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * Sets the 证件号码 .
	 *
	 * @param certNo the new 证件号码 
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	/**
	 * Gets the 手机号 .
	 *
	 * @return the 手机号 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the 手机号 .
	 *
	 * @param mobile the new 手机号 
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the 手机串号 .
	 *
	 * @return the 手机串号 
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * Sets the 手机串号 .
	 *
	 * @param imei the new 手机串号 
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * Gets the 邮箱 .
	 *
	 * @return the 邮箱 
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the 邮箱 .
	 *
	 * @param email the new 邮箱 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the 职业 .
	 *
	 * @return the 职业 
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Sets the 职业 .
	 *
	 * @param job the new 职业 
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * Gets the 住址 .
	 *
	 * @return the 住址 
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the 住址 .
	 *
	 * @param address the new 住址 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the 年龄 .
	 *
	 * @return the 年龄 
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the 年龄 .
	 *
	 * @param age the new 年龄 
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Gets the 性别: 男：male 女：female 未知：unknown .
	 *
	 * @return the 性别: 男：male 女：female 未知：unknown 
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the 性别: 男：male 女：female 未知：unknown .
	 *
	 * @param sex the new 性别: 男：male 女：female 未知：unknown 
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the 实名认证标识 N未实名认证  Y实名认证 .
	 *
	 * @return the 实名认证标识 N未实名认证  Y实名认证 
	 */
	public String getNameAuthFlag() {
		return nameAuthFlag;
	}

	/**
	 * Sets the 实名认证标识 N未实名认证  Y实名认证 .
	 *
	 * @param nameAuthFlag the new 实名认证标识 N未实名认证  Y实名认证 
	 */
	public void setNameAuthFlag(String nameAuthFlag) {
		this.nameAuthFlag = nameAuthFlag;
	}

	/**
	 * Gets the 登录密码 .
	 *
	 * @return the 登录密码 
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Sets the 登录密码 .
	 *
	 * @param pwd the new 登录密码 
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Gets the 加密机CODE .
	 *
	 * @return the 加密机CODE 
	 */
	public String getEncryptionCode() {
		return encryptionCode;
	}

	/**
	 * Sets the 加密机CODE .
	 *
	 * @param encryptionCode the new 加密机CODE 
	 */
	public void setEncryptionCode(String encryptionCode) {
		this.encryptionCode = encryptionCode;
	}

	/**
	 * Gets the 客户来源 .
	 *
	 * @return the 客户来源 
	 */
	public String getMemberSrc() {
		return memberSrc;
	}

	/**
	 * Sets the 客户来源 .
	 *
	 * @param memberSrc the new 客户来源 
	 */
	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc;
	}

	/**
	 * Gets the 微信公众号OPENID .
	 *
	 * @return the 微信公众号OPENID 
	 */
	public String getOpenIdGzhWx() {
		return openIdGzhWx;
	}

	/**
	 * Sets the 微信公众号OPENID .
	 *
	 * @param openIdGzhWx the new 微信公众号OPENID 
	 */
	public void setOpenIdGzhWx(String openIdGzhWx) {
		this.openIdGzhWx = openIdGzhWx;
	}

	/**
	 * Gets the 微信小程序OPENID .
	 *
	 * @return the 微信小程序OPENID 
	 */
	public String getOpenIdXcxWx() {
		return openIdXcxWx;
	}

	/**
	 * Sets the 微信小程序OPENID .
	 *
	 * @param openIdXcxWx the new 微信小程序OPENID 
	 */
	public void setOpenIdXcxWx(String openIdXcxWx) {
		this.openIdXcxWx = openIdXcxWx;
	}

	/**
	 * Gets the 微信号 .
	 *
	 * @return the 微信号 
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * Sets the 微信号 .
	 *
	 * @param noWx the new 微信号 
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * Gets the 昵称_微信 .
	 *
	 * @return the 昵称_微信 
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * Sets the 昵称_微信 .
	 *
	 * @param nickNameWx the new 昵称_微信 
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * Gets the 城市_微信 .
	 *
	 * @return the 城市_微信 
	 */
	public String getCityWx() {
		return cityWx;
	}

	/**
	 * Sets the 城市_微信 .
	 *
	 * @param cityWx the new 城市_微信 
	 */
	public void setCityWx(String cityWx) {
		this.cityWx = cityWx;
	}

	/**
	 * Gets the 国家 .
	 *
	 * @return the 国家 
	 */
	public String getCountryWx() {
		return countryWx;
	}

	/**
	 * Sets the 国家 .
	 *
	 * @param countryWx the new 国家 
	 */
	public void setCountryWx(String countryWx) {
		this.countryWx = countryWx;
	}

	/**
	 * Gets the 省_微信 .
	 *
	 * @return the 省_微信 
	 */
	public String getProvinceWx() {
		return provinceWx;
	}

	/**
	 * Sets the 省_微信 .
	 *
	 * @param provinceWx the new 省_微信 
	 */
	public void setProvinceWx(String provinceWx) {
		this.provinceWx = provinceWx;
	}

	/**
	 * Gets the 头像地址 .
	 *
	 * @return the 头像地址 
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the 头像地址 .
	 *
	 * @param headAddress the new 头像地址 
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * Gets the 关注时间 .
	 *
	 * @return the 关注时间 
	 */
	public Date getSubsribeTime() {
		return subsribeTime;
	}

	/**
	 * Sets the 关注时间 .
	 *
	 * @param subsribeTime the new 关注时间 
	 */
	public void setSubsribeTime(Date subsribeTime) {
		this.subsribeTime = subsribeTime;
	}

	/**
	 * Gets the 家庭组CODE .
	 *
	 * @return the 家庭组CODE 
	 */
	public String getFamilyCode() {
		return familyCode;
	}

	/**
	 * Sets the 家庭组CODE .
	 *
	 * @param familyCode the new 家庭组CODE 
	 */
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}

	/**
	 * Gets the 客户家庭组名称 .
	 *
	 * @return the 客户家庭组名称 
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Sets the 客户家庭组名称 .
	 *
	 * @param familyName the new 客户家庭组名称 
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * Gets the 兴趣 .
	 *
	 * @return the 兴趣 
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * Sets the 兴趣 .
	 *
	 * @param interest the new 兴趣 
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * Gets the 星座.
	 *
	 * @return the constellation
	 */
	public String getConstellation() {
		return constellation;
	}

	/**
	 * Sets the 星座.
	 *
	 * @param constellation the constellation to set
	 */
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	/**
	 * Gets the 区域CODE .
	 *
	 * @return the 区域CODE 
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the 区域CODE .
	 *
	 * @param areaCode the new 区域CODE 
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * Gets the 区域名称 .
	 *
	 * @return the 区域名称 
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the 区域名称 .
	 *
	 * @param areaName the new 区域名称 
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * Gets the 省CODE .
	 *
	 * @return the 省CODE 
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * Sets the 省CODE .
	 *
	 * @param provinceCode the new 省CODE 
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * Gets the 市CODE .
	 *
	 * @return the 市CODE 
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * Sets the 市CODE .
	 *
	 * @param cityCode the new 市CODE 
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * Gets the 市区CODE .
	 *
	 * @return the 市区CODE 
	 */
	public String getCityAreaCode() {
		return cityAreaCode;
	}

	/**
	 * Sets the 市区CODE .
	 *
	 * @param cityAreaCode the new 市区CODE 
	 */
	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}

	/**
	 * Gets the 出生日期 .
	 *
	 * @return the 出生日期 
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Sets the 出生日期 .
	 *
	 * @param birthday the new 出生日期 
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the 资料完善度 .
	 *
	 * @return the 资料完善度 
	 */
	public Double getRatioClientInfo() {
		return ratioClientInfo;
	}

	/**
	 * Sets the 资料完善度 .
	 *
	 * @param ratioClientInfo the new 资料完善度 
	 */
	public void setRatioClientInfo(Double ratioClientInfo) {
		this.ratioClientInfo = ratioClientInfo;
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
	 * Gets the 更新人 .
	 *
	 * @return the 更新人 
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * Sets the 更新人 .
	 *
	 * @param updateId the new 更新人 
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * Gets the 更新时间 .
	 *
	 * @return the 更新时间 
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the 更新时间 .
	 *
	 * @param updateDate the new 更新时间 
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	/**
	 * Gets the 微信号别名 .
	 *
	 * @return the 微信号别名 
	 */
	public String getNoWxAlias() {
		return noWxAlias;
	}

	/**
	 * Sets the 微信号别名 .
	 *
	 * @param noWxAlias the new 微信号别名 
	 */
	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
	}

	/**
	 * @return the noQQ
	 */
	public String getNoQQ() {
		return noQQ;
	}

	/**
	 * @param noQQ the noQQ to set
	 */
	public void setNoQQ(String noQQ) {
		this.noQQ = noQQ;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdatePersonMemberBase [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", certTypeCode=");
		builder.append(certTypeCode);
		builder.append(", certNo=");
		builder.append(certNo);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", email=");
		builder.append(email);
		builder.append(", job=");
		builder.append(job);
		builder.append(", address=");
		builder.append(address);
		builder.append(", age=");
		builder.append(age);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", nameAuthFlag=");
		builder.append(nameAuthFlag);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", encryptionCode=");
		builder.append(encryptionCode);
		builder.append(", memberSrc=");
		builder.append(memberSrc);
		builder.append(", openIdGzhWx=");
		builder.append(openIdGzhWx);
		builder.append(", openIdXcxWx=");
		builder.append(openIdXcxWx);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxAlias=");
		builder.append(noWxAlias);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", cityWx=");
		builder.append(cityWx);
		builder.append(", countryWx=");
		builder.append(countryWx);
		builder.append(", provinceWx=");
		builder.append(provinceWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", subsribeTime=");
		builder.append(subsribeTime);
		builder.append(", familyCode=");
		builder.append(familyCode);
		builder.append(", familyName=");
		builder.append(familyName);
		builder.append(", interest=");
		builder.append(interest);
		builder.append(", constellation=");
		builder.append(constellation);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityAreaCode=");
		builder.append(cityAreaCode);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", ratioClientInfo=");
		builder.append(ratioClientInfo);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", wxOpenId=");
		builder.append(wxOpenId);
		builder.append(", noQQ=");
		builder.append(noQQ);
		builder.append("]");
		return builder.toString();
	}
}
