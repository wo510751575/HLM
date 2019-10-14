package com.ye.business.rw.service;

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

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.rw.dto.FindRwUserPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.rw.dto.RwUserDto;

public interface IRwUserService {

	/**
	 * 
	 *
	 * 方法说明：添加热文用户记录信息
	 *
	 * @param rwUserDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public String addRwUser(RwUserDto rwUserDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找热文用户记录信息
	 *
	 * @param findRwUser
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public RwUserDto findRwUser(RwUserDto rwUserDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询热文用户记录信息
	 *
	 * @param findRwUserPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public List<RwUserDto> findRwUsers(FindRwUserPage findRwUserPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改热文用户记录信息
	 *
	 * @param updateRwUser
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public void updateRwUser(RwUserDto rwUserDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询热文用户记录信息
	 *
	 * @param findRwUserPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author sjiying CreateDate: 2019-04-18
	 *
	 */
	public Page<RwUserDto> findRwUserPage(FindRwUserPage findRwUserPage) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：用户登陆
	 *
	 * @param rwUser
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月31日
	 */
	public RwUserDto personRwUserLogin(RwUserDto rwUser) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：用户注册
	 *
	 * @param rwUser
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年5月31日
	 */
	public String personRwUserRegistered(RwUserDto rwUser) throws TsfaServiceException;

	/**
	 * 
	 * *方法说明：修改用户密码
	 *
	 * @param rwUser
	 * @return
	 * @throws TsfaServiceException
	 * @author sjiying
	 * @CreateDate 2019年6月2日
	 */
	public void personRwUserPwd(RwUserDto rwUser) throws TsfaServiceException;
}
