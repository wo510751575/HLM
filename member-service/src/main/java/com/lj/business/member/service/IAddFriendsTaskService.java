package com.lj.business.member.service;

import java.util.List;
import java.util.Map;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.domain.AddFriendsTaskDetail;
import com.lj.business.member.dto.AddFriendsTaskCountDto;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.FindAddFriendsTaskPage;
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
public interface IAddFriendsTaskService {
	
	/**
	 * 
	 *
	 * 方法说明：添加加粉任务详情信息
	 *
	 * @param addFriendsTaskDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addAddFriendsTask(AddFriendsTaskDto addFriendsTaskDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务详情信息
	 *
	 * @param findAddFriendsTask
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public AddFriendsTaskDto findAddFriendsTask(AddFriendsTaskDto addFriendsTaskDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询加粉任务详情信息
	 *
	 * @param findAddFriendsTaskPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<AddFriendsTaskDto>  findAddFriendsTasks(FindAddFriendsTaskPage findAddFriendsTaskPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改加粉任务详情信息
	 *
	 * @param updateAddFriendsTask
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateAddFriendsTask(AddFriendsTaskDto addFriendsTaskDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询加粉任务详情信息
	 *
	 * @param findAddFriendsTaskPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<AddFriendsTaskDto> findAddFriendsTaskPage(FindAddFriendsTaskPage findAddFriendsTaskPage) throws TsfaServiceException;
	
	/**
	 * 加粉统计
	 * @param addFriendsTaskDto
	 * @return
	 */
	public AddFriendsTaskCountDto selectAddFriendsTaskDetailCount(AddFriendsTaskDto addFriendsTaskDto);
	
	/**
	 * 按终端分组，去重
	 * @param addFriendsTaskDto
	 * @return
	 */
	String selectDistinctGroupByNoWxGms(AddFriendsTaskDto addFriendsTaskDto);
}
