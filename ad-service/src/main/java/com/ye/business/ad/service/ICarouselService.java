package com.ye.business.ad.service;

import com.ye.business.ad.dto.AdvertiseTaskDto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.CarouselDto;
import com.ye.business.ad.dto.FindCarouselPage;


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
public interface ICarouselService {
	
	/**
	 * 
	 *
	 * 方法说明：添加轮播广告记录信息
	 *
	 * @param carouselDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addCarousel(CarouselDto carouselDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找轮播广告记录信息
	 *
	 * @param findCarousel
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public CarouselDto findCarousel(CarouselDto carouselDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询轮播广告记录信息
	 *
	 * @param findCarouselPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<CarouselDto>  findCarousels(FindCarouselPage findCarouselPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改轮播广告记录信息
	 *
	 * @param updateCarousel
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateCarousel(CarouselDto carouselDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询轮播广告记录信息
	 *
	 * @param findCarouselPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<CarouselDto> findCarouselPage(FindCarouselPage findCarouselPage) throws TsfaServiceException;
	
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
	
	/**
	 * 
	 * *方法说明：批量下架广告
	 *
	 * @param batchNum
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月11日
	 */
	public void batchUpdateCarouselForUpOrDown(String batchNum) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：上下架方法
	 *
	 * @param record
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月18日
	 */
	public void updateForTask(AdvertiseTaskDto record) throws TsfaServiceException;
}
