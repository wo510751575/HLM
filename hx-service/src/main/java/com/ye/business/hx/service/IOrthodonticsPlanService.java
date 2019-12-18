package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.OrthodonticsPlanDto;
import com.ye.business.hx.dto.FindOrthodonticsPlanPage;


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
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IOrthodonticsPlanService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸计划配置信息
	 *
	 * @param orthodonticsPlanDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addOrthodonticsPlan(OrthodonticsPlanDto orthodonticsPlanDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸计划配置信息
	 *
	 * @param findOrthodonticsPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public OrthodonticsPlanDto findOrthodonticsPlan(OrthodonticsPlanDto orthodonticsPlanDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸计划配置信息
	 *
	 * @param findOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<OrthodonticsPlanDto>  findOrthodonticsPlans(FindOrthodonticsPlanPage findOrthodonticsPlanPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸计划配置信息
	 *
	 * @param updateOrthodonticsPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateOrthodonticsPlan(OrthodonticsPlanDto orthodonticsPlanDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸计划配置信息
	 *
	 * @param findOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<OrthodonticsPlanDto> findOrthodonticsPlanPage(FindOrthodonticsPlanPage findOrthodonticsPlanPage) throws TsfaServiceException;

	/**   
	 * @Title: delete   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param orthodonticsPlanDto      
	 * @return: void      
	 * @throws   
	 */
	public void delete(OrthodonticsPlanDto orthodonticsPlanDto)throws TsfaServiceException;

	/**   
	 * @Title: getMaxSort   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	public Integer getMaxSort();
	

}
