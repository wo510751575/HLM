package com.lj.business.api.domain.member;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lj.business.supcon.dto.token.Token;

/**
 * 
 * 
 * 类说明：用户登录成功后返回参数
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
public class PersonMemberLoginReturnExt implements Serializable {

	private static final long serialVersionUID = 8147137559700547472L;
	
	
	/**
	 * 通用参数
	 */
	private Map<String, String> params = new HashMap<String, String>();
	
	/**
	 * 令牌
	 */
	private Token token;
	
	/**
	 * 签名密钥密文
	 */
	private String apiKey;
	
	/**
	 * 
	 *
	 * 方法说明：设置参数
	 *
	 * @author 彭阳
	 *   
	 * CreateDate: 2017-7-1
	 * 
	 * @param key
	 * @param value
	 */
	public void putParam(String key, String value) {
		params.put(key, value);
	}



	/**
	 * @return the params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonMemberLoginReturnExt [personMember=");
		builder.append(", params=");
		builder.append(params);
		builder.append(", token=");
		builder.append(token);
		builder.append(", apiKey=");
		builder.append(apiKey);
		builder.append("]");
		return builder.toString();
	}

}
