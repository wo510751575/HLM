package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.coupon.AddCouponVo;
import com.lj.business.cp.dto.coupon.DelCoupon;
import com.lj.business.cp.dto.coupon.EmployCoupon;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPage;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.dto.coupon.FindCouponReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;
import com.lj.business.cp.dto.couponRule.AddCouponRule;

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
public interface ICouponService {

	/**
	 * 
	 *
	 * 方法说明：生成优惠券
	 *
	 * @param addCoupon
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	public void addCoupon(AddCoupon addCoupon) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：新增优惠券
	 *
	 * @param addCoupon
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰
	 * @date 2017年9月30日
	 *
	 */
	public Map<String, Object> insertCoupon(AddCouponVo addCouponVo) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findCoupon
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindCouponReturn findCoupon(FindCoupon findCoupon) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delCoupon
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delCoupon(DelCoupon delCoupon) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateCoupon
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateCoupon(UpdateCoupon updateCoupon) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findCouponPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年09月15日
	 *
	 */
	public Page<FindCouponPageReturn> findCouponPage(FindCouponPage findCouponPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：数据导出查询接口
	 *
	 * @param findCoupon
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年9月16日
	 *
	 */
	public List<FindCouponPageReturn> findCouponReturnList(FindCoupon findCoupon) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号，修改优惠券状态
	 *
	 * @param updateCoupon
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	public String updateCouponByCouponNo(UpdateCoupon updateCoupon) throws TsfaServiceException;
	/**
	 * 
	 *
	 * 方法说明：新增优惠券
	 *
	 * @param addCouponRule
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月24日
	 *
	 */
	public int addCoupon(AddCouponRule addCouponRule)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：领取优惠券
	 *
	 * @param findCoupon
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	public Map<String,Object> gainCoupon(FindCoupon findCoupon)throws TsfaServiceException;
	
	
	
	/**
	 * 
	 *
	 * 方法说明：客户使用优惠券
	 *
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2018年1月26日
	 *
	 */
	public String couponConsumption(EmployCoupon employCoupon)throws TsfaServiceException; 
	
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
	public List<FindCouponReturn> queryCouponReturnList(FindCoupon findCoupon)throws TsfaServiceException;
	
	/**
	 * 获取一张优惠券
	 * @param findCoupon
	 * @return
	 * @throws TsfaServiceException
	 */
	FindCouponReturn findCouponLimit1(FindCoupon findCoupon) throws TsfaServiceException;
}
