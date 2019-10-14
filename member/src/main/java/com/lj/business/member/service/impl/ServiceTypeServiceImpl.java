/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IServiceTypeDao;
import com.lj.business.member.domain.ServiceType;
import com.lj.business.member.dto.service.type.AddServiceType;
import com.lj.business.member.dto.service.type.FindServiceType;
import com.lj.business.member.dto.service.type.FindServiceTypeReturn;
import com.lj.business.member.dto.service.type.UpdateServiceType;
import com.lj.business.member.service.IServiceTypeService;

/**
 * 
 * 类说明：服务类型表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年9月20日
 */
@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServiceTypeServiceImpl.class);
	

	@Resource
	private IServiceTypeDao serviceTypeDao;
	
	
	@Override
	public void addServiceType(
			AddServiceType addServiceType) throws TsfaServiceException {
		logger.debug("addServiceType(AddServiceType addServiceType={}) - start", addServiceType); 

		AssertUtils.notNull(addServiceType);
		try {
			ServiceType serviceType = new ServiceType();
			//add数据录入
			serviceType.setCode(addServiceType.getCode());
			serviceType.setServiceType(addServiceType.getServiceType());
			serviceType.setServiceName(addServiceType.getServiceName());
			serviceType.setCreateId(addServiceType.getCreateId());
			serviceType.setCreateDate(addServiceType.getCreateDate());
			serviceType.setRemark(addServiceType.getRemark());
			serviceType.setRemark2(addServiceType.getRemark2());
			serviceType.setRemark3(addServiceType.getRemark3());
			serviceType.setRemark4(addServiceType.getRemark4());
			serviceTypeDao.insert(serviceType);
			
			logger.debug("addServiceType(AddServiceType) - end - return value={}"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增服务类型信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_ADD_ERROR,"新增服务类型信息错误！",e);
		}
	}
	
	

	@Override
	public void updateServiceType(
			UpdateServiceType updateServiceType)
			throws TsfaServiceException {
		logger.debug("updateServiceType(UpdateServiceType updateServiceType={}) - start", updateServiceType); 

		AssertUtils.notNull(updateServiceType);
		AssertUtils.notNullAndEmpty(updateServiceType.getCode(),"Code不能为空");
		try {
			ServiceType serviceType = new ServiceType();
			//update数据录入
			serviceType.setCode(updateServiceType.getCode());
			serviceType.setServiceType(updateServiceType.getServiceType());
			serviceType.setServiceName(updateServiceType.getServiceName());
			serviceType.setCreateId(updateServiceType.getCreateId());
			serviceType.setCreateDate(updateServiceType.getCreateDate());
			serviceType.setRemark(updateServiceType.getRemark());
			serviceType.setRemark2(updateServiceType.getRemark2());
			serviceType.setRemark3(updateServiceType.getRemark3());
			serviceType.setRemark4(updateServiceType.getRemark4());
			AssertUtils.notUpdateMoreThanOne(serviceTypeDao.updateByPrimaryKeySelective(serviceType));

			logger.debug("updateServiceType(UpdateServiceType) - end - return value={}"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("服务类型信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_UPDATE_ERROR,"服务类型信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindServiceTypeReturn findServiceType(
			FindServiceType findServiceType) throws TsfaServiceException {
		logger.debug("findServiceType(FindServiceType findServiceType={}) - start", findServiceType); 

		AssertUtils.notNull(findServiceType);
		AssertUtils.notAllNull(findServiceType.getCode(),"CODE不能为空");
		try {
			ServiceType serviceType = serviceTypeDao.selectByPrimaryKey(findServiceType.getCode());
			if(serviceType == null){
				throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_NOT_EXIST_ERROR,"服务类型信息不存在");
			}
			FindServiceTypeReturn findServiceTypeReturn = new FindServiceTypeReturn();
			//find数据录入
			findServiceTypeReturn.setCode(serviceType.getCode());
			findServiceTypeReturn.setServiceType(serviceType.getServiceType());
			findServiceTypeReturn.setServiceName(serviceType.getServiceName());
			findServiceTypeReturn.setCreateId(serviceType.getCreateId());
			findServiceTypeReturn.setCreateDate(serviceType.getCreateDate());
			findServiceTypeReturn.setRemark(serviceType.getRemark());
			findServiceTypeReturn.setRemark2(serviceType.getRemark2());
			findServiceTypeReturn.setRemark3(serviceType.getRemark3());
			findServiceTypeReturn.setRemark4(serviceType.getRemark4());
			
			logger.debug("findServiceType(FindServiceType) - end - return value={}", findServiceTypeReturn); 
			return findServiceTypeReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找服务类型信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_FIND_ERROR,"查找服务类型信息信息错误！",e);
		}


	}



	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IServiceTypeService#findServiceTypes(com.lj.business.member.dto.FindServiceType)
	 */
	@Override
	public List<FindServiceTypeReturn> findServiceTypes(FindServiceType findServiceType) {

		AssertUtils.notNull(findServiceType);
		List<FindServiceTypeReturn> returnList=null;
		try {
			returnList = serviceTypeDao.findServiceTypes(findServiceType);
		}  catch (Exception e) {
			logger.error("服务类型查询错误",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_FIND_ERROR,"查找服务类型表信息错误！",e);
		}

		return  returnList;
	}

}
