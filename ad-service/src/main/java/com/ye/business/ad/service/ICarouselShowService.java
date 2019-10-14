package com.ye.business.ad.service;

import java.util.Date;
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
import com.ye.business.ad.dto.CarouselShowDto;
import com.ye.business.ad.dto.FindCarouselShowPage;

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
public interface ICarouselShowService {

	/**
	 * 
	 *
	 * 方法说明：添加广告显示记录信息
	 *
	 * @param advertiseShowDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addCarouselShow(CarouselShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告显示记录信息
	 *
	 * @param findCarouselShow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public CarouselShowDto findCarouselShow(CarouselShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告显示记录信息
	 *
	 * @param findCarouselShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<CarouselShowDto> findCarouselShows(FindCarouselShowPage findCarouselShowPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告显示记录信息
	 *
	 * @param updateCarouselShow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateCarouselShow(CarouselShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告显示记录信息
	 *
	 * @param findCarouselShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<CarouselShowDto> findCarouselShowPage(FindCarouselShowPage findCarouselShowPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：插入广告显示记录
	 *
	 * @param advertiseCodeList 广告code list
	 * @param updateId 操作人ID
	 * @param date 显示时间
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	public void saveCarouselShowForTrack(List<String> advertiseCodeList, String updateId, Date date, String ip);

	/**
	 * 
	 * *方法说明：根据广告code分组统计
	 *
	 * @param advertiseCodeList
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	public Map<String, Integer> findCarouselShowPageCountForGroupCarouselCode(List<String> advertiseCodeList);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record CarouselDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public List<CarouselShowDto> findGroupTotalByExample(CarouselDto record) throws TsfaServiceException;
}
