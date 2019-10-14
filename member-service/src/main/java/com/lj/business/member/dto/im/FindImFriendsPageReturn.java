/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto.im;

import java.io.Serializable;

/**
 * 
 * 类说明：分页查询IM微信好友返回明细记录参数
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月28日
 */
public class FindImFriendsPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2314832129628345083L;

	/**
	 * pm表code
	 */
	private String codePm;
	/**
	 * 导购编号
	 */
	private String memberNoGm;
	
	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 客户微信号
	 */
	private String noWx;
	
	/**
	 * 客户微信别名
	 */
	private String alias;
	
	/**
	 * 昵称备注_微信
	 */
	private String nickNameRemarkWx;
	
	/**
	 * 昵称备注_本地
	 */
	private String nickNameRemarkLocal;
	
	/**
	 * 昵称_微信
	 */
	private String nickNameWx;
	
	/**
	 * 微信头像地址
	 */
	private String headAddress;
	
	/**
	 * 性别 男：male 女：female 未知：unknown
	 * 
	 */
	private String sex;
	
	/**
	 * 客户手机号
	 */
	private String mobile;
	
	/**
	 * 创建时间，即客户注册时间
	 */
	private Long createTime;
	
	/**
	 * 版本号
	 */
	private Long version;
	/**
     * 客户分类CODE .
     */
    private String pmTypeCode;
    
    /**
     * 客户分类名称 .
     */
    private String pmTypeName;
    
    /**
     * 门店微信
     */
    private String shopWx;
    
	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public String getCodePm() {
		return codePm;
	}

	public void setCodePm(String codePm) {
		this.codePm = codePm;
	}

	public String getPmTypeCode() {
		return pmTypeCode;
	}

	public void setPmTypeCode(String pmTypeCode) {
		this.pmTypeCode = pmTypeCode;
	}

	public String getPmTypeName() {
		return pmTypeName;
	}

	public void setPmTypeName(String pmTypeName) {
		this.pmTypeName = pmTypeName;
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
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	 * @return the nickNameRemarkLocal
	 */
	public String getNickNameRemarkLocal() {
		return nickNameRemarkLocal;
	}

	/**
	 * @param nickNameRemarkLocal the nickNameRemarkLocal to set
	 */
	public void setNickNameRemarkLocal(String nickNameRemarkLocal) {
		this.nickNameRemarkLocal = nickNameRemarkLocal;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindImFriendsPageReturn [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", alias=");
		builder.append(alias);
		builder.append(", nickNameRemarkWx=");
		builder.append(nickNameRemarkWx);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", nickNameWx=");
		builder.append(nickNameWx);
		builder.append(", headAddress=");
		builder.append(headAddress);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", version=");
		builder.append(version);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", pmTypeName=");
		builder.append(pmTypeName);
		builder.append("]");
		return builder.toString();
	}

}
