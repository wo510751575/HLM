package com.lj.business.member.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.service.projectproperty.AddProjectProperty;
import com.lj.business.member.dto.service.projectproperty.AddProjectPropertyReturn;
import com.lj.business.member.dto.service.projectproperty.FindProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPage;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPageReturn;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyReturn;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectProperty;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectPropertyReturn;


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
public interface IProjectPropertyService {
	
	/**
	 * 
	 *
	 * 方法说明：添加服务项目表信息
	 *
	 * @param addProjectProperty
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddProjectPropertyReturn addProjectProperty(AddProjectProperty addProjectProperty) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @param findProjectProperty
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindProjectPropertyReturn findProjectProperty(FindProjectProperty findProjectProperty) throws TsfaServiceException;
	

	/**
	 * 
	 *
	 * 方法说明：修改服务项目表信息
	 *
	 * @param updateProjectProperty
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateProjectPropertyReturn updateProjectProperty(UpdateProjectProperty updateProjectProperty)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找服务项目表信息
	 *
	 * @param findProjectPropertyPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindProjectPropertyPageReturn> findProjectPropertyPage(FindProjectPropertyPage findProjectPropertyPage) throws TsfaServiceException;
	

}
