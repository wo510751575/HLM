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
import com.lj.business.member.dao.IServiceProductDao;
import com.lj.business.member.domain.ServiceProduct;
import com.lj.business.member.dto.service.product.AddServiceProduct;
import com.lj.business.member.dto.service.product.AddServiceProductReturn;
import com.lj.business.member.dto.service.product.FindServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProductPage;
import com.lj.business.member.dto.service.product.FindServiceProductPageReturn;
import com.lj.business.member.dto.service.product.FindServiceProductReturn;
import com.lj.business.member.dto.service.product.UpdateServiceProduct;
import com.lj.business.member.dto.service.product.UpdateServiceProductReturn;
import com.lj.business.member.service.IServiceProductService;

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
public class ServiceProductServiceImpl implements IServiceProductService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ServiceProductServiceImpl.class);
	

	@Resource
	private IServiceProductDao serviceProductDao;
	
	
	@Override
	public AddServiceProductReturn addServiceProduct(
			AddServiceProduct addServiceProduct) throws TsfaServiceException {
		logger.debug("addServiceProduct(AddServiceProduct addServiceProduct={}) - start", addServiceProduct); 

		AssertUtils.notNull(addServiceProduct);
		try {
			ServiceProduct serviceProduct = new ServiceProduct();
			//add数据录入
			serviceProduct.setCode(GUID.getPreUUID());
			serviceProduct.setProductName(addServiceProduct.getProductName());
//			serviceProduct.setShopNo(addServiceProduct.getShopNo());
//			serviceProduct.setShopName(addServiceProduct.getShopName());
			serviceProduct.setMerchantNo(addServiceProduct.getMerchantNo());
			serviceProduct.setMerchantName(addServiceProduct.getMerchantName());
			serviceProduct.setKeywords(addServiceProduct.getKeywords());
			serviceProduct.setDescription(addServiceProduct.getDescription());
			serviceProduct.setCoverAddr(addServiceProduct.getCoverAddr());
			serviceProduct.setImgAddr(addServiceProduct.getImgAddr());
			serviceProduct.setCreateId(addServiceProduct.getCreateId());
			serviceProduct.setCreateDate(new Date());
			serviceProduct.setRemark(addServiceProduct.getRemark());
			serviceProduct.setRemark2(addServiceProduct.getRemark2());
			serviceProduct.setRemark3(addServiceProduct.getRemark3());
			serviceProduct.setRemark4(addServiceProduct.getRemark4());
			serviceProductDao.insertSelective(serviceProduct);
			AddServiceProductReturn addServiceProductReturn = new AddServiceProductReturn();
			logger.debug("addServiceProduct(AddServiceProduct) - end - return value={}", addServiceProductReturn); 
			return addServiceProductReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增服务人员作品表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PRODUCT_ADD_ERROR,"新增服务人员作品表信息错误！",e);
		}
	}
	

	@Override
	public UpdateServiceProductReturn updateServiceProduct(
			UpdateServiceProduct updateServiceProduct)
			throws TsfaServiceException {
		logger.debug("updateServiceProduct(UpdateServiceProduct updateServiceProduct={}) - start", updateServiceProduct); 

		AssertUtils.notNull(updateServiceProduct);
		AssertUtils.notNullAndEmpty(updateServiceProduct.getCode(),"Code不能为空");
		try {
			ServiceProduct serviceProduct = new ServiceProduct();
			//update数据录入
			serviceProduct.setCode(updateServiceProduct.getCode());
			serviceProduct.setProductName(updateServiceProduct.getProductName());
//			serviceProduct.setShopNo(updateServiceProduct.getShopNo());
//			serviceProduct.setShopName(updateServiceProduct.getShopName());
			serviceProduct.setMerchantNo(updateServiceProduct.getMerchantNo());
			serviceProduct.setMerchantName(updateServiceProduct.getMerchantName());
			serviceProduct.setKeywords(updateServiceProduct.getKeywords());
			serviceProduct.setDescription(updateServiceProduct.getDescription());
			serviceProduct.setCoverAddr(updateServiceProduct.getCoverAddr());
			serviceProduct.setImgAddr(updateServiceProduct.getImgAddr());
			serviceProduct.setCreateId(updateServiceProduct.getCreateId());
			serviceProduct.setCreateDate(updateServiceProduct.getCreateDate());
			serviceProduct.setRemark(updateServiceProduct.getRemark());
			serviceProduct.setRemark2(updateServiceProduct.getRemark2());
			serviceProduct.setRemark3(updateServiceProduct.getRemark3());
			serviceProduct.setRemark4(updateServiceProduct.getRemark4());
			AssertUtils.notUpdateMoreThanOne(serviceProductDao.updateByPrimaryKeySelective(serviceProduct));
			UpdateServiceProductReturn updateServiceProductReturn = new UpdateServiceProductReturn();

			logger.debug("updateServiceProduct(UpdateServiceProduct) - end - return value={}", updateServiceProductReturn); 
			return updateServiceProductReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("服务人员作品表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PRODUCT_UPDATE_ERROR,"服务人员作品表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindServiceProductReturn findServiceProduct(
			FindServiceProduct findServiceProduct) throws TsfaServiceException {
		logger.debug("findServiceProduct(FindServiceProduct findServiceProduct={}) - start", findServiceProduct); 

		AssertUtils.notNull(findServiceProduct);
		AssertUtils.notAllNull(findServiceProduct.getCode(),"Code不能为空");
		try {
			ServiceProduct serviceProduct = serviceProductDao.selectByPrimaryKey(findServiceProduct.getCode());
			if(serviceProduct == null){
				throw new TsfaServiceException(ErrorCode.SERVICE_PRODUCT_NOT_EXIST_ERROR,"服务人员作品表信息不存在");
			}
			FindServiceProductReturn findServiceProductReturn = new FindServiceProductReturn();
			//find数据录入
			findServiceProductReturn.setCode(serviceProduct.getCode());
			findServiceProductReturn.setProductName(serviceProduct.getProductName());
//			findServiceProductReturn.setShopNo(serviceProduct.getShopNo());
//			findServiceProductReturn.setShopName(serviceProduct.getShopName());
			findServiceProductReturn.setMerchantNo(serviceProduct.getMerchantNo());
			findServiceProductReturn.setMerchantName(serviceProduct.getMerchantName());
			findServiceProductReturn.setKeywords(serviceProduct.getKeywords());
			findServiceProductReturn.setDescription(serviceProduct.getDescription());
			findServiceProductReturn.setCoverAddr(serviceProduct.getCoverAddr());
			findServiceProductReturn.setImgAddr(serviceProduct.getImgAddr());
			findServiceProductReturn.setCreateId(serviceProduct.getCreateId());
			findServiceProductReturn.setCreateDate(serviceProduct.getCreateDate());
			findServiceProductReturn.setRemark(serviceProduct.getRemark());
			findServiceProductReturn.setRemark2(serviceProduct.getRemark2());
			findServiceProductReturn.setRemark3(serviceProduct.getRemark3());
			findServiceProductReturn.setRemark4(serviceProduct.getRemark4());
			
			logger.debug("findServiceProduct(FindServiceProduct) - end - return value={}", findServiceProductReturn); 
			return findServiceProductReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找服务人员作品表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PRODUCT_FIND_ERROR,"查找服务人员作品表信息信息错误！",e);
		}

	}

	
	@Override
	public Page<FindServiceProductPageReturn> findServiceProductPage(
			FindServiceProductPage findServiceProductPage)
			throws TsfaServiceException {
		logger.debug("findServiceProductPage(FindServiceProductPage findServiceProductPage={}) - start", findServiceProductPage); 

		AssertUtils.notNull(findServiceProductPage);
		List<FindServiceProductPageReturn> returnList;
		int count = 0;
		try {
			returnList = serviceProductDao.findServiceProductPage(findServiceProductPage);
			count = serviceProductDao.findServiceProductPageCount(findServiceProductPage);
		}  catch (Exception e) {
			logger.error("服务人员作品表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SERVICE_PRODUCT_FIND_PAGE_ERROR,"服务人员作品表信息不存在错误.！",e);
		}
		Page<FindServiceProductPageReturn> returnPage = new Page<FindServiceProductPageReturn>(returnList, count, findServiceProductPage);

		logger.debug("findServiceProductPage(FindServiceProductPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
