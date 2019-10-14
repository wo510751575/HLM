package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.FindBillOperatePage;


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
public interface IBillOperateService {
	
	/**
	 * 
	 *
	 * 方法说明：添加账单交易操作信息
	 *
	 * @param billOperateDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addBillOperate(BillOperateDto billOperateDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找账单交易操作信息
	 *
	 * @param findBillOperate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public BillOperateDto findBillOperate(BillOperateDto billOperateDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询账单交易操作信息
	 *
	 * @param findBillOperatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<BillOperateDto>  findBillOperates(FindBillOperatePage findBillOperatePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改账单交易操作信息
	 *
	 * @param updateBillOperate
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateBillOperate(BillOperateDto billOperateDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询账单交易操作信息
	 *
	 * @param findBillOperatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<BillOperateDto> findBillOperatePage(FindBillOperatePage findBillOperatePage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：分页查询账单交易操作信息(待处理账单)
	 *
	 * @param findBillOperatePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.18
	 *
	 */
	public Page<BillOperateDto> findUntreatedBillOperatePage(FindBillOperatePage findBillOperatePage) throws TsfaServiceException;
	
}
