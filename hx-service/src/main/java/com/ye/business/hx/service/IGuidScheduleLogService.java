package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.GuidScheduleLogDto;
import com.ye.business.hx.dto.FindGuidScheduleLogPage;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.dto.GuidScheduleCycleDto;
import com.ye.business.hx.dto.GuidScheduleDto;
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
public interface IGuidScheduleLogService {
	
	/**
	 * 
	 *
	 * 方法说明：添加员工历史排班信息
	 *
	 * @param guidScheduleLogDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void addGuidScheduleLog(GuidScheduleLogDto guidScheduleLogDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找员工历史排班信息
	 *
	 * @param findGuidScheduleLog
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public GuidScheduleLogDto findGuidScheduleLog(GuidScheduleLogDto guidScheduleLogDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询员工历史排班信息
	 *
	 * @param findGuidScheduleLogPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public List<GuidScheduleLogDto>  findGuidScheduleLogs(FindGuidScheduleLogPage findGuidScheduleLogPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改员工历史排班信息
	 *
	 * @param updateGuidScheduleLog
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public void updateGuidScheduleLog(GuidScheduleLogDto guidScheduleLogDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询员工历史排班信息
	 *
	 * @param findGuidScheduleLogPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019-03-08
	 *
	 */
	public Page<GuidScheduleLogDto> findGuidScheduleLogPage(FindGuidScheduleLogPage findGuidScheduleLogPage) throws TsfaServiceException;
	
	/**
	 * 方法说明：按员工分组分页查询。
	 * @param findGuidSchedulePage
	 * @return
	 * @throws TsfaServiceException
	 */
	public Page<GuidScheduleLogDto> findGuidScheduleLogPageGroupByGuid(FindGuidSchedulePage findGuidSchedulePage) throws TsfaServiceException;

	 /**按人查出所有排班*/
	public List<GuidScheduleCycleDto> findGuidScheduleLogByGm(GuidScheduleDto guidScheduleDto) throws TsfaServiceException;
    
	/**
	 * 1.将当周排班转移到历史排班
	 * 2.完成1后，将固定排班转移到当周排班
	 * @throws TsfaServiceException
	 */
	public void batchAddGuidScheduleLog(String batchNum)  throws TsfaServiceException;
}
