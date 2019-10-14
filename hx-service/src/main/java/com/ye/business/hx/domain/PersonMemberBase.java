package com.ye.business.hx.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PersonMemberBase {
	/** CODE */
	private String code;

	/** 客户编号 */
	private String memberNo;

	/** 客户名称 */
	private String memberName;

	/**
	 * 状态 NORMAL正常 CANCEL注销 FREEZE冻结
	 */
	private String status;

	/** 证件类型 */
	private String certTypeCode;

	/** 证件号码 */
	private String certNo;

	/** 手机号 */
	private String mobile;

	/** 手机串号 */
	private String imei;

	/** 邮箱 */
	private String email;

	/** 职业 */
	private String job;

	/** 住址 */
	private String address;

	/** 年龄 */
	private Integer age;

	/**
	 * 男：MALE 女：FEMALE 未知：UNKNOWN
	 */
	private String sex;

	/** 实名认证标识 N未实名认证 Y实名认证 */
	private String nameAuthFlag;

	/** 登录密码 */
	private String pwd;

	/** 加密机CODE */
	private String encryptionCode;

	/** 客户来源 */
	private String memberSrc;

	/** 微信公众号OPENID */
	private String openIdGzhWx;

	/** 微信小程序OPENID */
	private String openIdXcxWx;

	/** 微信号 */
	private String noWx;

	/** 微信号别名 */
	private String noWxAlias;

	/** 微信OPENID */
	private String wxOpenid;

	/** QQ号 */
	private String noQq;

	/** 旺旺账号 */
	private String noWw;

	/** 昵称_微信 */
	private String nickNameWx;

	/** 城市_微信 */
	private String cityWx;

	/** 国家 */
	private String countryWx;

	/** 省_微信 */
	private String provinceWx;

	/** 头像地址 */
	private String headAddress;

	/** 关注时间 */
	private Date subsribeTime;

	/** 家庭组CODE */
	private String familyCode;

	/** 客户家庭组名称 */
	private String familyName;

	/** 兴趣 */
	private String interest;

	/** 星座 */
	private String constellation;

	/** 区域CODE */
	private String areaCode;

	/** 区域名称 */
	private String areaName;

	/** 省CODE */
	private String provinceCode;

	/** 市CODE */
	private String cityCode;

	/** 市区CODE */
	private String cityAreaCode;

	/** 出生日期 */
	private Date birthday;

	/** 资料完善度 */
	private BigDecimal ratioClientInfo;

	/** 设置置顶的用户1置顶，0不置顶 */
	private String setUpUser;

	/** 创建人 */
	private String createId;

	/** 创建时间 */
	private Date createDate;

	/** 更新人 */
	private String updateId;

	/** 更新时间 */
	private Date updateDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo == null ? null : memberNo.trim();
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName == null ? null : memberName.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCertTypeCode() {
		return certTypeCode;
	}

	public void setCertTypeCode(String certTypeCode) {
		this.certTypeCode = certTypeCode == null ? null : certTypeCode.trim();
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo == null ? null : certNo.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei == null ? null : imei.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job == null ? null : job.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getNameAuthFlag() {
		return nameAuthFlag;
	}

	public void setNameAuthFlag(String nameAuthFlag) {
		this.nameAuthFlag = nameAuthFlag == null ? null : nameAuthFlag.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getEncryptionCode() {
		return encryptionCode;
	}

	public void setEncryptionCode(String encryptionCode) {
		this.encryptionCode = encryptionCode == null ? null : encryptionCode.trim();
	}

	public String getMemberSrc() {
		return memberSrc;
	}

	public void setMemberSrc(String memberSrc) {
		this.memberSrc = memberSrc == null ? null : memberSrc.trim();
	}

	public String getOpenIdGzhWx() {
		return openIdGzhWx;
	}

	public void setOpenIdGzhWx(String openIdGzhWx) {
		this.openIdGzhWx = openIdGzhWx == null ? null : openIdGzhWx.trim();
	}

	public String getOpenIdXcxWx() {
		return openIdXcxWx;
	}

	public void setOpenIdXcxWx(String openIdXcxWx) {
		this.openIdXcxWx = openIdXcxWx == null ? null : openIdXcxWx.trim();
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx == null ? null : noWx.trim();
	}

	public String getNoWxAlias() {
		return noWxAlias;
	}

	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias == null ? null : noWxAlias.trim();
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
	}

	public String getNoQq() {
		return noQq;
	}

	public void setNoQq(String noQq) {
		this.noQq = noQq == null ? null : noQq.trim();
	}

	public String getNoWw() {
		return noWw;
	}

	public void setNoWw(String noWw) {
		this.noWw = noWw == null ? null : noWw.trim();
	}

	public String getNickNameWx() {
		return nickNameWx;
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx == null ? null : nickNameWx.trim();
	}

	public String getCityWx() {
		return cityWx;
	}

	public void setCityWx(String cityWx) {
		this.cityWx = cityWx == null ? null : cityWx.trim();
	}

	public String getCountryWx() {
		return countryWx;
	}

	public void setCountryWx(String countryWx) {
		this.countryWx = countryWx == null ? null : countryWx.trim();
	}

	public String getProvinceWx() {
		return provinceWx;
	}

	public void setProvinceWx(String provinceWx) {
		this.provinceWx = provinceWx == null ? null : provinceWx.trim();
	}

	public String getHeadAddress() {
		return headAddress;
	}

	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress == null ? null : headAddress.trim();
	}

	public Date getSubsribeTime() {
		return subsribeTime;
	}

	public void setSubsribeTime(Date subsribeTime) {
		this.subsribeTime = subsribeTime;
	}

	public String getFamilyCode() {
		return familyCode;
	}

	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode == null ? null : familyCode.trim();
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName == null ? null : familyName.trim();
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest == null ? null : interest.trim();
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation == null ? null : constellation.trim();
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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode == null ? null : provinceCode.trim();
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode == null ? null : cityCode.trim();
	}

	public String getCityAreaCode() {
		return cityAreaCode;
	}

	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode == null ? null : cityAreaCode.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getRatioClientInfo() {
		return ratioClientInfo;
	}

	public void setRatioClientInfo(BigDecimal ratioClientInfo) {
		this.ratioClientInfo = ratioClientInfo;
	}

	public String getSetUpUser() {
		return setUpUser;
	}

	public void setSetUpUser(String setUpUser) {
		this.setUpUser = setUpUser == null ? null : setUpUser.trim();
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId == null ? null : createId.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId == null ? null : updateId.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}