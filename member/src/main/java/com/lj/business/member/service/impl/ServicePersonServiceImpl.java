/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service.impl;

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
import com.lj.business.member.dao.IServicePersonDao;
import com.lj.business.member.domain.ServicePerson;
import com.lj.business.member.dto.service.person.AddServicePerson;
import com.lj.business.member.dto.service.person.DelServicePerson;
import com.lj.business.member.dto.service.person.FindServicePerson;
import com.lj.business.member.dto.service.person.FindServicePersonApp;
import com.lj.business.member.dto.service.person.FindServicePersonAppReturn;
import com.lj.business.member.dto.service.person.FindServicePersonPage;
import com.lj.business.member.dto.service.person.FindServicePersonPageReturn;
import com.lj.business.member.dto.service.person.FindServicePersonReturn;
import com.lj.business.member.dto.service.person.UpdateServicePerson;
import com.lj.business.member.service.IServicePersonService;

/**
 * 
 * 类说明：服务人员表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭俊霖
 *   
 * CreateDate: 2017年9月21日
 */
@Service
public class ServicePersonServiceImpl implements IServicePersonService {

	private static final Logger logger = LoggerFactory.getLogger(ServicePersonServiceImpl.class);
	

	@Resource
	private IServicePersonDao servicePersonDao;

	@Override
	public void addServicePerson(AddServicePerson addServicePerson) throws TsfaServiceException {
		logger.debug("addServicePerson(AddServicePerson addServicePerson={}) - start", addServicePerson);

		AssertUtils.notNull(addServicePerson);
		try {
			ServicePerson servicePerson = new ServicePerson();
			// add数据录入
			servicePerson.setCode(GUID.getPreUUID());
		    servicePerson.setMerchantNo(addServicePerson.getMerchantNo());
			servicePerson.setMerchantName(addServicePerson.getMerchantName());
//			servicePerson.setShopNo(addServicePerson.getShopNo());
//			servicePerson.setShopName(addServicePerson.getShopName());
			servicePerson.setPersonNo(GUID.getPreUUID());
			servicePerson.setPersonName(addServicePerson.getPersonName());
			servicePerson.setHeadAddress(addServicePerson.getHeadAddress());
			servicePerson.setTitle(addServicePerson.getTitle());
			servicePerson.setServicePrice(addServicePerson.getServicePrice());
			servicePerson.setHcLabel(addServicePerson.getHcLabel());
			servicePerson.setSummary(addServicePerson.getSummary());
			servicePerson.setCreateId(addServicePerson.getCreateId());
			servicePerson.setCreateDate(new Date());
			servicePerson.setRemark(addServicePerson.getRemark());
			servicePerson.setRemark2(addServicePerson.getRemark2());
			servicePerson.setRemark3(addServicePerson.getRemark3());
			servicePerson.setRemark4(addServicePerson.getRemark4());
			
			servicePersonDao.insert(servicePerson);
			logger.debug("addServicePerson(AddServicePerson) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增服务人员表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_ADD_ERROR, "新增服务人员表信息错误！", e);
		}
	}

	@Override
	public void delServicePerson(DelServicePerson delServicePerson) throws TsfaServiceException {
		logger.debug("delServicePerson(DelServicePerson delServicePerson={}) - start", delServicePerson);

		AssertUtils.notNull(delServicePerson);
		AssertUtils.notNull(delServicePerson.getCode(), "Code不能为空！");
		try {
			servicePersonDao.deleteByPrimaryKey(delServicePerson.getCode());
			logger.debug("delServicePerson(DelServicePerson) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除服务人员表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_DEL_ERROR, "删除服务人员表信息错误！", e);

		}
	}

	@Override
	public void updateServicePerson(UpdateServicePerson updateServicePerson) throws TsfaServiceException {
		logger.debug("updateServicePerson(UpdateServicePerson updateServicePerson={}) - start", updateServicePerson); 

		AssertUtils.notNull(updateServicePerson);
		AssertUtils.notNullAndEmpty(updateServicePerson.getCode(), "Code不能为空");
		try {
			ServicePerson servicePerson = new ServicePerson();
			// update数据录入
			
			servicePerson.setCode(updateServicePerson.getCode());
			servicePerson.setMerchantNo(updateServicePerson.getMerchantNo());
			servicePerson.setMerchantName(updateServicePerson.getMerchantName());
//			servicePerson.setShopNo(updateServicePerson.getShopNo());
//			servicePerson.setShopName(updateServicePerson.getShopName());
			servicePerson.setPersonNo(updateServicePerson.getPersonNo());
			servicePerson.setPersonName(updateServicePerson.getPersonName());
			servicePerson.setHeadAddress(updateServicePerson.getHeadAddress());
			servicePerson.setTitle(updateServicePerson.getTitle());
			servicePerson.setServicePrice(updateServicePerson.getServicePrice());
			servicePerson.setHcLabel(updateServicePerson.getHcLabel());
			servicePerson.setSummary(updateServicePerson.getSummary());
			servicePerson.setCreateId(updateServicePerson.getCreateId());
			servicePerson.setCreateDate(updateServicePerson.getCreateDate());
			servicePerson.setRemark(updateServicePerson.getRemark());
			servicePerson.setRemark2(updateServicePerson.getRemark2());
			servicePerson.setRemark3(updateServicePerson.getRemark3());
			servicePerson.setRemark4(updateServicePerson.getRemark4());
			AssertUtils.notUpdateMoreThanOne(servicePersonDao.updateByPrimaryKeySelective(servicePerson));
			logger.debug("updateServicePerson(UpdateServicePerson) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("服务人员表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_UPDATE_ERROR, "服务人员表信息更新信息错误！", e);
		}
	}

	@Override
	public FindServicePersonReturn findServicePerson(FindServicePerson findServicePerson) throws TsfaServiceException {
		logger.debug("findServicePerson(FindServicePerson findServicePerson={}) - start", findServicePerson); 

		AssertUtils.notNull(findServicePerson);
		AssertUtils.notAllNull(findServicePerson.getCode(), "Code不能为空");
		try {
			ServicePerson servicePerson = servicePersonDao.selectByPrimaryKey(findServicePerson.getCode());
			if (servicePerson == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_NOT_EXIST_ERROR,"服务人员表信息不存在");
			}
			FindServicePersonReturn findServicePersonReturn = new FindServicePersonReturn();
			// find数据录入
			findServicePersonReturn.setCode(servicePerson.getCode());
			findServicePersonReturn.setMerchantNo(servicePerson.getMerchantNo());
			findServicePersonReturn.setMerchantName(servicePerson.getMerchantName());
//			findServicePersonReturn.setShopNo(servicePerson.getShopNo());
//			findServicePersonReturn.setShopName(servicePerson.getShopName());
			findServicePersonReturn.setPersonNo(servicePerson.getPersonNo());
			findServicePersonReturn.setPersonName(servicePerson.getPersonName());
			findServicePersonReturn.setHeadAddress(servicePerson.getHeadAddress());
			findServicePersonReturn.setTitle(servicePerson.getTitle());
			findServicePersonReturn.setServicePrice(servicePerson.getServicePrice());
			findServicePersonReturn.setHcLabel(servicePerson.getHcLabel());
			findServicePersonReturn.setSummary(servicePerson.getSummary());
			findServicePersonReturn.setCreateId(servicePerson.getCreateId());
			findServicePersonReturn.setCreateDate(servicePerson.getCreateDate());
			findServicePersonReturn.setRemark(servicePerson.getRemark());
			findServicePersonReturn.setRemark2(servicePerson.getRemark2());
			findServicePersonReturn.setRemark3(servicePerson.getRemark3());
			findServicePersonReturn.setRemark4(servicePerson.getRemark4());

			logger.debug("findServicePerson(FindServicePerson) - end - return value={}", findServicePersonReturn); 
			return findServicePersonReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找服务人员表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_FIND_ERROR, "查找服务人员表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindServicePersonPageReturn> findServicePersonPage(FindServicePersonPage findServicePersonPage) throws TsfaServiceException {
		 logger.debug("findServicePersonPage(FindServicePersonPage findServicePersonPage={}) - start", findServicePersonPage); 
		
		 AssertUtils.notNull(findServicePersonPage);
		 List<FindServicePersonPageReturn> returnList;
		 int count = 0;
		 try {
		 returnList = servicePersonDao.findServicePersonPage(findServicePersonPage);
		 count = servicePersonDao.findServicePersonPageCount(findServicePersonPage);
		 } catch (Exception e) {
		 logger.error("服务人员表信息不存在错误",e);
		 throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_FIND_PAGE_ERROR,"服务人员表信息不存在错误.！",e);
		 }
		 Page<FindServicePersonPageReturn> returnPage = new Page<FindServicePersonPageReturn>(returnList, count, findServicePersonPage);
		
		 logger.debug("findServicePersonPage(FindServicePersonPage) - end - return value={}", returnPage); 
		 return returnPage;
	}

	@Override
	public List<FindServicePersonAppReturn> findServicePersonApp(FindServicePersonApp findServicePersonApp) throws TsfaServiceException {
		AssertUtils.notNull(findServicePersonApp);
//		AssertUtils.notNull(findServicePersonApp.getShopNo(), "分店编号不能为空");
		try {
			return servicePersonDao.findServicePersonApp(findServicePersonApp);
		} catch(Exception e) {
			logger.error("查询服务人员错误", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_FIND_ERROR, "查找服务人员表信息信息错误！", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.member.service.IServicePersonService#findServicePersons(com.lj.business.member.dto.FindServicePerson)
	 */
	@Override
	public List<FindServicePersonReturn> findServicePersons(FindServicePerson findServicePerson) {

		AssertUtils.notNull(findServicePerson);
		List<FindServicePersonReturn> returnList=null;
		try {
			returnList = servicePersonDao.findServicePersons(findServicePerson);
		}  catch (Exception e) {
			logger.error("终端表信息分页查询错误",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_FIND_ERROR,"查找服务人员表信息错误！",e);
		}

		return  returnList;
	}
}
