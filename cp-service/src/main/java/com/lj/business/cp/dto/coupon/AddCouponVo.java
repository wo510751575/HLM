package com.lj.business.cp.dto.coupon;

public class AddCouponVo implements java.io.Serializable {
	private static final long serialVersionUID = 8342646182723663604L;
	// 商户号
	private String merchantNo;
	// 商户名称
	private String merchantName;
	// 分店号
	
	// 分店名称
	
	// 导购编号
	private String memberNoGm;
	// 导购名称
	private String memberNameGm;
	// 用户编号
	private String memberNo;
	// 用户名称
	private String memberName;
	// 规则编号
	private String ruleNo;
	// 微信号
	private String noWx;
	// 微信昵称
	private String nickNameWx;

	public String getNickNameWx() {
		return nickNameWx;
	}

	public void setNickNameWx(String nickNameWx) {
		this.nickNameWx = nickNameWx;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public String getMemberNoGm() {
		return memberNoGm;
	}

	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	public String getMemberNameGm() {
		return memberNameGm;
	}

	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getNoWx() {
		return noWx;
	}

	public void setNoWx(String noWx) {
		this.noWx = noWx;
	}

}
