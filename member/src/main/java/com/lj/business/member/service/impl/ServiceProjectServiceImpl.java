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
import com.lj.business.member.dao.IServiceProjectDao;
import com.lj.business.member.domain.ServiceProject;
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
import com.lj.business.member.service.IServiceProjectService;

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
public class ServiceProjectServiceImpl implements IServiceProjectService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServiceProjectServiceImpl.class);
	

	@Resource
	private IServiceProjectDao serviceProjectDao;
	
	
	@Override
	public AddServiceProjectReturn addServiceProject(
			AddServiceProject addServiceProject) throws TsfaServiceException {
		logger.debug("addServiceProject(AddServiceProject addServiceProject={}) - start", addServiceProject); 

		AssertUtils.notNull(addServiceProject);
		try {
			ServiceProject serviceProject = new ServiceProject();
			//add数据录入
			serviceProject.setCode(GUID.getPreUUID());
			serviceProject.setProjectName(addServiceProject.getProjectName());
			serviceProject.setShowIndex(addServiceProject.getShowIndex());
//			serviceProject.setShopNo(addServiceProject.getShopNo());
//			serviceProject.setShopName(addServiceProject.getShopName());
			serviceProject.setMerchantNo(addServiceProject.getMerchantNo());
			serviceProject.setMerchantName(addServiceProject.getMerchantName());
			serviceProject.setCreateId(addServiceProject.getCreateId());
			serviceProject.setCreateDate(new Date());
			serviceProject.setRemark(addServiceProject.getRemark());
			serviceProject.setRemark2(addServiceProject.getRemark2());
			serviceProject.setRemark3(addServiceProject.getRemark3());
			serviceProject.setRemark4(addServiceProject.getRemark4());
			serviceProjectDao.insert(serviceProject);
			AddServiceProjectReturn addServiceProjectReturn = new AddServiceProjectReturn();
			
			logger.debug("addServiceProject(AddServiceProject) - end - return value={}", addServiceProjectReturn); 
			return addServiceProjectReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增服务项目表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_ADD_ERROR,"新增服务项目表信息错误！",e);
		}
	}
	

	@Override
	public UpdateServiceProjectReturn updateServiceProject(
			UpdateServiceProject updateServiceProject)
			throws TsfaServiceException {
		logger.debug("updateServiceProject(UpdateServiceProject updateServiceProject={}) - start", updateServiceProject); 

		AssertUtils.notNull(updateServiceProject);
		AssertUtils.notNullAndEmpty(updateServiceProject.getCode(),"code不能为空");
		try {
			ServiceProject serviceProject = new ServiceProject();
			//update数据录入
			serviceProject.setCode(updateServiceProject.getCode());
			serviceProject.setProjectName(updateServiceProject.getProjectName());
			serviceProject.setShowIndex(updateServiceProject.getShowIndex());
//			serviceProject.setShopNo(updateServiceProject.getShopNo());
//			serviceProject.setShopName(updateServiceProject.getShopName());
			serviceProject.setMerchantNo(updateServiceProject.getMerchantNo());
			serviceProject.setMerchantName(updateServiceProject.getMerchantName());
			serviceProject.setCreateId(updateServiceProject.getCreateId());
			serviceProject.setCreateDate(updateServiceProject.getCreateDate());
			serviceProject.setRemark(updateServiceProject.getRemark());
			serviceProject.setRemark2(updateServiceProject.getRemark2());
			serviceProject.setRemark3(updateServiceProject.getRemark3());
			serviceProject.setRemark4(updateServiceProject.getRemark4());
			AssertUtils.notUpdateMoreThanOne(serviceProjectDao.updateByPrimaryKeySelective(serviceProject));
			UpdateServiceProjectReturn updateServiceProjectReturn = new UpdateServiceProjectReturn();

			logger.debug("updateServiceProject(UpdateServiceProject) - end - return value={}", updateServiceProjectReturn); 
			return updateServiceProjectReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("服务项目表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_UPDATE_ERROR,"服务项目表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindServiceProjectReturn findServiceProject(
			FindServiceProject findServiceProject) throws TsfaServiceException {
		logger.debug("findServiceProject(FindServiceProject findServiceProject={}) - start", findServiceProject); 

		AssertUtils.notNull(findServiceProject);
		AssertUtils.notAllNull(findServiceProject.getCode(),"ID不能为空");
		try {
			ServiceProject serviceProject = serviceProjectDao.selectByPrimaryKey(findServiceProject.getCode());
			if(serviceProject == null){
				throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_NOT_EXIST_ERROR,"服务项目表信息不存在");
			}
			FindServiceProjectReturn findServiceProjectReturn = new FindServiceProjectReturn();
			//find数据录入
			findServiceProjectReturn.setCode(serviceProject.getCode());
			findServiceProjectReturn.setProjectName(serviceProject.getProjectName());
			findServiceProjectReturn.setShowIndex(serviceProject.getShowIndex());
//			findServiceProjectReturn.setShopNo(serviceProject.getShopNo());
//			findServiceProjectReturn.setShopName(serviceProject.getShopName());
			findServiceProjectReturn.setMerchantNo(serviceProject.getMerchantNo());
			findServiceProjectReturn.setMerchantName(serviceProject.getMerchantName());
			findServiceProjectReturn.setCreateId(serviceProject.getCreateId());
			findServiceProjectReturn.setCreateDate(serviceProject.getCreateDate());
			findServiceProjectReturn.setRemark(serviceProject.getRemark());
			findServiceProjectReturn.setRemark2(serviceProject.getRemark2());
			findServiceProjectReturn.setRemark3(serviceProject.getRemark3());
			findServiceProjectReturn.setRemark4(serviceProject.getRemark4());
			
			logger.debug("findServiceProject(FindServiceProject) - end - return value={}", findServiceProjectReturn); 
			return findServiceProjectReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找服务项目表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_FIND_ERROR,"查找服务项目表信息信息错误！",e);
		}

	}

	
	@Override
	public Page<FindServiceProjectPageReturn> findServiceProjectPage(
			FindServiceProjectPage findServiceProjectPage)
			throws TsfaServiceException {
		logger.debug("findServiceProjectPage(FindServiceProjectPage findServiceProjectPage={}) - start", findServiceProjectPage); 

		AssertUtils.notNull(findServiceProjectPage);
		List<FindServiceProjectPageReturn> returnList;
		int count = 0;
		try {
			returnList = serviceProjectDao.findServiceProjectPage(findServiceProjectPage);
			count = serviceProjectDao.findServiceProjectPageCount(findServiceProjectPage);
		}  catch (Exception e) {
			logger.error("服务项目表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_FIND_PAGE_ERROR,"服务项目表信息不存在错误.！",e);
		}
		Page<FindServiceProjectPageReturn> returnPage = new Page<FindServiceProjectPageReturn>(returnList, count, findServiceProjectPage);

		logger.debug("findServiceProjectPage(FindServiceProjectPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<FindServiceProjectAppReturn> findServiceProjectApp(FindServiceProjectApp findServiceProjectApp) throws TsfaServiceException {
		AssertUtils.notNull(findServiceProjectApp);
//		AssertUtils.notNull(findServiceProjectApp.getShopNo(), "分店编号不能为空");
		try {
			return serviceProjectDao.findServiceProjectApp(findServiceProjectApp);
		} catch(Exception e) {
			logger.error("查找服务项目表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_FIND_ERROR,"查找服务项目表信息信息错误！",e);
		}
	}


	@Override
	public List<FindServiceProjectAppReturn> findServiceProjectList(FindServiceProjectApp findServiceProjectApp) {
		logger.debug("findServiceProjectPage(FindServiceProjectPage findServiceProjectPage={}) - start", findServiceProjectApp); 
	     AssertUtils.notNull(findServiceProjectApp);
	     List<FindServiceProjectAppReturn> list=null;
		try {
			list=serviceProjectDao.findServiceProjectList(findServiceProjectApp);		
			} catch (Exception e) {
				logger.error("服务项目表信息不存在错误",e);
				throw new TsfaServiceException(ErrorCode.SERVICE_PROJECT_FIND_PAGE_ERROR,"服务项目表信息不存在错误.！",e);
		}
		return list;
	}



}
