package com.lj.business.service.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.config.AiConfig;
import com.lj.business.util.SerializableUtil;
import com.lj.distributecache.RedisCacheConfigFromCC;

@Component
public class AiHandler {

	@Resource
	RedisCacheConfigFromCC redisCacheConfigFromCC;

	@Resource
	AiConfig aiConfig;


	private static Logger LOG = LoggerFactory.getLogger(AiHandler.class);
	
	private JedisPool jedisPool = null;
	private Jedis jedis  = null;
	/**
	 * 获取会话ID
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getSesssionId(String memberNo) {
		
		LOG.info("  getSesssionId  , memberNo :{}", memberNo);
		byte[] sessionId  = null;
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			sessionId = jedis.get(memberNo.getBytes());
			LOG.info(" MEMBER NO:{} BEGIN GET SESSION , ID :{}", memberNo, sessionId);
			if (sessionId==null||sessionId.length==0) {
				sessionId = GUID.generateByUUID().getBytes();
				LOG.info(" MEMBER NO:{} BEGIN NEW SESSION , ID :{}", memberNo, sessionId);
				jedis.setex(memberNo.getBytes(),aiConfig .getSessionTimeiout().intValue(), sessionId);
			}
		} catch (Exception e) {
			LOG.error(" releaseSession  error :{}",new String(sessionId),e);
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
		return new String(sessionId);
	}
	
	
	public AiCacheDto getSessionCache(String sessionId,String memberNo){
		LOG.info("  SESSION , ID :{}", sessionId);
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			byte[] aiCache = jedis.get(sessionId.getBytes());

			if (aiCache != null) {
				AiCacheDto aiCacheDto = (AiCacheDto) SerializableUtil.unserialize(aiCache);
				return aiCacheDto;
			}
			AiCacheDto aiCacheDto = new AiCacheDto();
			aiCacheDto.setMemberNo(memberNo);
			aiCacheDto.setSessionId(sessionId);
			return aiCacheDto;
		}  catch (Exception e) {
			LOG.error(" getSessionCache  error :{}",sessionId,e);
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
		return null;
		
	}
	
	
	public void releaseSession(String sessionId){
		LOG.info("  releaseSession  , ID :{}", sessionId);
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			jedis.del(sessionId.getBytes());
		} catch (Exception e) {
			LOG.error(" releaseSession  error :{}",sessionId,e);
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
	}
	public Integer getSessionCurrentCount(String sessionId) {
		AssertUtils.notNullAndEmpty(sessionId, "会话ID不能为空");
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			byte[] aiCache = jedis.get(sessionId.getBytes());
			if (aiCache != null) {
				AiCacheDto aiCacheDto = (AiCacheDto) SerializableUtil.unserialize(aiCache);
				return aiCacheDto.getCurrentCount();
			}
		} catch (Exception e) {
			LOG.error(" releaseSession  error :{}",sessionId,e);
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
		
		return 0;

	}
	
	
	public boolean flushSession(String sessionId){
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			byte[] aiCache = jedis.get(sessionId.getBytes());
			if (aiCache != null) {
				AiCacheDto aiCacheDto = (AiCacheDto) SerializableUtil.unserialize(aiCache);
				aiCacheDto.setCurrentCount(1);
				jedis.set(aiCacheDto.getSessionId().getBytes(), SerializableUtil.serialize(aiCacheDto));
				return true;
			}
		} catch (Exception e) {
			LOG.error(" flushSession  error :{}",sessionId,e);
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
		
		return false;
	}

	/**
	 * 缓存客户当前询问信息
	 * 
	 * @param aiCacheDto
	 */
	public Integer cacheSession(AiCacheDto aiCacheDto) {
		Integer resultFlag = 0;
		try {
			jedisPool = redisCacheConfigFromCC.getPool();
			jedis= jedisPool.getResource();
			AssertUtils.notNull(aiCacheDto);
			AssertUtils.notNullAndEmpty(aiCacheDto.getSessionId(), "会话ID不能为空");
			AssertUtils.notNullAndEmpty(aiCacheDto.getProblemCode(), "匹配的问题不能为空");
			byte[] aiCache =jedis.get(aiCacheDto.getSessionId().getBytes());
			if (aiCache == null) {
				aiCacheDto.setCurrentCount(1);
				resultFlag = 1; // 首次问询
				jedis.set(aiCacheDto.getSessionId().getBytes(), SerializableUtil.serialize(aiCacheDto));

			} else {
				AiCacheDto oldAiCacheDto =(AiCacheDto)SerializableUtil.unserialize(aiCache);
				if (aiCacheDto.getProblemCode().equals(oldAiCacheDto.getProblemCode())) {
					LOG.info(" last problem equals this problem  :last {}, this {}",oldAiCacheDto.getProblemCode(),aiCacheDto.getProblemCode());
					if (oldAiCacheDto.getCurrentCount() >= aiConfig.getMatchCount()) {
						resultFlag = 3; // 不再匹配 已达匹配次数 转人工
						return resultFlag;
					}
					aiCacheDto.setCurrentCount(oldAiCacheDto.getCurrentCount() + 1);
					if (oldAiCacheDto.getCurrentCount() == aiConfig.getMatchCount()) {
						resultFlag = 4; // 最后一次匹配
					}
					jedis.set(aiCacheDto.getSessionId().getBytes(), SerializableUtil.serialize(aiCacheDto));
					resultFlag = 2; // 非首次 并且与上次匹配问题相同
				}
			}
			jedis.setex(aiCacheDto.getMemberNo(),aiConfig .getSessionTimeiout().intValue(), aiCacheDto.getSessionId());
			
		} catch (Exception e) {
			LOG.error(" cache session error :{}",aiCacheDto,e);
			
		}finally{
			if(jedisPool!=null &&jedis!=null){
				redisCacheConfigFromCC.returnResource(jedisPool, jedis);
			}
		}
		
		return resultFlag;

	}

	
	
	

}
