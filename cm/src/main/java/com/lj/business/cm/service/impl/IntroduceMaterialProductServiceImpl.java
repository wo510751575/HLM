package com.lj.business.cm.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IIntroduceMaterialProductDao;
import com.lj.business.cm.domain.IntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.AddIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.DelIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPage;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPageReturn;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductReturn;
import com.lj.business.cm.dto.introduceMaterialProduct.UpdateIntroduceMaterialProduct;
import com.lj.business.cm.service.IIntroduceMaterialProductService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class IntroduceMaterialProductServiceImpl implements IIntroduceMaterialProductService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(IntroduceMaterialProductServiceImpl.class);
	

	@Resource
	private IIntroduceMaterialProductDao introduceMaterialProductDao;
	
	
	@Override
	public void addIntroduceMaterialProduct(
			AddIntroduceMaterialProduct addIntroduceMaterialProduct) throws TsfaServiceException {
		logger.debug("addIntroduceMaterialProduct(AddIntroduceMaterialProduct addIntroduceMaterialProduct={}) - start", addIntroduceMaterialProduct); 

		AssertUtils.notNull(addIntroduceMaterialProduct);
		try {
			IntroduceMaterialProduct introduceMaterialProduct = new IntroduceMaterialProduct();
			//add数据录入
			introduceMaterialProduct.setCode(GUID.getPreUUID());
			introduceMaterialProduct.setMaterialCode(addIntroduceMaterialProduct.getMaterialCode());
			introduceMaterialProduct.setBomName(addIntroduceMaterialProduct.getBomName());
			introduceMaterialProduct.setBomAddress(addIntroduceMaterialProduct.getBomAddress());
			introduceMaterialProduct.setSort(addIntroduceMaterialProduct.getSort());
			introduceMaterialProduct.setCreateId(addIntroduceMaterialProduct.getCreateId());
			introduceMaterialProduct.setCreateDate(addIntroduceMaterialProduct.getCreateDate());
			introduceMaterialProduct.setRemark(addIntroduceMaterialProduct.getRemark());
			introduceMaterialProduct.setRemark2(addIntroduceMaterialProduct.getRemark2());
			introduceMaterialProduct.setRemark3(addIntroduceMaterialProduct.getRemark3());
			introduceMaterialProduct.setRemark4(addIntroduceMaterialProduct.getRemark4());
			introduceMaterialProductDao.insert(introduceMaterialProduct);
			logger.debug("addIntroduceMaterialProduct(AddIntroduceMaterialProduct) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增个人素材介绍产品关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_ADD_ERROR,"新增个人素材介绍产品关联表信息错误！",e);
		}
	}
	
	
	@Override
	public void delIntroduceMaterialProduct(
			DelIntroduceMaterialProduct delIntroduceMaterialProduct) throws TsfaServiceException {
		logger.debug("delIntroduceMaterialProduct(DelIntroduceMaterialProduct delIntroduceMaterialProduct={}) - start", delIntroduceMaterialProduct); 

		AssertUtils.notNull(delIntroduceMaterialProduct);
		AssertUtils.notNull(delIntroduceMaterialProduct.getCode(),"Code不能为空！");
		try {
			introduceMaterialProductDao.deleteByPrimaryKey(delIntroduceMaterialProduct.getCode());
			logger.debug("delIntroduceMaterialProduct(DelIntroduceMaterialProduct) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除个人素材介绍产品关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_DEL_ERROR,"删除个人素材介绍产品关联表信息错误！",e);

		}
	}

	@Override
	public void updateIntroduceMaterialProduct(
			UpdateIntroduceMaterialProduct updateIntroduceMaterialProduct)
			throws TsfaServiceException {
		logger.debug("updateIntroduceMaterialProduct(UpdateIntroduceMaterialProduct updateIntroduceMaterialProduct={}) - start", updateIntroduceMaterialProduct); 

		AssertUtils.notNull(updateIntroduceMaterialProduct);
		AssertUtils.notNullAndEmpty(updateIntroduceMaterialProduct.getCode(),"Code不能为空");
		try {
			IntroduceMaterialProduct introduceMaterialProduct = new IntroduceMaterialProduct();
			//update数据录入
			introduceMaterialProduct.setCode(updateIntroduceMaterialProduct.getCode());
			introduceMaterialProduct.setMaterialCode(updateIntroduceMaterialProduct.getMaterialCode());
			introduceMaterialProduct.setBomName(updateIntroduceMaterialProduct.getBomName());
			introduceMaterialProduct.setBomAddress(updateIntroduceMaterialProduct.getBomAddress());
			introduceMaterialProduct.setSort(updateIntroduceMaterialProduct.getSort());
			introduceMaterialProduct.setCreateId(updateIntroduceMaterialProduct.getCreateId());
			introduceMaterialProduct.setCreateDate(updateIntroduceMaterialProduct.getCreateDate());
			introduceMaterialProduct.setRemark(updateIntroduceMaterialProduct.getRemark());
			introduceMaterialProduct.setRemark2(updateIntroduceMaterialProduct.getRemark2());
			introduceMaterialProduct.setRemark3(updateIntroduceMaterialProduct.getRemark3());
			introduceMaterialProduct.setRemark4(updateIntroduceMaterialProduct.getRemark4());
			AssertUtils.notUpdateMoreThanOne(introduceMaterialProductDao.updateByPrimaryKeySelective(introduceMaterialProduct));
			logger.debug("updateIntroduceMaterialProduct(UpdateIntroduceMaterialProduct) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("个人素材介绍产品关联表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_UPDATE_ERROR,"个人素材介绍产品关联表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindIntroduceMaterialProductReturn findIntroduceMaterialProduct(
			FindIntroduceMaterialProduct findIntroduceMaterialProduct) throws TsfaServiceException {
		logger.debug("findIntroduceMaterialProduct(FindIntroduceMaterialProduct findIntroduceMaterialProduct={}) - start", findIntroduceMaterialProduct); 

		AssertUtils.notNull(findIntroduceMaterialProduct);
		AssertUtils.notAllNull(findIntroduceMaterialProduct.getCode(),"Code不能为空");
		try {
			IntroduceMaterialProduct introduceMaterialProduct = introduceMaterialProductDao.selectByPrimaryKey(findIntroduceMaterialProduct.getCode());
			if(introduceMaterialProduct == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_NOT_EXIST_ERROR,"个人素材介绍产品关联表信息不存在");
			}
			FindIntroduceMaterialProductReturn findIntroduceMaterialProductReturn = new FindIntroduceMaterialProductReturn();
			//find数据录入
			findIntroduceMaterialProductReturn.setCode(introduceMaterialProduct.getCode());
			findIntroduceMaterialProductReturn.setMaterialCode(introduceMaterialProduct.getMaterialCode());
			findIntroduceMaterialProductReturn.setBomName(introduceMaterialProduct.getBomName());
			findIntroduceMaterialProductReturn.setBomAddress(introduceMaterialProduct.getBomAddress());
			findIntroduceMaterialProductReturn.setSort(introduceMaterialProduct.getSort());
			findIntroduceMaterialProductReturn.setCreateId(introduceMaterialProduct.getCreateId());
			findIntroduceMaterialProductReturn.setCreateDate(introduceMaterialProduct.getCreateDate());
			findIntroduceMaterialProductReturn.setRemark(introduceMaterialProduct.getRemark());
			findIntroduceMaterialProductReturn.setRemark2(introduceMaterialProduct.getRemark2());
			findIntroduceMaterialProductReturn.setRemark3(introduceMaterialProduct.getRemark3());
			findIntroduceMaterialProductReturn.setRemark4(introduceMaterialProduct.getRemark4());
			
			logger.debug("findIntroduceMaterialProduct(FindIntroduceMaterialProduct) - end - return value={}", findIntroduceMaterialProductReturn); 
			return findIntroduceMaterialProductReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找个人素材介绍产品关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_FIND_ERROR,"查找个人素材介绍产品关联表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindIntroduceMaterialProductPageReturn> findIntroduceMaterialProductPage(
			FindIntroduceMaterialProductPage findIntroduceMaterialProductPage)
			throws TsfaServiceException {
		logger.debug("findIntroduceMaterialProductPage(FindIntroduceMaterialProductPage findIntroduceMaterialProductPage={}) - start", findIntroduceMaterialProductPage); 

		AssertUtils.notNull(findIntroduceMaterialProductPage);
		List<FindIntroduceMaterialProductPageReturn> returnList;
		int count = 0;
		try {
			returnList = introduceMaterialProductDao.findIntroduceMaterialProductPage(findIntroduceMaterialProductPage);
			count = introduceMaterialProductDao.findIntroduceMaterialProductPageCount(findIntroduceMaterialProductPage);
		}  catch (Exception e) {
			logger.error("个人素材介绍产品关联表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.INTRODUCE_MATERIAL_PRODUCT_FIND_PAGE_ERROR,"个人素材介绍产品关联表信息不存在错误.！",e);
		}
		Page<FindIntroduceMaterialProductPageReturn> returnPage = new Page<FindIntroduceMaterialProductPageReturn>(returnList, count, findIntroduceMaterialProductPage);

		logger.debug("findIntroduceMaterialProductPage(FindIntroduceMaterialProductPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<IntroduceMaterialProduct> findIntroduceMaterialProductByMaterCode(
			FindIntroduceMaterialProduct findIntroduceMaterialProduct) {
		logger.debug("findIntroduceMaterialProductByMaterCode(FindIntroduceMaterialProduct findIntroduceMaterialProduct={}) - start", findIntroduceMaterialProduct); 

		AssertUtils.notNull(findIntroduceMaterialProduct);
		AssertUtils.notAllNull(findIntroduceMaterialProduct.getMaterialCode(),"materialCode不能为空");
		List<IntroduceMaterialProduct> introduceMaterialProducts =  introduceMaterialProductDao.finfindIntroduceMaterialProductByMaterCode(findIntroduceMaterialProduct);
		logger.debug("findIntroduceMaterialProductByMaterCode(FindIntroduceMaterialProduct) - end - return value={}", introduceMaterialProducts); 
		return introduceMaterialProducts;
	}


	@Override
	public void delIntroduceMaterialProductByMaterCode(
			DelIntroduceMaterialProduct delIntroduceMaterialProduct) {
		logger.debug("delIntroduceMaterialProductByMaterCode(DelIntroduceMaterialProduct delIntroduceMaterialProduct={}) - start", delIntroduceMaterialProduct); 

		AssertUtils.notNull(delIntroduceMaterialProduct);
		AssertUtils.notAllNull(delIntroduceMaterialProduct.getMaterialCode(),"materialCode不能为空");
		introduceMaterialProductDao.delIntroduceMaterialProductByMaterCode(delIntroduceMaterialProduct);
	} 


}
