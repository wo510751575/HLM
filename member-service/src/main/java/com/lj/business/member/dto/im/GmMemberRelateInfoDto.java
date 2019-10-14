package com.lj.business.member.dto.im;

import java.io.Serializable;

/**
 * @author: dengxiudong
 * @title: GmMemberRelateInfoDto
 * @description: 导购和客户关联信息
 **/
public class GmMemberRelateInfoDto implements Serializable {

	private static final long serialVersionUID = 876700462188846513L;

	/**
     * CODE .
     */
    private String code;

	/**
	 * 导购编号  .
	 */
	private String memberNoGm;

    /**
     * 会员号  .
     */
    private String memberNo;

	/**
	 * 导购姓名 .
	 */
	private String memberNameGm;

    /**
     * 会员名称 .
     */
    private String memberName;

	/**
	 * 导购微信
	 */
	private String gmWx;
    
    /**
     * 客户微信
     */
    private String noWx;
    
    /**
     * 客户微信别名
     */
    private String noWxAlias;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

	/**
	 * @return the gmWx
	 */
	public String getGmWx() {
		return gmWx;
	}

	/**
	 * @param gmWx the gmWx to set
	 */
	public void setGmWx(String gmWx) {
		this.gmWx = gmWx;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the noWxAlias
	 */
	public String getNoWxAlias() {
		return noWxAlias;
	}

	/**
	 * @param noWxAlias the noWxAlias to set
	 */
	public void setNoWxAlias(String noWxAlias) {
		this.noWxAlias = noWxAlias;
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
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindPersonMemberByChatReturn [code=");
		builder.append(code);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", gmWx=");
		builder.append(gmWx);
		builder.append(", noWx=");
		builder.append(noWx);
		builder.append(", noWxAlias=");
		builder.append(noWxAlias);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", merchantName=");
		builder.append(merchantName);
		builder.append("]");
		return builder.toString();
	}
}
