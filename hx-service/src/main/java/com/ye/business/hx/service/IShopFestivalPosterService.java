package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ShopFestivalPosterDto;
import com.ye.business.hx.dto.FindShopFestivalPosterPage;


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
public interface IShopFestivalPosterService {
	
	/**
	 * 
	 *
	 * 方法说明：添加门诊节日问候海报信息
	 *
	 * @param shopFestivalPosterDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopFestivalPosterDto addShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊节日问候海报信息
	 *
	 * @param findShopFestivalPoster
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ShopFestivalPosterDto findShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊节日问候海报信息
	 *
	 * @param findShopFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopFestivalPosterDto>  findShopFestivalPosters(FindShopFestivalPosterPage findShopFestivalPosterPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改门诊节日问候海报信息
	 *
	 * @param updateShopFestivalPoster
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateShopFestivalPoster(ShopFestivalPosterDto shopFestivalPosterDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询门诊节日问候海报信息
	 *
	 * @param findShopFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ShopFestivalPosterDto> findShopFestivalPosterPage(FindShopFestivalPosterPage findShopFestivalPosterPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询门诊节日问候海报信息。仅查在模板中存在的图片。
	 *
	 * @param findShopFestivalPosterPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ShopFestivalPosterDto>  findShopFestivalPostersByTemplateImg(FindShopFestivalPosterPage findShopFestivalPosterPage)throws TsfaServiceException;


}
