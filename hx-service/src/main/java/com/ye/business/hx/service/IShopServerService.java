package com.ye.business.hx.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.domain.ShopServer;
import com.ye.business.hx.dto.FindShopServerPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ShopServerDto;
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
public interface IShopServerService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门诊服务信息
	 *
	 * @param shopServerDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addShopServer(ShopServerDto shopServerDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务信息
	 *
	 * @param findShopServer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopServerDto findShopServer(ShopServerDto shopServerDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊服务信息
	 *
	 * @param findShopServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopServerDto>  findShopServers(FindShopServerPage findShopServerPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门诊服务信息
	 *
	 * @param updateShopServer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateShopServer(ShopServerDto shopServerDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询门诊服务信息
	 *
	 * @param findShopServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ShopServerDto> findShopServerPage(FindShopServerPage findShopServerPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：添加门诊服务信息
	 *
	 * @param shopServerDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public String addShopServerRecord(ShopServerDto shopServerDto) throws TsfaServiceException;
}
