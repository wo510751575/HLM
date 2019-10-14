package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.dto.FindShopServerDetailPage;


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
public interface IShopServerDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门诊服务项信息
	 *
	 * @param shopServerDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addShopServerDetail(ShopServerDetailDto shopServerDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务项信息
	 *
	 * @param findShopServerDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopServerDetailDto findShopServerDetail(ShopServerDetailDto shopServerDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊服务项信息
	 *
	 * @param findShopServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopServerDetailDto>  findShopServerDetails(FindShopServerDetailPage findShopServerDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门诊服务项信息
	 *
	 * @param updateShopServerDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateShopServerDetail(ShopServerDetailDto shopServerDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询门诊服务项信息
	 *
	 * @param findShopServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ShopServerDetailDto> findShopServerDetailPage(FindShopServerDetailPage findShopServerDetailPage) throws TsfaServiceException;
	

}
