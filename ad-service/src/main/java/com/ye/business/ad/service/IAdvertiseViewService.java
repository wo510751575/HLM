package com.ye.business.ad.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.AdvertiseDto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.AdvertiseViewDto;
import com.ye.business.ad.dto.FindAdvertiseViewPage;

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
public interface IAdvertiseViewService {

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
	public String addAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告查看记录信息
	 *
	 * @param findAdvertiseView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public AdvertiseViewDto findAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告查看记录信息
	 *
	 * @param findAdvertiseViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<AdvertiseViewDto> findAdvertiseViews(FindAdvertiseViewPage findAdvertiseViewPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告查看记录信息
	 *
	 * @param updateAdvertiseView
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateAdvertiseView(AdvertiseViewDto advertiseViewDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告查看记录信息
	 *
	 * @param findAdvertiseViewPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<AdvertiseViewDto> findAdvertiseViewPage(FindAdvertiseViewPage findAdvertiseViewPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：根据广告code分组统计
	 *
	 * @param advertiseCodeList
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	public Map<String, Integer> findAdvertiseViewPageCountForGroupAdvertiseCode(List<String> advertiseCodeList) throws TsfaServiceException;

	public int selectAdvertiseViewCount(AdvertiseDto advertiseDto) throws TsfaServiceException;

	public int getCountView(AdvertiseViewDto param) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record AdvertiseDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public List<AdvertiseViewDto> findGroupTotalByExample(AdvertiseDto record) throws TsfaServiceException;

}
