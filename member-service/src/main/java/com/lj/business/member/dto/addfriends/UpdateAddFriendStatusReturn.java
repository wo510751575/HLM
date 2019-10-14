package com.lj.business.member.dto.addfriends;

import java.io.Serializable;

public class UpdateAddFriendStatusReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5307083527436915308L; 
	
	/**
	 * 导购编号
	 */
	private String memberNoGm;

	/**
	 * 导购微信号
	 */
	private String noWxGm;

	/**
	 * 客户编号
	 */
	private String memberNo;
	
	/**
	 * 客户名称
	 */
	private String memberName;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 昵称备注_本地
	 */
	private String nickNameRemarkLocal;
	
	/**
	 * 客户版本号
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
	 * @return the noWxGm
	 */
	public String getNoWxGm() {
		return noWxGm;
	}

	/**
	 * @param noWxGm the noWxGm to set
	 */
	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateAddFriendStatusReturn [memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", noWxGm=");
		builder.append(noWxGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", nickNameRemarkLocal=");
		builder.append(nickNameRemarkLocal);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
}
