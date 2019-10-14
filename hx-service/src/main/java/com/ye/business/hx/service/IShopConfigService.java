package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ShopConfigDto;
import com.ye.business.hx.dto.FindShopConfigPage;


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
public interface IShopConfigService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门店配置信息
	 *
	 * @param shopConfigDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopConfigDto addShopConfig(ShopConfigDto shopConfigDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门店配置信息
	 *
	 * @param findShopConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopConfigDto findShopConfig(ShopConfigDto shopConfigDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门店配置信息
	 *
	 * @param findShopConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopConfigDto>  findShopConfigs(FindShopConfigPage findShopConfigPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门店配置信息
	 *
	 * @param updateShopConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateShopConfig(ShopConfigDto shopConfigDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询门店配置信息
	 *
	 * @param findShopConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ShopConfigDto> findShopConfigPage(FindShopConfigPage findShopConfigPage) throws TsfaServiceException;
	
	
	/**
	 * 获取顶级门诊配置信息。
	 * @param toplableNo 顶级配置的lableNo
	 * @return
	 * @throws TsfaServiceException
	 */
	public ShopConfigDto getTopShopCofig(String toplableNo)  throws TsfaServiceException;


	/**
	 * 
	 *
	 * 方法说明：批量修改门店配置排序信息
	 *
	 * @param updateShopConfig
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.15
	 *
	 */
	public void updateShopConfigIndexNo(ShopConfigDto shopConfigDto)throws TsfaServiceException;


	/**
	 * 
	 *
	 * 方法说明：分页查询二级门店配置信息。
	 *
	 * @param findShopConfigPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.15
	 *
	 */
	public Page<ShopConfigDto> findSecondShopConfigPage(FindShopConfigPage findShopConfigPage) throws TsfaServiceException;
	
	/**
	 * 删除配置。
	 * @param shopConfigDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public int deleteShopConfig(ShopConfigDto shopConfigDto) throws TsfaServiceException;
}
