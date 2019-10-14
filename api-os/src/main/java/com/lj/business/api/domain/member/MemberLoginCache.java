package com.lj.business.api.domain.member;

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
public class MemberLoginCache {
	
	/** 导购编号. */
	private String memberNoGm;
	
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
	
	public MemberLoginCache(String memberNoGm, String appKey,long loginTime, long lastTime) {
		super();
		this.memberNoGm = memberNoGm;
		this.appKey = appKey;
		this.loginTime = loginTime;
		this.lastTime = lastTime;
	}

	/**
	 * @return the memberNoGm
	 */
	public String getMemberNoGm() {
		return memberNoGm;
	}

	/**
	 * @param memberNoGm the memberNoGm to set
	 */
	public void setMemberNoGm(String memberNoGm) {
		this.memberNoGm = memberNoGm;
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
		builder.append("MemberLoginCache [memberNoGm=");
		builder.append(memberNoGm);
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
