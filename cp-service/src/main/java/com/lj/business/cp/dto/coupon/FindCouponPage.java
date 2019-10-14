package com.lj.business.cp.dto.coupon;

import java.util.Date;

import com.lj.base.core.pagination.PageParamEntity;

/**
 * The Class FindCouponPage.
 */
public class FindCouponPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1110065780700580473L;

	/** 商戶編號. */
	private String merchantNo;

	/** 状态. */
	private String couponStatus;
	
	private String couponNo;

	/** 是用时间. */
	private Date useDate;


	/** 开始时间. */
	private Date startTime;

	/** 结束时间. */
	private Date endTime;

	/**
	 * 优惠券开始时间
	 */
	private Date beginDate;
	/**
	 * 优惠券结束时间
	 */
	private Date endDate;

	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 
	 * 优惠券类型
	 */
	private String couponType;
	/**
	 * 终端微信
	 */
	private String shopWx;

	
	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getShopWx() {
		return shopWx;
	}

	public void setShopWx(String shopWx) {
		this.shopWx = shopWx;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the merchant no.
	 *
	 * @return the merchant no
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the merchant no.
	 *
	 * @param merchantNo the merchant no
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the coupon status.
	 *
	 * @return the coupon status
	 */
	public String getCouponStatus() {
		return couponStatus;
	}

	/**
	 * Sets the coupon status.
	 *
	 * @param couponStatus the coupon status
	 */
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	/**
	 * Gets the use date.
	 *
	 * @return the use date
	 */
	public Date getUseDate() {
		return useDate;
	}

	/**
	 * Sets the use date.
	 *
	 * @param useDate the use date
	 */
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", couponStatus=");
		builder.append(couponStatus);
		builder.append(", useDate=");
		builder.append(useDate);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", beginDate=");
		builder.append(beginDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", couponName=");
		builder.append(couponName);
		builder.append(", couponType=");
		builder.append(couponType);
		builder.append(", shopWx=");
		builder.append(shopWx);
		builder.append("]");
		return builder.toString();
	}


}
