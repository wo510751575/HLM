package com.lj.business.cp.domain;

import java.util.Date;

public class CouponRecords {
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

	/**
     * CODE .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * CODE .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 商户编号 .
     *
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 用户编号 .
     *
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * 用户编号 .
     *
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * 用户姓名 .
     *
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 用户姓名 .
     *
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * 优惠券编号 .
     *
     */
    public String getCouponNo() {
        return couponNo;
    }

    /**
     * 优惠券编号 .
     *
     */
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    /**
     * 优惠券名称 .
     *
     */
    public String getCouponName() {
        return couponName;
    }

    /**
     * 优惠券名称 .
     *
     */
    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    /**
     * 使用时间 .
     *
     */
    public Date getUseDate() {
        return useDate;
    }

    /**
     * 使用时间 .
     *
     */
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    /**
     * 创建人 .
     *
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CouponRecords [code=");
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
		builder.append("]");
		return builder.toString();
	}
}