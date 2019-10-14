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
import com.lj.business.member.dao.IServiceTypeChooseDao;
import com.lj.business.member.domain.ServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChooseReturn;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePage;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePageReturn;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChooseReturn;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChooseReturn;
import com.lj.business.member.service.IServiceTypeChooseService;

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
public class ServiceTypeChooseServiceImpl implements IServiceTypeChooseService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServiceTypeChooseServiceImpl.class);
	

	@Resource
	private IServiceTypeChooseDao serviceTypeChooseDao;
	
	
	@Override
	public AddServiceTypeChooseReturn addServiceTypeChoose(
			AddServiceTypeChoose addServiceTypeChoose) throws TsfaServiceException {
		logger.debug("addServiceTypeChoose(AddServiceTypeChoose addServiceTypeChoose={}) - start", addServiceTypeChoose); 

		AssertUtils.notNull(addServiceTypeChoose);
		try {
			ServiceTypeChoose serviceTypeChoose = new ServiceTypeChoose();
			//add数据录入
			serviceTypeChoose.setCode(GUID.generateByUUID());
			serviceTypeChoose.setMerchantNo(addServiceTypeChoose.getMerchantNo());
			serviceTypeChoose.setMerchantName(addServiceTypeChoose.getMerchantName());
//			serviceTypeChoose.setShopNo(addServiceTypeChoose.getShopNo());
//			serviceTypeChoose.setShopName(addServiceTypeChoose.getShopName());
			serviceTypeChoose.setServiceCode(addServiceTypeChoose.getServiceCode());
			serviceTypeChoose.setServiceType(addServiceTypeChoose.getServiceType());
			serviceTypeChoose.setServiceName(addServiceTypeChoose.getServiceName());
			serviceTypeChoose.setShowIndex(addServiceTypeChoose.getShowIndex());
			serviceTypeChoose.setCreateId(addServiceTypeChoose.getCreateId());
			serviceTypeChoose.setCreateDate(new Date());
			serviceTypeChoose.setRemark(addServiceTypeChoose.getRemark());
			serviceTypeChoose.setRemark2(addServiceTypeChoose.getRemark2());
			serviceTypeChoose.setRemark3(addServiceTypeChoose.getRemark3());
			serviceTypeChoose.setRemark4(addServiceTypeChoose.getRemark4());
			serviceTypeChooseDao.insert(serviceTypeChoose);
			AddServiceTypeChooseReturn addServiceTypeChooseReturn = new AddServiceTypeChooseReturn();
			
			logger.debug("addServiceTypeChoose(AddServiceTypeChoose) - end - return value={}", addServiceTypeChooseReturn); 
			return addServiceTypeChooseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增服务类型选择表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_CHOOSE_ADD_ERROR,"新增服务类型选择表信息错误！",e);
		}
	}
	
	

	@Override
	public UpdateServiceTypeChooseReturn updateServiceTypeChoose(
			UpdateServiceTypeChoose updateServiceTypeChoose)
			throws TsfaServiceException {
		logger.debug("updateServiceTypeChoose(UpdateServiceTypeChoose updateServiceTypeChoose={}) - start", updateServiceTypeChoose); 

		AssertUtils.notNull(updateServiceTypeChoose);
		AssertUtils.notNullAndEmpty(updateServiceTypeChoose.getCode(),"ID不能为空");
		try {
			ServiceTypeChoose serviceTypeChoose = new ServiceTypeChoose();
			//update数据录入
			serviceTypeChoose.setCode(updateServiceTypeChoose.getCode());
			serviceTypeChoose.setMerchantNo(updateServiceTypeChoose.getMerchantNo());
			serviceTypeChoose.setMerchantName(updateServiceTypeChoose.getMerchantName());
//			serviceTypeChoose.setShopNo(updateServiceTypeChoose.getShopNo());
//			serviceTypeChoose.setShopName(updateServiceTypeChoose.getShopName());
			serviceTypeChoose.setServiceCode(updateServiceTypeChoose.getServiceCode());
			serviceTypeChoose.setServiceType(updateServiceTypeChoose.getServiceType());
			serviceTypeChoose.setServiceName(updateServiceTypeChoose.getServiceName());
			serviceTypeChoose.setShowIndex(updateServiceTypeChoose.getShowIndex());
			serviceTypeChoose.setCreateId(updateServiceTypeChoose.getCreateId());
			serviceTypeChoose.setCreateDate(updateServiceTypeChoose.getCreateDate());
			serviceTypeChoose.setRemark(updateServiceTypeChoose.getRemark());
			serviceTypeChoose.setRemark2(updateServiceTypeChoose.getRemark2());
			serviceTypeChoose.setRemark3(updateServiceTypeChoose.getRemark3());
			serviceTypeChoose.setRemark4(updateServiceTypeChoose.getRemark4());
			AssertUtils.notUpdateMoreThanOne(serviceTypeChooseDao.updateByPrimaryKeySelective(serviceTypeChoose));
			UpdateServiceTypeChooseReturn updateServiceTypeChooseReturn = new UpdateServiceTypeChooseReturn();

			logger.debug("updateServiceTypeChoose(UpdateServiceTypeChoose) - end - return value={}", updateServiceTypeChooseReturn); 
			return updateServiceTypeChooseReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("服务类型选择表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_CHOOSE_UPDATE_ERROR,"服务类型选择表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindServiceTypeChooseReturn findServiceTypeChoose(
			FindServiceTypeChoose findServiceTypeChoose) throws TsfaServiceException {
		logger.debug("findServiceTypeChoose(FindServiceTypeChoose findServiceTypeChoose={}) - start", findServiceTypeChoose); 

		AssertUtils.notNull(findServiceTypeChoose);
		AssertUtils.notAllNull(findServiceTypeChoose.getCode(),"Code不能为空");
		try {
			ServiceTypeChoose serviceTypeChoose = serviceTypeChooseDao.selectByPrimaryKey(findServiceTypeChoose.getCode());
			if(serviceTypeChoose == null){
				throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_CHOOSE_NOT_EXIST_ERROR,"服务类型选择表信息不存在");
			}
			FindServiceTypeChooseReturn findServiceTypeChooseReturn = new FindServiceTypeChooseReturn();
			//find数据录入
			findServiceTypeChooseReturn.setCode(serviceTypeChoose.getCode());
			findServiceTypeChooseReturn.setMerchantNo(serviceTypeChoose.getMerchantNo());
			findServiceTypeChooseReturn.setMerchantName(serviceTypeChoose.getMerchantName());
//			findServiceTypeChooseReturn.setShopNo(serviceTypeChoose.getShopNo());
//			findServiceTypeChooseReturn.setShopName(serviceTypeChoose.getShopName());
			findServiceTypeChooseReturn.setServiceCode(serviceTypeChoose.getServiceCode());
			findServiceTypeChooseReturn.setServiceType(serviceTypeChoose.getServiceType());
			findServiceTypeChooseReturn.setServiceName(serviceTypeChoose.getServiceName());
			findServiceTypeChooseReturn.setShowIndex(serviceTypeChoose.getShowIndex());
			findServiceTypeChooseReturn.setCreateId(serviceTypeChoose.getCreateId());
			findServiceTypeChooseReturn.setCreateDate(serviceTypeChoose.getCreateDate());
			findServiceTypeChooseReturn.setRemark(serviceTypeChoose.getRemark());
			findServiceTypeChooseReturn.setRemark2(serviceTypeChoose.getRemark2());
			findServiceTypeChooseReturn.setRemark3(serviceTypeChoose.getRemark3());
			findServiceTypeChooseReturn.setRemark4(serviceTypeChoose.getRemark4());
			
			logger.debug("findServiceTypeChoose(FindServiceTypeChoose) - end - return value={}", findServiceTypeChooseReturn); 
			return findServiceTypeChooseReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找服务类型选择表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_CHOOSE_FIND_ERROR,"查找服务类型选择表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindServiceTypeChoosePageReturn> findServiceTypeChoosePage(
			FindServiceTypeChoosePage findServiceTypeChoosePage)
			throws TsfaServiceException {
		logger.debug("findServiceTypeChoosePage(FindServiceTypeChoosePage findServiceTypeChoosePage={}) - start", findServiceTypeChoosePage); 

		AssertUtils.notNull(findServiceTypeChoosePage);
		List<FindServiceTypeChoosePageReturn> returnList;
		int count = 0;
		try {
			returnList = serviceTypeChooseDao.findServiceTypeChoosePage(findServiceTypeChoosePage);
			count = serviceTypeChooseDao.findServiceTypeChoosePageCount(findServiceTypeChoosePage);
		}  catch (Exception e) {
			logger.error("服务类型选择表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_TYPE_CHOOSE_FIND_PAGE_ERROR,"服务类型选择表信息不存在错误.！",e);
		}
		Page<FindServiceTypeChoosePageReturn> returnPage = new Page<FindServiceTypeChoosePageReturn>(returnList, count, findServiceTypeChoosePage);

		logger.debug("findServiceTypeChoosePage(FindServiceTypeChoosePage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
