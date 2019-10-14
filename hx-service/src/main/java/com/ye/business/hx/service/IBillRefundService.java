package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillRefundPage;


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
public interface IBillRefundService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单退款信息信息
	 *
	 * @param billRefundDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillRefund(BillRefundDto billRefundDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款信息信息
	 *
	 * @param findBillRefund
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillRefundDto findBillRefund(BillRefundDto billRefundDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单退款信息信息
	 *
	 * @param findBillRefundPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillRefundDto>  findBillRefunds(FindBillRefundPage findBillRefundPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单退款信息信息
	 *
	 * @param updateBillRefund
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillRefund(BillRefundDto billRefundDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单退款信息信息
	 *
	 * @param findBillRefundPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillRefundDto> findBillRefundPage(FindBillRefundPage findBillRefundPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款信息信息
	 *
	 * @param findBillRefund
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.19
	 *
	 */
	public BillRefundDto findBillRefundByOperateCode(BillRefundDto billRefundDto) throws TsfaServiceException;
	
}
