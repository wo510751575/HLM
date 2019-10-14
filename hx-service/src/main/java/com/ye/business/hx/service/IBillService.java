package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillPage;


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
public interface IBillService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单信息
	 *
	 * @param billDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillDto addBill(BillDto billDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单信息
	 *
	 * @param findBill
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillDto findBill(BillDto billDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillDto>  findBills(FindBillPage findBillPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单信息
	 *
	 * @param updateBill
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBill(BillDto billDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单信息
	 *
	 * @param findBillPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillDto> findBillPage(FindBillPage findBillPage) throws TsfaServiceException;
	

	/**
	 * 方法说明：修改账单全部信息
	 *
	 * @param updateBill
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.17
	 *
	 */
	public void updateBillInfo(BillDto billDto)throws TsfaServiceException;
	
	
	/**
	 * 收欠费。
	 * @param operateDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillOperateDto debtRepay(BillOperateDto operateDto)throws TsfaServiceException;

	/**
	 * 编辑收欠费交易并收费。
	 * @param operateDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillOperateDto debtRepayEdit(BillOperateDto operateDto)throws TsfaServiceException;


	/**
	 * 账单退款申请
	 * @param refundDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillRefundDto billRefundApply(BillRefundDto refundDto)throws TsfaServiceException;

	/**
	 * 账单退款申请信息修改
	 * @param refundDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillRefundDto billRefundApplyEdit(BillRefundDto refundDto)throws TsfaServiceException;

	/**
	 * 账单退款
	 * @param refundDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillRefundDto billRefund(BillRefundDto refundDto) throws TsfaServiceException;
	
	
	/**
	 * 账单作废
	 * @param billDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillDto billCancel(BillDto billDto) throws TsfaServiceException;
	
	/**
	 * 支付记录作废
	 * @param paymentDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public void paymentCancel(BillPaymentDto paymentDto) throws TsfaServiceException;

	/**
	 * 统计患者总收费，总欠款，总退款数。
	 * @param billDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillDto billSum(BillDto billDto) throws TsfaServiceException;
	
	/**
	 * 退款交易审核。
	 * @param billOperateDto
	 * @throws TsfaServiceException
	 */
	public void billRefundCheck(BillOperateDto billOperateDto) throws TsfaServiceException; 
	
	/**
	 * 账单交易作废。
	 * @param billOperateDto
	 * @throws TsfaServiceException
	 */
	public void billOperateCancel(BillOperateDto billOperateDto) throws TsfaServiceException; 
	
	/**
	 * 统计商户总收费，总欠款，总退款数。
	 * @param billDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public BillDto billSumBySearch(FindBillPage findBillPage) throws TsfaServiceException;
	
}
