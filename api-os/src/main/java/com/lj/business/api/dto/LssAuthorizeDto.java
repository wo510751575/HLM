/**
 * 
 */
package com.lj.business.api.dto;

import java.io.Serializable;

/**
 * 
 * 
 * 类说明：乐莎莎登录帐号
 * 
 * 
 * <p>
 * 
 * @Company: 扬恩科技有限公司
 * @author 刘红艳
 * 
 *         CreateDate: 2019年5月13日
 */
public class LssAuthorizeDto implements Serializable {

	private String loginame;// 登录名
	private String loginpwd;// 密码
	private String appKey;// 每次登录相同则只允许一处登录，每次登录不同值则代表可同时多处登录。

	public String getAppKey() {
		return appKey;
	}

	public String getLoginame() {
		return loginame;
	}

	public void setLoginame(String loginame) {
		this.loginame = loginame;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
