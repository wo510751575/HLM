package com.lj.business.member.service;

import java.util.List;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.forecastName.AddForecastName;
import com.lj.business.member.dto.forecastName.AddForecastNameReturn;
import com.lj.business.member.dto.forecastName.FindForecastName;
import com.lj.business.member.dto.forecastName.FindForecastNamePage;
import com.lj.business.member.dto.forecastName.FindForecastNamePageReturn;
import com.lj.business.member.dto.forecastName.FindForecastNameReturn;
import com.lj.business.member.dto.forecastName.UpdateForecastName;
import com.lj.business.member.dto.forecastName.UpdateForecastNameReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭俊霖
 * 
 * 
 * CreateDate: 2017-11-01
 */
public interface IForecastNameService {
	
	/**
	 * 
	 *
	 * 方法说明：添加预报名信息
	 *
	 * @param addForecastName
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public AddForecastNameReturn addForecastName(AddForecastName addForecastName) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找预报名信息
	 *
	 * @param findForecastName
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public FindForecastNameReturn findForecastName(FindForecastName findForecastName) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改预报名信息
	 *
	 * @param updateForecastName
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public UpdateForecastNameReturn updateForecastName(UpdateForecastName updateForecastName)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找预报名信息
	 *
	 * @param findForecastNamePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月01日
	 *
	 */
	public Page<FindForecastNamePageReturn> findForecastNamePage(FindForecastNamePage findForecastNamePage) throws TsfaServiceException;
	
	
	List<FindForecastNameReturn> findForecastNameByCondition(FindForecastName findForecastName) throws TsfaServiceException;

}
