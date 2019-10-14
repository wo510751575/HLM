package com.ye.business.ad.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.ad.dto.FindRwServerPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.RwServerDto;

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
public interface IRwServerService {

	/**
	 * 
	 *
	 * 方法说明：添加热文用户服务记录信息
	 *
	 * @param rwServerDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addRwServer(RwServerDto rwServerDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找热文用户服务记录信息
	 *
	 * @param findRwServer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public RwServerDto findRwServer(RwServerDto rwServerDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询热文用户服务记录信息
	 *
	 * @param findRwServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<RwServerDto> findRwServers(FindRwServerPage findRwServerPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改热文用户服务记录信息
	 *
	 * @param updateRwServer
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateRwServer(RwServerDto rwServerDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询热文用户服务记录信息
	 *
	 * @param findRwServerPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<RwServerDto> findRwServerPage(FindRwServerPage findRwServerPage) throws TsfaServiceException;

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
