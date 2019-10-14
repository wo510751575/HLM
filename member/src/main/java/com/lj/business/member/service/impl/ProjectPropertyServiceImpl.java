package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IProjectPropertyDao;
import com.lj.business.member.domain.ProjectProperty;
import com.lj.business.member.dto.service.projectproperty.AddProjectProperty;
import com.lj.business.member.dto.service.projectproperty.AddProjectPropertyReturn;
import com.lj.business.member.dto.service.projectproperty.FindProjectProperty;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPage;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyPageReturn;
import com.lj.business.member.dto.service.projectproperty.FindProjectPropertyReturn;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectProperty;
import com.lj.business.member.dto.service.projectproperty.UpdateProjectPropertyReturn;
import com.lj.business.member.service.IProjectPropertyService;

/**
 * 类说明：实现类
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
@Service
public class ProjectPropertyServiceImpl implements IProjectPropertyService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ProjectPropertyServiceImpl.class);
	

	@Resource
	private IProjectPropertyDao projectPropertyDao;
	
	
	@Override
	public AddProjectPropertyReturn addProjectProperty(
			AddProjectProperty addProjectProperty) throws TsfaServiceException {
		logger.debug("addProjectProperty(AddProjectProperty addProjectProperty={}) - start", addProjectProperty); 

		AssertUtils.notNull(addProjectProperty);
		try {
			ProjectProperty projectProperty = new ProjectProperty();
			//add数据录入
			projectProperty.setCode(GUID.getPreUUID());
			projectProperty.setPropertyName(addProjectProperty.getPropertyName());
			projectProperty.setProjectCode(addProjectProperty.getProjectCode());
			projectProperty.setValueRange(addProjectProperty.getValueRange());
			projectProperty.setShowIndex(addProjectProperty.getShowIndex());
			projectProperty.setCreateId(addProjectProperty.getCreateId());
			projectProperty.setCreateDate(new Date());
			projectProperty.setRemark(addProjectProperty.getRemark());
			projectProperty.setRemark2(addProjectProperty.getRemark2());
			projectProperty.setRemark3(addProjectProperty.getRemark3());
			projectProperty.setRemark4(addProjectProperty.getRemark4());
			projectPropertyDao.insertSelective(projectProperty);
			AddProjectPropertyReturn addProjectPropertyReturn = new AddProjectPropertyReturn();
			
			logger.debug("addProjectProperty(AddProjectProperty) - end - return value={}", addProjectPropertyReturn); 
			return addProjectPropertyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增服务项目表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PROPERTY_ADD_ERROR,"新增服务项目表信息错误！",e);
		}
	}
	

	@Override
	public UpdateProjectPropertyReturn updateProjectProperty(
			UpdateProjectProperty updateProjectProperty)
			throws TsfaServiceException {
		logger.debug("updateProjectProperty(UpdateProjectProperty updateProjectProperty={}) - start", updateProjectProperty); 

		AssertUtils.notNull(updateProjectProperty);
		AssertUtils.notNullAndEmpty(updateProjectProperty.getCode(),"Code不能为空");
		try {
			ProjectProperty projectProperty = new ProjectProperty();
			//update数据录入
			projectProperty.setCode(updateProjectProperty.getCode());
			projectProperty.setPropertyName(updateProjectProperty.getPropertyName());
			projectProperty.setProjectCode(updateProjectProperty.getProjectCode());
			projectProperty.setShowIndex(updateProjectProperty.getShowIndex());
			projectProperty.setCreateId(updateProjectProperty.getCreateId());
			projectProperty.setValueRange(updateProjectProperty.getValueRange());
			projectProperty.setCreateDate(updateProjectProperty.getCreateDate());
			projectProperty.setRemark(updateProjectProperty.getRemark());
			projectProperty.setRemark2(updateProjectProperty.getRemark2());
			projectProperty.setRemark3(updateProjectProperty.getRemark3());
			projectProperty.setRemark4(updateProjectProperty.getRemark4());
			AssertUtils.notUpdateMoreThanOne(projectPropertyDao.updateByPrimaryKeySelective(projectProperty));
			UpdateProjectPropertyReturn updateProjectPropertyReturn = new UpdateProjectPropertyReturn();

			logger.debug("updateProjectProperty(UpdateProjectProperty) - end - return value={}", updateProjectPropertyReturn); 
			return updateProjectPropertyReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("服务项目表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PROPERTY_UPDATE_ERROR,"服务项目表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindProjectPropertyReturn findProjectProperty(
			FindProjectProperty findProjectProperty) throws TsfaServiceException {
		logger.debug("findProjectProperty(FindProjectProperty findProjectProperty={}) - start", findProjectProperty); 

		AssertUtils.notNull(findProjectProperty);
		AssertUtils.notAllNull(findProjectProperty.getCode(),"Code不能为空");
		try {
			ProjectProperty projectProperty = projectPropertyDao.selectByPrimaryKey(findProjectProperty.getCode());
			if(projectProperty == null){
				throw new TsfaServiceException(ErrorCode.PROJECT_PROPERTY_NOT_EXIST_ERROR,"服务项目表信息不存在");
			}
			FindProjectPropertyReturn findProjectPropertyReturn = new FindProjectPropertyReturn();
			//find数据录入
			findProjectPropertyReturn.setCode(projectProperty.getCode());
			findProjectPropertyReturn.setPropertyName(projectProperty.getPropertyName());
			findProjectPropertyReturn.setValueRange(projectProperty.getValueRange());
			findProjectPropertyReturn.setProjectCode(projectProperty.getProjectCode());
			findProjectPropertyReturn.setShowIndex(projectProperty.getShowIndex());
			findProjectPropertyReturn.setCreateId(projectProperty.getCreateId());
			findProjectPropertyReturn.setCreateDate(projectProperty.getCreateDate());
			findProjectPropertyReturn.setRemark(projectProperty.getRemark());
			findProjectPropertyReturn.setRemark2(projectProperty.getRemark2());
			findProjectPropertyReturn.setRemark3(projectProperty.getRemark3());
			findProjectPropertyReturn.setRemark4(projectProperty.getRemark4());
			
			logger.debug("findProjectProperty(FindProjectProperty) - end - return value={}", findProjectPropertyReturn); 
			return findProjectPropertyReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找服务项目表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PROPERTY_FIND_ERROR,"查找服务项目表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindProjectPropertyPageReturn> findProjectPropertyPage(
			FindProjectPropertyPage findProjectPropertyPage)
			throws TsfaServiceException {
		logger.debug("findProjectPropertyPage(FindProjectPropertyPage findProjectPropertyPage={}) - start", findProjectPropertyPage); 

		AssertUtils.notNull(findProjectPropertyPage);
		List<FindProjectPropertyPageReturn> returnList;
		int count = 0;
		try {
			returnList = projectPropertyDao.findProjectPropertyPage(findProjectPropertyPage);
			count = projectPropertyDao.findProjectPropertyPageCount(findProjectPropertyPage);
		}  catch (Exception e) {
			logger.error("服务项目表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PROJECT_PROPERTY_FIND_PAGE_ERROR,"服务项目表信息不存在错误.！",e);
		}
		Page<FindProjectPropertyPageReturn> returnPage = new Page<FindProjectPropertyPageReturn>(returnList, count, findProjectPropertyPage);

		logger.debug("findProjectPropertyPage(FindProjectPropertyPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
