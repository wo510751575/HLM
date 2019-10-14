package com.lj.business.cp.domain;

import java.util.Date;

public class CouponMemberRelation {
    /**
     * CODE .
     */
    private String code;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 用户编号 .
     */
    private String memberNo;

    /**
     * 用户姓名 .
     */
    private String memberName;

    /**
     * 优惠券编号 .
     */
    private String couponNo;

    /**
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
     */
    private String couponStatus;

    /**
     * 使用时间 .
     */
    private Date useDate;

    /**
     * 更新人 .
     */
    private String updateId;

    /**
     * 更新时间 .
     */
    private Date updateDate;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 规则编号
     */
    private String ruleNo;
    
    /**
     * 微信好友code
     */
    private String addFriendsCode;
    

    public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
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
     * 导购编号 .
     *
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号 .
     *
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
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
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
     *
     */
    public String getCouponStatus() {
        return couponStatus;
    }

    /**
     * 优惠券状态             未使用：UNUSED             已使用：USED             已过期：EXPIRED .
     *
     */
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus == null ? null : couponStatus.trim();
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
     * 更新人 .
     *
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 更新人 .
     *
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * 更新时间 .
     *
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间 .
     *
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
		builder.append("CouponMemberRelation [code=");
		builder.append(code);
		builder.append(", memberNoGm=");
		builder.append(memberNoGm);
		builder.append(", memberNameGm=");
		builder.append(memberNameGm);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", couponNo=");
		builder.append(couponNo);
		builder.append(", couponStatus=");
		builder.append(couponStatus);
		builder.append(", useDate=");
		builder.append(useDate);
		builder.append(", updateId=");
		builder.append(updateId);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", createId=");
		builder.append(createId);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", ruleNo=");
		builder.append(ruleNo);
		builder.append(", addFriendsCode=");
		builder.append(addFriendsCode);
		builder.append("]");
		return builder.toString();
	}
}