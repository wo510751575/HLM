package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cm.dto.merchantParams.FindMerchantParams;
import com.lj.business.cm.service.IMerchantParamsService;
import com.lj.business.common.KeyConstant;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.ICouponRuleDao;
import com.lj.business.cp.domain.CouponRule;
import com.lj.business.cp.dto.coupon.FindInviteCode;
import com.lj.business.cp.dto.coupon.FindInviteCodeReturn;
import com.lj.business.cp.dto.coupon.FindMemberInfoByInviteCodeReturn;
import com.lj.business.cp.dto.couponRule.AddCouponRule;
import com.lj.business.cp.dto.couponRule.DelCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRulePage;
import com.lj.business.cp.dto.couponRule.FindCouponRulePageReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRule.SendCouponAfterAddWxFriends;
import com.lj.business.cp.dto.couponRule.UpdateCouponRule;
import com.lj.business.cp.emus.RealName;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.emus.UseScope;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.couponmultipush.FindShopTerminalByWxList;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.weixin.dto.imchat.SendImChatByNonMember;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.RedisCache;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 杨杰
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class CouponRuleServiceImpl implements ICouponRuleService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponRuleServiceImpl.class);

	@Resource
	private ICouponRuleDao couponRuleDao;
	@Autowired 
    private RedisCache redisCache;
	@Autowired 
    private IImChatInfoService imChatInfoService;
//	@Autowired
//    private IShopTerminalService shopTerminalService;
	@Resource
    private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
    private IMerchantService merchantService;
	@Autowired
    private IMerchantParamsService merchantParamsService;
	@Resource
	private IGuidMemberService guidMemberService;
	@Resource
	private IAddFriendsService addFriendsService;

	

	@Override
	public AddCouponRule addCouponRule(AddCouponRule addCouponRule) throws TsfaServiceException {
		logger.debug("addCouponRule(AddCouponRule addCouponRule={}) - start", addCouponRule);

		AssertUtils.notNull(addCouponRule);
		try {
			CouponRule couponRule = new CouponRule();
			// add数据录入
			couponRule.setCode(GUID.getPreUUID());
			couponRule.setMerchantNo(addCouponRule.getMerchantNo());
			couponRule.setMerchantName(addCouponRule.getMerchantName());
//			couponRule.setShopNo(addCouponRule.getShopNo());
//			couponRule.setShopName(addCouponRule.getShopName());
			couponRule.setCouponNum(addCouponRule.getCouponNum());
			couponRule.setCouponNotes(addCouponRule.getDoubleCouponNotes());
			couponRule.setBeginDate(addCouponRule.getBeginDate());
			couponRule.setEndDate(addCouponRule.getEndDate());
			couponRule.setCouponName(addCouponRule.getCouponName());
			couponRule.setCouponMax(addCouponRule.getDoubleCouponMax());
			couponRule.setCouponTypeCode(addCouponRule.getCouponTypeCode());
			couponRule.setCouponType(addCouponRule.getCouponType());
			couponRule.setCouponRemark(addCouponRule.getCouponRemark());
			couponRule.setToCoupon(addCouponRule.getToCoupon());
			couponRule.setUseScope(addCouponRule.getUseScope());
			couponRule.setRealName(addCouponRule.getRealName());
			couponRule.setRuleStatus(addCouponRule.getRuleStatus());
			couponRule.setUpdateId(addCouponRule.getUpdateId());
			couponRule.setUpdateDate(new Date());
			couponRule.setCreateId(addCouponRule.getCreateId());
			couponRule.setCreateDate(new Date());
			couponRule.setEnableDate(addCouponRule.getEnableDate());
			couponRule.setUseNums(addCouponRule.getUseNums());
			couponRule.setIsWriteoff(addCouponRule.getIsWriteoff());
			couponRule.setIsProduce(addCouponRule.getIsProduce());
			couponRule.setCouponAvgNum(addCouponRule.getCouponAvgNum());
			
			//返回信息
			addCouponRule.setCode(couponRule.getCode());
			addCouponRule.setMerchantName(couponRule.getMerchantName());
			addCouponRule.setMerchantNo(couponRule.getMerchantNo());
			addCouponRule.setCouponAvgNum(couponRule.getCouponAvgNum());
			addCouponRule.setUseRule(couponRule.getUseRule());
			couponRuleDao.insert(couponRule);
			logger.debug("addCouponRule(AddCouponRule) - end - return");
			return addCouponRule;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delCouponRule(DelCouponRule delCouponRule) throws TsfaServiceException {
		logger.debug("delCouponRule(DelCouponRule delCouponRule={}) - start", delCouponRule);

		AssertUtils.notNull(delCouponRule);
		AssertUtils.notNull(delCouponRule.getCode(), "Code不能为空！");
		try {
			couponRuleDao.deleteByPrimaryKey(delCouponRule.getCode());
			logger.debug("delCouponRule(DelCouponRule) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateCouponRule(UpdateCouponRule updateCouponRule) throws TsfaServiceException {
		logger.debug("updateCouponRule(UpdateCouponRule updateCouponRule={}) - start", updateCouponRule); 
		AssertUtils.notNullAndEmpty(updateCouponRule.getCode(), "Code不能为空");
		try { 
			CouponRule couponRule = new CouponRule();
			// update数据录入
			couponRule.setCode(updateCouponRule.getCode());
			couponRule.setMerchantNo(updateCouponRule.getMerchantNo());
			couponRule.setMerchantName(updateCouponRule.getMerchantName());
			couponRule.setCouponNum(updateCouponRule.getCouponNum());
			couponRule.setCouponNotes(updateCouponRule.getDoubleCouponNotes());
			couponRule.setBeginDate(updateCouponRule.getBeginDate());
			couponRule.setEndDate(updateCouponRule.getEndDate());
			couponRule.setCouponName(updateCouponRule.getCouponName());
			couponRule.setCouponMax(updateCouponRule.getDoubleCouponMax());
			couponRule.setCouponTypeCode(updateCouponRule.getCouponTypeCode());
			couponRule.setCouponType(updateCouponRule.getCouponType());
			couponRule.setCouponRemark(updateCouponRule.getCouponRemark());
			couponRule.setToCoupon(updateCouponRule.getToCoupon());
			couponRule.setUseScope(updateCouponRule.getUseScope());
			couponRule.setRealName(updateCouponRule.getRealName());
			couponRule.setRuleStatus(updateCouponRule.getRuleStatus());
			couponRule.setUpdateId(updateCouponRule.getUpdateId());
			couponRule.setUpdateDate(new Date());
			couponRule.setEnableDate(updateCouponRule.getEnableDate());
			couponRule.setIsProduce(updateCouponRule.getIsProduce());
			couponRule.setIsWriteoff(updateCouponRule.getIsWriteoff());
			couponRule.setUseNums(updateCouponRule.getUseNums());
			couponRule.setCouponAvgNum(updateCouponRule.getCouponAvgNum());
			couponRuleDao.updateByPrimaryKeySelective(couponRule);
			logger.debug("updateCouponRule(UpdateCouponRule) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public FindCouponRuleReturn findCouponRule(FindCouponRule findCouponRule) throws TsfaServiceException {
		logger.debug("findCouponRule(FindCouponRule findCouponRule={}) - start", findCouponRule); 

		AssertUtils.notNull(findCouponRule);
		AssertUtils.notAllNull(findCouponRule.getCode(), "Code不能为空");
		try {
			CouponRule couponRule = couponRuleDao.selectByPrimaryKey(findCouponRule.getCode());
			if (couponRule == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.COUPON_RULE_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindCouponRuleReturn findCouponRuleReturn = new FindCouponRuleReturn();
			
			// find数据录入
			findCouponRuleReturn.setCode(couponRule.getCode());
			findCouponRuleReturn.setMerchantNo(couponRule.getMerchantNo());
			findCouponRuleReturn.setMerchantName(couponRule.getMerchantName());
//			findCouponRuleReturn.setShopNo(couponRule.getShopNo());
//			findCouponRuleReturn.setShopName(couponRule.getShopName());
			findCouponRuleReturn.setCouponNum(couponRule.getCouponNum());
			findCouponRuleReturn.setCouponNotes((int)couponRule.getCouponNotes().doubleValue());
			findCouponRuleReturn.setBeginDate(couponRule.getBeginDate());
			findCouponRuleReturn.setEndDate(couponRule.getEndDate());
			findCouponRuleReturn.setCouponName(couponRule.getCouponName());
			findCouponRuleReturn.setCouponMax((int)couponRule.getCouponMax().doubleValue());
			findCouponRuleReturn.setCouponTypeCode(couponRule.getCouponTypeCode());
			findCouponRuleReturn.setCouponType(couponRule.getCouponType());
			//转码 防止特殊字符乱码
			String couponRemark= StringEscapeUtils.unescapeHtml(couponRule.getCouponRemark()).toString();
			findCouponRuleReturn.setCouponRemark(couponRemark);
			findCouponRuleReturn.setToCoupon(couponRule.getToCoupon());
			findCouponRuleReturn.setUseScope(couponRule.getUseScope());
			findCouponRuleReturn.setRealName(couponRule.getRealName());
			findCouponRuleReturn.setRuleStatus(couponRule.getRuleStatus());
			findCouponRuleReturn.setUpdateId(couponRule.getUpdateId());
			findCouponRuleReturn.setUpdateDate(couponRule.getUpdateDate());
			findCouponRuleReturn.setCreateId(couponRule.getCreateId());
			findCouponRuleReturn.setCreateDate(couponRule.getCreateDate());
			findCouponRuleReturn.setIsProduce(couponRule.getIsProduce());
			findCouponRuleReturn.setIsWriteoff(couponRule.getIsWriteoff());
			findCouponRuleReturn.setUseNums(couponRule.getUseNums());
			findCouponRuleReturn.setEnableDate(couponRule.getEnableDate());
			findCouponRuleReturn.setCouponAvgNum(couponRule.getCouponAvgNum());
			logger.debug("findCouponRule(FindCouponRule) - end - return value={}", findCouponRuleReturn); 
			return findCouponRuleReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindCouponRulePageReturn> findCouponRulePage(FindCouponRulePage findCouponRulePage) throws TsfaServiceException {
		logger.debug("findCouponRulePage(FindCouponRulePage findCouponRulePage={}) - start", findCouponRulePage); 

		AssertUtils.notNull(findCouponRulePage);
		List<FindCouponRulePageReturn> returnList;
		int count = 0;
		try {
			returnList = couponRuleDao.findCouponRulePage(findCouponRulePage);
			count = couponRuleDao.findCouponRulePageCount(findCouponRulePage);
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_FIND_PAGE_ERROR, "导购行为信息记录表信息不存在错误.！", e);
		}
		Page<FindCouponRulePageReturn> returnPage = new Page<FindCouponRulePageReturn>(returnList, count, findCouponRulePage);

		logger.debug("findCouponRulePage(FindCouponRulePage) - end - return value={}", returnPage); 
		return returnPage;
	}

	@Override
	public List<FindCouponRuleReturn> findCouponRuleList(FindCouponRule findCouponRule) throws TsfaServiceException {
		logger.debug("findCouponRuleList(FindCouponRule findCouponRule={}) - start", findCouponRule);
		List<FindCouponRuleReturn> retAllList =null;
		try {
			retAllList = couponRuleDao.findCouponRuleList(findCouponRule);
		} catch (Exception e) {
			logger.error("根据优惠券类型获取当前类型下的所有优惠券规则 >>> 对象拷贝失败：",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_FIND_ERROR,"根据优惠券类型获取当前类型下的所有优惠券规则 >>> 对象拷贝失败");
		}
		logger.debug("findCouponRuleList() - end - return value={}", retAllList); 
		
		return retAllList;
	}
	
	@Override
    public List<FindCouponRuleReturn> findCouponRuleListWeb(FindCouponRule findCouponRule) throws TsfaServiceException {
        logger.debug("findCouponRuleListWeb(FindCouponRule findCouponRule={}) - start", findCouponRule); 

        List<FindCouponRuleReturn> retAllList = new ArrayList<FindCouponRuleReturn>();
        FindCouponRule findCouponRule1 = new FindCouponRule();
        try {
            BeanUtils.copyProperties(findCouponRule1, findCouponRule);
        } catch (Exception e) {
            logger.error("根据优惠券类型获取当前类型下的所有优惠券规则 >>> 对象拷贝失败：",e);
            throw new TsfaServiceException(ErrorCode.COUPON_RULE_FIND_ERROR,"根据优惠券类型获取当前类型下的所有优惠券规则 >>> 对象拷贝失败");
        }
        findCouponRule1.setRealName(RealName.NO.toString());
        findCouponRule1.setRuleStatus(RuleStatus.YES.toString());
        findCouponRule1.setUseScope(UseScope.ALL.toString()); // 查询全店铺通用券
        List<FindCouponRuleReturn> retList1 = couponRuleDao.findCouponRuleList(findCouponRule1);
        
        //根据选中的多个终端终端微信，查询其对应的终端编号集合
        FindShopTerminalByWxList findShopTerminalByWxList = new FindShopTerminalByWxList();
        String[] noWxShopArr = findCouponRule.getNoWxShops().split(";");
        findShopTerminalByWxList.setNoWxList(Arrays.asList(noWxShopArr));
        
        findCouponRule1.setUseScope(UseScope.ASSIGN.toString()); // 查询多个指定店铺可用的券
        List<FindCouponRuleReturn> retList2 = couponRuleDao.findCouponRuleListByShops(findCouponRule1);
        if(CollectionUtils.isNotEmpty(retList1)){
            retAllList.addAll(retList1);
        }
        if(CollectionUtils.isNotEmpty(retList2)){
            retAllList.addAll(retList2);
        }

        logger.debug("findCouponRuleListWeb(FindCouponRule) - end - return value={}", retAllList); 
        return retAllList;
    }

	@Override
	public List<FindCouponRuleReturn> findcouponRuleTodayEnableDate()throws TsfaServiceException {
		List<FindCouponRuleReturn> list = Lists.newArrayList();
		try {
			list = couponRuleDao.findcouponRuleTodayEnableDate();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}catch (Exception e) {
		   logger.error("查找优惠券规则错误！",e);
		}
		logger.debug("findcouponRuleTodayEnableDate() - end - return value={}", list); 
		return list;
	}

	/**
	 * 后缀H的是hash结构；后缀S的是set结构，防止重复
	 */
    @Override
    public FindInviteCodeReturn findInviteCode(FindInviteCode findInviteCode) throws TsfaServiceException {
        logger.debug("findInviteCode(FindInviteCode findInviteCode={}) - start", findInviteCode); 

        FindInviteCodeReturn findInviteCodeReturn = new FindInviteCodeReturn();
        if (!findInviteCode.validate()) {
            logger.error("查询邀请码参数错误-{}", findInviteCode);
            return null;
        }
        try { 
            if (findInviteCode.getType() == 1) {
                if (redisCache.hexists(
                        KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                        findInviteCode.getMemberNo())) {//已存在
                    String inviteCode = redisCache.hget(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                            findInviteCode.getMemberNo());
                    findInviteCodeReturn.setInviteCode(inviteCode);
                } else {//不存在
                    String inviteCode = String.format("%04d", new Random().nextInt(10000));
                    while (redisCache.sismember(KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":S",
                            inviteCode)) {
                        inviteCode = String.format("%04d", new Random().nextInt(10000));
                    }
                    //添加到set
                    redisCache.sadd(KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":S",
                            inviteCode);
                    //添加到hash
                    redisCache.hset(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                            findInviteCode.getMemberNo(), inviteCode);
                    //根据inviteCode索引关联信息
                    redisCache.set(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + inviteCode, 
                            "1#" + findInviteCode.getShopNoWx() + 
                            "#" + findInviteCode.getCouponRuleCode() + 
                            "#" + findInviteCode.getMemberNo() + 
                            "#" + findInviteCode.getMemberNoGm());
                    findInviteCodeReturn.setInviteCode(inviteCode);
                }
            } else {
                if (redisCache.hexists(
                        KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                        findInviteCode.getAddFriendsCode())) {//已存在
                    String inviteCode = redisCache.hget(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                            findInviteCode.getAddFriendsCode());
                    findInviteCodeReturn.setInviteCode(inviteCode);
                } else {//不存在
                    String inviteCode = String.format("%04d", new Random().nextInt(10000));
                    while (redisCache.sismember(KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":S",
                            inviteCode)) {
                        inviteCode = String.format("%04d", new Random().nextInt(10000));
                    }
                    //添加到set
                    redisCache.sadd(KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":S",
                            inviteCode);
                    //添加到hash
                    redisCache.hset(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":" + findInviteCode.getCouponRuleCode() + ":H",
                            findInviteCode.getAddFriendsCode(), inviteCode);
                    //根据inviteCode索引关联信息
                    redisCache.set(
                            KeyConstant.COUPON_SHARE + findInviteCode.getShopNoWx() + ":"  + inviteCode, 
                            "2#" + findInviteCode.getShopNoWx() + "#" + findInviteCode.getCouponRuleCode() + "#" + findInviteCode.getAddFriendsCode());
                    findInviteCodeReturn.setInviteCode(inviteCode);
                }
            }

            logger.debug("findInviteCode(FindInviteCode) - end - return value={}", findInviteCodeReturn); 
            return findInviteCodeReturn;
        } catch (Exception e) {
            logger.error("优惠券分享，获取邀请码失败:{}", e);
            throw new TsfaServiceException(ErrorCode.COUPON_SHARE_FIND_INVITE_CODE_ERROR, "优惠券分享，获取邀请码失败");
        }
    }
    
    @Override
    public FindMemberInfoByInviteCodeReturn findMemberInfoByInviteCode(String shopNoWx, String inviteCode) throws TsfaServiceException {
        logger.debug("findMemberInfoByInviteCode(String shopNoWx={}, String inviteCode={}) - start", shopNoWx, inviteCode); 

        FindMemberInfoByInviteCodeReturn findMemberInfoByInviteCodeReturn = new FindMemberInfoByInviteCodeReturn();
        try {
            String info = redisCache.get(KeyConstant.COUPON_SHARE + shopNoWx + ":" + inviteCode);
            if (StringUtils.isBlank(info)) {
                logger.debug("findMemberInfoByInviteCode(String, String) - end - return 为空"); 
                return null;
            }
            String[] infos = info.split("#");
            Integer type = Integer.valueOf(infos[0]);
            findMemberInfoByInviteCodeReturn.setType(type);
            findMemberInfoByInviteCodeReturn.setShopNoWx(infos[1]);
            findMemberInfoByInviteCodeReturn.setCouponRuleCode(infos[2]);
            if (type == 1) {
                findMemberInfoByInviteCodeReturn.setMemberNo(infos[3]);
                findMemberInfoByInviteCodeReturn.setMemberNoGm(infos[4]);
            } else {
                findMemberInfoByInviteCodeReturn.setAddFriendsCode(infos[3]);
            }
            
            logger.debug("findMemberInfoByInviteCode(String, String) - end - return value={}", findMemberInfoByInviteCodeReturn); 
            return findMemberInfoByInviteCodeReturn;
        } catch (Exception e) {
            logger.error("优惠券分享，根据邀请码查询客户信息失败:{}", e);
            throw new TsfaServiceException(ErrorCode.COUPON_SHARE_FIND_MEMBER_BY_INVITE_CODE_ERROR, "优惠券分享，根据邀请码查询客户信息失败");
        }
    }
    @Override
    public void sendCouponAfterAddWxFriends(SendCouponAfterAddWxFriends sendCouponAfterAddWxFriends) throws TsfaServiceException {
        logger.debug("sendCouponAfterAddWxFriends(SendCouponAfterAddWxFriends sendCouponAfterAddWxFriends={}) - start", sendCouponAfterAddWxFriends); 

        try {
        	FindMerchantParams findMerchantParams = new FindMerchantParams();
            String groupName = "push_switch";//组名
            findMerchantParams.setGroupName(groupName);
            findMerchantParams.setMerchantNo(sendCouponAfterAddWxFriends.getMerchantNo());
            Map<String, String> merchantParamsMap = merchantParamsService.findMerchantParamsByGroup(findMerchantParams);
           
                if (MapUtils.isNotEmpty(merchantParamsMap)) {
                	if (merchantParamsMap.get("new_coupon") != null && "ON".equals(merchantParamsMap.get("new_coupon"))) {
    					//商户筛选  ，按商户配置优惠券发送
                		FindCouponRule findCouponRule = new FindCouponRule();
                		findCouponRule.setRuleStatus(RuleStatus.YES.toString());
                		findCouponRule.setToCoupon(ToCoupon.NONE.toString());
                		findCouponRule.setRealName(RealName.YES.toString());
                		findCouponRule.setProduce("YES");
                		findCouponRule.setMerchantNo(sendCouponAfterAddWxFriends.getMerchantNo());
                		
                    	List<FindCouponRuleReturn> findCouponRuleList = this.findCouponRuleList(findCouponRule);
                    	
                    	if(CollectionUtils.isEmpty(findCouponRuleList)) {
                    		return;
                    	}
                    	
                    	SendImChatByNonMember chatByNonMember = new SendImChatByNonMember();
                        chatByNonMember.setNoWxShop(sendCouponAfterAddWxFriends.getNoWxShop());
                        chatByNonMember.setNoWx(sendCouponAfterAddWxFriends.getNoWx());
                        chatByNonMember.setType("491");//优惠券
                        
                        //查询商户信息
                        FindMerchantDto findMerchantDto = new FindMerchantDto();
                        findMerchantDto.setMerchantNo(sendCouponAfterAddWxFriends.getMerchantNo());
                        FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
                        //设置分享的title和desc
                        if(findMerchantReturnDto != null) {
                        	chatByNonMember.setShareTitle("恭喜您获得" + findMerchantReturnDto.getMerchantName() + "的优惠券！");
                            chatByNonMember.setShareDes("点击立即领取……");
                        }
                        if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
                            String uploadUrl = localCacheSystemParams.getSystemParam("ms", "upload", "uploadUrl");  // 商户图片静态地址
                            chatByNonMember.setResources(uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
                        } else {
                            String couponUrl = localCacheSystemParams.getSystemParam("api", "logo", "couponLogoUrl");
                            chatByNonMember.setResources(couponUrl);
                        }
                        
                        String cp_invite_url =
                                localCacheSystemParams.getSystemParam("cp", "invite_url", "cp_invite_url");
                        Map<String, Object> paramsMap = new HashMap<>(4);
                        paramsMap.put("noWxGm", sendCouponAfterAddWxFriends.getNoWxShop());
                        paramsMap.put("addFriendsCode", sendCouponAfterAddWxFriends.getAddFriendsCode());
                        paramsMap.put("ShareStatus", "MEMBER");
                        paramsMap.put("merchantNo", sendCouponAfterAddWxFriends.getMerchantNo());
//                        paramsMap.put("shopNo", sendCouponAfterAddWxFriends.getShopNo());
                        paramsMap.put("ts", new Date().getTime());
                        String resultUrl = cp_invite_url + "params=" + JsonUtils.jsonFromObject(paramsMap);
//                                cp_invite_url + "noWxGm=" + sendCouponAfterAddWxFriends.getNoWxShop()
//                                        + "&addFriendsCode=" + sendCouponAfterAddWxFriends.getAddFriendsCode()
//                                        + "&ShareStatus=MEMBER"
//                                        + "&merchantNo=" + sendCouponAfterAddWxFriends.getMerchantNo();
                                
                        chatByNonMember.setShareUrl(resultUrl);
                        chatByNonMember.setMsgSource(MessageSource.SA.getCode());
                        
                        imChatInfoService.sendChatByNonMember(chatByNonMember);
    				}
    			}
            

        } catch (Exception e) {
            logger.error("添加微信好友成功后推送领取优惠券链接失败:{}", e);
            throw new TsfaServiceException(ErrorCode.COUPON_SHARE_FIND_MEMBER_BY_INVITE_CODE_ERROR, "优惠券分享，根据邀请码查询客户信息失败");
        }

        logger.debug("sendCouponAfterAddWxFriends(SendCouponAfterAddWxFriends) - end"); 
    }
	@Override
	public List<FindCouponRuleReturn> queryCouponRulePast() throws TsfaServiceException {
		List<FindCouponRuleReturn> list =Lists.newArrayList();
		try {
			list = couponRuleDao.queryCouponRulePast();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}catch (Exception e) {
			logger.error("获取过期优惠券信息错误！",e);
		}
		
		return list;
	}

}
