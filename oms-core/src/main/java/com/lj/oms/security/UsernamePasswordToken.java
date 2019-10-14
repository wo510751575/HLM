package com.lj.oms.security;

/**
 * 用户和密码（包含验证码）令牌类
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	/** 登录类型 ：密码登录 */
	public static final String LOGIN_TYPE_PWD="pwd";
	/** 登录类型 ：token登录 */
	public static final String LOGIN_TYPE_TOKEN="token";
	
	
	/** 登录类型 ，token：token登录，pwd:密码登录 .类型不允许set方法，不允许表单注入  */
	private String type=LOGIN_TYPE_PWD;
	
	private String captcha;
	
	public UsernamePasswordToken() {
		super();
	}
	public UsernamePasswordToken(String type) {
		super();
		this.type = type;
	}
	
	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
}