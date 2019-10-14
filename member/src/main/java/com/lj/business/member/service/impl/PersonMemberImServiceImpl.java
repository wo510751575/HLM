/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.IPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.core.util.WxOpenIdUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IAddFriendsDao;
import com.lj.business.member.dao.IPersonMemberBaseDao;
import com.lj.business.member.dao.IPersonMemberDao;
import com.lj.business.member.dao.IPersonMemberImDao;
import com.lj.business.member.domain.PersonMember;
import com.lj.business.member.domain.PersonMemberBase;
import com.lj.business.member.dto.FindPersonMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.im.FindImFriendsPage;
import com.lj.business.member.dto.im.FindImFriendsPageReturn;
import com.lj.business.member.dto.im.FindMaxVersion;
import com.lj.business.member.dto.im.FindMemberWxByNoWxGm;
import com.lj.business.member.dto.im.FindPersonMemberByChat;
import com.lj.business.member.dto.im.FindPersonMemberByChatReturn;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWx;
import com.lj.business.member.dto.im.FindPersonMemberByShopAndNoWxReturn;
import com.lj.business.member.dto.im.GmMemberRelateInfoDto;
import com.lj.business.member.dto.im.PersonMemberWxInfo;
import com.lj.business.member.dto.im.SyncPersonWxInfoRequest;
import com.lj.business.member.dto.im.SyncPersonWxInfoResponse;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.IHashMap;

/**
 * 
 * 类说明：客户服务接口实现类（IM相关）
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年10月28日
 */
@Service
public class PersonMemberImServiceImpl implements IPersonMemberImService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(PersonMemberImServiceImpl.class);

	/** The person member dao. */
	@Resource
	private IPersonMemberImDao personMemberImDao;

	@Resource
	private IAddFriendsDao addFriendsDao;

	@Resource
	private IPersonMemberBaseDao personMemberBaseDao;

	@Resource
	private IPersonMemberDao personMemberDao;

	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Resource
	IHashMap hashMap;
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public long findMaxVersion(FindMaxVersion findMaxVersion) {
		logger.debug("findMaxVersion(FindMaxVersion findMaxVersion={}) - start", findMaxVersion);

		AssertUtils.notNull(findMaxVersion);
		AssertUtils.notNullAndEmpty(findMaxVersion.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notAllNullAndEmpty(findMaxVersion.getMemberNoGm(), findMaxVersion.getNoWxGm(), "导购编号和微信号不能同时为空");
		try {
			return personMemberImDao.findMaxVersion(findMaxVersion);
		} catch (Exception e) {
			logger.error("查询客户最大版本号错误", e);
			throw new TsfaServiceException(ErrorCode.FIND_MAX_PM_VERSION, "查询客户最大版本号错误", e);
		}
	}

	@Override
	public IPage<FindImFriendsPageReturn> findImFriends(FindImFriendsPage findImFriendsPage) {
		logger.debug("findImFriends(FindImFriendsPage findImFriendsPage={}) - start", findImFriendsPage);

		AssertUtils.notNull(findImFriendsPage);
		AssertUtils.notNullAndEmpty(findImFriendsPage.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notAllNullAndEmpty(findImFriendsPage.getMemberNoGm(), findImFriendsPage.getNoWxGm(),
				"导购编号和微信号不能同时为空");
		AssertUtils.notNullAndEmpty(findImFriendsPage.getVersion(), "最小版本号不能为空");

		List<FindImFriendsPageReturn> returnList = null;
		int count = 0;
		try {
			count = personMemberImDao.findImFriendsCount(findImFriendsPage);
			if (count > 0) {
				returnList = personMemberImDao.findImFriendsList(findImFriendsPage);
			}
		} catch (Exception e) {
			logger.error("分页查询IM微信好友失败", e);
			throw new TsfaServiceException(ErrorCode.FIND_IM_FRIENDS_ERROR, "分页查询IM微信好友失败", e);
		}

		Page<FindImFriendsPageReturn> returnPage = new Page<>(returnList, count, findImFriendsPage);
		logger.debug("findImFriends(FindImFriendsPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public FindPersonMemberByChatReturn findPersonMemberByChat(FindPersonMemberByChat findPersonMemberByChat) {
		logger.debug("findPersonMemberByNoWx(FindPersonMemberByChat findPersonMemberByNoWx={}) - start",
				findPersonMemberByChat);

		AssertUtils.notNull(findPersonMemberByChat);

		FindPersonMemberByChatReturn findPersonMemberByChatReturn = null;
		try {
			findPersonMemberByChatReturn = personMemberImDao.findPersonMemberByChat(findPersonMemberByChat);
		} catch (Exception e) {
			logger.error("查询客户信息失败", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查询客户信息失败", e);
		}

		logger.debug("findPersonMemberByNoWx(FindPersonMemberByChat) - end - return value={}",
				findPersonMemberByChatReturn);
		return findPersonMemberByChatReturn;
	}

	@Override
	public void cacheAllGmMemberRelateInfo() throws TsfaServiceException {
		try {
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					List<GmMemberRelateInfoDto> gmMemberRelateInfo = personMemberImDao.findGmMemberRelateInfo();

					int i = 0;
					for (GmMemberRelateInfoDto user : gmMemberRelateInfo) {
						// 缓存数据
						cacheGmMemberRelateInfo(user);
						i++;
						logger.info("导入第" + i + "条记录。");
					}
					logger.info("从MySQL导入数据到redis完成，共导入" + i + "条数据");
				}
			});

		} catch (Exception e) {
			logger.error("从MySQL中加载导购客户关联信息到Redis中失败", e);
			throw new TsfaServiceException(ErrorCode.CACHE_GM_MEMBER_RELATE_INFO_ERROR, "缓存客户导购关联信息失败", e);
		}
	}

	@Override
	public void cacheGmMemberRelateInfo(GmMemberRelateInfoDto gmMemberInfo) throws TsfaServiceException {
		try {
			// 编号关联信息
			if (gmMemberInfo.getMemberNoGm() != null && gmMemberInfo.getMemberNo() != null) {
				String key = "gm:mem:" + gmMemberInfo.getMemberNoGm() + ":" + gmMemberInfo.getMemberNo();

				if (gmMemberInfo.getGmWx() != null) {
					hashMap.hset(key, "noWxGm", gmMemberInfo.getGmWx());
				}
				if (gmMemberInfo.getNoWx() != null) {
					hashMap.hset(key, "noWx", gmMemberInfo.getNoWx());
				}
				if (gmMemberInfo.getNoWxAlias() != null) {
					hashMap.hset(key, "wxAlias", gmMemberInfo.getNoWxAlias());
				}
//                if (gmMemberInfo.getShopNo() != null) {
//                    hashMap.hset(key, "shopNo", gmMemberInfo.getShopNo());
//                }
//                if (gmMemberInfo.getShopName() != null) {
//                    hashMap.hset(key, "shopName", gmMemberInfo.getShopName());
//                }
				if (gmMemberInfo.getMerchantNo() != null) {
					hashMap.hset(key, "merchantNo", gmMemberInfo.getMerchantNo());
				}
				if (gmMemberInfo.getMerchantName() != null) {
					hashMap.hset(key, "merchantName", gmMemberInfo.getMerchantName());
				}
			}
			// 微信关联信息
			if (gmMemberInfo.getGmWx() != null && gmMemberInfo.getNoWx() != null) {
				String keyWx = "gmwx:memwx:" + gmMemberInfo.getGmWx() + ":" + gmMemberInfo.getNoWx();

				if (gmMemberInfo.getMemberNoGm() != null) {
					hashMap.hset(keyWx, "noGm", gmMemberInfo.getMemberNoGm());
				}
				if (gmMemberInfo.getMemberNo() != null) {
					hashMap.hset(keyWx, "memberNo", gmMemberInfo.getMemberNo());
				}
				if (gmMemberInfo.getNoWxAlias() != null) {
					hashMap.hset(keyWx, "wxAlias", gmMemberInfo.getNoWxAlias());
				}
//                if (gmMemberInfo.getShopNo() != null) {
//                    hashMap.hset(keyWx, "shopNo", gmMemberInfo.getShopNo());
//                }
//                if (gmMemberInfo.getShopName() != null) {
//                    hashMap.hset(keyWx, "shopName", gmMemberInfo.getShopName());
//                }
				if (gmMemberInfo.getMerchantNo() != null) {
					hashMap.hset(keyWx, "merchantNo", gmMemberInfo.getMerchantNo());
				}
				if (gmMemberInfo.getMerchantName() != null) {
					hashMap.hset(keyWx, "merchantName", gmMemberInfo.getMerchantName());
				}
			}
		} catch (Exception e) {
			logger.error("将数据缓存到Redis中失败。", e);
			throw new TsfaServiceException(ErrorCode.CACHE_GM_MEMBER_RELATE_INFO_ERROR, "缓存客户导购关联信息失败", e);
		}
	}

	@Override
	public GmMemberRelateInfoDto getGmMemberRelateCacheByNo(String key) throws TsfaServiceException {
		GmMemberRelateInfoDto relateInfo = new GmMemberRelateInfoDto();
		try {
			Map<String, String> cacheData = hashMap.hgetAll(key);
			if (cacheData == null || StringUtils.isEmpty(cacheData.get("memberNo"))) {
				logger.error("Redis中没有缓存客户导购信息：" + key);
				return null;
			} else {
				// relateInfo.setCode(addImChatInfo.getCode());
				// relateInfo.setMemberNoGm(addImChatInfo.getMemberNoGm());
				// relateInfo.setMemberNo(addImChatInfo.getMemberNo());
				relateInfo.setMemberNameGm(cacheData.get("gmName"));
				relateInfo.setMemberName(cacheData.get("memberName"));
				relateInfo.setGmWx(cacheData.get("noWxGm"));
				relateInfo.setNoWx(cacheData.get("noWx"));
				relateInfo.setNoWxAlias(cacheData.get("wxAlias"));
//                relateInfo.setShopNo(cacheData.get("shopNo"));
//                relateInfo.setShopName(cacheData.get("shopName"));
				relateInfo.setMerchantNo(cacheData.get("merchantNo"));
				relateInfo.setMerchantName(cacheData.get("merchantName"));

				logger.debug("^^^^^^^^^^^^^^^gm-zk--从缓存中获取到用户数据^^^^^^^^^^^^^^^^^");
				return relateInfo;
			}
		} catch (Exception e) {
			logger.error("从redis中获取用户关联信息失败。", e);
			throw new TsfaServiceException(ErrorCode.GET_GM_MEMBER_RELATE_INFO_FROM_REDIS_ERROR, "缓存客户导购关联信息失败", e);
		}
	}

	@Override
	public GmMemberRelateInfoDto getGmMemberRelateCacheByWx(String key) throws TsfaServiceException {
		GmMemberRelateInfoDto relateInfo = new GmMemberRelateInfoDto();
		try {
			Map<String, String> cacheData = hashMap.hgetAll(key);
			if (cacheData == null || StringUtils.isEmpty(cacheData.get("memberNo"))) {
				logger.error("Redis中没有缓存客户导购信息：" + key);
				return null;
			} else {
				// relateInfo.setCode(addImChatInfo.getCode());
				relateInfo.setMemberNoGm(cacheData.get("noGm"));
				relateInfo.setMemberNo(cacheData.get("memberNo"));
				relateInfo.setMemberNameGm(cacheData.get("gmName"));
				relateInfo.setMemberName(cacheData.get("memberName"));
				// relateInfo.setNoWxGm(addImChatInfo.getNoWxGm());
				// relateInfo.setNoWx(addImChatInfo.getNoWx());
				relateInfo.setNoWxAlias(cacheData.get("wxAlias"));
//                relateInfo.setShopNo(cacheData.get("shopNo"));
//                relateInfo.setShopName(cacheData.get("shopName"));
				relateInfo.setMerchantNo(cacheData.get("merchantNo"));
				relateInfo.setMerchantName(cacheData.get("merchantName"));
				logger.debug("---------------------zk-gm--从缓存中获取到用户数据------------------{}", relateInfo);
				return relateInfo;
			}
		} catch (Exception e) {
			logger.error("从redis中获取用户关联信息失败。", e);
			throw new TsfaServiceException(ErrorCode.GET_GM_MEMBER_RELATE_INFO_FROM_REDIS_ERROR, "缓存客户导购关联信息失败", e);
		}
	}

	@Override
	public Map<String, List<String>> findMemberWxByNoWxGm(FindMemberWxByNoWxGm findMemberWxByNoWxGm) {
		logger.debug("findMemberWxByNoWxGm(FindMemberWxByNoWxGm findMemberWxByNoWxGm={}) - start",
				findMemberWxByNoWxGm);

		AssertUtils.notNull(findMemberWxByNoWxGm);
//        AssertUtils.notNullAndEmpty(findMemberWxByNoWxGm.getShopNo(), "终端编号不能为空");
		AssertUtils.notNull(findMemberWxByNoWxGm.getNoWxGms(), "微信号列表不能为空");

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			// 循环每一个微信号
			for (String noWxGm : findMemberWxByNoWxGm.getNoWxGms()) {
				// 查询已添加客户
				List<String> noWxList = personMemberImDao.findMemberWxByNoWxGm(null, noWxGm);
				// 查询未认领（未分配）客户
				List<String> noWxClaimList = addFriendsDao.findClaimMemberWxByNoWxGm(null, noWxGm);
				// 合并两个列表
				if (noWxList == null) {
					noWxList = noWxClaimList;
				} else if (noWxClaimList != null) {
					noWxList.addAll(noWxClaimList);
				}
				map.put(noWxGm, noWxList);
			}
		} catch (Exception e) {
			logger.error("查询绑定导购微信号所有客户的微信列表失败", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查询绑定导购微信号所有客户的微信列表失败", e);
		}

		logger.debug("findMemberWxByNoWxGm(FindMemberWxByNoWxGm) - end - return value={}", map);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPersonMemberImService#findPersonMemberByNoWx(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public FindPersonMemberByShopAndNoWxReturn findPersonMemberByShopWxAndNoWx(FindPersonMemberByShopAndNoWx find) {
		logger.debug("findPersonMemberByShopAndNoWx(FindPersonMemberByShopAndNoWx={}) - start", find);

		AssertUtils.notNull(find);
//		AssertUtils.notNullAndEmpty(find.getShopNo(), "终端编号不能为空");
		AssertUtils.notNullAndEmpty(find.getNoWx(), "微信号不能为空");

		FindPersonMemberByShopAndNoWxReturn personMemberReturn = null;
		try {
			personMemberReturn = personMemberImDao.findPersonMemberByShopAndNoWx(find);
		} catch (Exception e) {
			logger.error("返回终端下添加了指定微信的客户信息失败", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "返回终端下添加了指定微信的客户信息失败", e);
		}

		logger.debug("findPersonMemberByShopAndNoWx() - end - return value={}", personMemberReturn);
		return personMemberReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPersonMemberImService#findPersonMemberWxInfo(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public PersonMemberWxInfo findPersonMemberWxInfo(String memberNoGm, String memberNo, String noWxGm) {
		logger.debug("findPersonMemberWxInfo(memberNoGm={}, memberNo={，noWxGm={}}) - start", memberNoGm, memberNo,
				noWxGm);

		AssertUtils.notNullAndEmpty(memberNoGm, "导购编号不能为空");
		AssertUtils.notNullAndEmpty(memberNo, "客户编号不能为空");
		AssertUtils.notNullAndEmpty(noWxGm, "终端微信不能为空");

		PersonMemberWxInfo personMemberWxInfo = null;
		try {
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setMemberNo(memberNo);
			PersonMemberBase personMemberBase = personMemberBaseDao.selectByParams(findPersonMemberBase);
			if (personMemberBase != null) {
				FindPersonMember findPersonMember = new FindPersonMember();
				findPersonMember.setShopWx(noWxGm);
				findPersonMember.setMemberNo(memberNo);
				findPersonMember.setMemberNoGm(memberNoGm);
				PersonMember personMember = personMemberDao.selectByMGM(findPersonMember);
				if (personMember != null) {
					personMemberWxInfo = new PersonMemberWxInfo();
					personMemberWxInfo.setNoWx(personMemberBase.getNoWx());
					personMemberWxInfo.setAlias(personMemberBase.getNoWxAlias());
					personMemberWxInfo.setNickNameWx(personMemberBase.getNickNameWx());
					personMemberWxInfo.setHeadAddress(personMemberBase.getHeadAddress());
					personMemberWxInfo.setSex(personMemberBase.getSex());
					personMemberWxInfo.setVersion(personMember.getVersion());
				}
			}
		} catch (Exception e) {
			logger.error("查询客户微信基本信息失败", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "查询客户微信基本信息失败", e);
		}

		logger.debug("findPersonMemberWxInfo(memberNoGm, memberNo) - return value={}", personMemberWxInfo);
		return personMemberWxInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lj.business.member.service.IPersonMemberImService#syncPersonWxInfo(com.lj
	 * .business.member.dto.im.SyncPersonWxInfoRequest)
	 */
	@Override
	public SyncPersonWxInfoResponse syncPersonWxInfo(SyncPersonWxInfoRequest syncPersonWxInfoRequest) {
		logger.debug("syncPersonWxInfo(SyncPersonWxInfoRequest={}) - start", syncPersonWxInfoRequest);

		AssertUtils.notNull(syncPersonWxInfoRequest);
		AssertUtils.notAllNullAndEmpty(syncPersonWxInfoRequest.getMemberNoGm(), syncPersonWxInfoRequest.getNoWxGm(),
				"导购编号和导购微信不能同时为空");
		AssertUtils.notAllNullAndEmpty(syncPersonWxInfoRequest.getMemberNo(), syncPersonWxInfoRequest.getNoWx(),
				"客户编号和客户微信号不能同时为空");

		SyncPersonWxInfoResponse syncPersonWxInfoResponse = new SyncPersonWxInfoResponse();
		try {
			// 查询客户基础信息数据
			PersonMemberBase personMemberBase = null;
			if (StringUtils.isNotEmpty(syncPersonWxInfoRequest.getMemberNo())) { // 客户编号不为空，则优先按客户编号查
				FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
				findPersonMemberBase.setMemberNo(syncPersonWxInfoRequest.getMemberNo());
				personMemberBase = personMemberBaseDao.selectByParams(findPersonMemberBase);
			} else { // 否则按微信号和微信别名查，此处中控返回的微信号有可能是错误的（如真实微信是l-d-q123456，返回时为ldq123456）
				personMemberBase = personMemberBaseDao.findMemberBaseByNoWxOrAlias(syncPersonWxInfoRequest.getNoWx(),
						syncPersonWxInfoRequest.getAlias());
			}
			if (personMemberBase != null) {
				boolean hasChanged = Boolean.FALSE; // 与数据库数据相比有更新
				PersonMemberBase updateBase = new PersonMemberBase();
				if (!StringUtils.toString(syncPersonWxInfoRequest.getAlias()).equals(personMemberBase.getNoWxAlias())) {
					hasChanged = Boolean.TRUE;
					updateBase.setNoWxAlias(syncPersonWxInfoRequest.getAlias());
				}
				if (!StringUtils.toString(syncPersonWxInfoRequest.getNickNameWx())
						.equals(personMemberBase.getNickNameWx())) {
					hasChanged = Boolean.TRUE;
					updateBase.setNickNameWx(syncPersonWxInfoRequest.getNickNameWx());
				}
				if (!StringUtils.toString(syncPersonWxInfoRequest.getHeadAddress())
						.equals(personMemberBase.getHeadAddress())) {
					hasChanged = Boolean.TRUE;
					updateBase.setHeadAddress(syncPersonWxInfoRequest.getHeadAddress());
				}
				if (!StringUtils.toString(syncPersonWxInfoRequest.getSex()).equals(personMemberBase.getSex())) {
					hasChanged = Boolean.TRUE;
					updateBase.setSex(syncPersonWxInfoRequest.getSex());
				}
				if (StringUtils.isEmpty(personMemberBase.getNoWx())) { // 原客户基数信息没有微信号,则设置微信号及openid
					hasChanged = Boolean.TRUE;
					updateBase.setNoWx(syncPersonWxInfoRequest.getNoWx());
					updateBase.setWxOpenId(WxOpenIdUtils.generateWxOpenId(syncPersonWxInfoRequest.getNoWx()));
				}
				// 手机号码
				if (StringUtils.isNotEmpty(syncPersonWxInfoRequest.getMobile())) {
					if (StringUtils.isEmpty(personMemberBase.getMobile())
							|| !syncPersonWxInfoRequest.getMobile().equals(personMemberBase.getMobile())) { // 原客户基数信息没有微信号,则设置微信号及openid
						hasChanged = Boolean.TRUE;
						updateBase.setMobile(syncPersonWxInfoRequest.getMobile());
					}
				}

				if (hasChanged) { // 微信信息有更新
					// 更新基础表
					updateBase.setCode(personMemberBase.getCode());
					personMemberBaseDao.updateByPrimaryKeySelective(updateBase);

					/**
					 * 更新客户基础数据关联的所有导购客户关联数据PM表
					 * 更新了关联表所有数据的客户版本号后，服务器不主动通知添加了该客户的所有导购端，而是由导购端通过触发客户微信基本信息同步操作来更新
					 */
					long version = System.currentTimeMillis();
					personMemberDao.updateVersionAllMember(personMemberBase.getMemberNo(), version);

					// 返回
					syncPersonWxInfoResponse.setChanged(Boolean.TRUE);
					syncPersonWxInfoResponse.setVersion(version);
				} else { // 没有更新
					syncPersonWxInfoResponse.setChanged(Boolean.FALSE);
				}
			}
		} catch (Exception e) {
			logger.error("同步客户微信基本信息失败", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_UPDATE_ERROR, "同步客户微信基本信息失败", e);
		}

		logger.debug("syncPersonWxInfo(SyncPersonWxInfoRequest) - return value={}", syncPersonWxInfoResponse);
		return syncPersonWxInfoResponse;
	}

	/**
	 * 
	 *
	 * 方法说明：统计导购助手下未回复客户数
	 *
	 * @param assistantNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	@Override
	@Deprecated
	public int findUnrespMemberCount(String assistantNo) {
		logger.debug("findUnrespMemberCount(String assistantNo={}) - start", assistantNo);

		AssertUtils.notNullAndEmpty(assistantNo, "助手编号不能为空");
		int count = 0;
		try {
			// 统计6分钟内未回复的客户数
//			count = personMemberImDao.findUnrespMemberCount(assistantNo, DateUtils.addMinutes(new Date(), -6));
//			count = personMemberImDao.findUnrespMemberCount(assistantNo, null);
		} catch (Exception e) {
			logger.error("统计导购助手下未回复客户数错误", e);
			throw new TsfaServiceException(ErrorCode.PERSON_MEMBER_FIND_ERROR, "统计导购助手下未回复客户数错误！", e);
		}
		logger.debug("findUnrespMemberCount(String) - end - return value={}", count);
		return count;
	}

	@Override
	public int findImFriendsCount(FindImFriendsPage findImFriendsPage) throws TsfaServiceException {
		logger.debug("findImFriends(FindImFriendsPage findImFriendsPage={}) - start", findImFriendsPage);

		AssertUtils.notNull(findImFriendsPage);
		AssertUtils.notNullAndEmpty(findImFriendsPage.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notAllNullAndEmpty(findImFriendsPage.getMemberNoGm(), findImFriendsPage.getNoWxGm(),
				"导购编号和微信号不能同时为空");
		AssertUtils.notNullAndEmpty(findImFriendsPage.getVersion(), "最小版本号不能为空");

		int count = 0;
		try {
			count = personMemberImDao.findImFriendsCount(findImFriendsPage);
		} catch (Exception e) {
			logger.error("查询IM微信好友失败", e);
			throw new TsfaServiceException(ErrorCode.FIND_IM_FRIENDS_ERROR, "查询IM微信好友失败", e);
		}

		logger.debug("findImFriends(FindImFriendsPage) - end - return value={}", count);
		return count;
	}

}
