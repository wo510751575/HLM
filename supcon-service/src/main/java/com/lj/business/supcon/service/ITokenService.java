/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;

/**
 * 
 * 类说明：公共服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月27日
 */
public interface ITokenService {

	public MemberLoginCache parseMember(String accessToken, int tokenTimeout);

	public MemberLoginCache parseMember(String accessToken);
	
	public Token generateToken(String memberNoGm, String appKey,int tokenTimeout);
	
	public Token refreshToken(String refreshToken, String appKey, int tokenTimeout);
	
	public void removeToken(String accessToken);
	
	public void removeToken(String memberNoGm, String appKey);
}
