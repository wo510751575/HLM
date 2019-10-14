package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ServerInfoDto;
import com.ye.business.hx.dto.FindServerInfoPage;


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
public interface IServerInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加系统服务信息
	 *
	 * @param serverInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addServerInfo(ServerInfoDto serverInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务信息
	 *
	 * @param findServerInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ServerInfoDto findServerInfo(ServerInfoDto serverInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询系统服务信息
	 *
	 * @param findServerInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ServerInfoDto>  findServerInfos(FindServerInfoPage findServerInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改系统服务信息
	 *
	 * @param updateServerInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateServerInfo(ServerInfoDto serverInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询系统服务信息
	 *
	 * @param findServerInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ServerInfoDto> findServerInfoPage(FindServerInfoPage findServerInfoPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：添加系统服务信息
	 *
	 * @param serverInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public String addServerInfoRecord(ServerInfoDto serverInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：修改系统服务信息
	 *
	 * @param updateServerInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateServerInfoRecord(ServerInfoDto serverInfoDto)throws TsfaServiceException;
	
}
