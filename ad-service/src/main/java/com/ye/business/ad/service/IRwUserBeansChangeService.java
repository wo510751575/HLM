package com.ye.business.ad.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.RwUserBeansChangeDto;
import com.ye.business.ad.dto.FindRwUserBeansChangePage;


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
public interface IRwUserBeansChangeService {
	
	/**
	 * 
	 *
	 * 方法说明：添加用户豆子更改记录信息
	 *
	 * @param rwUserBeansChangeDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addRwUserBeansChange(RwUserBeansChangeDto rwUserBeansChangeDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找用户豆子更改记录信息
	 *
	 * @param findRwUserBeansChange
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public RwUserBeansChangeDto findRwUserBeansChange(RwUserBeansChangeDto rwUserBeansChangeDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询用户豆子更改记录信息
	 *
	 * @param findRwUserBeansChangePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<RwUserBeansChangeDto>  findRwUserBeansChanges(FindRwUserBeansChangePage findRwUserBeansChangePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改用户豆子更改记录信息
	 *
	 * @param updateRwUserBeansChange
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateRwUserBeansChange(RwUserBeansChangeDto rwUserBeansChangeDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询用户豆子更改记录信息
	 *
	 * @param findRwUserBeansChangePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<RwUserBeansChangeDto> findRwUserBeansChangePage(FindRwUserBeansChangePage findRwUserBeansChangePage) throws TsfaServiceException;
	
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
