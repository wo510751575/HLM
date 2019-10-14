package com.lj.business.cp.dto.couponMemberRelation;

import java.io.Serializable;
import java.util.Date;

public class FindCouponMemberRelationReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7301964928820192739L;

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
	 * 微信名称
	 */
	private String nickName;

	/**
	 * 用户姓名 .
	 */
	private String memberName;

	/**
	 * 优惠券编号 .
	 */
	private String couponNo;

	/**
	 * 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED .
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
	 * 微信好友CODE
	 */
    private String addFriendsCode;
    
    /**
     * 优惠券规则CODE
     */
    private String ruleNo;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getAddFriendsCode() {
		return addFriendsCode;
	}

	public void setAddFriendsCode(String addFriendsCode) {
		this.addFriendsCode = addFriendsCode;
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

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindCouponMemberRelationReturn [code=");
        builder.append(code);
        builder.append(", memberNoGm=");
        builder.append(memberNoGm);
        builder.append(", memberNameGm=");
        builder.append(memberNameGm);
        builder.append(", memberNo=");
        builder.append(memberNo);
        builder.append(", nickName=");
        builder.append(nickName);
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
        builder.append(", addFriendsCode=");
        builder.append(addFriendsCode);
        builder.append(", ruleNo=");
        builder.append(ruleNo);
        builder.append("]");
        return builder.toString();
    }

}
