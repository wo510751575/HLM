package com.lj.business.cp.couponRecords;

import java.io.Serializable;
import java.util.Date;

public class FindCouponRecordsPageReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925162074627900904L; 
	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 分店编号 .
     */
    

    /**
     * 分店名称 .
     */
    

    /**
     * 用户编号 .
     */
    private String memberNo;

    /**
     * 用户姓名 .
     */
    private String memberName;
    /**
     * 微信好友code
     */
    private String addFriendsCode;
    
    /**
     * 微信名称
     */
    private String nickName;

    /**
     * 优惠券编号 .
     */
    private String couponNo;

    /**
     * 优惠券名称 .
     */
    private String couponName;

    /**
     * 使用时间 .
     */
    private Date useDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 面额
     */
    private Double  couponNotes;
    
    /**
     * 类型
     */
    private String couponType;
    
    /**
     * 优惠券状态
     */
    private String ruleStatus;
    
    /**
     * 推荐人
     */
    private String referrer;
    
    
    

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public Double getCouponNotes() {
		return couponNotes;
	}

	public void setCouponNotes(Double couponNotes) {
		this.couponNotes = couponNotes;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
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

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponRecordsPageReturn [code=");
		builder.append(code);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", addFriendsCode=");
		builder.append(addFriendsCode);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", couponNo=");
		builder.append(couponNo);
		builder.append(", couponName=");
		builder.append(couponName);
		builder.append(", useDate=");
		builder.append(useDate);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", couponNotes=");
		builder.append(couponNotes);
		builder.append(", couponType=");
		builder.append(couponType);
		builder.append(", ruleStatus=");
		builder.append(ruleStatus);
		builder.append(", referrer=");
		builder.append(referrer);
		builder.append("]");
		return builder.toString();
	}
    
    
}
