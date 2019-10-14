package com.lj.business.supcon.dto.token;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：会员登录后缓存信息
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
public class MemberLoginCache implements Serializable{
	
	/** 导购端登录是：导购编号 - memberNoGm/
	 * 	中控端登录是：终端号 - terminalCode.
	 *  */
	private String code;
	
	/** 登录应用 */
	private String appKey;
	
	/** 登录时间. */
	private long loginTime;
	
	/** 最后操作时间. */
	private long lastTime;
	
	/**
	 * The Constructor.
	 */
	public MemberLoginCache() {
		super();
	}
	
	public MemberLoginCache(String code, String appKey,long loginTime, long lastTime) {
		super();
		this.code = code;
		this.appKey = appKey;
		this.loginTime = loginTime;
		this.lastTime = lastTime;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * Gets the 登录时间.
	 *
	 * @return the loginTime
	 */
	public long getLoginTime() {
		return loginTime;
	}

	/**
	 * Sets the 登录时间.
	 *
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * Gets the 最后操作时间.
	 *
	 * @return the lastTime
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * Sets the 最后操作时间.
	 *
	 * @param lastTime the lastTime to set
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberLoginCache [code=");
		builder.append(code);
		builder.append(", appKey=");
		builder.append(appKey);
		builder.append(", loginTime=");
		builder.append(loginTime);
		builder.append(", lastTime=");
		builder.append(lastTime);
		builder.append("]");
		return builder.toString();
	}

}
