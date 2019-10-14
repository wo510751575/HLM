package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.HxClueOrderDto;
import com.ye.business.hx.dto.FindHxClueOrderPage;


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
public interface IHxClueOrderService {
	
	/**
	 * 
	 *
	 * 方法说明：添加线索使用订单信息
	 *
	 * @param hxClueOrderDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addHxClueOrder(HxClueOrderDto hxClueOrderDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找线索使用订单信息
	 *
	 * @param findHxClueOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public HxClueOrderDto findHxClueOrder(HxClueOrderDto hxClueOrderDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询线索使用订单信息
	 *
	 * @param findHxClueOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<HxClueOrderDto>  findHxClueOrders(FindHxClueOrderPage findHxClueOrderPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改线索使用订单信息
	 *
	 * @param updateHxClueOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateHxClueOrder(HxClueOrderDto hxClueOrderDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询线索使用订单信息
	 *
	 * @param findHxClueOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<HxClueOrderDto> findHxClueOrderPage(FindHxClueOrderPage findHxClueOrderPage) throws TsfaServiceException;
	

}
