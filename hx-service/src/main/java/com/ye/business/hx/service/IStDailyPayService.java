package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.StDailyPayDto;
import com.ye.business.hx.dto.FindStDailyPayPage;


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
public interface IStDailyPayService {
	
	/**
	 * 
	 *
	 * 方法说明：添加每日收费统计信息
	 *
	 * @param stDailyPayDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addStDailyPay(StDailyPayDto stDailyPayDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找每日收费统计信息
	 *
	 * @param findStDailyPay
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public StDailyPayDto findStDailyPay(StDailyPayDto stDailyPayDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询每日收费统计信息
	 *
	 * @param findStDailyPayPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<StDailyPayDto>  findStDailyPays(FindStDailyPayPage findStDailyPayPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改每日收费统计信息
	 *
	 * @param updateStDailyPay
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateStDailyPay(StDailyPayDto stDailyPayDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询每日收费统计信息
	 *
	 * @param findStDailyPayPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<StDailyPayDto> findStDailyPayPage(FindStDailyPayPage findStDailyPayPage) throws TsfaServiceException;
	
	
	/**
	 * 方法说明：按各商户支付方式统计前一日的收支
	 * @throws TsfaServiceException
	 * @author lhy CreateDate: 2019.06.18
	 */
	public void batchAddStDailyPay(String batchNum) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询每日收费统计信息
	 *
	 * @param findStDailyPayPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<StDailyPayDto> findStDailyPayPageGroupByStDay(FindStDailyPayPage findStDailyPayPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：按支付方式合计金额
	 *
	 * @param findStDailyPayPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.06.18
	 *
	 */
	public List<StDailyPayDto> stDailyPaySumGroupByPayType(FindStDailyPayPage findStDailyPayPage) throws TsfaServiceException;
	
}
