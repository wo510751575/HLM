package com.ye.business.ad.service;

import java.util.Date;
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
import com.ye.business.ad.dto.AdvertiseShowDto;
import com.ye.business.ad.dto.FindAdvertiseShowPage;

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
public interface IAdvertiseShowService {

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
	public String addAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找广告显示记录信息
	 *
	 * @param findAdvertiseShow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public AdvertiseShowDto findAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询广告显示记录信息
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<AdvertiseShowDto> findAdvertiseShows(FindAdvertiseShowPage findAdvertiseShowPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告显示记录信息
	 *
	 * @param updateAdvertiseShow
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateAdvertiseShow(AdvertiseShowDto advertiseShowDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告显示记录信息
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<AdvertiseShowDto> findAdvertiseShowPage(FindAdvertiseShowPage findAdvertiseShowPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：插入广告显示记录
	 *
	 * @param advertiseCodeList 广告code list
	 * @param updateId 操作人ID
	 * @param date 显示时间
	 * @param articleCode 来源文章code
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	/**
	 * 
	 * *方法说明：插入广告显示记录
	 *
	 * @param advertiseCodeList 广告code list
	 * @param updateId 操作人ID
	 * @param date 显示时间
	 * @param ip 来源IP
	 * @param articleCode 来源文章code
	 * @return 新增广告显示code记录list
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	public List<String> saveAdvertiseShowForTrack(List<String> advertiseCodeList, String updateId, Date date, String ip, String articleCode);

	/**
	 * 
	 * *方法说明：根据广告code分组统计
	 *
	 * @param advertiseCodeList
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年5月15日
	 */
	public Map<String, Integer> findAdvertiseShowPageCountForGroupAdvertiseCode(List<String> advertiseCodeList);

	/**
	 * 
	 * *方法说明：根据条件统计点击量
	 *
	 * @param record AdvertiseDto
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月2日
	 */
	public List<AdvertiseShowDto> findGroupTotalByExample(AdvertiseDto record) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：广告显示记录统计
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public List<AdvertiseShowDto> findAdvertiseShowForGroupList(AdvertiseShowDto record) throws TsfaServiceException;
	/**
	 * 
	 * *方法说明：广告显示记录统计
	 *
	 * @param findAdvertiseShowPage
	 * @return
	 * @author sjiying
	 * @CreateDate 2019年7月3日
	 */
	public List<AdvertiseShowDto> findAdvertiseShowInfoForGroupList(AdvertiseShowDto record) throws TsfaServiceException;
}
