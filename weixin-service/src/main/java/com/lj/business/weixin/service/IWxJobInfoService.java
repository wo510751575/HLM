package com.lj.business.weixin.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.weixin.dto.FindWxJobInfoPage;
import com.lj.business.weixin.dto.WxJobInfoDto;
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
 * CreateDate: 2017-08-22
 */
public interface IWxJobInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信任务信息
	 *
	 * @param wxJobInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public WxJobInfoDto addWxJobInfo(WxJobInfoDto wxJobInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信任务信息
	 *
	 * @param findWxJobInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public WxJobInfoDto findWxJobInfo(WxJobInfoDto wxJobInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询微信任务信息
	 *
	 * @param findWxJobInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<WxJobInfoDto>  findWxJobInfos(FindWxJobInfoPage findWxJobInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信任务信息
	 *
	 * @param updateWxJobInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateWxJobInfo(WxJobInfoDto wxJobInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询微信任务信息
	 *
	 * @param findWxJobInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<WxJobInfoDto> findWxJobInfoPage(FindWxJobInfoPage findWxJobInfoPage) throws TsfaServiceException;
	

}
