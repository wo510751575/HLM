package com.lj.business.member.dto.guidCard;

import java.io.Serializable;

public class FindGuidCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1907354995629688809L; 

	/**
	 * code .
	 */
	private String code;
	
	/**
     * 导购编号 .
     */
    private String memberNoGm;
    
    /**
     * 导购编号
     */
    private String noWxGm;
    

	/**
     * openId签名 .
     */
    private String signature;
    
    
    
    public String getNoWxGm() {
		return noWxGm;
	}

	public void setNoWxGm(String noWxGm) {
		this.noWxGm = noWxGm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "FindGuidCard [code=" + code + ", memberNoGm=" + memberNoGm
				+ ", signature=" + signature + "]";
	}

}
