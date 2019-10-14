package com.lj.business.api.domain.msg;
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class SmsCodeVerifyRequest {
	/***
	 * 发送手机号
	 */
	private String mobile;
	/***
	 * smsCode
	 */
	private String smsCode;
	
	/**
	 * 是否返回处理码
	 */
	private boolean processFlag;
	
	/**
	 * 如果返回处理码，则设置处理码的过期时间，单位为秒，默认10分钟
	 */
	private int processExpired = 600;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	/**
	 * @return the processFlag
	 */
	public boolean isProcessFlag() {
		return processFlag;
	}
	/**
	 * @param processFlag the processFlag to set
	 */
	public void setProcessFlag(boolean processFlag) {
		this.processFlag = processFlag;
	}
	/**
	 * @return the processExpired
	 */
	public int getProcessExpired() {
		return processExpired;
	}
	/**
	 * @param processExpired the processExpired to set
	 */
	public void setProcessExpired(int processExpired) {
		this.processExpired = processExpired;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmsCodeVerifyRequest [mobile=");
		builder.append(mobile);
		builder.append(", smsCode=");
		builder.append(smsCode);
		builder.append(", processFlag=");
		builder.append(processFlag);
		builder.append(", processExpired=");
		builder.append(processExpired);
		builder.append("]");
		return builder.toString();
	}
	
}
