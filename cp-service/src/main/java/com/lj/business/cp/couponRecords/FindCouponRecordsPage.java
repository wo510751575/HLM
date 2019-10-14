package com.lj.business.cp.couponRecords;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity;

public class FindCouponRecordsPage extends PageParamEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6543349456335151365L; 
	
	/**
	 * 商户编号
	 */
	private String merchantNo;
 
	/**
	 * code
	 */
	private String code;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
    
	/**
	 * 优惠券名称
	 */
    private String couponName;
    

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCouponRecordsPage [merchantNo=");
		builder.append(merchantNo);
		builder.append(", code=");
		builder.append(code);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", couponName=");
		builder.append(couponName);
		builder.append("]");
		return builder.toString();
	}
	 
}
