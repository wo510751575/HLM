package com.lj.business.cp.dao;

import java.util.List;

import com.lj.business.cp.domain.CouponType;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypePage;
import com.lj.business.cp.dto.couponType.FindCouponTypePageReturn;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;

public interface ICouponTypeDao {

	int deleteByPrimaryKey(String code);

	int insert(CouponType record);

	int insertSelective(CouponType record);

	CouponType selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(CouponType record);

	int updateByPrimaryKey(CouponType record);

	/**
	 * 
	 *
	 * 方法说明：获取优惠券类型List
	 *
	 * @param findCouponType
	 * @return
	 *
	 * @author 杨杰 CreateDate: 2017年9月15日
	 *
	 */
	List<FindCouponTypeReturn> findCouponTypeList(FindCouponType findCouponType);

	List<FindCouponTypePageReturn> findCouponTypePage(FindCouponTypePage findCouponTypePage);

	int findCouponTypePageCount(FindCouponTypePage findCouponTypePage);
}