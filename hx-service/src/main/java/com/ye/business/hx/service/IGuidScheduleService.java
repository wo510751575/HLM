package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
import java.util.Map;
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
public interface IGuidScheduleService {
	
	/**
	 * 
	 *
	 * 方法说明：添加员工班次信息
	 *
	 * @param guidScheduleDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public GuidScheduleDto addGuidSchedule(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找员工班次信息
	 *
	 * @param findGuidSchedule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public GuidScheduleDto findGuidSchedule(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询员工班次信息
	 *
	 * @param findGuidSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<GuidScheduleDto>  findGuidSchedules(FindGuidSchedulePage findGuidSchedulePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改员工班次信息
	 *
	 * @param updateGuidSchedule
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateGuidSchedule(GuidScheduleDto guidScheduleDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询员工班次信息
	 *
	 * @param findGuidSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<GuidScheduleDto> findGuidSchedulePage(FindGuidSchedulePage findGuidSchedulePage) throws TsfaServiceException;
	

	/**
	 * 方法说明：删除员工班次信息
	 * @param guidScheduleDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public int deleteGuidSchedule(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;

	/**
	 * 方法说明：按员工分组分页查询。
	 * @param findGuidSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 */
	public Page<GuidScheduleDto> findGuidSchedulePageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage) throws TsfaServiceException;

	 /**按人查出所有排班*/
	public List<GuidScheduleCycleDto> findGuidScheduleByGm(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;
    
	 /**查可预约的员工*/
	public List<GuidScheduleCycleDto> findEnableGuidSchedule(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;
	
 
	/**
	 * 方法说明：批量修改员工某类型某天班次信息
	 * @param guidScheduleDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public  Map<String, String> upadteGuidScheduleByTypeAndDayNum(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;

	/**
	 * 方法说明：批量新增某员工某类型班次信息
	 * @param guidScheduleDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public void addGuidScheduleBatch(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;

}
