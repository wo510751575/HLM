package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ServerDetailDto;
import com.ye.business.hx.dto.FindServerDetailPage;


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
public interface IServerDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加系统服务项信息
	 *
	 * @param serverDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addServerDetail(ServerDetailDto serverDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找系统服务项信息
	 *
	 * @param findServerDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ServerDetailDto findServerDetail(ServerDetailDto serverDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询系统服务项信息
	 *
	 * @param findServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ServerDetailDto>  findServerDetails(FindServerDetailPage findServerDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改系统服务项信息
	 *
	 * @param updateServerDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateServerDetail(ServerDetailDto serverDetailDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询系统服务项信息
	 *
	 * @param findServerDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ServerDetailDto> findServerDetailPage(FindServerDetailPage findServerDetailPage) throws TsfaServiceException;
	
	/**
	 * 根据服务code删除数据
	 * @param serverCode
	 * @throws TsfaServiceException
	 */
	public void removeByServerCode(String serverCode) throws TsfaServiceException;
}
