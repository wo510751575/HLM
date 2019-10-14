package com.lj.oms.entity.dto.kuaitui;

import java.io.Serializable;

/**
 * 签名相关参数
 * @author wo510
 *
 */
public class BaseDto implements Serializable{
	private static final long serialVersionUID = 3482168215231758893L;
	private String timeStamp;
	private String sign;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseDto [timeStamp=");
		builder.append(timeStamp);
		builder.append(", sign=");
		builder.append(sign);
		builder.append("]");
		return builder.toString();
	}
}
