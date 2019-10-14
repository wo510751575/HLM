package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.CouponMemberRelation;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationReturn;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationVoReturn;

public interface ICouponMemberRelationDao {

	int deleteByPrimaryKey(String code);

	int insert(CouponMemberRelation record);

	int insertSelective(CouponMemberRelation record);

	CouponMemberRelation selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(CouponMemberRelation record);

	int updateByPrimaryKey(CouponMemberRelation record);
	/**
	 * 
	 *
	 * 方法说明：根据导购编号获取已使用的优惠券
	 *
	 * @param findCouponMemberRelation
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	List<FindCouponMemberRelationReturn> findCouponMemberRelationList(FindCouponMemberRelation findCouponMemberRelation);
	
	/**
	 * 
	 *
	 * 方法说明：根据条件获取客户获取的优惠券
	 *
	 * @param findCouponMemberRelation
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月20日
	 *
	 */
	FindCouponMemberRelationVoReturn findCouponMemberRelation(FindCouponMemberRelation findCouponMemberRelation);
	
	/**
	 *   
	 *
	 * 方法说明：查询客户最后一次领取的优惠券
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	List<FindCouponMemberRelationReturn>  findMemberCoupon(FindCoupon findCoupon);
	
	
	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号更新状态与使用时间
	 *
	 * @param couponMemberRelation
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月29日
	 *
	 */
	int updateByCouponStatus(CouponMemberRelation couponMemberRelation);
	
}