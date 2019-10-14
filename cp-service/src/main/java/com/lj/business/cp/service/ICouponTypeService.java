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
import com.lj.business.cp.dto.couponType.AddCouponType;
import com.lj.business.cp.dto.couponType.DelCouponType;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypePage;
import com.lj.business.cp.dto.couponType.FindCouponTypePageReturn;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;
import com.lj.business.cp.dto.couponType.UpdateCouponType;

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
public interface ICouponTypeService {

	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @param addCouponType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void addCouponType(AddCouponType addCouponType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @param findCouponType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public FindCouponTypeReturn findCouponType(FindCouponType findCouponType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @param delCouponType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void delCouponType(DelCouponType delCouponType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @param updateCouponType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public void updateCouponType(UpdateCouponType updateCouponType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询导购行为信息记录表信息
	 *
	 * @param findCouponTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	public Page<FindCouponTypePageReturn> findCouponTypePage(FindCouponTypePage findCouponTypePage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：获取所有的优惠券类型
	 *
	 * @param findCouponType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月15日
	 *
	 */
	public List<FindCouponTypeReturn> findCouponTypeList(FindCouponType findCouponType) throws TsfaServiceException;

}
