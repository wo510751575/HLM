package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindGuidMemberPageReturn.
 */
public class FindGuidMemberPageReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3313387943607085558L;

	/**
	 * CODE .
	 */
	private String code;

	/**
	 * 会员号 .
	 */
	private String memberNo;

	/**
	 * 导购姓名 .
	 */
	private String memberName;

	/**
	 * 职位
	 */
	private String memberType;

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
	 * 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结 .
	 */
	private String status;

	/**
	 * 工号 .
	 */
	private String jobNum;

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
	 * 区域名称.
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
	 * 头像地址 .
	 */
	private String headAddress;

	/**
	 * 入职时间 .
	 */
	private Date workDate;

	/** 二维码. */
	private String qcord;

	/**
	 * 创建时间 .
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	/** 微信号. */
	private String noWx;
	/** 个人微信号. */
	private String noWxPersonal;
	/**
	 * 终端代码
	 */
	private String shopNoMerchant;
	/**
	 * 加密机code
	 */
	private String encryptionCode;

	/**
	 * 更新人
	 */
	private String updateId;
	/**
	 * 创建人
	 */
	private String createId;
	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 当前页数
	 */
	private Integer pageNo;

	private String loginName;

	/**
	 * 分享次数
	 */
	private Integer shareNum;

	/**
	 * 分享日期
	 */
	private Date shareDate;
	/**
	 * 广告显示次数
	 */
	private Integer adShowNum;
	/**
	 * 广告显示时间
	 */
	private Date adShowDate;

	/**
	 * 显示量
	 */
	private Integer showNum;

	private Date showDate;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getNoWxPersonal() {
		return noWxPersonal;
	}

	public void setNoWxPersonal(String noWxPersonal) {
		this.noWxPersonal = noWxPersonal;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getEncryptionCode() {
		return encryptionCode;
	}

	public void setEncryptionCode(String encryptionCode) {
		this.encryptionCode = encryptionCode;
	}

	/**
	 * @return the shopNoMerchant
	 */
	public String getShopNoMerchant() {
		return shopNoMerchant;
	}

	/**
	 * @param shopNoMerchant the shopNoMerchant to set
	 */
	public void setShopNoMerchant(String shopNoMerchant) {
		this.shopNoMerchant = shopNoMerchant;
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
	 * Gets the 会员号 .
	 *
	 * @return the 会员号
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 会员号 .
	 *
	 * @param memberNo the new 会员号
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberName the new 导购姓名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 商户名称 .
	 *
	 * @return the 商户名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the new 商户名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * Gets the 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结 .
	 *
	 * @return the 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结 .
	 *
	 * @param status the new 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the 工号 .
	 *
	 * @return the 工号
	 */
	public String getJobNum() {
		return jobNum;
	}

	/**
	 * Sets the 工号 .
	 *
	 * @param jobNum the new 工号
	 */
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
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
	 * Gets the 昵称 .
	 *
	 * @return the 昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the 昵称 .
	 *
	 * @param nickName the new 昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * Gets the 区域名称.
	 *
	 * @return the 区域名称
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the 区域名称.
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
	 * Gets the 性别:male,female .
	 *
	 * @return the 性别:male,female
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the 性别:male,female .
	 *
	 * @param gender the new 性别:male,female
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * Gets the 入职时间 .
	 *
	 * @return the 入职时间
	 */
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * Sets the 入职时间 .
	 *
	 * @param workDate the new 入职时间
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	/**
	 * Gets the 二维码.
	 *
	 * @return the 二维码
	 */
	public String getQcord() {
		return qcord;
	}

	/**
	 * Sets the 二维码.
	 *
	 * @param qcord the new 二维码
	 */
	public void setQcord(String qcord) {
		this.qcord = qcord;
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
	 * Gets the 微信号.
	 *
	 * @return the 微信号
	 */
	public String getNoWx() {
		return noWx;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	/**
	 * Sets the 微信号.
	 *
	 * @param noWx the new 微信号
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * 更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGuidMemberPageReturn [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberType=");
		builder.append(memberType);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", jobNum=");
		builder.append(jobNum);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nickName=");
		builder.append(nickName);
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
		builder.append(", address=");
		builder.append(address);
		builder.append(", age=");
		builder.append(age);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxPersonal=");
		builder.append(noWxPersonal);
		builder.append(", shopNoMerchant=");
		builder.append(shopNoMerchant);
		builder.append(", encryptionCode=");
		builder.append(encryptionCode);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append("]");
		return builder.toString();
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public Integer getAdShowNum() {
		return adShowNum;
	}

	public void setAdShowNum(Integer adShowNum) {
		this.adShowNum = adShowNum;
	}

	public Date getAdShowDate() {
		return adShowDate;
	}

	public void setAdShowDate(Date adShowDate) {
		this.adShowDate = adShowDate;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

}
