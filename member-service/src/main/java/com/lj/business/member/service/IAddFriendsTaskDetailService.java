package com.lj.business.member.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.FindAddFriendsTaskDetailPage;
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
public interface IAddFriendsTaskDetailService {
	
	/**
	 * 
	 *
	 * 方法说明：添加加粉任务信息
	 *
	 * @param addFriendsTaskDetailDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addAddFriendsTaskDetail(AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找加粉任务信息
	 *
	 * @param findAddFriendsTaskDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public AddFriendsTaskDetailDto findAddFriendsTaskDetail(AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询加粉任务信息
	 *
	 * @param findAddFriendsTaskDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<AddFriendsTaskDetailDto>  findAddFriendsTaskDetails(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改加粉任务信息
	 *
	 * @param updateAddFriendsTaskDetail
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateAddFriendsTaskDetail(AddFriendsTaskDetailDto addFriendsTaskDetailDto)throws TsfaServiceException;

	/**
	 * 更新详情信息
	 * @throws TsfaServiceException
	 */
	public void updateByCond(AddFriendsTaskDetailDto addFriendsTaskDetailDto)throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：分页查询加粉任务信息
	 *
	 * @param findAddFriendsTaskDetailPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<AddFriendsTaskDetailDto> findAddFriendsTaskDetailPage(FindAddFriendsTaskDetailPage findAddFriendsTaskDetailPage) throws TsfaServiceException;
	/**
	 * 根据条件查询
	 * @param addFriendsTaskDetailDto
	 * @return
	 * @throws TsfaServiceException
	 */
	public AddFriendsTaskDetailDto findByCond(AddFriendsTaskDetailDto addFriendsTaskDetailDto) throws TsfaServiceException;
	/**
	 * 批量插入
	 * 按1000位单位批次插入，不足直接提交
	 * @param record
	 * @return
	 * @throws TsfaServiceException
	 */
	public int insertForeach(List<AddFriendsTaskDetailDto> record)throws TsfaServiceException;
	
	
	int deleteByPrimaryKey(String code)throws TsfaServiceException;
	
	/**
	 * 定时任务每天推送的结果集
	 * 
	 * @param num 每个终端推送的数量，默认20
	 * @return
	 */
	List<AddFriendsTaskDetailDto> findJobResult(int num)throws TsfaServiceException;
}
