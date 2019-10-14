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
import com.lj.business.member.dao.IServicePersonProductDao;
import com.lj.business.member.domain.ServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.AddServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.DelServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPage;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPageReturn;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductReturn;
import com.lj.business.member.dto.service.personproduct.UpdateServicePersonProduct;
import com.lj.business.member.service.IServicePersonProductService;

/**
 * 
 * 类说明：服务人员作品表
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
public class ServicePersonProductServiceImpl implements IServicePersonProductService {

	private static final Logger logger = LoggerFactory.getLogger(ServicePersonProductServiceImpl.class);
	

	@Resource
	private IServicePersonProductDao servicePersonProductDao;

	@Override
	public void addServicePersonProduct(AddServicePersonProduct addServicePersonProduct) throws TsfaServiceException {
		logger.debug("addServicePersonProduct(AddServicePersonProduct addServicePersonProduct={}) - start", addServicePersonProduct);

		AssertUtils.notNull(addServicePersonProduct);
		try {
			ServicePersonProduct servicePersonProduct = new ServicePersonProduct();
			// add数据录入
			servicePersonProduct.setCode(GUID.getPreUUID());
			servicePersonProduct.setPersonNo(addServicePersonProduct.getPersonNo());
			servicePersonProduct.setPersonName(addServicePersonProduct.getPersonName());
			servicePersonProduct.setMerchantNo(addServicePersonProduct.getMerchantNo());
			servicePersonProduct.setMerchantName(addServicePersonProduct.getMerchantName());
//			servicePersonProduct.setShopNo(addServicePersonProduct.getShopNo());
//			servicePersonProduct.setShopName(addServicePersonProduct.getShopName());
			servicePersonProduct.setPrice(addServicePersonProduct.getPrice());
			servicePersonProduct.setDescription(addServicePersonProduct.getDescription());
			servicePersonProduct.setCoverAddr(addServicePersonProduct.getCoverAddr());
			servicePersonProduct.setImgAddr(addServicePersonProduct.getImgAddr());
			servicePersonProduct.setServiceChooseCode(addServicePersonProduct.getServiceChooseCode());
			servicePersonProduct.setCreateId(addServicePersonProduct.getCreateId());
			servicePersonProduct.setCreateDate(new Date());
			servicePersonProduct.setRemark(addServicePersonProduct.getRemark());
			servicePersonProduct.setRemark2(addServicePersonProduct.getRemark2());
			servicePersonProduct.setRemark3(addServicePersonProduct.getRemark3());
			servicePersonProduct.setRemark4(addServicePersonProduct.getRemark4());
			servicePersonProductDao.insert(servicePersonProduct);
			logger.debug("addServicePersonProduct(AddServicePersonProduct) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增服务人员作品表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_ADD_ERROR, "新增服务人员作品表信息错误！", e);
		}
	}

	@Override
	public void delServicePersonProduct(DelServicePersonProduct delServicePersonProduct) throws TsfaServiceException {
		logger.debug("delServicePersonProduct(DelServicePersonProduct delServicePersonProduct={}) - start", delServicePersonProduct);

		AssertUtils.notNull(delServicePersonProduct);
		AssertUtils.notNull(delServicePersonProduct.getCode(), "Code不能为空！");
		try {
			servicePersonProductDao.deleteByPrimaryKey(delServicePersonProduct.getCode());
			logger.debug("delServicePersonProduct(DelServicePersonProduct) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除服务人员作品表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_DEL_ERROR, "删除服务人员作品表信息错误！", e);

		}
	}

	@Override
	public void updateServicePersonProduct(UpdateServicePersonProduct updateServicePersonProduct) throws TsfaServiceException {
		logger.debug("updateServicePersonProduct(UpdateServicePersonProduct updateServicePersonProduct={}) - start", updateServicePersonProduct); 

		AssertUtils.notNull(updateServicePersonProduct);
		AssertUtils.notNullAndEmpty(updateServicePersonProduct.getCode(), "Code不能为空");
		try {
			ServicePersonProduct servicePersonProduct = new ServicePersonProduct();
			// update数据录入
			servicePersonProduct.setCode(updateServicePersonProduct.getCode());
			servicePersonProduct.setPersonNo(updateServicePersonProduct.getPersonNo());
			servicePersonProduct.setPersonName(updateServicePersonProduct.getPersonName());
			servicePersonProduct.setMerchantNo(updateServicePersonProduct.getMerchantNo());
			servicePersonProduct.setMerchantName(updateServicePersonProduct.getMerchantName());
//			servicePersonProduct.setShopNo(updateServicePersonProduct.getShopNo());
//			servicePersonProduct.setShopName(updateServicePersonProduct.getShopName());
			servicePersonProduct.setPrice(updateServicePersonProduct.getPrice());
			servicePersonProduct.setDescription(updateServicePersonProduct.getDescription());
			servicePersonProduct.setCoverAddr(updateServicePersonProduct.getCoverAddr());
			servicePersonProduct.setImgAddr(updateServicePersonProduct.getImgAddr());
			servicePersonProduct.setServiceChooseCode(updateServicePersonProduct.getServiceChooseCode());
			servicePersonProduct.setCreateId(updateServicePersonProduct.getCreateId());
			servicePersonProduct.setCreateDate(updateServicePersonProduct.getCreateDate());
			servicePersonProduct.setRemark(updateServicePersonProduct.getRemark());
			servicePersonProduct.setRemark2(updateServicePersonProduct.getRemark2());
			servicePersonProduct.setRemark3(updateServicePersonProduct.getRemark3());
			servicePersonProduct.setRemark4(updateServicePersonProduct.getRemark4());
			
			AssertUtils.notUpdateMoreThanOne(servicePersonProductDao.updateByPrimaryKeySelective(servicePersonProduct));
			logger.debug("updateServicePersonProduct(UpdateServicePersonProduct) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("服务人员作品表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_UPDATE_ERROR, "服务人员作品表信息更新信息错误！", e);
		}
	}

	@Override
	public FindServicePersonProductReturn findServicePersonProduct(FindServicePersonProduct findServicePersonProduct) throws TsfaServiceException {
		logger.debug("findServicePersonProduct(FindServicePersonProduct findServicePersonProduct={}) - start", findServicePersonProduct); 

		AssertUtils.notNull(findServicePersonProduct);
		AssertUtils.notAllNull(findServicePersonProduct.getCode(), "Code不能为空");
		try {
			ServicePersonProduct servicePersonProduct = servicePersonProductDao.selectByPrimaryKey(findServicePersonProduct.getCode());
			if (servicePersonProduct == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_NOT_EXIST_ERROR,"服务人员作品表信息不存在");
			}
			FindServicePersonProductReturn findServicePersonProductReturn = new FindServicePersonProductReturn();
			// find数据录入
			findServicePersonProductReturn.setCode(servicePersonProduct.getCode());
			findServicePersonProductReturn.setPersonNo(servicePersonProduct.getPersonNo());
			findServicePersonProductReturn.setPersonName(servicePersonProduct.getPersonName());
			findServicePersonProductReturn.setMerchantNo(servicePersonProduct.getMerchantNo());
			findServicePersonProductReturn.setMerchantName(servicePersonProduct.getMerchantName());
//			findServicePersonProductReturn.setShopNo(servicePersonProduct.getShopNo());
//			findServicePersonProductReturn.setShopName(servicePersonProduct.getShopName());
			findServicePersonProductReturn.setPrice(servicePersonProduct.getPrice());
			findServicePersonProductReturn.setDescription(servicePersonProduct.getDescription());
			findServicePersonProductReturn.setCoverAddr(servicePersonProduct.getCoverAddr());
			findServicePersonProductReturn.setImgAddr(servicePersonProduct.getImgAddr());
			findServicePersonProductReturn.setServiceChooseCode(servicePersonProduct.getServiceChooseCode());
			findServicePersonProductReturn.setCreateId(servicePersonProduct.getCreateId());
			findServicePersonProductReturn.setCreateDate(servicePersonProduct.getCreateDate());
			findServicePersonProductReturn.setRemark(servicePersonProduct.getRemark());
			findServicePersonProductReturn.setRemark2(servicePersonProduct.getRemark2());
			findServicePersonProductReturn.setRemark3(servicePersonProduct.getRemark3());
			findServicePersonProductReturn.setRemark4(servicePersonProduct.getRemark4());

			logger.debug("findServicePersonProduct(FindServicePersonProduct) - end - return value={}", findServicePersonProductReturn); 
			return findServicePersonProductReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找服务人员作品表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_FIND_ERROR, "查找服务人员作品表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindServicePersonProductPageReturn> findServicePersonProductPage(FindServicePersonProductPage findServicePersonProductPage) throws TsfaServiceException {
		 logger.debug("findServicePersonProductPage(FindServicePersonProductPage findServicePersonProductPage={}) - start", findServicePersonProductPage); 
		
		 AssertUtils.notNull(findServicePersonProductPage);
		 List<FindServicePersonProductPageReturn> returnList;
		 int count = 0;
		 try {
		 returnList = servicePersonProductDao.findServicePersonProductPage(findServicePersonProductPage);
		 count = servicePersonProductDao.findServicePersonProductPageCount(findServicePersonProductPage);
		 } catch (Exception e) {
		 logger.error("服务人员作品表信息不存在错误",e);
		 throw new TsfaServiceException(ErrorCode.SERVICE_PERSON_PRODUCT_FIND_PAGE_ERROR,"服务人员作品表信息不存在错误.！",e);
		 }
		 Page<FindServicePersonProductPageReturn> returnPage = new Page<FindServicePersonProductPageReturn>(returnList, count, findServicePersonProductPage);
		
		 logger.debug("findServicePersonProductPage(FindServicePersonProductPage) - end - return value={}", returnPage); 
		 return returnPage;
	}
}
