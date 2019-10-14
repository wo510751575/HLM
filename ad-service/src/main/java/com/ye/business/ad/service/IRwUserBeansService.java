package com.ye.business.ad.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.ad.dto.RwUserBeansDto;
import com.ye.business.ad.dto.FindRwUserBeansPage;


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
public interface IRwUserBeansService {
	
	/**
	 * 
	 *
	 * 方法说明：添加用户豆子记录信息
	 *
	 * @param rwUserBeansDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addRwUserBeans(RwUserBeansDto rwUserBeansDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找用户豆子记录信息
	 *
	 * @param findRwUserBeans
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public RwUserBeansDto findRwUserBeans(RwUserBeansDto rwUserBeansDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询用户豆子记录信息
	 *
	 * @param findRwUserBeansPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<RwUserBeansDto>  findRwUserBeanss(FindRwUserBeansPage findRwUserBeansPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改用户豆子记录信息
	 *
	 * @param updateRwUserBeans
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateRwUserBeans(RwUserBeansDto rwUserBeansDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询用户豆子记录信息
	 *
	 * @param findRwUserBeansPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<RwUserBeansDto> findRwUserBeansPage(FindRwUserBeansPage findRwUserBeansPage) throws TsfaServiceException;
	
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
	
	/**
	 * 
	 * *方法说明：导购编号查询
	 *
	 * @param memberNo
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月2日
	 */
	public RwUserBeansDto findRwUserBeans(String memberNo) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：变更豆子记录
	 *
	 * @param record
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年7月12日
	 */
	public void updateIncreaseAmountByPrimaryKey(RwUserBeansDto record) throws TsfaServiceException;
}
