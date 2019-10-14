package com.lj.business.member.dto;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FindGuidMemberReturn.
 */
public class FindGuidMemberReturn implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8866570532137567179L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 会员号  .
     */
    private String memberNo;

    /**
     * 导购姓名 .
     */
    private String memberName;


    
    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 状态 
              INIT初始(未审核) 、
             UNPASS(审核未通过)、
             NORMAL正常、
             CANCEL注销。
             FREEZE冻结
              .
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
     * 邮箱 .
     */
    private String email;

    /**
     * 昵称 .
     */
    private String nickName;

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
     * 登录密码 .
     */
    private String pwd;

    /**
     * 加密机CODE .
     */
    private String encryptionCode;

    /**
     * 头像地址 .
     */
    private String headAddress;

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
    
    /** 二维码. */
    private String qcord;
    
    /** 微信号. */
    private String noWx;
    
    private String loginName;


	/**
     * 个人微信号 .
     */
    private String noWxPersonal;
    
    /** 区域code. */
    private String areaCode;
    
    /** 区域名称. */
    private String areaName;
    
	/**
	 * Gets the 二维码.
	 *
	 * @return the qcord
	 */
	public String getQcord() {
		return qcord;
	}

	/**
	 * Sets the 二维码.
	 *
	 * @param qcord the qcord to set
	 */
	public void setQcord(String qcord) {
		this.qcord = qcord;
	}

	/**
	 * Gets the 微信号.
	 *
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * Sets the 微信号.
	 *
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the noWxPersonal
	 */
	public String getNoWxPersonal() {
		return noWxPersonal;
	}

	/**
	 * @param noWxPersonal the noWxPersonal to set
	 */
	public void setNoWxPersonal(String noWxPersonal) {
		this.noWxPersonal = noWxPersonal;
	}

	/**
	 * Gets the cODE .
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 会员号  .
	 *
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 会员号  .
	 *
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 商户名称 .
	 *
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * Gets the 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结 .
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the 状态 INIT初始(未审核) 、 UNPASS(审核未通过)、 NORMAL正常、 CANCEL注销。 FREEZE冻结 .
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the 工号 .
	 *
	 * @return the jobNum
	 */
	public String getJobNum() {
		return jobNum;
	}

	/**
	 * Sets the 工号 .
	 *
	 * @param jobNum the jobNum to set
	 */
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	/**
	 * Gets the 手机号 .
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the 手机号 .
	 *
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the 邮箱 .
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the 邮箱 .
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the 昵称 .
	 *
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the 昵称 .
	 *
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Gets the 住址 .
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the 住址 .
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the 年龄 .
	 *
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the 年龄 .
	 *
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Gets the 性别:male,female .
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the 性别:male,female .
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the 登录密码 .
	 *
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Sets the 登录密码 .
	 *
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Gets the 加密机CODE .
	 *
	 * @return the encryptionCode
	 */
	public String getEncryptionCode() {
		return encryptionCode;
	}

	/**
	 * Sets the 加密机CODE .
	 *
	 * @param encryptionCode the encryptionCode to set
	 */
	public void setEncryptionCode(String encryptionCode) {
		this.encryptionCode = encryptionCode;
	}

	/**
	 * Gets the 头像地址 .
	 *
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * Sets the 头像地址 .
	 *
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * Gets the 创建人 .
	 *
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * Sets the 创建人 .
	 *
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the 更新人 .
	 *
	 * @return the updateId
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * Sets the 更新人 .
	 *
	 * @param updateId the updateId to set
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * Gets the 更新时间 .
	 *
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the 更新时间 .
	 *
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Gets the area code.
	 *
	 * @return the area code
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the area code.
	 *
	 * @param areaCode the new area code
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
	
    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindGuidMemberReturn [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
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
		builder.append(", email=");
		builder.append(email);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", age=");
		builder.append(age);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", encryptionCode=");
		builder.append(encryptionCode);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", qcord=");
		builder.append(qcord);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxPersonal=");
		builder.append(noWxPersonal);
		builder.append(", areaCode=");
		builder.append(areaCode);
		builder.append(", areaName=");
		builder.append(areaName);
		builder.append("]");
		return builder.toString();
	}
	
}
