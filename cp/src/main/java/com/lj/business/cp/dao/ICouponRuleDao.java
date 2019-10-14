package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.CouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRulePage;
import com.lj.business.cp.dto.couponRule.FindCouponRulePageReturn;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;

public interface ICouponRuleDao {

	int deleteByPrimaryKey(String code);

	int insert(CouponRule record);

	int insertSelective(CouponRule record);

	CouponRule selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(CouponRule record);

	int updateByPrimaryKey(CouponRule record);
	
	/**
     * 
     *
     * 方法说明：分页查询
     *
     * @param findCouponRulePage
     * @return
     *
     * @author 彭俊霖 CreateDate: 2017年9月16日
     *
     */
	List<FindCouponRulePageReturn> findCouponRulePage(FindCouponRulePage findCouponRulePage);

	int findCouponRulePageCount(FindCouponRulePage findCouponRulePage);

	/**
	 * 
	 *
	 * 方法说明：根据优惠券类型获取当前类型下的所有优惠券规则
	 *
	 * @param findCouponRule
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月16日
	 *
	 */
	List<FindCouponRuleReturn> findCouponRuleList(FindCouponRule findCouponRule);
	
	/**
	 * 
	 *
	 * 方法说明：根据终端编号集合查询优惠券列表
	 *
	 * @param findCouponRule
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2018年1月29日
	 *
	 */
	List<FindCouponRuleReturn> findCouponRuleListByShops(FindCouponRule findCouponRule);
	
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
	List<FindCouponRuleReturn> findcouponRuleTodayEnableDate();
	
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
	List<FindCouponRuleReturn> queryCouponRulePast();
}