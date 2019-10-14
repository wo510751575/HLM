/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.dto;

import java.io.Serializable;

/**
 * 
 * 类说明：通过客户微信添加客户信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月25日
 */
public class AddPersonMemberByWxReturn implements Serializable {

	private static final long serialVersionUID = -4287325673889833023L;

	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 昵称备注_本地
	 */
	private String nickNameRemarkLocal;
	
	/**
	 * 客户手机
	 */
	private String mobile;
	
	/**
	 * 客户版本号，即创建时间戳
	 */
	private long version;
	/**
	 * 客户分类相关信息
	 * DZP 2018-12-14
	 */
	private String codePm;
	private String pmTypeCode;
	private String pmTypeName;
	
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
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddPersonMemberByWxReturn [memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", version=");
		builder.append(version);
		builder.append(", codePm=");
		builder.append(codePm);
		builder.append(", pmTypeCode=");
		builder.append(pmTypeCode);
		builder.append(", pmTypeName=");
		builder.append(pmTypeName);
		builder.append("]");
		return builder.toString();
	}
}
