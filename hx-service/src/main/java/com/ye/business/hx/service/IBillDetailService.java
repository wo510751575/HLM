package com.ye.business.hx.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.FindBillDetailPage;
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
public interface IBillDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单详情信息
	 *
	 * @param billDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillDetail(BillDetailDto billDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单详情信息
	 *
	 * @param findBillDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillDetailDto findBillDetail(BillDetailDto billDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单详情信息
	 *
	 * @param findBillDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillDetailDto>  findBillDetails(FindBillDetailPage findBillDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单详情信息
	 *
	 * @param updateBillDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillDetail(BillDetailDto billDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单详情信息
	 *
	 * @param findBillDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillDetailDto> findBillDetailPage(FindBillDetailPage findBillDetailPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改账单详情的绩效人。
	 *
	 * @param updateBillDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillDetailMember(BillDto billDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单详情信息
	 *
	 * @param findBillDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.06.17
	 *
	 */
	public Page<BillDetailDto> findBillDetailPageByMerchant(FindBillDetailPage findBillDetailPage) throws TsfaServiceException;

	
	/**
	 * 
	 * 方法说明：统计项目实收，欠收，退款金额
	 *
	 * @param findBillDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.06.17
	 *
	 */
	public BillDetailDto billProjectSum(FindBillDetailPage findBillDetailPage) throws TsfaServiceException;
	
}
