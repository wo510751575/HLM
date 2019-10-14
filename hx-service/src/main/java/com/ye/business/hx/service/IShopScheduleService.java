package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ShopScheduleDto;
import com.ye.business.hx.dto.FindShopSchedulePage;


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
public interface IShopScheduleService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门店班次信息
	 *
	 * @param shopScheduleDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopScheduleDto addShopSchedule(ShopScheduleDto shopScheduleDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门店班次信息
	 *
	 * @param findShopSchedule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopScheduleDto findShopSchedule(ShopScheduleDto shopScheduleDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门店班次信息
	 *
	 * @param findShopSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopScheduleDto>  findShopSchedules(FindShopSchedulePage findShopSchedulePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门店班次信息
	 *
	 * @param updateShopSchedule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateShopSchedule(ShopScheduleDto shopScheduleDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询门店班次信息
	 *
	 * @param findShopSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ShopScheduleDto> findShopSchedulePage(FindShopSchedulePage findShopSchedulePage) throws TsfaServiceException;
	

}
