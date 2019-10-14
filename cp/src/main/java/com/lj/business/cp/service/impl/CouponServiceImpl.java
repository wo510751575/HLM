package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.couponRecords.AddCouponRecords;
import com.lj.business.cp.dao.ICouponDao;
import com.lj.business.cp.dao.ICouponMemberRelationDao;
import com.lj.business.cp.dao.ICouponRuleDao;
import com.lj.business.cp.domain.Coupon;
import com.lj.business.cp.domain.CouponMemberRelation;
import com.lj.business.cp.domain.CouponRule;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.coupon.AddCouponVo;
import com.lj.business.cp.dto.coupon.DelCoupon;
import com.lj.business.cp.dto.coupon.EmployCoupon;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPage;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.dto.coupon.FindCouponReturn;
import com.lj.business.cp.dto.coupon.FindMemberInfoByInviteCodeReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationReturn;
import com.lj.business.cp.dto.couponMemberRelation.UpdateCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.AddCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.dto.memberInviteRecord.AddMemberInviteRecord;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.emus.RuleStatus;
import com.lj.business.cp.emus.ToCoupon;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRecordsService;
import com.lj.business.cp.service.ICouponRuleExService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.cp.service.ICouponService;
import com.lj.business.cp.service.IMemberInviteRecordService;
import com.lj.business.member.dto.FindGuidMemberDto;
import com.lj.business.member.dto.FindMerchantDto;
import com.lj.business.member.dto.FindMerchantReturnDto;
import com.lj.business.member.dto.FindPersonMemberBaseList;
import com.lj.business.member.dto.FindPersonMemberBaseReturnList;
import com.lj.business.member.dto.GuidMemberReturnDto;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.DistributionMemberReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminal;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IMerchantService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.RedisCache;
import com.lj.distributelock.IDistributeLock;

import edu.emory.mathcs.backport.java.util.Collections;

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
public class CouponServiceImpl implements ICouponService {
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);
	@Resource
	private ICouponDao couponDao;
	@Resource
	private ICouponMemberRelationDao couponMemberRelationDao;
	@Resource
	private ICouponRuleDao couponRuleDao;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Resource
	private IMerchantService merchantService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private RedisCache redisCache;
	@Resource
	private ICouponMemberRelationService couponMemberRelationService;
	@Resource
	private ICouponRuleExService couponRuleExService;
	@Resource
	private ICouponRuleService couponRuleService;
	@Resource
	private IMemberInviteRecordService memberInviteRecordService;
	@Resource
	private IGuidMemberService guidMemberService;
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	@Resource
	private ICouponRecordsService couponRecordsService;
	@Resource
	private IAddFriendsService addFriendsService;
	@Resource
	private IPersonMemberService personMemberService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Resource 
	private IDistributeLock redisLock;
	
	/***
	 * memberNoGm:导购编号 merchantNo:商户编号 ruleNo：优惠券编号 shopNo:终端编号 memberNo：会员编号
	 */
	private static final String MEMBERNO_GM = "memberNoGm";
	private static final String MERCHANT_NO = "merchantNo";
	private static final String RULE_NO = "ruleNo";
	private static final String MEMBER_NO = "memberNo";

	/**
	 * couponRemark:备注 title:标题 useScope:使用范围 shopName:终端名称
	 */
	private static final String COUPON_REMARK = "couponRemark";
	private static final String TITLE = "title";
	private static final String USE_SCOPE = "useScope";

	/**
	 * resultUrl:URL地址 noWx:微信号 nickNameWx：微信称呢
	 */
	private static final String NO_WX = "noWx";
	private static final String NICK_NAME_WX = "nickNameWx";

	/**
	 * 系统名
	 */
	private static final String CP = "cp";
	private static final String MS = "ms";
	private static final String API = "api";
	/**
	 * 参数名称
	 */
	private static final String RESULT_URL = "resultUrl";
	private static final String UPLOAD_URL = "uploadUrl";
	private static final String COUPON_LOGO_URL = "couponLogoUrl";

	/**
	 * 組名
	 */
	private static final String UPLOAD = "upload";
	private static final String LOGO = "logo";
	private static final String COUPON_RESULT_URL = "coupon_result_url";

	/**
	 * 商戶LOGO链接URL
	 */
	private static final String MERCHANT_LOGO_URL = "merchantLogoUrl";

	/**
	 * message:消息提示 status：领取状态 data:领取成功后返回该领取人的信息到前端并缓存
	 */
	private static final String MESSAGE = "message";
	private static final String STATUS = "status";
	private static final String DATA = "data";

	@Override
	public void addCoupon(AddCoupon addCoupon) throws TsfaServiceException {
		logger.debug("addCoupon(AddCoupon addCoupon={}) - start", addCoupon);

		AssertUtils.notNull(addCoupon);
		try {
			Coupon coupon = new Coupon();
			// add数据录入
			if(StringUtils.isEmpty(addCoupon.getCode())) {
				coupon.setCode(GUID.generateCode());
			}else {
				coupon.setCode(addCoupon.getCode());
			}
			coupon.setMerchantNo(addCoupon.getMerchantNo());
			coupon.setMerchantName(addCoupon.getMerchantName());
			coupon.setShopWx(addCoupon.getShopWx());
			coupon.setRuleNo(addCoupon.getRuleNo());
			coupon.setCouponNo(addCoupon.getCouponNo());
			coupon.setCouponStatus(addCoupon.getCouponStatus());
			coupon.setUseDate(addCoupon.getUseDate());
			coupon.setCreateId(addCoupon.getCreateId());
			coupon.setCreateDate(new Date());
			couponDao.insertSelective(coupon);
			logger.debug("addCoupon(AddCoupon) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_ADD_ERROR, "新增优惠券信息错误！", e);
		}
	}

	@Override
	public void delCoupon(DelCoupon delCoupon) throws TsfaServiceException {
		logger.debug("delCoupon(DelCoupon delCoupon={}) - start", delCoupon);

		AssertUtils.notNull(delCoupon);
		AssertUtils.notNull(delCoupon.getCode(), "Code不能为空！");
		try {
			couponDao.deleteByPrimaryKey(delCoupon.getCode());
			logger.debug("delCoupon(DelCoupon) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_DEL_ERROR, "删除优惠券信息错误！", e);

		}
	}

	@Override
	public void updateCoupon(UpdateCoupon updateCoupon) throws TsfaServiceException {
		logger.debug("updateCoupon(UpdateCoupon updateCoupon={}) - start", updateCoupon); 

		AssertUtils.notNull(updateCoupon);
		AssertUtils.notNullAndEmpty(updateCoupon.getCode(), "Code不能为空");
		try {
			Coupon coupon = new Coupon();
			// update数据录入
			coupon.setCode(updateCoupon.getCode());
			coupon.setMerchantNo(updateCoupon.getMerchantNo());
			coupon.setMerchantName(updateCoupon.getMerchantName());
			coupon.setShopWx(updateCoupon.getShopWx());
			coupon.setRuleNo(updateCoupon.getRuleNo());
			coupon.setCouponNo(updateCoupon.getCouponNo());
			coupon.setCouponStatus(updateCoupon.getCouponStatus());
			coupon.setUseDate(updateCoupon.getUseDate());
			coupon.setUpdateId(updateCoupon.getUpdateId());
			coupon.setUpdateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(couponDao.updateByPrimaryKeySelective(coupon));
			logger.debug("updateCoupon(UpdateCoupon) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_UPDATE_ERROR, "查找优惠券信息错误！", e);
		}
	}

	@Override
	public FindCouponReturn findCoupon(FindCoupon findCoupon) throws TsfaServiceException {
		logger.debug("findCoupon(FindCoupon findCoupon={}) - start", findCoupon); 

		AssertUtils.notNull(findCoupon);
		AssertUtils.notAllNull(findCoupon.getCode(), "Code不能为空");
		try {
			Coupon coupon = couponDao.selectByPrimaryKey(findCoupon.getCode());
			if (coupon == null) {
				return null;
			}
			FindCouponReturn findCouponReturn = new FindCouponReturn();
			// find数据录入
			findCouponReturn.setCode(coupon.getCode());
			findCouponReturn.setMerchantNo(coupon.getMerchantNo());
			findCouponReturn.setMerchantName(coupon.getMerchantName());
			findCouponReturn.setShopWx(coupon.getShopWx());
			findCouponReturn.setRuleNo(coupon.getRuleNo());
			findCouponReturn.setCouponNo(coupon.getCouponNo());
			findCouponReturn.setCouponStatus(coupon.getCouponStatus());
			findCouponReturn.setUseDate(coupon.getUseDate());
			findCouponReturn.setUpdateId(coupon.getUpdateId());
			findCouponReturn.setUpdateDate(coupon.getUpdateDate());
			findCouponReturn.setCreateId(coupon.getCreateId());
			findCouponReturn.setCreateDate(coupon.getCreateDate());

			logger.debug("findCoupon(FindCoupon) - end - return value={}", findCouponReturn); 
			return findCouponReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;

		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_ERROR, "查找优惠券信息错误！", e);
		}

	}

	@Override
	public Page<FindCouponPageReturn> findCouponPage(FindCouponPage findCouponPage) throws TsfaServiceException {
		logger.debug("findCouponPage(FindCouponPage findCouponPage={}) - start", findCouponPage); 
		AssertUtils.notNull(findCouponPage);
		List<FindCouponPageReturn> returnList = null;
		int count = 0;
		try {
			count = couponDao.findCouponPageCount(findCouponPage);
			if(count>0) {
				returnList = couponDao.findCouponPage(findCouponPage);
			}
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR, "查找优惠券信息错误！", e);
		}
		Page<FindCouponPageReturn> returnPage = new Page<FindCouponPageReturn>(returnList, count, findCouponPage);
		logger.debug("findCouponPage(FindCouponPage) - end - return value={}", returnPage); 
		return returnPage;
	}

	@Override
	public List<FindCouponPageReturn> findCouponReturnList(FindCoupon findCoupon) throws TsfaServiceException {
		AssertUtils.notNull(findCoupon);
		List<FindCouponPageReturn> returnList = null;
		try {
			returnList = couponDao.findCouponReturnList(findCoupon);
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR, "查找优惠券信息错误！", e);
		}

		return returnList;
	}

	/**
	 * 该方法已经弃用(需求已经更改)
	 */
	@Deprecated
	public String updateCouponByCouponNo(UpdateCoupon updateCoupon) throws TsfaServiceException {
		FindCoupon findCoupon = new FindCoupon();
		findCoupon.setCouponNo(updateCoupon.getCouponNo());
		FindCouponReturn couponReturn = couponDao.findCouponByCouponNo(findCoupon);
		if (couponReturn == null) {
			return CouponStatus.NONE.getName();
		}
		// 优惠券已使用
		if (couponReturn != null
				&& StringUtils.equalsIgnoreCase(couponReturn.getCouponStatus(), CouponStatus.USED.toString())) {
			return couponReturn.getCouponStatus();
		}
		// 优惠券已过期
		if (couponReturn != null
				&& StringUtils.equalsIgnoreCase(couponReturn.getCouponStatus(), CouponStatus.EXPIRED.toString())) {
			return couponReturn.getCouponStatus();
		}
		UpdateCoupon uCoupon = new UpdateCoupon();
		uCoupon.setCouponNo(updateCoupon.getCouponNo());
		uCoupon.setCouponStatus(CouponStatus.USED.toString());
		uCoupon.setUseDate(new Date());
		uCoupon.setUpdateDate(new Date());
		couponDao.updateCouponByCouponNo(uCoupon); // 修改优惠券

		CouponMemberRelation couponMemberRelation = new CouponMemberRelation();
		couponMemberRelation.setCode(GUID.generateByUUID());
		couponMemberRelation.setMemberNoGm(updateCoupon.getMemberNoGm());
		couponMemberRelation.setMemberNameGm(updateCoupon.getMemberNameGm());
		couponMemberRelation.setCouponNo(updateCoupon.getCouponNo());
		couponMemberRelation.setCouponStatus(CouponStatus.USED.toString());
		couponMemberRelation.setUseDate(new Date());
		couponMemberRelation.setCreateDate(new Date());
		couponMemberRelation.setUpdateDate(new Date());
		couponMemberRelationDao.insertSelective(couponMemberRelation);

		return null;
	}

	/**
	 * 应用场景: 组装数据用 此方法执行优惠券群推任务,该方法由前端控制推送，只返回前端推送时的参数信息， 与推送后详情页面的数据信息
	 */
	@Override
	public Map<String, Object> insertCoupon(AddCouponVo addCouponVo) throws TsfaServiceException {
		// 修改以前的逻辑,按照新版优惠券流程走 luoshuming
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> map = Maps.newHashMap();

		map.put(MEMBERNO_GM, addCouponVo.getMemberNoGm());
		map.put(MERCHANT_NO, addCouponVo.getMerchantNo());
		map.put(RULE_NO, addCouponVo.getRuleNo());
		map.put(MEMBER_NO, addCouponVo.getMemberNo());
		// 优惠券领取url
		String cp_result_url = localCacheSystemParams.getSystemParam(CP, RESULT_URL, COUPON_RESULT_URL);

		paramMap.put(RESULT_URL, cp_result_url + JsonUtils.jsonFromObject(map) + "#");
		paramMap.put(NO_WX, addCouponVo.getNoWx()); // 微信号
		paramMap.put(NICK_NAME_WX, addCouponVo.getNickNameWx()); // 微信昵称
		CouponRule couponRule = couponRuleDao.selectByPrimaryKey(addCouponVo.getRuleNo());
		if (couponRule == null) {
			throw new TsfaServiceException(ErrorCode.COUPON_RULE_FIND_ERROR, "根据优惠券规则ID没有查询到优惠券规则");
		}
		// 新版优惠券改动
		paramMap.put(COUPON_REMARK, couponRule.getCouponRemark()); // 优惠券备注(规则)
		paramMap.put(TITLE, couponRule.getCouponName()); // 推送标题
		paramMap.put(USE_SCOPE, couponRule.getUseScope()); // 使用范围

		// 商户图片静态地址
		String uploadUrl = localCacheSystemParams.getSystemParam(MS, UPLOAD, UPLOAD_URL);

		FindMerchantDto findMerchantDto = new FindMerchantDto();
		findMerchantDto.setMerchantNo(addCouponVo.getMerchantNo());
		FindMerchantReturnDto findMerchantReturnDto = merchantService.selectMerchant(findMerchantDto);
		if (findMerchantReturnDto != null && StringUtils.isNotEmpty(findMerchantReturnDto.getLogoAddr())) {
			paramMap.put(MERCHANT_LOGO_URL, uploadUrl + findMerchantReturnDto.getLogoAddr()); // 商户图标
		} else {
			paramMap.put(MERCHANT_LOGO_URL, localCacheSystemParams.getSystemParam(API, LOGO, COUPON_LOGO_URL));
		}
		return paramMap;
	}

	/**
	 * 在制定优惠券规则后,系统生成优惠券 UseScope为ALL 则是商户下的全部终端都制券 否则只是生成部分终端的优惠券 shopNos 制券的终端编号
	 * shopName 名称 useNums 每个终端的制券数量(UseScope是ALL时,每个终端的数量都是统一的 )
	 */
	public int addCoupon(AddCouponRule addCouponRule) throws TsfaServiceException {
		AssertUtils.notNull(addCouponRule);
		AssertUtils.notNullAndEmpty(addCouponRule.getMerchantNo(),"商户号不能为空");
		logger.debug("addCoupon(AddCoupon addCoupon={}) - start", addCouponRule);
		int num = 0;	//制作券总数
		try {

			String ruleNo = addCouponRule.getCode();
			logger.debug("执行终端制券任务！");

			FindShopTerminal findShopTerminal = new FindShopTerminal();
			findShopTerminal.setMerchantNo(addCouponRule.getMerchantNo());
			List<FindShopTerminalReturn> list = shopTerminalService.findShopTerminalSelect(findShopTerminal);
			for (FindShopTerminalReturn findShopTerminalReturn : list) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						/**
						 * 线程名，用户生成code
						 * 多线程，UUID会重复
						 */
						String threadName = Thread.currentThread().getName();
						for (int i = 0; i < addCouponRule.getCouponAvgNum(); i++) {
							insertCoupon(ruleNo,findShopTerminalReturn, threadName);
						}
					}
				});
			}

			num = addCouponRule.getCouponAvgNum() * list.size();
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw new TsfaServiceException(ErrorCode.COUPON_UPDATE_ERROR, "新增优惠券信息错误！", e);
		} catch (Exception e) {
			logger.error("新增优惠券信息错误！", e);
		}
		return num;
	}

	
	/**
	 * 新增优惠群
	 * 
	 * @param ruleNo
	 * @param coupon
	 * @param findShopPageReturn
	 */
	private void insertCoupon(String ruleNo, FindShopTerminalReturn findShopTerminalReturn, String threadName) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char string = chars.charAt((int) (Math.random() * 26));
		String inviteCode = String.format("%04d", new Random().nextInt(10000000));
		
		try {
			AddCoupon addCoupon = new AddCoupon();
			addCoupon.setCode(threadName.substring(threadName.length()-1)+GUID.generateCode());
			addCoupon.setMerchantNo(findShopTerminalReturn.getMerchantNo());
			addCoupon.setMerchantName(findShopTerminalReturn.getMerchantName());
			addCoupon.setShopWx(findShopTerminalReturn.getNoWx());
			addCoupon.setCouponNo(string + inviteCode);//券号
			addCoupon.setRuleNo(ruleNo);			
			addCoupon.setCouponStatus(CouponStatus.UNUSED.toString());
			addCoupon.setCreateId(findShopTerminalReturn.getMerchantName());
			this.addCoupon(addCoupon);

		} catch (Exception e) {
			logger.error("生成优惠券失败 e={}", e);
		}
	}

	/**
	 * 应用场景:优惠券链依靠聚客APP导购端分享扩散到客户以及通过客户分享到朋友圈，微信好友，微博等
	 * 客户或好友通过此链接领取优惠券,此方法对应不同场景建立领取流程,绑定关联关系 本方法抛出的异常由调用方捕获展示
	 */
	@Override
	public Map<String, Object> gainCoupon(FindCoupon findCoupon) throws TsfaServiceException {
		logger.debug("findCouponByRuleNo(FindCoupon findCoupon={}) - start", findCoupon);
		Map<String, Object> map = Maps.newHashMap();
		try {
			if (StringUtils.isBlank((findCoupon.getShareStatus()))) {
				/**
				 * ShareStatus为空或等于null则标识该优惠券链接是由导购直接分享到客户 分享链接带有导购编号与客户编号，可以直接绑定邀请关系
				 * 
				 */
				map = memberGainCoupon(findCoupon, true);

			} else {
				if (StringUtils.isEmpty(findCoupon.getCouponCode())) {
					/**
					 * ShareStatus 不等于null 标识为客户分享的优惠券’此链接仅带有领取人的addFriendsCode微信好友id，没有领取人的信息
					 * CouponCode 优惠券邀请码为null则无法识别邀请人信息 ,无法判断与新增用户之间的关联关系 (用户不输入邀请码也可以领取优惠券)
					 * 该情况仅识别为微信好友，此时领取人并未与导购作任何关联，也没有memberNo参数信息，无法建立邀请关系
					 */
					map = friendGainCoupon(findCoupon);

				} else {
					// 获取缓存信息 根据导购微信号与邀请码获取
					FindMemberInfoByInviteCodeReturn byInviteCodeReturn = couponRuleService
							.findMemberInfoByInviteCode(findCoupon.getNoWxGm(), findCoupon.getCouponCode());
					if (byInviteCodeReturn != null) {
						if (StringUtils.isEmpty(byInviteCodeReturn.getMemberNo())
								&& StringUtils.isNotEmpty(byInviteCodeReturn.getAddFriendsCode())) {
							/**
							 * 应用场景 ：导购分享优惠券链接出去给自己客户,客户在分享到朋友圈/微博/微信朋友时
							 * 无法识别领取人的用户信息或当分享人只是微信好友的关系时,此时并没有与导购作关联，无法与导购绑定关系
							 * （如果识别该优惠券链接源头是某个导购分享出去的则该领取人绑定在该导购下）
							 * 
							 */
							map = addFriendCoupon(findCoupon, byInviteCodeReturn);
						} else {
							/**
							 * 应用场景:用户输入的优惠券邀请码为分享人的邀请码,并通过缓存获取到分享人的用户信息 系统校验到分享人信息在系统存在记录并与导购绑定了关联关系
							 * 则与分享人建立绑定关系,并把领取人归属到分享人的导购下(终端导购新增客户的一种方式后期可能会添加奖励条件)
							 */
							DistributionMemberReturn distributionMemberReturn = null;
							try {
								/**
								 * 与领取人绑定关系，归属到分享人的导购下
								 */
								DistributionMember distributionMember = new DistributionMember();
								distributionMember.setCode(findCoupon.getAddFriendsCode());
								distributionMember.setMemberNoGm(byInviteCodeReturn.getMemberNoGm());
								distributionMemberReturn = addFriendsService.distributionMember(distributionMember);

								findCoupon.setRuleNo(byInviteCodeReturn.getCouponRuleCode());
								findCoupon.setMemberNoGm(distributionMemberReturn.getMemberNoGm());
								findCoupon.setMemberNo(distributionMemberReturn.getMemberNo());
								findCoupon.setMemberName(distributionMemberReturn.getMemberName());
								findCoupon.setMemberNameGm(distributionMemberReturn.getMemberNameGm());
								findCoupon.setInvitationNo(byInviteCodeReturn.getMemberNo());
								findCoupon.setAddFriendsCode(findCoupon.getAddFriendsCode());
								/**
								 * 应用场景:当与分享人导购做绑定身份后，系统识别出领取人的所有信息 程序流程与导购直接分享给客户的流程时一样的，所以直接调取导购直接分享后的领取人的领取流程
								 * 
								 * boolean = false 标注为该链接有导购的客户分享出来的，并不是导购直接分享的，用作优惠券推荐人绑定时判断的条件
								 */
								map = memberGainCoupon(findCoupon, false);
							} catch (TsfaServiceException e) {
								logger.debug(e.getMessage(), e);

								/**
								 * 应用场景:分享人分享优惠券链到朋友圈或则微博,微信朋友等场景 领取人可能已经被该商户的某一个导购下绑定了关系
								 * 并可能领取过优惠券(每一种优惠券只能领取一次,不同优惠券可以多次领取) ,这时与分享的导购去做绑定时就会抛出已关联
								 * 绑定了其他或本终端的导购，无法关联绑定(系统则会抛出异常信息)但这种条件下用户是可以去领取优惠券的 所以跳出认领流程执行正常领取流程
								 */
								if (e.getExceptionCode().equals(
										com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR)) {
									logger.debug("执行已关联过导购的客户领取券方法！");
									FindCouponRuleReturn couponRuleReturn = null;

									FindCouponRule couponRule = new FindCouponRule();
									couponRule.setCode(byInviteCodeReturn.getCouponRuleCode());
									couponRule.setRuleStatus(RuleStatus.YES.toString());
									couponRule.setToCoupon(ToCoupon.NONE.toString());
									couponRuleReturn = couponRuleService.findCouponRule(couponRule);
									Date startTime = com.lj.base.core.util.DateUtils
											.getDateByLastSeconds(couponRuleReturn.getEndDate());
									if (startTime.getTime() < new Date().getTime()) {
										throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR_TIME,
												"优惠券已过期！");
									}

									// 判断优惠券剩余数量
									FindCouponRuleEx couponRuleEx = new FindCouponRuleEx();
									couponRuleEx.setRuleCode(byInviteCodeReturn.getCouponRuleCode());
									FindCouponRuleExReturn ruleExReturn = couponRuleExService
											.findCouponRuleExByRuleCode(couponRuleEx);
									if (ruleExReturn.getSurplusNum() <= 0) {
										throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
									}

									// 查找客户信息
									FindAddFriends findAddFriends = new FindAddFriends();
									findAddFriends.setCode(findCoupon.getAddFriendsCode());
									FindAddFriendsReturn addFriendsReturn = addFriendsService
											.findAddFriends(findAddFriends);

									// 用户领取优惠券次数,超过次数不提示信息直接返回该券信息
									findCoupon.setRuleNo(byInviteCodeReturn.getCouponRuleCode());
									List<FindCouponMemberRelationReturn> relationReturns = couponMemberRelationService
											.findMemberCoupon(findCoupon);
									if (relationReturns.size() > 0) {
										// 达到限领次数后返回最后领取的优惠券信息给客户
										FindCouponMemberRelationReturn coupon = relationReturns.get(0);
										// 返回客户信息
										couponRuleReturn.setMemberNo(addFriendsReturn.getMemberNo());
										couponRuleReturn.setCouponNo(coupon.getCouponNo());
										couponRuleReturn.setMemberNoGm(addFriendsReturn.getMemberNoGm());
										couponRuleReturn.setCouponStatus(coupon.getCouponStatus());
										map.put(MESSAGE, "你已经领取了该优惠券！");
										map.put(STATUS, false);
										map.put(DATA, couponRuleReturn);
										return map;
									}
									/**
									 * 找不到优惠券信息时抛出友好优惠券领取完提示
									 */
//									findCoupon.setShopNo(addFriendsReturn.getShopNo());
									FindCouponReturn findCouponReturn = couponDao.findCouponByRuleNo(findCoupon);
									if (findCouponReturn == null) {
										throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
									}
									// 领取后同步更新状态为已领取
									UpdateCoupon uCoupon = new UpdateCoupon();
									uCoupon.setCouponNo(findCouponReturn.getCouponNo());
									uCoupon.setCouponStatus(CouponStatus.IS_GET.toString());
									couponDao.updateCouponByCouponNo(uCoupon);

									// 更新规则扩展表信息
									UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
									updateCouponRuleEx.setRuleCode(byInviteCodeReturn.getCouponRuleCode());
									couponRuleExService.updateCouponUseNum(updateCouponRuleEx);

									// 新增关联关系
									AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
									addCouponMemberRelation.setCouponNo(findCouponReturn.getCouponNo());
									addCouponMemberRelation.setCouponStatus(CouponStatus.UNUSED.toString());
									addCouponMemberRelation.setMemberNo(addFriendsReturn.getMemberName());
									addCouponMemberRelation.setMemberName(addFriendsReturn.getMemberNo());
									// 查找导购信息，关联导购是推送券的店员不是客户归属的导购
									FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
									findGuidMemberDto.setMemberNo(byInviteCodeReturn.getMemberNoGm());
									GuidMemberReturnDto guidMemberReturnDto = guidMemberService
											.findGuid(findGuidMemberDto);
									addCouponMemberRelation.setMemberNoGm(guidMemberReturnDto.getMemberNo());
									addCouponMemberRelation.setMemberNameGm(guidMemberReturnDto.getMemberName());
									addCouponMemberRelation.setAddFriendsCode(findCoupon.getAddFriendsCode());
									addCouponMemberRelation.setRuleNo(findCoupon.getRuleNo());
									addCouponMemberRelation.setCreateDate(new Date());
									couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation);

									// 查找邀请人信息
									FindPersonMemberBaseList findPersonMemberBaseList = new FindPersonMemberBaseList();
									findPersonMemberBaseList.setMemberNo(byInviteCodeReturn.getMemberNo());
									FindPersonMemberBaseReturnList baseReturnList = personMemberBaseService
											.findPersonMemberBaseList(findPersonMemberBaseList);

									// 新增客户邀请信息 (邀请人归属不同导购)
									AddMemberInviteRecord addMemberInviteRecord = new AddMemberInviteRecord();
									addMemberInviteRecord.setMemberMobile(baseReturnList.getMobile());
									addMemberInviteRecord.setMemberName(baseReturnList.getMemberName());
									addMemberInviteRecord.setMemberNo(baseReturnList.getPmMemberNo());
									addMemberInviteRecord.setInvitedMobile(
											addFriendsReturn.getMobile() == null ? null : addFriendsReturn.getMobile());
									addMemberInviteRecord.setInvitedDate(new Date());
									addMemberInviteRecord.setMemberNameInvited(addFriendsReturn.getMemberName());
									addMemberInviteRecord.setMemberWxInvited(
											addFriendsReturn.getNoWx() == null ? null : addFriendsReturn.getNoWx());
									addMemberInviteRecord
											.setMemberHeadWxInvited(addFriendsReturn.getHeadAddress() == null ? null
													: addFriendsReturn.getHeadAddress());
									addMemberInviteRecord.setRuleCode(findCoupon.getRuleNo());
									addMemberInviteRecord.setCreateDate(new Date());
									memberInviteRecordService.addMemberInviteRecord(addMemberInviteRecord);

									// 返回优惠券信息
									couponRuleReturn.setCouponNo(findCouponReturn.getCouponNo());
									couponRuleReturn.setCouponStatus(CouponStatus.IS_GET.toString());
									couponRuleReturn.setAddFriendsCode(findCoupon.getAddFriendsCode());
									map.put(MESSAGE, "领取成功！");
									map.put(STATUS, Boolean.TRUE);
									map.put(DATA, couponRuleReturn);

								} else {
									throw new TsfaServiceException(ErrorCode.COUPON_NOT_EXIST_ERROR, "系统异常,无法领取优惠券！");
								}

							}
						}
					} else {
						/**
						 * 应用场景：当用户输入的邀请识别码系统无法识别时，无法做出与分享人的关联关系也无法绑定导购信息
						 * 此时系统把领取人当作微信好友关系处理，不做绑定,由导购手动绑定关系(绑定时不会把信息反馈到优惠券系统)
						 * 此种场景也可以领取优惠券信息,系统跳过绑定关系逻辑，只执行登记领券的信息，并与优惠券绑定关系
						 */
						map = friendGainCoupon(findCoupon);
					}
				}
			}
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw new TsfaServiceException(ErrorCode.COUPON_NOT_EXIST_ERROR, "查找优惠券信息错误！", e);
		} catch (Exception e) {
			logger.error("查询优惠券信息错误！", e);
		}
		return map;
	}

	private Map<String, Object> addFriendCoupon(FindCoupon findCoupon,
			FindMemberInfoByInviteCodeReturn byInviteCodeReturn) {
		logger.debug(
				"addFriendCoupon(FindCoupon findCoupon,FindMemberInfoByInviteCodeReturn byInviteCodeReturn){} start"
						+ "优惠券信息：" + findCoupon + "缓存参数：" + byInviteCodeReturn);
		Map<String, Object> map = Maps.newHashMap();
		FindAddFriends findAddFriends = new FindAddFriends();
		findAddFriends.setCode(byInviteCodeReturn.getAddFriendsCode());
		FindAddFriendsReturn addFriendsReturn = addFriendsService.findAddFriends(findAddFriends);
		if (addFriendsReturn == null) {
			throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.ADD_FRIENDS_NOT_EXIST_ERROR,
					"微信好友信息不存在");
		}
		//
		/**
		 * 导购认领可后会生成memberNo客户编号 邀请人分享后被导购领取,此时可以通过缓存信息的微信好友code找出分享人被导购认领
		 * 此种情况则把优惠券领取人归属到该导购下
		 */
		if (StringUtils.isNotBlank(addFriendsReturn.getMemberNo())) {
			// 认领导购
			DistributionMember distributionMember = new DistributionMember();
			distributionMember.setCode(findCoupon.getAddFriendsCode());
			distributionMember.setMemberNoGm(addFriendsReturn.getMemberNoGm());
			DistributionMemberReturn distributionMemberReturn = addFriendsService
					.distributionMember(distributionMember);

			// 按导购分享处理 (优惠券关联分享人导购信息)
			findCoupon.setRuleNo(byInviteCodeReturn.getCouponRuleCode());
			findCoupon.setMemberNoGm(distributionMemberReturn.getMemberNoGm());
			findCoupon.setMemberNo(distributionMemberReturn.getMemberNo());
			findCoupon.setMemberName(distributionMemberReturn.getMemberName());
			findCoupon.setMemberNameGm(distributionMemberReturn.getMemberNameGm());
			findCoupon.setInvitationNo(addFriendsReturn.getMemberNo());

			/**
			 * 应用场景:当与分享人导购做绑定身份后，系统识别出领取人的所有信息 程序流程与导购直接分享给客户的流程时一样的，所以直接调取导购直接分享后的领取人的领取流程
			 * 
			 * boolean = false 标注为该链接有导购的客户分享出来的，并不是导购直接分享的，用作优惠券推荐人绑定时判断的条件
			 */
			return memberGainCoupon(findCoupon, false);
		}

		// 优惠券规则
		FindCouponRule couponRule = new FindCouponRule();
		couponRule.setCode(byInviteCodeReturn.getCouponRuleCode());
		FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(couponRule);

		Date startTime = com.lj.base.core.util.DateUtils.getDateByLastSeconds(couponRuleReturn.getEndDate());
		if (startTime.getTime() < new Date().getTime()) {
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR_TIME, "优惠券已过期！");
		}

		// 判断优惠券剩余数量
		FindCouponRuleEx couponRuleEx = new FindCouponRuleEx();
		couponRuleEx.setRuleCode(byInviteCodeReturn.getCouponRuleCode());
		FindCouponRuleExReturn ruleExReturn = couponRuleExService.findCouponRuleExByRuleCode(couponRuleEx);
		if (ruleExReturn.getUseNum() <= 0) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}
		// 用户领取优惠券次数,超过次数不提示信息直接返回该券信息
		findCoupon.setRuleNo(byInviteCodeReturn.getCouponRuleCode());
		List<FindCouponMemberRelationReturn> relationReturns = couponMemberRelationService.findMemberCoupon(findCoupon);
		if (relationReturns.size() > 0) {
			// 达到限领次数后返回最后领取的优惠券信息给微信好友
			FindCouponMemberRelationReturn coupon = relationReturns.get(0);
			couponRule = new FindCouponRule();
			couponRule.setCode(couponRuleReturn.getCode());
			couponRuleReturn.setCouponNo(coupon.getCouponNo());
			couponRuleReturn.setAddFriendsCode(coupon.getAddFriendsCode());
			couponRuleReturn.setCouponStatus(coupon.getCouponStatus());
			map.put(MESSAGE, "你已领取过该优惠券！");
			map.put(DATA, couponRuleReturn);
			map.put(STATUS, Boolean.TRUE);
			return map;
		}
		FindCouponReturn findCouponReturn = couponDao.findCouponByRuleNo(findCoupon);
		if (findCouponReturn == null) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}

		// 领取后同步更新状态为已领取
		UpdateCoupon uCoupon = new UpdateCoupon();
		uCoupon.setCouponNo(findCouponReturn.getCouponNo());
		uCoupon.setCouponStatus(CouponStatus.IS_GET.toString());
		couponDao.updateCouponByCouponNo(uCoupon);
		// 新增关联关系
		AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
		addCouponMemberRelation.setCouponNo(findCouponReturn.getCouponNo());
		addCouponMemberRelation.setCouponStatus(CouponStatus.UNUSED.toString());
		addCouponMemberRelation.setAddFriendsCode(findCoupon.getAddFriendsCode());
		addCouponMemberRelation.setRuleNo(findCoupon.getRuleNo());
		addCouponMemberRelation.setCreateDate(new Date());
		couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation);

		// 更新规则扩展表信息
		UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
		updateCouponRuleEx.setRuleCode(findCoupon.getRuleNo());
		couponRuleExService.updateCouponUseNum(updateCouponRuleEx);

		findAddFriends = new FindAddFriends();
		findAddFriends.setCode(findCoupon.getAddFriendsCode());
		FindAddFriendsReturn findAddFriendsReturn = addFriendsService.findAddFriends(findAddFriends);
		// 新增微信好友邀请信息
		AddMemberInviteRecord addMemberInviteRecord = new AddMemberInviteRecord();
		addMemberInviteRecord.setAddFriendsCode(addFriendsReturn.getCode());
		addMemberInviteRecord
				.setNickName(addFriendsReturn.getNickName() == null ? null : addFriendsReturn.getNickName());
		addMemberInviteRecord.setInvitedDate(new Date());
		addMemberInviteRecord.setMemberNameInvited(findCoupon.getMemberName());
		addMemberInviteRecord.setMemberWxInvited(findAddFriendsReturn.getNoWx());
		addMemberInviteRecord.setMemberHeadWxInvited(findAddFriendsReturn.getHeadAddress());
		addMemberInviteRecord.setRuleCode(findCoupon.getRuleNo());
		addMemberInviteRecord.setCreateDate(new Date());
		memberInviteRecordService.addMemberInviteRecord(addMemberInviteRecord);

		// 返回客户信息
		couponRuleReturn.setAddFriendsCode(findCoupon.getAddFriendsCode());
		couponRuleReturn.setCouponNo(findCouponReturn.getCouponNo());
		couponRuleReturn.setCouponStatus(findCouponReturn.getCouponStatus());

		map.put(MESSAGE, "领取成功！");
		map.put(STATUS, true);
		map.put(DATA, couponRuleReturn);
		return map;
	}

	private Map<String, Object> friendGainCoupon(FindCoupon findCoupon) {
		logger.debug("friendGainCoupon(FindCoupon findCoupon){} staet " + findCoupon);

		Map<String, Object> map = Maps.newHashMap();
		FindCouponRule findCouponRule = new FindCouponRule();
		findCouponRule.setMerchantNo(findCoupon.getMerchantNo());
//		findCouponRule.setShopNo(findCoupon.getShopNo());
		findCouponRule.setRuleStatus(RuleStatus.YES.toString());
		findCouponRule.setToCoupon(ToCoupon.NONE.toString());
		findCouponRule.setProduce("YES");
		// 找出所有正常使用的优惠券规则
		List<FindCouponRuleReturn> findCouponRuleReturnList = couponRuleService.findCouponRuleList(findCouponRule);
		if (CollectionUtils.isEmpty(findCouponRuleReturnList)) {
			throw new TsfaServiceException(ErrorCode.MERCHANT_NO_COUPON_IS_NULL_ERROR, "没有找到商户优惠券信息！");
		}
		Collections.sort(findCouponRuleReturnList, new Comparator<FindCouponRuleReturn>() {
			@Override
			public int compare(FindCouponRuleReturn o1, FindCouponRuleReturn o2) {
				return (int) (o2.getCouponNotes() - o1.getCouponNotes());
			}
		});
		// 选择折扣力度最大的优惠券
		FindCouponRuleReturn findCouponRuleReturn = findCouponRuleReturnList.get(0);

		// 判断优惠券剩余数量
		FindCouponRuleEx couponRuleEx = new FindCouponRuleEx();
		couponRuleEx.setRuleCode(findCouponRuleReturn.getCode());
		FindCouponRuleExReturn ruleExReturn = couponRuleExService.findCouponRuleExByRuleCode(couponRuleEx);
		if (ruleExReturn.getSurplusNum() <= 0) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}

		// 用户领取优惠券次数超过限定次数,直接返回该券信息
		findCoupon.setRuleNo(findCouponRuleReturn.getCode());
		List<FindCouponMemberRelationReturn> relationReturns = couponMemberRelationService.findMemberCoupon(findCoupon);

		if (relationReturns.size() > 0) {
			// 达到限领次数后返回最后领取的优惠券信息给客户
			FindCouponMemberRelationReturn relationReturn = relationReturns.get(0);
			FindCouponRule couponRule = new FindCouponRule();
			couponRule.setCode(findCouponRuleReturn.getCode());
			FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(couponRule);
			// 返回微信好友信息
			couponRuleReturn.setAddFriendsCode(relationReturn.getAddFriendsCode());
			couponRuleReturn.setCouponNo(relationReturn.getCouponNo());
			couponRuleReturn.setCouponStatus(relationReturn.getCouponStatus());
			map.put(DATA, couponRuleReturn);
			map.put(STATUS, Boolean.TRUE);
			return map;

		}
		// 领取优惠券
		FindCouponReturn findCouponReturn = couponDao.findCouponByRuleNo(findCoupon);
		if (findCouponReturn == null) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}
		// 领取后同步更新状态为已领取
		UpdateCoupon uCoupon = new UpdateCoupon();
		uCoupon.setCouponNo(findCouponReturn.getCouponNo());
		uCoupon.setCouponStatus(CouponStatus.IS_GET.toString());
		couponDao.updateCouponByCouponNo(uCoupon);
		// 更新规则扩展表信息
		UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
		updateCouponRuleEx.setRuleCode(findCouponRuleReturn.getCode());
		couponRuleExService.updateCouponUseNum(updateCouponRuleEx);

		// 新增关联关系(还不是客户，无法关联导购信息，使用微信好友code与优惠券绑定关系)
		AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
		addCouponMemberRelation.setCouponNo(findCouponReturn.getCouponNo());
		addCouponMemberRelation.setCouponStatus(CouponStatus.UNUSED.toString());
		addCouponMemberRelation.setAddFriendsCode(findCoupon.getAddFriendsCode());
		addCouponMemberRelation.setRuleNo(findCoupon.getRuleNo());
		addCouponMemberRelation.setCreateDate(new Date());
		couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation);

		// 返回优惠券信息
		findCouponRuleReturn.setCouponNo(findCouponReturn.getCouponNo());
		findCouponRuleReturn.setCouponStatus(CouponStatus.IS_GET.toString());
		findCouponRuleReturn.setAddFriendsCode(findCoupon.getAddFriendsCode());

		map.put(MESSAGE, "领取成功！");
		map.put(STATUS, Boolean.TRUE);
		map.put(DATA, findCouponRuleReturn);
		return map;
	}

	private Map<String, Object> memberGainCoupon(FindCoupon findCoupon, boolean b) {
		logger.debug("memberGainCoupon(FindCoupon findCoupon,boolean b){} start" + findCoupon);

		Map<String, Object> map = Maps.newHashMap();
		FindCouponRuleReturn couponRuleReturn = null;
		FindCouponReturn findCouponReturn;
		FindCouponRule couponRule = new FindCouponRule();
		couponRule.setCode(findCoupon.getRuleNo());
		couponRule.setRuleStatus(RuleStatus.YES.toString());
		couponRule.setToCoupon(ToCoupon.NONE.toString());
		couponRuleReturn = couponRuleService.findCouponRule(couponRule);
		if (couponRuleReturn == null) {
			throw new TsfaServiceException(ErrorCode.MERCHANT_NO_COUPON_IS_NULL_ERROR, "商户没有该优惠券信息！");
		}

		Date startTime = com.lj.base.core.util.DateUtils.getDateByLastSeconds(couponRuleReturn.getEndDate());
		if (startTime.getTime() < new Date().getTime()) {
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR_TIME, "优惠券已过期！");
		}

		// 判断优惠券剩余数量
		FindCouponRuleEx couponRuleEx = new FindCouponRuleEx();
		couponRuleEx.setRuleCode(findCoupon.getRuleNo());
		FindCouponRuleExReturn ruleExReturn = couponRuleExService.findCouponRuleExByRuleCode(couponRuleEx);
		if (ruleExReturn.getSurplusNum() <= 0) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}
		// 用户领取优惠券次数,超过次数不提示信息直接返回该券信息
		List<FindCouponMemberRelationReturn> relationReturns = couponMemberRelationService.findMemberCoupon(findCoupon);
		if (relationReturns.size() > 0) {
			// 达到限领次数后返回最后领取的优惠券信息给客户
			FindCouponMemberRelationReturn coupon = relationReturns.get(0);
			couponRuleReturn.setMemberNo(coupon.getMemberNo());
			couponRuleReturn.setCouponNo(coupon.getCouponNo());
			couponRuleReturn.setMemberNoGm(coupon.getMemberNoGm());
			couponRuleReturn.setCouponStatus(coupon.getCouponStatus());

			map.put(MESSAGE, "你已经领取了该优惠券！");
			map.put(STATUS, true);
			map.put(DATA, couponRuleReturn);
			return map;
		}
		findCoupon.setMerchantNo(couponRuleReturn.getMerchantNo());
//		findCoupon.setShopNo(findCoupon.getShopNo());
		findCouponReturn = couponDao.findCouponByRuleNo(findCoupon);
		if (findCouponReturn == null) {
			throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券已领取完了！");
		}

		// 领取后同步更新状态为已领取
		UpdateCoupon uCoupon = new UpdateCoupon();
		uCoupon.setCouponNo(findCouponReturn.getCouponNo());
		uCoupon.setCouponStatus(CouponStatus.IS_GET.toString());
		couponDao.updateCouponByCouponNo(uCoupon);
		FindGuidMemberDto findGuidMemberDto = new FindGuidMemberDto();
		findGuidMemberDto.setMemberNo(findCoupon.getMemberNoGm());
		GuidMemberReturnDto guidMemberReturnDto = guidMemberService.findGuid(findGuidMemberDto);

		FindPersonMemberBaseList findPersonMemberBaseList = new FindPersonMemberBaseList();
		findPersonMemberBaseList.setMemberNo(findCoupon.getMemberNo());
		FindPersonMemberBaseReturnList baseReturnList = personMemberBaseService
				.findPersonMemberBaseList(findPersonMemberBaseList);

		if (baseReturnList == null) {
			throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR,
					"客户信息不存在！");
		}
		if (guidMemberReturnDto == null) {
			throw new TsfaServiceException(com.lj.business.member.constant.ErrorCode.GUID_MEMBER_UPDATE_ERROR,
					"导购信息不存在！");
		}
		// 新增关联关系
		AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
		addCouponMemberRelation.setCouponNo(findCouponReturn.getCouponNo());
		addCouponMemberRelation.setCouponStatus(CouponStatus.UNUSED.toString());
		addCouponMemberRelation.setMemberName(baseReturnList.getMemberName());
		addCouponMemberRelation.setMemberNameGm(guidMemberReturnDto.getMemberName());
		addCouponMemberRelation.setMemberNo(baseReturnList.getPmMemberNo());
		addCouponMemberRelation.setMemberNoGm(guidMemberReturnDto.getMemberNo());
		addCouponMemberRelation.setRuleNo(findCoupon.getRuleNo());
		addCouponMemberRelation.setAddFriendsCode(findCoupon.getAddFriendsCode());
		addCouponMemberRelation.setCreateDate(new Date());
		couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation);

		// 更新规则扩展表信息
		UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
		updateCouponRuleEx.setRuleCode(findCoupon.getRuleNo());
		couponRuleExService.updateCouponUseNum(updateCouponRuleEx);

		// 新增客户邀请信息 b == true 为导购邀请
		AddMemberInviteRecord addMemberInviteRecord = new AddMemberInviteRecord();
		if (b) {
			addMemberInviteRecord.setMemberMobile(guidMemberReturnDto.getMobile());
			addMemberInviteRecord.setMemberName(guidMemberReturnDto.getMemberName());
			addMemberInviteRecord.setMemberNo(guidMemberReturnDto.getMemberNo());
		} else {
			// 查找邀请人信息
			findPersonMemberBaseList = new FindPersonMemberBaseList();
			findPersonMemberBaseList.setMemberNo(findCoupon.getInvitationNo());
			FindPersonMemberBaseReturnList memberBaseReturnList = personMemberBaseService
					.findPersonMemberBaseList(findPersonMemberBaseList);
			addMemberInviteRecord.setMemberMobile(memberBaseReturnList.getMobile());
			addMemberInviteRecord.setMemberName(memberBaseReturnList.getMemberName());
			addMemberInviteRecord.setMemberNo(memberBaseReturnList.getPmMemberNo());
		}
		addMemberInviteRecord.setInvitedMobile(baseReturnList.getMobile() == null ? null : baseReturnList.getMobile());
		addMemberInviteRecord.setInvitedDate(new Date());
		addMemberInviteRecord.setMemberNameInvited(baseReturnList.getMemberName());
		addMemberInviteRecord.setMemberWxInvited(baseReturnList.getNoWx() == null ? null : baseReturnList.getNoWx());
		addMemberInviteRecord.setMemberHeadWxInvited(
				baseReturnList.getHeadAddress() == null ? null : baseReturnList.getHeadAddress());
		addMemberInviteRecord.setRuleCode(findCoupon.getRuleNo());
		addMemberInviteRecord.setCreateDate(new Date());
		memberInviteRecordService.addMemberInviteRecord(addMemberInviteRecord);

		// 返回客户信息
		couponRuleReturn.setMemberNo(findCoupon.getMemberNo());
		couponRuleReturn.setMemberNoGm(guidMemberReturnDto.getMemberNo());
		couponRuleReturn.setCouponNo(findCouponReturn.getCouponNo());
		couponRuleReturn.setCouponStatus(CouponStatus.IS_GET.toString());
		map.put(MESSAGE, "领取成功！");
		map.put(STATUS, Boolean.TRUE);
		map.put(DATA, couponRuleReturn);
		return map;
	}

	/**
	 * 应用场景:此方法作为优惠券使用方法,只简单的做修改状态与记录消息记录等信息用
	 */
	@Override
	public String couponConsumption(EmployCoupon employCoupon) throws TsfaServiceException {
		logger.debug("couponConsumption(EmployCoupon employCoupon)", employCoupon);
		AssertUtils.notNullAndEmpty(employCoupon.getCouponNo(), "优惠券编号不能为空");
		try {
			FindCoupon findCoupon = new FindCoupon();
			findCoupon.setCouponNo(employCoupon.getCouponNo());
			FindCouponReturn couponReturn = couponDao.findCouponByCouponNo(findCoupon);
			if (couponReturn == null) {
				throw new TsfaServiceException(ErrorCode.COUPON_IS_NULL_ERROR, "优惠券不存在！");
			}

			FindCouponRule couponRule = new FindCouponRule();
			couponRule.setCode(couponReturn.getRuleNo());
			FindCouponRuleReturn couponRuleReturn = couponRuleService.findCouponRule(couponRule);
			// 优惠券已使用
			if (couponReturn != null
					&& StringUtils.equalsIgnoreCase(couponReturn.getCouponStatus(), CouponStatus.USED.toString())) {
				throw new TsfaServiceException(ErrorCode.COUPON_USED_ERROR, "优惠券已使用");
			}
			// 优惠券已过期
			Date startTime = com.lj.base.core.util.DateUtils.getDateByLastSeconds(couponRuleReturn.getEndDate());
			if (startTime.getTime() < new Date().getTime()) {
				throw new TsfaServiceException(ErrorCode.COUPON_FIND_PAGE_ERROR_TIME, "优惠券已过期！");
			}
			UpdateCoupon uCoupon = new UpdateCoupon();
			AddCouponRecords addCouponRecords = new AddCouponRecords();

			// 更新状态
			uCoupon.setCouponNo(employCoupon.getCouponNo());
			uCoupon.setCouponStatus(CouponStatus.USED.toString());
			uCoupon.setUseDate(new Date());
			couponDao.updateCouponByCouponNo(uCoupon);

			// 更新扩展信息表
			UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
			updateCouponRuleEx.setRuleCode(couponReturn.getRuleNo());
			couponRuleExService.updateCouponCv(updateCouponRuleEx);

			// 更新关联优惠券关联表
			UpdateCouponMemberRelation updateCouponMemberRelation = new UpdateCouponMemberRelation();
			updateCouponMemberRelation.setCouponNo(couponReturn.getCouponNo());
			couponMemberRelationService.updateByCouponStatus(updateCouponMemberRelation);
			// 新增消费记录
			if (StringUtils.isEmpty(employCoupon.getMemberNo())) {
				// 该消费者没有归属导购
				FindAddFriends findAddFriends = new FindAddFriends();
				findAddFriends.setCode(employCoupon.getAddFriendsCode());
				FindAddFriendsReturn addFriendsReturn = addFriendsService.findAddFriends(findAddFriends);
				addCouponRecords.setAddFriendsCode(addFriendsReturn.getCode());
				addCouponRecords
						.setNickName(addFriendsReturn.getNickName() == null ? null : addFriendsReturn.getNickName());
			} else {
				FindPersonMemberBaseList findPersonMemberBaseList = new FindPersonMemberBaseList();
				findPersonMemberBaseList.setMemberNo(employCoupon.getMemberNo());
				FindPersonMemberBaseReturnList memberBaseReturnList = personMemberBaseService
						.findPersonMemberBaseList(findPersonMemberBaseList);
				addCouponRecords.setMemberNo(employCoupon.getMemberNo());
				addCouponRecords.setMemberName(memberBaseReturnList.getMemberName());
			}
			addCouponRecords.setMerchantNo(couponReturn.getMerchantNo());
//			addCouponRecords.setShopNo(couponReturn.getShopNo());
//			addCouponRecords.setShopName(couponReturn.getShopName());
			addCouponRecords.setCouponNo(couponReturn.getCouponNo());
			addCouponRecords.setCouponName(employCoupon.getCouponName());
			addCouponRecords.setUseDate(new Date());
			addCouponRecords.setCreateDate(new Date());
			couponRecordsService.addCouponRecords(addCouponRecords);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw new TsfaServiceException(ErrorCode.COUPON_UPDATE_ERROR, "更新优惠券信息错误！", e);
		} catch (Exception e) {
			logger.error("使用优惠券错误！", e);
		}
		return CouponStatus.USED.toString();
	}

	@Override
	public List<FindCouponReturn> queryCouponReturnList(FindCoupon findCoupon) throws TsfaServiceException {
		AssertUtils.notNullAndEmpty(findCoupon.getRuleNo(), "规则编号不能为空");
		List<FindCouponReturn> list = Lists.newArrayList();
		try {
			list = couponDao.queryCouponReturnList(findCoupon);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_ERROR, "查找优惠券信息错误！", e);
		}

		return list;
	}

	@Override
	public FindCouponReturn findCouponLimit1(FindCoupon findCoupon) throws TsfaServiceException {
		logger.debug("findCouponLimit1(FindCoupon findCoupon={}) - start", findCoupon); 

		AssertUtils.notNull(findCoupon);
		AssertUtils.notAllNull(findCoupon.getCode(), "Code不能为空");
		try {
			Coupon coupon = couponDao.findCouponLimit1(findCoupon);
			if (coupon == null) {
				return null;
			}
			FindCouponReturn findCouponReturn = new FindCouponReturn();
			// find数据录入
			findCouponReturn.setCode(coupon.getCode());
			findCouponReturn.setMerchantNo(coupon.getMerchantNo());
			findCouponReturn.setMerchantName(coupon.getMerchantName());
			findCouponReturn.setShopWx(coupon.getShopWx());
			findCouponReturn.setRuleNo(coupon.getRuleNo());
			findCouponReturn.setCouponNo(coupon.getCouponNo());
			findCouponReturn.setCouponStatus(coupon.getCouponStatus());
			findCouponReturn.setUseDate(coupon.getUseDate());
			findCouponReturn.setUpdateId(coupon.getUpdateId());
			findCouponReturn.setUpdateDate(coupon.getUpdateDate());
			findCouponReturn.setCreateId(coupon.getCreateId());
			findCouponReturn.setCreateDate(coupon.getCreateDate());

			logger.debug("findCouponLimit1(FindCoupon) - end - return value={}", findCouponReturn); 
			return findCouponReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;

		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_FIND_ERROR, "查找优惠券信息错误！", e);
		}
	}

}
