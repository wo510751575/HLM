package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.token.MemberLoginCache;
import com.lj.business.supcon.dto.token.Token;
import com.lj.business.supcon.service.ITokenService;
import com.lj.distributecache.IDistributeCache;

/**
 * 
 * 
 * 类说明：Token服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月7日
 */
@Service
public class TokenService implements ITokenService{
	
	private static Logger logger = LoggerFactory.getLogger(TokenService.class);	
	/**
	 * 访问令牌缓存前缀
	 */
	private static final String ACCESS_TOKEN_PREFIX = "APIATOKEN";
	
	@Resource 
	private IDistributeCache distributeCache;
	/**
	 * 刷新令牌缓存前缀
	 */
	private static final String REFRESH_TOKEN_PREFIX = "APIRTOKEN";
	/**
	 * 存储会员令牌缓存前缀
	 */
	private static final String MEMBER_TOKEN_PREFIX = "APIMTOKEN";
	/**
	 * 刷新令牌长度
	 */
	private static final int REFRESH_LENGTH = 32;
	
	
	/**
	 * 
	 *
	 * 方法说明：生成令牌
	 *
	 * @param memberNoGm
	 * @param appKey
	 * @param tokenTimeout		登录超时时间(秒)
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public Token generateToken(String memberNoGm, String appKey,int tokenTimeout) {
		// 生成访问令牌
		long now = System.currentTimeMillis();
		String accessToken = GUID.generateByUUID();
		// 访问令牌存入缓存，value格式 = 登录时时间戳 + 最后一次操作时间戳
		MemberLoginCache memberCache = new MemberLoginCache(memberNoGm, appKey, now, now);
		distributeCache.set(getAccessCacheKey(accessToken), JsonUtils.jsonFromObject_LongToString(memberCache), tokenTimeout);
		
		// 生成刷新令牌
		String refreshToken = GUID.generateByUUID();
		// 刷新令牌存入缓存，value格式 = 导购编号																																																																																																																																																																																																																																																																																							
		distributeCache.set(getRefreshCacheKey(refreshToken), memberNoGm, Token.TOKEN_TIMEOUT_SECONDS);
		
		// 删除会员原令牌
		String memberKey = getMemberCacheKey(memberNoGm, appKey);
		String memberToken = distributeCache.get(memberKey);
		if(StringUtils.isNotEmpty(memberToken)) {
			distributeCache.del(getRefreshCacheKey(memberToken.substring(0, REFRESH_LENGTH)));	// 删除刷新令牌
			String oriAccessToken = getAccessCacheKey(memberToken.substring(REFRESH_LENGTH));
			distributeCache.del(oriAccessToken);		// 删除访问令牌
			logger.info("已删除登录账户[{}]原访问令牌[{}]", memberNoGm, oriAccessToken);
		}
		
		// 存储会员当前令牌，格式 = 刷新令牌 + 访问令牌
		distributeCache.set(memberKey, refreshToken + accessToken, Token.TOKEN_TIMEOUT_SECONDS);
		
		return new Token(accessToken, refreshToken);
	}
	
	/**
	 * 
	 *
	 * 方法说明：刷新令牌
	 *
	 * @param refreshToken
	 * @param appKey
	 * @param tokenTimeout		登录超时时间(秒)
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public Token refreshToken(String refreshToken, String appKey, int tokenTimeout) {
		String memberNoGm = distributeCache.get(getRefreshCacheKey(refreshToken));
		if(StringUtils.isEmpty(memberNoGm)) {
			throw new TsfaServiceException(ErrorCode.TOKEN_REFRESH_ERROR, "令牌刷新失败");
		}
		return this.generateToken(memberNoGm, appKey, tokenTimeout);
	}
	
	/**
	 * 
	 *
	 * 方法说明：校验token并解析获得会员登录信息
	 * 
	 * @param accessToken		访问令牌
	 * @param tokenTimeout		登录超时时间(秒)
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public MemberLoginCache parseMember(String accessToken, int tokenTimeout){
		String key = getAccessCacheKey(accessToken);
		String tokenVaue = distributeCache.get(key);			// 从缓存中获取时间戳
		if(StringUtils.isEmpty(tokenVaue)) {
			throw new TsfaServiceException(ErrorCode.TOKEN_TIMEOUT, "登录超时！");
		}
		MemberLoginCache memberCache = (MemberLoginCache) JsonUtils.objectFromJson(tokenVaue, MemberLoginCache.class);
		long now = System.currentTimeMillis();					// 当前时间戳
		if(now < tokenTimeout * 1000 + memberCache.getLastTime()) {	// 未过期，则更新最后一次操作时间戳
			memberCache.setLastTime(now);		// 更新最后操作时间
			distributeCache.set(key, JsonUtils.jsonFromObject_LongToString(memberCache), tokenTimeout); 	// 更新缓存
			return memberCache;
		} else {	// 过期
			distributeCache.del(key);	// 删除缓存
			throw new TsfaServiceException(ErrorCode.TOKEN_TIMEOUT,"登录超时！");
		}
	}
	
	@Override
	public MemberLoginCache parseMember(String accessToken) {
		return parseMember(accessToken, Token.TOKEN_TIMEOUT_SECONDS);
	}
	
	/**
	 * 
	 *
	 * 方法说明：退出登录，删除token缓存
	 *
	 * @param accessToken		访问令牌
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月7日
	 *
	 */
	public void removeToken(String accessToken) {
		String key = getAccessCacheKey(accessToken);
		String tokenVaue = distributeCache.get(key);			// 获取导购信息
		if(StringUtils.isEmpty(tokenVaue)) {
			return;
		}
		distributeCache.del(key);	// 删除访问令牌
		
		// 导购信息
		MemberLoginCache memberCache = (MemberLoginCache) JsonUtils.objectFromJson(tokenVaue, MemberLoginCache.class);
		
		// 删除会员令牌和刷新令牌
		String memberKey = getMemberCacheKey(memberCache.getCode(), memberCache.getAppKey());
		String memberToken = distributeCache.get(memberKey);
		if(StringUtils.isNotEmpty(memberToken)) {
			distributeCache.del(getRefreshCacheKey(memberToken.substring(0, REFRESH_LENGTH)));	// 删除刷新令牌
			distributeCache.del(memberKey);		// 删除会员令牌
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购指定应用的所有令牌
	 *
	 * @param memberNoGm
	 * @param appKey
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月10日
	 *
	 */
	public void removeToken(String memberNoGm, String appKey) {
		String memberKey = getMemberCacheKey(memberNoGm, appKey);
		String memberToken = distributeCache.get(memberKey);
		if(StringUtils.isNotEmpty(memberToken)) {
			distributeCache.del(getRefreshCacheKey(memberToken.substring(0, REFRESH_LENGTH)));	// 删除刷新令牌
			distributeCache.del(getAccessCacheKey(memberToken.substring(REFRESH_LENGTH)));		// 删除访问令牌
			distributeCache.del(memberKey);	// 删除会员令牌
		}
	}
	
	private String getMemberCacheKey(String memberNoGm, String appKey) {
		return MEMBER_TOKEN_PREFIX + memberNoGm + appKey;
	}
	
	private String getAccessCacheKey(String accessToken) {
		return ACCESS_TOKEN_PREFIX + accessToken;
	}
	
	private String getRefreshCacheKey(String refreshToken) {
		return REFRESH_TOKEN_PREFIX + refreshToken;
	}
}
