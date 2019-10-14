package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.business.member.dto.SetFriendNumDto;
import com.lj.business.member.dto.FindSetFriendNumPage;


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
public interface ISetFriendNumService {
	
	/**
	 * 
	 *
	 * 方法说明：添加设置添加好友数信息
	 *
	 * @param setFriendNumDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addSetFriendNum(SetFriendNumDto setFriendNumDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找设置添加好友数信息
	 *
	 * @param findSetFriendNum
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public SetFriendNumDto findSetFriendNum(SetFriendNumDto setFriendNumDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询设置添加好友数信息
	 *
	 * @param findSetFriendNumPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<SetFriendNumDto>  findSetFriendNums(FindSetFriendNumPage findSetFriendNumPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改设置添加好友数信息
	 *
	 * @param updateSetFriendNum
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateSetFriendNum(SetFriendNumDto setFriendNumDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询设置添加好友数信息
	 *
	 * @param findSetFriendNumPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<SetFriendNumDto> findSetFriendNumPage(FindSetFriendNumPage findSetFriendNumPage) throws TsfaServiceException;
	/**
	 * 删除信息
	 * @param code
	 * @return
	 */
	int deleteByPrimaryKey(String code);
}
