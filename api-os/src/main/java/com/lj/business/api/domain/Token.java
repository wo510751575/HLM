package com.lj.business.api.domain;


/**
 * 
 * 
 * 类说明：令牌
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
public class Token {

	/**
	 * 访问令牌，用于访问开放平台数据的令牌，有超时限制
	 */
	private String accessToken;
	
	/**
	 * 刷新令牌，为防止访问令牌accessToken超时，可用该令牌刷新访问令牌
	 * 应用场景：
	 * 1、客户端需持续长时间访问开放平台的业务功能
	 * 2、客户端手势密码校验成功后，刷新访问令牌
	 */
	private String refreshToken;
	
	public Token() {
		super();
	}
	
	public Token(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Token [accessToken=");
		builder.append(accessToken);
		builder.append(", refreshToken=");
		builder.append(refreshToken);
		builder.append("]");
		return builder.toString();
	}
	
}
