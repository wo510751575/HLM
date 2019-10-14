package com.lj.business.cp.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.couponRecords.AddCouponRecords;
import com.lj.business.cp.couponRecords.AddCouponRecordsReturn;
import com.lj.business.cp.couponRecords.DelCouponRecords;
import com.lj.business.cp.couponRecords.DelCouponRecordsReturn;
import com.lj.business.cp.couponRecords.FindCouponRecords;
import com.lj.business.cp.couponRecords.FindCouponRecordsPage;
import com.lj.business.cp.couponRecords.FindCouponRecordsPageReturn;
import com.lj.business.cp.couponRecords.FindCouponRecordsReturn;
import com.lj.business.cp.couponRecords.UpdateCouponRecords;
import com.lj.business.cp.couponRecords.UpdateCouponRecordsReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface ICouponRecordsService {
	
	/**
	 * 
	 *
	 * 方法说明：添加IM含敏感词聊天记录表信息
	 *
	 * @param addCouponRecords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddCouponRecordsReturn addCouponRecords(AddCouponRecords addCouponRecords) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找IM含敏感词聊天记录表信息
	 *
	 * @param findCouponRecords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindCouponRecordsReturn findCouponRecords(FindCouponRecords findCouponRecords) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：删除IM含敏感词聊天记录表信息
	 *
	 * @param delCouponRecords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelCouponRecordsReturn delCouponRecords(DelCouponRecords delCouponRecords) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改IM含敏感词聊天记录表信息
	 *
	 * @param updateCouponRecords
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateCouponRecordsReturn updateCouponRecords(UpdateCouponRecords updateCouponRecords)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找IM含敏感词聊天记录表信息
	 *
	 * @param findCouponRecordsPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindCouponRecordsPageReturn> findCouponRecordsPage(FindCouponRecordsPage findCouponRecordsPage) throws TsfaServiceException;
	

}
