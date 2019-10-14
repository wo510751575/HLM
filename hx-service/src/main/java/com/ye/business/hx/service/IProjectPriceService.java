package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.ProjectPriceDto;
import com.ye.business.hx.dto.FindProjectPricePage;


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
public interface IProjectPriceService {
	
	/**
	 * 
	 *
	 * 方法说明：添加项目单价信息
	 *
	 * @param projectPriceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ProjectPriceDto addProjectPrice(ProjectPriceDto projectPriceDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找项目单价信息
	 *
	 * @param findProjectPrice
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public ProjectPriceDto findProjectPrice(ProjectPriceDto projectPriceDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询项目单价信息
	 *
	 * @param findProjectPricePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<ProjectPriceDto>  findProjectPrices(FindProjectPricePage findProjectPricePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改项目单价信息
	 *
	 * @param updateProjectPrice
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateProjectPrice(ProjectPriceDto projectPriceDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询项目单价信息
	 *
	 * @param findProjectPricePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<ProjectPriceDto> findProjectPricePage(FindProjectPricePage findProjectPricePage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：删除项目单价信息
	 *
	 * @param updateProjectPrice
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.04.16
	 *
	 */
	public int deleteProjectPrice(ProjectPriceDto projectPriceDto)throws TsfaServiceException;

}
