package com.ye.business.ad.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.CarouselDto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.CarouselViewDto;
import com.ye.business.ad.dto.FindCarouselViewPage;

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
public interface ICarouselViewService {

	/**
	 * 
	 *
	 * 方法说明：添加广告查看记录信息
	 *
	 * @param advertiseViewDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addCarouselView(CarouselViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告查看记录信息
	 *
	 * @param findCarouselView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public CarouselViewDto findCarouselView(CarouselViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告查看记录信息
	 *
	 * @param findCarouselViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<CarouselViewDto> findCarouselViews(FindCarouselViewPage findCarouselViewPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告查看记录信息
	 *
	 * @param updateCarouselView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateCarouselView(CarouselViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告查看记录信息
	 *
	 * @param findCarouselViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<CarouselViewDto> findCarouselViewPage(FindCarouselViewPage findCarouselViewPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：根据广告code分组统计
	 *
	 * @param advertiseCodeList
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	public Map<String, Integer> findCarouselViewPageCountForGroupCarouselCode(List<String> advertiseCodeList);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record CarouselDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public List<CarouselViewDto> findGroupTotalByExample(CarouselDto record) throws TsfaServiceException;

}
