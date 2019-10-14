package com.lj.oms.service.impl.sms;

import com.lj.oms.utils.excel.annotation.ExcelField;

public class SmsExcelDto {

	/** 手机号. */
	@ExcelField(title="手机号", align=2, sort=10)
    private String mobile;
	
	/** 赠送积分. */
	@ExcelField(title="赠送积分", align=2, sort=20)
	private String count;

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsExcelDto [mobile=");
		builder.append(mobile);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	
}
