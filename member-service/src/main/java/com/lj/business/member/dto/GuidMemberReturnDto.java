package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class GuidMemberReturnDto.
 */
public class GuidMemberReturnDto implements Serializable{

	 /** Generate cron. */
	private static final long serialVersionUID = -1355694040733665148L;
	/**
     * CODE .
     */
    private String code;
    /**
     * 导购编号 .
     */
    private String memberNo;

    /**
     * 会员姓名 .
     */
    private String memberName;

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
     * 手机号 .
     */
    private String mobile;
    
    /**
     * 邮箱 .
     */
    private String email;
    /**
     * 区域CODE .
     */
    private String areaCode;

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
     * 头像地址 .
     */
    private String headAddress;
    /**
     * 性别 .
     */
    private String gender;
    /**
     * 入职时间 .
     */
    private Date workDate;
    
    /**
     * 微信号 .
     */
    private String noWx;

    /**
     * 个人微信号 .
     */
    private String noWxPersonal;
    /**
     * 手机串号
     */
    private String imei;
    /**
     * 年龄
     */
    private Integer age;
    
    private String status;
    /**
     * 二维码地址
     */
    private String qcord;
    
    /**
     * 条目数
     */
    private Integer pageNo;
    
    /**
     * 登录名
     */
    private String loginName;
    
    
    
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

	public String getQcord() {
		return qcord;
	}

	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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
	 * Gets the 导购编号 .
	 *
	 * @return the 导购编号 
	 */
	public String getMemberNo() {
		return memberNo;
	}
	
	/**
	 * Sets the 导购编号 .
	 *
	 * @param memberNo the new 导购编号 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	/**
	 * Gets the 会员姓名 .
	 *
	 * @return the 会员姓名 
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * Sets the 会员姓名 .
	 *
	 * @param memberName the new 会员姓名 
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
	 * Gets the 性别 .
	 *
	 * @return the 性别 
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the 性别 .
	 *
	 * @param gender the new 性别 
	 */
	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuidMemberReturnDto [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", provinceCode=");
		builder.append(provinceCode);
		builder.append(", cityCode=");
		builder.append(cityCode);
		builder.append(", cityAreaCode=");
		builder.append(cityAreaCode);
		builder.append(", address=");
		builder.append(address);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", workDate=");
		builder.append(workDate);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxPersonal=");
		builder.append(noWxPersonal);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", age=");
		builder.append(age);
		builder.append(", status=");
		builder.append(status);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append("]");
		return builder.toString();
	}
    
}
