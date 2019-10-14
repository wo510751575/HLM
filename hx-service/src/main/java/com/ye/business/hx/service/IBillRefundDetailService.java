package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.FindBillRefundDetailPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public interface IBillRefundDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单退款详情信息
	 *
	 * @param billRefundDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillRefundDetail(BillRefundDetailDto billRefundDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款详情信息
	 *
	 * @param findBillRefundDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillRefundDetailDto findBillRefundDetail(BillRefundDetailDto billRefundDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单退款详情信息
	 *
	 * @param findBillRefundDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillRefundDetailDto>  findBillRefundDetails(FindBillRefundDetailPage findBillRefundDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单退款详情信息
	 *
	 * @param updateBillRefundDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillRefundDetail(BillRefundDetailDto billRefundDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单退款详情信息
	 *
	 * @param findBillRefundDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillRefundDetailDto> findBillRefundDetailPage(FindBillRefundDetailPage findBillRefundDetailPage) throws TsfaServiceException;
	

}
