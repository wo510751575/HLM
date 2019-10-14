package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
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

/**
 * 类说明：接口类
 * 
 *  
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface ICouponRuleService {

	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addCouponRule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public AddCouponRule addCouponRule(AddCouponRule addCouponRule) throws TsfaServiceException;

	/**
	 *  
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findCouponRule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindCouponRuleReturn findCouponRule(FindCouponRule findCouponRule) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delCouponRule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delCouponRule(DelCouponRule delCouponRule) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateCouponRule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateCouponRule(UpdateCouponRule updateCouponRule) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findCouponRulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindCouponRulePageReturn> findCouponRulePage(FindCouponRulePage findCouponRulePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据优惠券类型获取当前类型下的所有优惠券规则
	 *
	 * @param findCouponRule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月16日
	 *
	 */
	public List<FindCouponRuleReturn> findCouponRuleList(FindCouponRule findCouponRule) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：获取所有商户当天未生成券的优惠券规则
	 *
	 * 
	 * @return List
	 *
	 * @author 罗书明 CreateDate: 2018年1月25日
	 *
	 */
	List<FindCouponRuleReturn> findcouponRuleTodayEnableDate() throws TsfaServiceException ;
	
	/***
	 * 
	 *
	 * 方法说明：查询认领客户或者非认领客户的邀请码
	 *
	 * @param findInviteCode
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月29日
	 *
	 */
	FindInviteCodeReturn findInviteCode(FindInviteCode findInviteCode) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据邀请码查询认领客户或者非认领客户的信息
	 *
	 * @param inviteCode
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 许新龙 CreateDate: 2018年1月29日
	 *
	 */
    FindMemberInfoByInviteCodeReturn findMemberInfoByInviteCode(String shopNoWx, String inviteCode)
            throws TsfaServiceException;

    /**
     * 
     *
     * 方法说明：微信好友添加成功后，发送领取优惠券的链接
     *
     * @param sendCouponAfterAddWxFriends
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年1月29日
     *
     */
    void sendCouponAfterAddWxFriends(SendCouponAfterAddWxFriends sendCouponAfterAddWxFriends)
            throws TsfaServiceException;

    /**
     * 
     *
     * 方法说明：根据网点微信查询优惠券（含全店铺通用和网点微信下的终端可用的优惠券）
     *
     * @param findCouponRule
     * @return
     * @throws TsfaServiceException
     *
     * @author 许新龙 CreateDate: 2018年1月29日
     *
     */
    List<FindCouponRuleReturn> findCouponRuleListWeb(FindCouponRule findCouponRule)
            throws TsfaServiceException;
    
	/**
	 *  
	 *
	 * 方法说明：获取过期优惠券
	 *
	 * @param findCouponRule
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年2月6日
	 *
	 */
	List<FindCouponRuleReturn> queryCouponRulePast() throws TsfaServiceException;
}
