package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */


import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.project.AddServiceProject;
import com.lj.business.member.dto.service.project.AddServiceProjectReturn;
import com.lj.business.member.dto.service.project.FindServiceProject;
import com.lj.business.member.dto.service.project.FindServiceProjectApp;
import com.lj.business.member.dto.service.project.FindServiceProjectAppReturn;
import com.lj.business.member.dto.service.project.FindServiceProjectPage;
import com.lj.business.member.dto.service.project.FindServiceProjectPageReturn;
import com.lj.business.member.dto.service.project.FindServiceProjectReturn;
import com.lj.business.member.dto.service.project.UpdateServiceProject;
import com.lj.business.member.dto.service.project.UpdateServiceProjectReturn;


/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public interface IServiceProjectService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务项目表信息
	 *
	 * @param addServiceProject
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddServiceProjectReturn addServiceProject(AddServiceProject addServiceProject) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @param findServiceProject
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindServiceProjectReturn findServiceProject(FindServiceProject findServiceProject) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改服务项目表信息
	 *
	 * @param updateServiceProject
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateServiceProjectReturn updateServiceProject(UpdateServiceProject updateServiceProject)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @param findServiceProjectPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindServiceProjectPageReturn> findServiceProjectPage(FindServiceProjectPage findServiceProjectPage) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：查询服务项目详情-APP
	 *
	 * @param findServiceProjectApp
	 * @return
	 * @throws TsfaServiceException
	 *
	 *
	 */
	public List<FindServiceProjectAppReturn> findServiceProjectApp(FindServiceProjectApp findServiceProjectApp) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明:项目名称列表
	 *
	 * @param findServiceProjectApp
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月21日
	 *
	 */
	 public List<FindServiceProjectAppReturn> findServiceProjectList(FindServiceProjectApp findServiceProjectApp);
}
