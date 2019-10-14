package com.ye.business.hx.service;

import com.ye.business.hx.dto.BillDto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.FindBillPaymentPage;


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
public interface IBillPaymentService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单支付信息信息
	 *
	 * @param billPaymentDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillPayment(BillPaymentDto billPaymentDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单支付信息信息
	 *
	 * @param findBillPayment
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillPaymentDto findBillPayment(BillPaymentDto billPaymentDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单支付信息信息
	 *
	 * @param findBillPaymentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillPaymentDto>  findBillPayments(FindBillPaymentPage findBillPaymentPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单支付信息信息
	 *
	 * @param updateBillPayment
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillPayment(BillPaymentDto billPaymentDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单支付信息信息
	 *
	 * @param findBillPaymentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillPaymentDto> findBillPaymentPage(FindBillPaymentPage findBillPaymentPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：分页查询账单支付信息信息
	 *
	 * @param findBillPaymentPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019年6月17日
	 *
	 */
	public Page<BillPaymentDto> findBillPaymentPageByMerchant(FindBillPaymentPage findBillPaymentPage) throws TsfaServiceException;
	
	/**
	 * 按条件统计总收费，总退款金额。
	 * @param findBillPaymentPage
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillDto paymentSum(FindBillPaymentPage findBillPaymentPage) throws TsfaServiceException;
	
}
