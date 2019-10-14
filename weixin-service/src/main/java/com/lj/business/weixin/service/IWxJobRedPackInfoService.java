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
import com.lj.business.weixin.dto.FindWxJobRedPackInfoPage;
import com.lj.business.weixin.dto.WxJobRedPackInfoDto;
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
public interface IWxJobRedPackInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加微信红包任务信息
	 *
	 * @param wxJobRedPackInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addWxJobRedPackInfo(WxJobRedPackInfoDto wxJobRedPackInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找微信红包任务信息
	 *
	 * @param findWxJobRedPackInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public WxJobRedPackInfoDto findWxJobRedPackInfo(WxJobRedPackInfoDto wxJobRedPackInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询微信红包任务信息
	 *
	 * @param findWxJobRedPackInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<WxJobRedPackInfoDto>  findWxJobRedPackInfos(FindWxJobRedPackInfoPage findWxJobRedPackInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改微信红包任务信息
	 *
	 * @param updateWxJobRedPackInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateWxJobRedPackInfo(WxJobRedPackInfoDto wxJobRedPackInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询微信红包任务信息
	 *
	 * @param findWxJobRedPackInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<WxJobRedPackInfoDto> findWxJobRedPackInfoPage(FindWxJobRedPackInfoPage findWxJobRedPackInfoPage) throws TsfaServiceException;

	/**
	 *@Desc 
	 *@param wxJobRedPackInfoDto
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午6:30:26
	 */
	public void delete(WxJobRedPackInfoDto wxJobRedPackInfoDto);
	

}
