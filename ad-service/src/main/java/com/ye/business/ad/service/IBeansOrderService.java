package com.ye.business.ad.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.BeansOrderDto;
import com.ye.business.ad.dto.FindBeansOrderPage;


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
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
public interface IBeansOrderService {
	
	/**
	 * 
	 *
	 * 方法说明：添加豆子充值记录信息
	 *
	 * @param beansOrderDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addBeansOrder(BeansOrderDto beansOrderDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找豆子充值记录信息
	 *
	 * @param findBeansOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public BeansOrderDto findBeansOrder(BeansOrderDto beansOrderDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询豆子充值记录信息
	 *
	 * @param findBeansOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<BeansOrderDto>  findBeansOrders(FindBeansOrderPage findBeansOrderPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改豆子充值记录信息
	 *
	 * @param updateBeansOrder
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateBeansOrder(BeansOrderDto beansOrderDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询豆子充值记录信息
	 *
	 * @param findBeansOrderPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<BeansOrderDto> findBeansOrderPage(FindBeansOrderPage findBeansOrderPage) throws TsfaServiceException;
	
	/**
	 * 
	 * *方法说明：刪除
	 *
	 * @param code
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月8日
	 */
	public void removeByPrimaryKey(String code) throws TsfaServiceException;

}
