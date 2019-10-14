package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.ICouponMemberRelationDao;
import com.lj.business.cp.domain.CouponMemberRelation;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.DelCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPage;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPageReturn;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationReturn;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;
import com.lj.business.cp.dto.couponMemberRelation.UpdateCouponMemberRelation;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.emus.CouponStatus;
import com.lj.business.cp.service.ICouponMemberRelationService;
import com.lj.business.cp.service.ICouponRuleService;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IPersonMemberBaseService;

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
public class CouponMemberRelationServiceImpl implements ICouponMemberRelationService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponMemberRelationServiceImpl.class);
	@Resource
	private ICouponMemberRelationDao couponMemberRelationDao;
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	@Resource
    private ICouponRuleService couponRuleService;
	@Resource
    private IAddFriendsService addFriendsService;

	@Override
	public void addCouponMemberRelation(AddCouponMemberRelation addCouponMemberRelation) throws TsfaServiceException {
		logger.debug("addCouponMemberRelation(AddCouponMemberRelation addCouponMemberRelation={}) - start", addCouponMemberRelation);

		AssertUtils.notNull(addCouponMemberRelation);
		try {
			CouponMemberRelation couponMemberRelation = new CouponMemberRelation();
			// add数据录入
			couponMemberRelation.setCode(GUID.generateCode());
			couponMemberRelation.setMemberNoGm(addCouponMemberRelation.getMemberNoGm());
			couponMemberRelation.setMemberNameGm(addCouponMemberRelation.getMemberNameGm());
			couponMemberRelation.setMemberNo(addCouponMemberRelation.getMemberNo());
			couponMemberRelation.setMemberName(addCouponMemberRelation.getMemberName());
			couponMemberRelation.setCouponNo(addCouponMemberRelation.getCouponNo());
			couponMemberRelation.setCouponStatus(addCouponMemberRelation.getCouponStatus());
			couponMemberRelation.setUseDate(addCouponMemberRelation.getUseDate());
			couponMemberRelation.setUpdateId(addCouponMemberRelation.getUpdateId());
			couponMemberRelation.setUpdateDate(addCouponMemberRelation.getUpdateDate());
			couponMemberRelation.setCreateId(addCouponMemberRelation.getCreateId());
			couponMemberRelation.setCreateDate(addCouponMemberRelation.getCreateDate());
			couponMemberRelation.setRuleNo(addCouponMemberRelation.getRuleNo());
			couponMemberRelation.setAddFriendsCode(addCouponMemberRelation.getAddFriendsCode());
			couponMemberRelationDao.insertSelective(couponMemberRelation);
			logger.debug("addCouponMemberRelation(AddCouponMemberRelation) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_MEMBER_RELATION_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delCouponMemberRelation(DelCouponMemberRelation delCouponMemberRelation) throws TsfaServiceException {
		logger.debug("delCouponMemberRelation(DelCouponMemberRelation delCouponMemberRelation={}) - start", delCouponMemberRelation);

		AssertUtils.notNull(delCouponMemberRelation);
		AssertUtils.notNull(delCouponMemberRelation.getCode(), "Code不能为空！");
		try {
			couponMemberRelationDao.deleteByPrimaryKey(delCouponMemberRelation.getCode());
			logger.debug("delCouponMemberRelation(DelCouponMemberRelation) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_MEMBER_RELATION_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateCouponMemberRelation(UpdateCouponMemberRelation updateCouponMemberRelation) throws TsfaServiceException {
		logger.debug("updateCouponMemberRelation(UpdateCouponMemberRelation updateCouponMemberRelation={}) - start", updateCouponMemberRelation); 

		AssertUtils.notNull(updateCouponMemberRelation);
		AssertUtils.notNullAndEmpty(updateCouponMemberRelation.getCode(), "Code不能为空");
		try {
			CouponMemberRelation couponMemberRelation = new CouponMemberRelation();
			// update数据录入
			couponMemberRelation.setCode(updateCouponMemberRelation.getCode());
			couponMemberRelation.setMemberNoGm(updateCouponMemberRelation.getMemberNoGm());
			couponMemberRelation.setMemberNameGm(updateCouponMemberRelation.getMemberNameGm());
			couponMemberRelation.setMemberNo(updateCouponMemberRelation.getMemberNo());
			couponMemberRelation.setMemberName(updateCouponMemberRelation.getMemberName());
			couponMemberRelation.setCouponNo(updateCouponMemberRelation.getCouponNo());
			couponMemberRelation.setCouponStatus(updateCouponMemberRelation.getCouponStatus());
			couponMemberRelation.setUpdateId(updateCouponMemberRelation.getUpdateId());
			couponMemberRelation.setUpdateDate(updateCouponMemberRelation.getUpdateDate());
			couponMemberRelation.setCreateId(updateCouponMemberRelation.getCreateId());
			couponMemberRelation.setCreateDate(updateCouponMemberRelation.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(couponMemberRelationDao.updateByPrimaryKeySelective(couponMemberRelation));
			logger.debug("updateCouponMemberRelation(UpdateCouponMemberRelation) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_MEMBER_RELATION_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public List<FindCouponMemberRelationReturn> findCouponMemberRelationList(FindCouponMemberRelation findCouponMemberRelation) throws TsfaServiceException {
		AssertUtils.notNull(findCouponMemberRelation);
		try {
			return couponMemberRelationDao.findCouponMemberRelationList(findCouponMemberRelation);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			throw new TsfaServiceException(ErrorCode.COUPON_MEMBER_RELATION_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindCouponMemberRelationPageReturn> findCouponMemberRelationPage(FindCouponMemberRelationPage findCouponMemberRelationPage) throws TsfaServiceException {
		//		logger.debug("findCouponMemberRelationPage(FindCouponMemberRelationPage findCouponMemberRelationPage={}) - start", findCouponMemberRelationPage); 
		//
		// AssertUtils.notNull(findCouponMemberRelationPage);
		// List<FindCouponMemberRelationPageReturn> returnList;
		// int count = 0;
		// try {
		// returnList = couponMemberRelationDao.findCouponMemberRelationPage(findCouponMemberRelationPage);
		// count = couponMemberRelationDao.findCouponMemberRelationPageCount(findCouponMemberRelationPage);
		// } catch (Exception e) {
		// logger.error("导购行为信息记录表信息不存在错误",e);
		// throw new TsfaServiceException(ErrorCode.COUPON_MEMBER_RELATION_FIND_PAGE_ERROR,"导购行为信息记录表信息不存在错误.！",e);
		// }
		// Page<FindCouponMemberRelationPageReturn> returnPage = new Page<FindCouponMemberRelationPageReturn>(returnList, count, findCouponMemberRelationPage);
		//
		//		logger.debug("findCouponMemberRelationPage(FindCouponMemberRelationPage) - end - return value={}", returnPage); 
		// return returnPage;

		return null;
	}

	@Override
	public FindCouponMemberRelationVoReturn findCouponMemberRelation(FindCouponMemberRelation findCouponMemberRelation) throws TsfaServiceException {
		logger.debug("findCouponMemberRelation(FindCouponMemberRelation findCouponMemberRelation={}) - start", findCouponMemberRelation); 

		FindCouponMemberRelationVoReturn findCouponMemberRelationVoReturn = couponMemberRelationDao.findCouponMemberRelation(findCouponMemberRelation);
		if (findCouponMemberRelationVoReturn != null) { // 设置用户头像
			FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
			findPersonMemberBase.setMemberNo(findCouponMemberRelationVoReturn.getMemberNo());
			findPersonMemberBase.setMemberNoGm(findCouponMemberRelationVoReturn.getMemberNoGm());
			FindPersonMemberBaseReturn findPersonMemberBaseReturn = personMemberBaseService.findPersonMemberBaseParams(findPersonMemberBase);
			if (findPersonMemberBaseReturn != null) {
				findCouponMemberRelationVoReturn.setHeadAddress(findPersonMemberBaseReturn.getHeadAddress());
			}
		}
		logger.info("优惠券关联对象为：{}",JsonUtils.jsonFromObject(findCouponMemberRelationVoReturn));
		return findCouponMemberRelationVoReturn;
	}

	@Override
	public List<FindCouponMemberRelationReturn> findMemberCoupon(FindCoupon findCoupon)throws TsfaServiceException {
		logger.debug("findMemberCoupon(FindCoupon findCoupon={}) - start", findCoupon); 
		AssertUtils.notNullAndEmpty(findCoupon.getRuleNo(), "规则编号不能为空！");
		List<FindCouponMemberRelationReturn> memberRelationReturns =Lists.newArrayList();
		try {
			memberRelationReturns = couponMemberRelationDao.findMemberCoupon(findCoupon);
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
		}catch (Exception e) {
            logger.error("获取客户领取优惠券次数错误！",e);
		}
		return memberRelationReturns;
	}

	@Override
	public int updateByCouponStatus(UpdateCouponMemberRelation updateCouponMemberRelation)
			throws TsfaServiceException {
		logger.debug("updateByCouponStatus(CouponMemberRelation couponMemberRelation)",updateCouponMemberRelation);
		AssertUtils.notNullAndEmpty(updateCouponMemberRelation.getCouponNo(), "优惠券编号不能为空");
	    int i = 0;
		try {
			CouponMemberRelation couponMemberRelation = new CouponMemberRelation();
			couponMemberRelation.setCouponNo(updateCouponMemberRelation.getCouponNo());
			couponMemberRelation.setUseDate(new Date());
			couponMemberRelation.setUpdateDate(new Date());
			couponMemberRelation.setCouponStatus(CouponStatus.USED.toString());
			i=couponMemberRelationDao.updateByCouponStatus(couponMemberRelation);
		} catch (TsfaServiceException e) {
          logger.error(e.getMessage(),e);
		}catch (Exception e) {
	      logger.error("更新关联表信息错误！",e);
		}
		return i;
	}
	
	@Override
    public Map<String, Object> findCouponStatus(FindCoupon findCoupon) throws TsfaServiceException {
	    Map<String, Object> map = new HashMap<>(3);
        try {
            if (StringUtils.isEmpty(findCoupon.getAddFriendsCode())) {//A领取和使用状态
                List<FindCouponMemberRelationReturn> list = couponMemberRelationDao.findMemberCoupon(findCoupon);
                map.put("result", Boolean.TRUE);//返回结果是否正确
                if (CollectionUtils.isEmpty(list)) {
                    map.put("own", Boolean.FALSE);//是否领取
                } else {
                    map.put("own", Boolean.TRUE);
                    FindCouponMemberRelationReturn relationReturn = list.get(0);
                    map.put("couponStatus", relationReturn.getCouponStatus());//使用状态
                    map.put("couponNo", relationReturn.getCouponNo());
                    
                    //查询优惠券名称
                    FindCouponRule findCouponRule = new FindCouponRule();
                    findCouponRule.setCode(relationReturn.getRuleNo());
                    FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
                    if (findCouponRuleReturn != null) {
                        map.put("couponName", findCouponRuleReturn.getCouponName());
                    }
                }
            } else {//B领取和使用状态
                List<FindCouponMemberRelationReturn> list = couponMemberRelationDao.findMemberCoupon(findCoupon);
                map.put("result", Boolean.TRUE);//返回结果是否正确
                if (CollectionUtils.isEmpty(list)) {
                    //如果B输入正确邀请码会被自动认领，所以得去AddFriends表查询memberNo
                    FindAddFriends findAddFriends = new FindAddFriends();
                    findAddFriends.setCode(findCoupon.getAddFriendsCode());
                    FindAddFriendsReturn findAddFriendsReturn = addFriendsService.findAddFriends(findAddFriends);
                    if (findAddFriendsReturn == null || (findAddFriendsReturn != null && findAddFriendsReturn.getMemberNo() == null)) {
                        map.put("own", Boolean.FALSE);//是否领取
                    } else {
                        findCoupon.setAddFriendsCode(null);
                        findCoupon.setMemberNo(findAddFriendsReturn.getMemberNo());
                        
                        list = couponMemberRelationDao.findMemberCoupon(findCoupon);
                        if (CollectionUtils.isEmpty(list)) {
                            map.put("own", Boolean.FALSE);//是否领取
                        } else {
                            map.put("own", Boolean.TRUE);
                            FindCouponMemberRelationReturn relationReturn = list.get(0);
                            map.put("couponStatus", relationReturn.getCouponStatus());//使用状态
                            map.put("couponNo", relationReturn.getCouponNo());
                            
                            //查询优惠券名称
                            FindCouponRule findCouponRule = new FindCouponRule();
                            findCouponRule.setCode(relationReturn.getRuleNo());
                            FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
                            if (findCouponRuleReturn != null) {
                                map.put("couponName", findCouponRuleReturn.getCouponName());
                            }
                        }
                    }
                    
                } else {
                    map.put("own", Boolean.TRUE);
                    FindCouponMemberRelationReturn relationReturn = list.get(0);
                    map.put("couponStatus", relationReturn.getCouponStatus());//使用状态
                    map.put("couponNo", relationReturn.getCouponNo());
                    
                    //查询优惠券名称
                    FindCouponRule findCouponRule = new FindCouponRule();
                    findCouponRule.setCode(relationReturn.getRuleNo());
                    FindCouponRuleReturn findCouponRuleReturn = couponRuleService.findCouponRule(findCouponRule);
                    if (findCouponRuleReturn != null) {
                        map.put("couponName", findCouponRuleReturn.getCouponName());
                    }
                }
            }
        } catch (Exception e) {
            map.put("result", Boolean.FALSE);
            logger.error("获取优惠券领取信息错误！",e);
        }
        return map;
    }

}
