package com.lj.business.cp.dto.couponMemberRelation;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindCouponMemberRelation.
 */
public class FindCouponMemberRelation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4391438220505176396L;

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
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 导购编号 .
	 *
	 * @return the 导购编号 
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * Sets the 导购编号 .
	 *
	 * @param memberNoGm the new 导购编号 
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
	}

	/**
	 * Gets the 导购姓名 .
	 *
	 * @return the 导购姓名 
	 */
	public String getMemberNameGm() {
		return memberNameGm;
	}

	/**
	 * Sets the 导购姓名 .
	 *
	 * @param memberNameGm the new 导购姓名 
	 */
	public void setMemberNameGm(String memberNameGm) {
		this.memberNameGm = memberNameGm;
	}

	/**
	 * Gets the 用户编号 .
	 *
	 * @return the 用户编号 
	 */
	public String getMemberNo() {
		return memberNo;
	}

	/**
	 * Sets the 用户编号 .
	 *
	 * @param memberNo the new 用户编号 
	 */
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * Gets the 用户姓名 .
	 *
	 * @return the 用户姓名 
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Sets the 用户姓名 .
	 *
	 * @param memberName the new 用户姓名 
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * Gets the 优惠券编号 .
	 *
	 * @return the 优惠券编号 
	 */
	public String getCouponNo() {
		return couponNo;
	}

	/**
	 * Sets the 优惠券编号 .
	 *
	 * @param couponNo the new 优惠券编号 
	 */
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	/**
	 * Gets the 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED .
	 *
	 * @return the 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED 
	 */
	public String getCouponStatus() {
		return couponStatus;
	}

	/**
	 * Sets the 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED .
	 *
	 * @param couponStatus the new 优惠券状态 未使用：UNUSED 已使用：USED 已过期：EXPIRED 
	 */
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	/**
	 * Gets the 使用时间 .
	 *
	 * @return the 使用时间 
	 */
	public Date getUseDate() {
		return useDate;
	}

	/**
	 * Sets the 使用时间 .
	 *
	 * @param useDate the new 使用时间 
	 */
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	/**
	 * Gets the 更新人 .
	 *
	 * @return the 更新人 
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * Sets the 更新人 .
	 *
	 * @param updateId the new 更新人 
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * Gets the 更新时间 .
	 *
	 * @return the 更新时间 
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the 更新时间 .
	 *
	 * @param updateDate the new 更新时间 
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Gets the 创建人 .
	 *
	 * @return the 创建人 
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * Sets the 创建人 .
	 *
	 * @param createId the new 创建人 
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the 创建时间 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the new 创建时间 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponMemberRelation [code=").append(code)
				.append(", memberNoGm=").append(memberNoGm)
				.append(", memberNameGm=").append(memberNameGm)
				.append(", memberNo=").append(memberNo).append(", memberName=")
				.append(memberName).append(", couponNo=").append(couponNo)
				.append(", couponStatus=").append(couponStatus)
				.append(", useDate=").append(useDate).append(", updateId=")
				.append(updateId).append(", updateDate=").append(updateDate)
				.append(", createId=").append(createId).append(", createDate=")
				.append(createDate).append("]");
		return builder.toString();
	}

}
