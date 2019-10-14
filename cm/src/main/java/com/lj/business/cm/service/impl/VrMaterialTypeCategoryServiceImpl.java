package com.lj.business.cm.service.impl;

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
import com.lj.business.cm.constant.ErrorCode;
import com.lj.business.cm.dao.IVrMaterialTypeCategoryDao;
import com.lj.business.cm.domain.VrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.AddVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.DelVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.DelVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPage;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.UpdateVrMaterialTypeCategoryReturn;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;

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
public class VrMaterialTypeCategoryServiceImpl implements IVrMaterialTypeCategoryService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialTypeCategoryServiceImpl.class);
	

	@Resource
	private IVrMaterialTypeCategoryDao vrMaterialTypeCategoryDao;
	
	
	@Override
	public AddVrMaterialTypeCategoryReturn addVrMaterialTypeCategory(
			AddVrMaterialTypeCategory addVrMaterialTypeCategory) throws TsfaServiceException {
		logger.debug("addVrMaterialTypeCategory(AddVrMaterialTypeCategory addVrMaterialTypeCategory={}) - start", addVrMaterialTypeCategory); 

		AssertUtils.notNull(addVrMaterialTypeCategory);
		try {
			VrMaterialTypeCategory vrMaterialTypeCategory = new VrMaterialTypeCategory();
			//add数据录入
			vrMaterialTypeCategory.setCode(GUID.getPreUUID());
			vrMaterialTypeCategory.setTypeCode(addVrMaterialTypeCategory.getTypeCode());
			vrMaterialTypeCategory.setCategoryName(addVrMaterialTypeCategory.getCategoryName());
			vrMaterialTypeCategory.setShowIndex(addVrMaterialTypeCategory.getShowIndex());
			vrMaterialTypeCategory.setCreateId(addVrMaterialTypeCategory.getCreateId());
			vrMaterialTypeCategory.setCreateDate(new Date());
			vrMaterialTypeCategory.setRemark(addVrMaterialTypeCategory.getRemark());
			vrMaterialTypeCategory.setRemark2(addVrMaterialTypeCategory.getRemark2());
			vrMaterialTypeCategory.setRemark3(addVrMaterialTypeCategory.getRemark3());
			vrMaterialTypeCategory.setRemark4(addVrMaterialTypeCategory.getRemark4());
			vrMaterialTypeCategoryDao.insert(vrMaterialTypeCategory);
			AddVrMaterialTypeCategoryReturn addVrMaterialTypeCategoryReturn = new AddVrMaterialTypeCategoryReturn();
			
			logger.debug("addVrMaterialTypeCategory(AddVrMaterialTypeCategory) - end - return value={}", addVrMaterialTypeCategoryReturn); 
			return addVrMaterialTypeCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增VR素材类型分类信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_ADD_ERROR,"新增VR素材类型分类信息错误！",e);
		}
	}
	
	
	@Override
	public DelVrMaterialTypeCategoryReturn delVrMaterialTypeCategory(
			DelVrMaterialTypeCategory delVrMaterialTypeCategory) throws TsfaServiceException {
		logger.debug("delVrMaterialTypeCategory(DelVrMaterialTypeCategory delVrMaterialTypeCategory={}) - start", delVrMaterialTypeCategory); 

		AssertUtils.notNull(delVrMaterialTypeCategory);
		AssertUtils.notNull(delVrMaterialTypeCategory.getCode(),"CODE不能为空！");
		try {
			vrMaterialTypeCategoryDao.deleteByPrimaryKey(delVrMaterialTypeCategory.getCode());
			DelVrMaterialTypeCategoryReturn delVrMaterialTypeCategoryReturn  = new DelVrMaterialTypeCategoryReturn();

			logger.debug("delVrMaterialTypeCategory(DelVrMaterialTypeCategory) - end - return value={}", delVrMaterialTypeCategoryReturn); 
			return delVrMaterialTypeCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除VR素材类型分类信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_DEL_ERROR,"删除VR素材类型分类信息错误！",e);

		}
	}

	@Override
	public UpdateVrMaterialTypeCategoryReturn updateVrMaterialTypeCategory(
			UpdateVrMaterialTypeCategory updateVrMaterialTypeCategory)
			throws TsfaServiceException {
		logger.debug("updateVrMaterialTypeCategory(UpdateVrMaterialTypeCategory updateVrMaterialTypeCategory={}) - start", updateVrMaterialTypeCategory); 

		AssertUtils.notNull(updateVrMaterialTypeCategory);
		AssertUtils.notNullAndEmpty(updateVrMaterialTypeCategory.getCode(),"CODE不能为空");
		try {
			VrMaterialTypeCategory vrMaterialTypeCategory = new VrMaterialTypeCategory();
			//update数据录入
			vrMaterialTypeCategory.setCode(updateVrMaterialTypeCategory.getCode());
			vrMaterialTypeCategory.setTypeCode(updateVrMaterialTypeCategory.getTypeCode());
			vrMaterialTypeCategory.setCategoryName(updateVrMaterialTypeCategory.getCategoryName());
			vrMaterialTypeCategory.setShowIndex(updateVrMaterialTypeCategory.getShowIndex());
			vrMaterialTypeCategory.setCreateId(updateVrMaterialTypeCategory.getCreateId());
			vrMaterialTypeCategory.setCreateDate(updateVrMaterialTypeCategory.getCreateDate());
			vrMaterialTypeCategory.setRemark(updateVrMaterialTypeCategory.getRemark());
			vrMaterialTypeCategory.setRemark2(updateVrMaterialTypeCategory.getRemark2());
			vrMaterialTypeCategory.setRemark3(updateVrMaterialTypeCategory.getRemark3());
			vrMaterialTypeCategory.setRemark4(updateVrMaterialTypeCategory.getRemark4());
			AssertUtils.notUpdateMoreThanOne(vrMaterialTypeCategoryDao.updateByPrimaryKeySelective(vrMaterialTypeCategory));
			UpdateVrMaterialTypeCategoryReturn updateVrMaterialTypeCategoryReturn = new UpdateVrMaterialTypeCategoryReturn();

			logger.debug("updateVrMaterialTypeCategory(UpdateVrMaterialTypeCategory) - end - return value={}", updateVrMaterialTypeCategoryReturn); 
			return updateVrMaterialTypeCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("VR素材类型分类信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_UPDATE_ERROR,"VR素材类型分类信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindVrMaterialTypeCategoryReturn findVrMaterialTypeCategory(
			FindVrMaterialTypeCategory findVrMaterialTypeCategory) throws TsfaServiceException {
		logger.debug("findVrMaterialTypeCategory(FindVrMaterialTypeCategory findVrMaterialTypeCategory={}) - start", findVrMaterialTypeCategory); 

		AssertUtils.notNull(findVrMaterialTypeCategory);
		AssertUtils.notAllNull(findVrMaterialTypeCategory.getCode(),"CODE不能为空");
		try {
			VrMaterialTypeCategory vrMaterialTypeCategory = vrMaterialTypeCategoryDao.selectByPrimaryKey(findVrMaterialTypeCategory.getCode());
			if(vrMaterialTypeCategory == null){
				return null;
			}
			FindVrMaterialTypeCategoryReturn findVrMaterialTypeCategoryReturn = new FindVrMaterialTypeCategoryReturn();
			//find数据录入
			findVrMaterialTypeCategoryReturn.setCode(vrMaterialTypeCategory.getCode());
			findVrMaterialTypeCategoryReturn.setTypeCode(vrMaterialTypeCategory.getTypeCode());
			findVrMaterialTypeCategoryReturn.setCategoryName(vrMaterialTypeCategory.getCategoryName());
			findVrMaterialTypeCategoryReturn.setShowIndex(vrMaterialTypeCategory.getShowIndex());
			findVrMaterialTypeCategoryReturn.setCreateId(vrMaterialTypeCategory.getCreateId());
			findVrMaterialTypeCategoryReturn.setCreateDate(vrMaterialTypeCategory.getCreateDate());
			findVrMaterialTypeCategoryReturn.setRemark(vrMaterialTypeCategory.getRemark());
			findVrMaterialTypeCategoryReturn.setRemark2(vrMaterialTypeCategory.getRemark2());
			findVrMaterialTypeCategoryReturn.setRemark3(vrMaterialTypeCategory.getRemark3());
			findVrMaterialTypeCategoryReturn.setRemark4(vrMaterialTypeCategory.getRemark4());
			
			logger.debug("findVrMaterialTypeCategory(FindVrMaterialTypeCategory) - end - return value={}", findVrMaterialTypeCategoryReturn); 
			return findVrMaterialTypeCategoryReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找VR素材类型分类信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_FIND_ERROR,"查找VR素材类型分类信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindVrMaterialTypeCategoryPageReturn> findVrMaterialTypeCategoryPage(
			FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage)
			throws TsfaServiceException {
		logger.debug("findVrMaterialTypeCategoryPage(FindVrMaterialTypeCategoryPage findVrMaterialTypeCategoryPage={}) - start", findVrMaterialTypeCategoryPage); 

		AssertUtils.notNull(findVrMaterialTypeCategoryPage);
		List<FindVrMaterialTypeCategoryPageReturn> returnList;
		int count = 0;
		try {
			returnList = vrMaterialTypeCategoryDao.findVrMaterialTypeCategoryPage(findVrMaterialTypeCategoryPage);
			count = vrMaterialTypeCategoryDao.findVrMaterialTypeCategoryPageCount(findVrMaterialTypeCategoryPage);
		}  catch (Exception e) {
			logger.error("VR素材类型分类信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_FIND_PAGE_ERROR,"VR素材类型分类信息不存在错误.！",e);
		}
		Page<FindVrMaterialTypeCategoryPageReturn> returnPage = new Page<FindVrMaterialTypeCategoryPageReturn>(returnList, count, findVrMaterialTypeCategoryPage);

		logger.debug("findVrMaterialTypeCategoryPage(FindVrMaterialTypeCategoryPage) - end - return value={}", returnPage); 
		return  returnPage;
	}


	@Override
	public List<FindVrMaterialTypeCategoryReturn> findVrMaterialTypeCategoryReturnList(
			FindVrMaterialTypeCategory findVrMaterialTypeCategory)throws TsfaServiceException {
		List<FindVrMaterialTypeCategoryReturn> list;
	  try {
		list = vrMaterialTypeCategoryDao.findVrMaterialTypeCategoryReturnList(findVrMaterialTypeCategory);
	} catch (Exception e) {
		logger.error("VR素材类型分类信息不存在错误",e);
		throw new TsfaServiceException(ErrorCode.VR_MATERIAL_TYPE_CATEGORY_FIND_PAGE_ERROR,"VR素材类型分类信息不存在错误.！",e);
	}
		return list;
	} 


}
