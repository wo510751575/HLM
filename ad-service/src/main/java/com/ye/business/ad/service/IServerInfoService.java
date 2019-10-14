package com.ye.business.ad.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.FindServerInfoPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.ServerInfoDto;

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
public interface IServerInfoService {

	/**
	 * 
	 *
	 * 方法说明：添加热文服务记录信息
	 *
	 * @param serverInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addServerInfo(ServerInfoDto serverInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找热文服务记录信息
	 *
	 * @param findServerInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public ServerInfoDto findServerInfo(ServerInfoDto serverInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询热文服务记录信息
	 *
	 * @param findServerInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<ServerInfoDto> findServerInfos(FindServerInfoPage findServerInfoPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改热文服务记录信息
	 *
	 * @param updateServerInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateServerInfo(ServerInfoDto serverInfoDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询热文服务记录信息
	 *
	 * @param findServerInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<ServerInfoDto> findServerInfoPage(FindServerInfoPage findServerInfoPage) throws TsfaServiceException;

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

}
