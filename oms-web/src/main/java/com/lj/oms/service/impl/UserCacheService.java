/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.oms.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.distributecache.RedisCache;
import com.lj.oms.entity.sys.User;

/**
 * 
 * 类说明：第三方开商户的token处理。
 * 
 * 
 * <p>
 * 
 * @Company: 扬恩科技有限公司
 * @author lhy
 * 
 *         CreateDate: 2018年1月29日
 */
@Service
public class UserCacheService {
	private static final String LOGIN_NAME = "loginName";
	private static final String ID = "id";
	private static final String TOKEN_PREFIX="OMS_TOKEN_";
	private static final String TOKEN_ID_PREFIX="OMS_TOKEN_ID_";
	@Resource
	RedisCache redisCache;
	
	/**
	 * 方法说明: 新增第三方开商户后的token。
	 * 
	 * @param user
	 * @return token
	 *
	 * @author lhy CreateDate: 2018年1月29日
	 *
	 */
	public String addUserToken(User user) {
		AssertUtils.notNullAndEmpty(user.getId(), "ID不能为空");
		AssertUtils.notNullAndEmpty(user.getLoginName(), "LoginName不能为空");
		String token = null;
		StringBuilder sb = new StringBuilder();
		sb.append(user.getLoginName());
		sb.append(",");
		sb.append(user.getId());
		sb.append(",");
		sb.append(System.currentTimeMillis());
		token = MD5.encryptByMD5(sb.toString());

		Map<String, String> value = new HashMap<>();
		value.put(ID, user.getId());
		value.put(LOGIN_NAME, user.getLoginName());

		String userJson = JsonUtils.jsonFromObject(value);
		redisCache.set(getTokenKey(token), userJson);
		redisCache.set(getIdKey(user.getId()), token);//用户ID-存token
		return token;
	}

	/**
	 * 方法说明：通过token查找user.
	 *
	 * @param token
	 * @return
	 *
	 * @author lhy CreateDate: 2018年1月29日
	 *
	 */
	public User getUserByToken(String token) {
		String userJson = redisCache.get(getTokenKey(token));
		User user = null;
		if (userJson != null) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> userMap = (HashMap<String, String>) JsonUtils
					.objectFromJson(userJson, HashMap.class);
			user = new User();
			user.setId((String) userMap.get(ID));
			user.setLoginName((String) userMap.get(LOGIN_NAME));
		}
		return user;
	}
	
	/**
	 * 方法说明：根据用户ID获取token.
	 *
	 * @param id 用户ID(User.id)
	 * @return
	 *
	 * @author lhy  2018年1月31日
	 *
	 */
	public String getTokenByUserId(String id){
		AssertUtils.notNullAndEmpty(id, "ID不能为空");
		String token = redisCache.get(getIdKey(id));
		return token;
	}
	
	
	private String getTokenKey(String token){
		return TOKEN_PREFIX+token;
	}
	
	private String getIdKey(String token){
		return TOKEN_ID_PREFIX+token;
	}
}
