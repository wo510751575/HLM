package com.ye.business.ad.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.AdvertiseTypeDto;
import com.ye.business.ad.dto.FindAdvertiseTypePage;


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
 * @author sjiying
 * 
 * 
 * CreateDate: 2019.04.18
 */
public interface IAdvertiseTypeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加广告类型信息
	 *
	 * @param advertiseTypeDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addAdvertiseType(AdvertiseTypeDto advertiseTypeDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找广告类型信息
	 *
	 * @param findAdvertiseType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public AdvertiseTypeDto findAdvertiseType(AdvertiseTypeDto advertiseTypeDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询广告类型信息
	 *
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<AdvertiseTypeDto>  findAdvertiseTypes(FindAdvertiseTypePage findAdvertiseTypePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告类型信息
	 *
	 * @param updateAdvertiseType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateAdvertiseType(AdvertiseTypeDto advertiseTypeDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告类型信息
	 *
	 * @param findAdvertiseTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<AdvertiseTypeDto> findAdvertiseTypePage(FindAdvertiseTypePage findAdvertiseTypePage) throws TsfaServiceException;
	
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
