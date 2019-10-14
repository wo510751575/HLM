/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.addfriends;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月23日
 */
public class FindClaimMemberPageReturn extends PageParamEntity {

	private static final long serialVersionUID = -1254073576756796414L;
	
	/**
     * 认领CODE .
     */
    private String mbrCode;
	
	/**
	 * 认领标识：true已认领，false未认领
	 */
	private Boolean flag;
	
    /**
     * 客户编号 .
     */
    private String memberNo;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 客户微信号 .
     */
    private String noWx;
    
    /**
     * 客户微信别名 .
     */
    private String alias;
    
    /**
     * 头像地址 .
     */
    private String headAddress;
    
	/**
	 * 微信昵称
	 */
	private String nickNameWx;
	
	/**
	 * 微信昵称备注
	 */
	private String nickNameRemarkWx;
    
    /**
     * 性别:
             男：MALE
             女：FEMALE
             未知：UNKNOWN .
     */
    private String sex;

    /**
     * 客户添加时间 .
     */
    private Long createTime;
    
    /**
     * 客户认领时间 .
     */
    private Long claimTime;
    /**
     * 客户code pm表
     */
    private String pmCode;
    
    private String validation;
    
	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getPmCode() {
		return pmCode;
	}

	public void setPmCode(String pmCode) {
		this.pmCode = pmCode;
	}

	/**
	 * @return the mbrCode
	 */
	public String getMbrCode() {
		return mbrCode;
	}

	/**
	 * @param mbrCode the mbrCode to set
	 */
	public void setMbrCode(String mbrCode) {
		this.mbrCode = mbrCode;
	}

	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the memberNo
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * @return the memberNameGm
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * @param memberNameGm the memberNameGm to set
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * @return the noWx
	 */
	public String getNoWx() {
		return noWx;
	}

	/**
	 * @param noWx the noWx to set
	 */
	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the headAddress
	 */
	public String getHeadAddress() {
		return headAddress;
	}

	/**
	 * @param headAddress the headAddress to set
	 */
	public void setHeadAddress(String headAddress) {
		this.headAddress = headAddress;
	}

	/**
	 * @return the nickNameWx
	 */
	public String getNickNameWx() {
		return nickNameWx;
	}

	/**
	 * @param nickNameWx the nickNameWx to set
	 */
	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	/**
	 * @return the nickNameRemarkWx
	 */
	public String getNickNameRemarkWx() {
		return nickNameRemarkWx;
	}

	/**
	 * @param nickNameRemarkWx the nickNameRemarkWx to set
	 */
	public void setNickNameRemarkWx(String nickNameRemarkWx) {
		this.nickNameRemarkWx = nickNameRemarkWx;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the claimTime
	 */
	public Long getClaimTime() {
		return claimTime;
	}

	/**
	 * @param claimTime the claimTime to set
	 */
	public void setClaimTime(Long claimTime) {
		this.claimTime = claimTime;
	}

	@Override
	public String toString() {
		return "FindClaimMemberPageReturn [mbrCode=" + mbrCode + ", flag=" + flag + ", memberNo=" + memberNo
				+ ", memberNoGm=" + memberNoGm + ", memberNameGm=" + memberNameGm + ", noWx=" + noWx + ", alias="
				+ alias + ", headAddress=" + headAddress + ", nickNameWx=" + nickNameWx + ", nickNameRemarkWx="
				+ nickNameRemarkWx + ", sex=" + sex + ", createTime=" + createTime + ", claimTime=" + claimTime
				+ ", pmCode=" + pmCode + ", validation=" + validation + "]";
	}
}
