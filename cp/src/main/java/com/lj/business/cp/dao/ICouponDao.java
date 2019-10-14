package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.Coupon;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPage;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.dto.coupon.FindCouponReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;

public interface ICouponDao {

	int deleteByPrimaryKey(String code);


	int insertSelective(Coupon record);

	Coupon selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(Coupon record);

	/**
	 * 
	 *
	 * 方法说明：分页查询
	 *
	 * @param findCouponPage
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月15日
	 *
	 */
	List<FindCouponPageReturn> findCouponPage(FindCouponPage findCouponPage);

	int findCouponPageCount(FindCouponPage findCouponPage);

	List<FindCouponPageReturn> findCouponReturnList(FindCoupon findCoupon);

	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号查询
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	FindCouponReturn findCouponByCouponNo(FindCoupon findCoupon);

	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号修改
	 *
	 * @param uCoupon
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	void updateCouponByCouponNo(UpdateCoupon uCoupon);

	/**
	 * 
	 *
	 * 方法说明：根据导购编号获取已使用优惠券列表
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	List<FindCouponReturn> findCouponReturnListByGuidNo(FindCoupon findCoupon);
	
	/**
	 * 
	 *
	 * 方法说明：获取优惠券编号
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	FindCouponReturn findCouponByRuleNo(FindCoupon findCoupon);
	/**
	 * 
	 *
	 * 方法说明：查找规则下的所有优惠券
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年2月6日
	 *
	 */
	List<FindCouponReturn> queryCouponReturnList(FindCoupon findCoupon);
	
	/**
	 * 获取一张优惠券
	 * @param findCoupon
	 * @return
	 */
	Coupon findCouponLimit1(FindCoupon findCoupon);
}