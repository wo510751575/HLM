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
import com.lj.business.cm.dao.IVrMaterialCommenCategoryDao;
import com.lj.business.cm.domain.VrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.AddVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.DelVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.DelVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryPage;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryPageReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.FindVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialCommenCategory.UpdateVrMaterialCommenCategory;
import com.lj.business.cm.dto.vrMaterialCommenCategory.UpdateVrMaterialCommenCategoryReturn;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialType;
import com.lj.business.cm.dto.vrMaterialType.FindVrMaterialTypeReturn;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategory;
import com.lj.business.cm.dto.vrMaterialTypeCategory.FindVrMaterialTypeCategoryReturn;
import com.lj.business.cm.service.IVrMaterialCommenCategoryService;
import com.lj.business.cm.service.IVrMaterialTypeCategoryService;
import com.lj.business.cm.service.IVrMaterialTypeService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class VrMaterialCommenCategoryServiceImpl implements IVrMaterialCommenCategoryService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(VrMaterialCommenCategoryServiceImpl.class);
	

	@Resource
	private IVrMaterialCommenCategoryDao vrMaterialCommenCategoryDao;
	
	@Resource
	private IVrMaterialTypeService vrMaterialTypeService;
	
	@Resource
	private IVrMaterialTypeCategoryService vrMaterialTypeCategoryService;
	
	
	@Override
	public AddVrMaterialCommenCategoryReturn addVrMaterialCommenCategory(
			AddVrMaterialCommenCategory addVrMaterialCommenCategory) throws TsfaServiceException {
		logger.debug("addVrMaterialCommenCategory(AddVrMaterialCommenCategory addVrMaterialCommenCategory={}) - start", addVrMaterialCommenCategory); 

		AssertUtils.notNull(addVrMaterialCommenCategory);
		try {
			VrMaterialCommenCategory vrMaterialCommenCategory = new VrMaterialCommenCategory();
			//add数据录入
			String[] codeTypes=addVrMaterialCommenCategory.getCodes();
			FindVrMaterialType findVrMaterialType =new FindVrMaterialType();
			findVrMaterialType.setCode(codeTypes[0]);
			FindVrMaterialTypeReturn findVrMaterialTypeReturn =	vrMaterialTypeService.findVrMaterialType(findVrMaterialType);
			FindVrMaterialTypeCategory findVrMaterialTypeCategory = new FindVrMaterialTypeCategory();
			findVrMaterialTypeCategory.setCode(codeTypes[1]);
			FindVrMaterialTypeCategoryReturn findVrMaterialTypeCategoryReturn = vrMaterialTypeCategoryService.findVrMaterialTypeCategory(findVrMaterialTypeCategory);
			
			vrMaterialCommenCategory.setCode(GUID.getPreUUID());
			vrMaterialCommenCategory.setMaterialCode(addVrMaterialCommenCategory.getMaterialCode());
			vrMaterialCommenCategory.setMaterialTypeCode(findVrMaterialTypeReturn.getCode());
			vrMaterialCommenCategory.setMaterialTypeName(findVrMaterialTypeReturn.getTypeName());
			vrMaterialCommenCategory.setTypeCategoryCode(findVrMaterialTypeCategoryReturn.getCode());
			vrMaterialCommenCategory.setTypeCategoryName(findVrMaterialTypeCategoryReturn.getCategoryName());
			vrMaterialCommenCategory.setCreateId(addVrMaterialCommenCategory.getCreateId());
			vrMaterialCommenCategory.setCreateDate(addVrMaterialCommenCategory.getCreateDate());
			vrMaterialCommenCategory.setRemark(addVrMaterialCommenCategory.getRemark());
			vrMaterialCommenCategory.setRemark2(addVrMaterialCommenCategory.getRemark2());
			vrMaterialCommenCategory.setRemark3(addVrMaterialCommenCategory.getRemark3());
			vrMaterialCommenCategory.setRemark4(addVrMaterialCommenCategory.getRemark4());
			vrMaterialCommenCategoryDao.insert(vrMaterialCommenCategory);
			AddVrMaterialCommenCategoryReturn addVrMaterialCommenCategoryReturn = new AddVrMaterialCommenCategoryReturn();
			
			logger.debug("addVrMaterialCommenCategory(AddVrMaterialCommenCategory) - end - return value={}", addVrMaterialCommenCategoryReturn); 
			return addVrMaterialCommenCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增VR公用素材中心-分类关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_ADD_ERROR,"新增VR公用素材中心-分类关联表信息错误！",e);
		}
	}
	
	
	@Override
	public DelVrMaterialCommenCategoryReturn delVrMaterialCommenCategory(
			DelVrMaterialCommenCategory delVrMaterialCommenCategory) throws TsfaServiceException {
		logger.debug("delVrMaterialCommenCategory(DelVrMaterialCommenCategory delVrMaterialCommenCategory={}) - start", delVrMaterialCommenCategory); 

		AssertUtils.notNull(delVrMaterialCommenCategory);
		AssertUtils.notNull(delVrMaterialCommenCategory.getCode(),"CODE不能为空！");
		try {
			vrMaterialCommenCategoryDao.deleteByPrimaryKey(delVrMaterialCommenCategory.getCode());
			DelVrMaterialCommenCategoryReturn delVrMaterialCommenCategoryReturn  = new DelVrMaterialCommenCategoryReturn();

			logger.debug("delVrMaterialCommenCategory(DelVrMaterialCommenCategory) - end - return value={}", delVrMaterialCommenCategoryReturn); 
			return delVrMaterialCommenCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除VR公用素材中心-分类关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_DEL_ERROR,"删除VR公用素材中心-分类关联表信息错误！",e);

		}
	}

	@Override
	public UpdateVrMaterialCommenCategoryReturn updateVrMaterialCommenCategory(
			UpdateVrMaterialCommenCategory updateVrMaterialCommenCategory)
			throws TsfaServiceException {
		logger.debug("updateVrMaterialCommenCategory(UpdateVrMaterialCommenCategory updateVrMaterialCommenCategory={}) - start", updateVrMaterialCommenCategory); 

		AssertUtils.notNull(updateVrMaterialCommenCategory);
		AssertUtils.notNullAndEmpty(updateVrMaterialCommenCategory.getCode(),"CODE不能为空");
		try {
			VrMaterialCommenCategory vrMaterialCommenCategory = new VrMaterialCommenCategory();
			String[] codeTypes=updateVrMaterialCommenCategory.getCodes();
			FindVrMaterialType findVrMaterialType =new FindVrMaterialType();
			findVrMaterialType.setCode(codeTypes[0]);
			FindVrMaterialTypeReturn findVrMaterialTypeReturn =	vrMaterialTypeService.findVrMaterialType(findVrMaterialType);
			FindVrMaterialTypeCategory findVrMaterialTypeCategory = new FindVrMaterialTypeCategory();
			findVrMaterialTypeCategory.setCode(codeTypes[1]);
			FindVrMaterialTypeCategoryReturn findVrMaterialTypeCategoryReturn = vrMaterialTypeCategoryService.findVrMaterialTypeCategory(findVrMaterialTypeCategory);
			
			//update数据录入
			vrMaterialCommenCategory.setCode(updateVrMaterialCommenCategory.getCode());
			vrMaterialCommenCategory.setMaterialCode(updateVrMaterialCommenCategory.getMaterialCode());
			vrMaterialCommenCategory.setMaterialTypeCode(findVrMaterialTypeReturn.getCode());
			vrMaterialCommenCategory.setMaterialTypeName(findVrMaterialTypeReturn.getTypeName());
			vrMaterialCommenCategory.setTypeCategoryCode(findVrMaterialTypeCategoryReturn.getCode());
			vrMaterialCommenCategory.setTypeCategoryName(findVrMaterialTypeCategoryReturn.getCategoryName());
			vrMaterialCommenCategory.setCreateId(updateVrMaterialCommenCategory.getCreateId());
			vrMaterialCommenCategory.setCreateDate(updateVrMaterialCommenCategory.getCreateDate());
			vrMaterialCommenCategory.setRemark(updateVrMaterialCommenCategory.getRemark());
			vrMaterialCommenCategory.setRemark2(updateVrMaterialCommenCategory.getRemark2());
			vrMaterialCommenCategory.setRemark3(updateVrMaterialCommenCategory.getRemark3());
			vrMaterialCommenCategory.setRemark4(updateVrMaterialCommenCategory.getRemark4());
			AssertUtils.notUpdateMoreThanOne(vrMaterialCommenCategoryDao.updateByPrimaryKeySelective(vrMaterialCommenCategory));
			UpdateVrMaterialCommenCategoryReturn updateVrMaterialCommenCategoryReturn = new UpdateVrMaterialCommenCategoryReturn();

			logger.debug("updateVrMaterialCommenCategory(UpdateVrMaterialCommenCategory) - end - return value={}", updateVrMaterialCommenCategoryReturn); 
			return updateVrMaterialCommenCategoryReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("VR公用素材中心-分类关联表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_UPDATE_ERROR,"VR公用素材中心-分类关联表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindVrMaterialCommenCategoryReturn findVrMaterialCommenCategory(
			FindVrMaterialCommenCategory findVrMaterialCommenCategory) throws TsfaServiceException {
		logger.debug("findVrMaterialCommenCategory(FindVrMaterialCommenCategory findVrMaterialCommenCategory={}) - start", findVrMaterialCommenCategory); 

		AssertUtils.notNull(findVrMaterialCommenCategory);
		AssertUtils.notAllNullAndEmpty(findVrMaterialCommenCategory.getMaterialCode(),"素材CODE不能为空！");
		try {
			VrMaterialCommenCategory vrMaterialCommenCategory = vrMaterialCommenCategoryDao.selectByPrimaryKey(findVrMaterialCommenCategory.getCode());
			if(vrMaterialCommenCategory == null){
				throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_NOT_EXIST_ERROR,"VR公用素材中心-分类关联表信息不存在");
			}
			FindVrMaterialCommenCategoryReturn findVrMaterialCommenCategoryReturn = new FindVrMaterialCommenCategoryReturn();
			//find数据录入
			findVrMaterialCommenCategoryReturn.setCode(vrMaterialCommenCategory.getCode());
			findVrMaterialCommenCategoryReturn.setMaterialCode(vrMaterialCommenCategory.getMaterialCode());
			findVrMaterialCommenCategoryReturn.setMaterialTypeCode(vrMaterialCommenCategory.getMaterialTypeCode());
			findVrMaterialCommenCategoryReturn.setMaterialTypeName(vrMaterialCommenCategory.getMaterialTypeName());
			findVrMaterialCommenCategoryReturn.setTypeCategoryCode(vrMaterialCommenCategory.getTypeCategoryCode());
			findVrMaterialCommenCategoryReturn.setTypeCategoryName(vrMaterialCommenCategory.getTypeCategoryName());
			findVrMaterialCommenCategoryReturn.setCreateId(vrMaterialCommenCategory.getCreateId());
			findVrMaterialCommenCategoryReturn.setCreateDate(vrMaterialCommenCategory.getCreateDate());
			findVrMaterialCommenCategoryReturn.setRemark(vrMaterialCommenCategory.getRemark());
			findVrMaterialCommenCategoryReturn.setRemark2(vrMaterialCommenCategory.getRemark2());
			findVrMaterialCommenCategoryReturn.setRemark3(vrMaterialCommenCategory.getRemark3());
			findVrMaterialCommenCategoryReturn.setRemark4(vrMaterialCommenCategory.getRemark4());
			
			logger.debug("findVrMaterialCommenCategory(FindVrMaterialCommenCategory) - end - return value={}", findVrMaterialCommenCategoryReturn); 
			return findVrMaterialCommenCategoryReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找VR公用素材中心-分类关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_FIND_ERROR,"查找VR公用素材中心-分类关联表信息信息错误！",e);
		}


	}


	@Override
	public List<FindVrMaterialCommenCategoryReturn> findVrMaterialCommenCategoryList(
			FindVrMaterialCommenCategory findVrMaterialCommenCategory)
			throws TsfaServiceException {
		List<FindVrMaterialCommenCategoryReturn> list = null ;
		try {
		      list=	vrMaterialCommenCategoryDao.findVrMaterialCommenCategory(findVrMaterialCommenCategory);
		} catch (Exception e) {
			logger.error("查找VR公用素材中心-分类关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_FIND_ERROR,"查找VR公用素材中心-分类关联表信息信息错误！",e);
		}
		return list;
	}


	@Override
	public FindVrMaterialCommenCategoryReturn findByPrimaryKey(FindVrMaterialCommenCategory findVrMaterialCommenCategory) throws TsfaServiceException {
		logger.debug("findVrMaterialCommenCategory(FindVrMaterialCommenCategory findVrMaterialCommenCategory={}) - start", findVrMaterialCommenCategory); 

		AssertUtils.notNull(findVrMaterialCommenCategory);
		AssertUtils.notAllNullAndEmpty(findVrMaterialCommenCategory.getMaterialCode(),"素材CODE不能为空！");
		try {
			VrMaterialCommenCategory vrMaterialCommenCategory = vrMaterialCommenCategoryDao.findByPrimaryKey(findVrMaterialCommenCategory.getMaterialCode());
			if(vrMaterialCommenCategory == null){
				throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_NOT_EXIST_ERROR,"VR公用素材中心-分类关联表信息不存在");
			}
			FindVrMaterialCommenCategoryReturn findVrMaterialCommenCategoryReturn = new FindVrMaterialCommenCategoryReturn();
			//find数据录入
			findVrMaterialCommenCategoryReturn.setCode(vrMaterialCommenCategory.getCode());
			findVrMaterialCommenCategoryReturn.setMaterialCode(vrMaterialCommenCategory.getMaterialCode());
			findVrMaterialCommenCategoryReturn.setMaterialTypeCode(vrMaterialCommenCategory.getMaterialTypeCode());
			findVrMaterialCommenCategoryReturn.setMaterialTypeName(vrMaterialCommenCategory.getMaterialTypeName());
			findVrMaterialCommenCategoryReturn.setTypeCategoryCode(vrMaterialCommenCategory.getTypeCategoryCode());
			findVrMaterialCommenCategoryReturn.setTypeCategoryName(vrMaterialCommenCategory.getTypeCategoryName());
			findVrMaterialCommenCategoryReturn.setCreateId(vrMaterialCommenCategory.getCreateId());
			findVrMaterialCommenCategoryReturn.setCreateDate(vrMaterialCommenCategory.getCreateDate());
			findVrMaterialCommenCategoryReturn.setRemark(vrMaterialCommenCategory.getRemark());
			findVrMaterialCommenCategoryReturn.setRemark2(vrMaterialCommenCategory.getRemark2());
			findVrMaterialCommenCategoryReturn.setRemark3(vrMaterialCommenCategory.getRemark3());
			findVrMaterialCommenCategoryReturn.setRemark4(vrMaterialCommenCategory.getRemark4());
			
			logger.debug("findVrMaterialCommenCategory(FindVrMaterialCommenCategory) - end - return value={}", findVrMaterialCommenCategoryReturn); 
			return findVrMaterialCommenCategoryReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找VR公用素材中心-分类关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_FIND_ERROR,"查找VR公用素材中心-分类关联表信息信息错误！",e);
		}
	}

/*	
	@Override
	public Page<FindVrMaterialCommenCategoryPageReturn> findVrMaterialCommenCategoryPage(
			FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage)
			throws TsfaServiceException {
		logger.debug("findVrMaterialCommenCategoryPage(FindVrMaterialCommenCategoryPage findVrMaterialCommenCategoryPage={}) - start", findVrMaterialCommenCategoryPage); 

		AssertUtils.notNull(findVrMaterialCommenCategoryPage);
		List<FindVrMaterialCommenCategoryPageReturn> returnList;
		int count = 0;
		try {
			returnList = vrMaterialCommenCategoryDao.findVrMaterialCommenCategoryPage(findVrMaterialCommenCategoryPage);
			count = vrMaterialCommenCategoryDao.findVrMaterialCommenCategoryPageCount(findVrMaterialCommenCategoryPage);
		}  catch (Exception e) {
			logger.error("VR公用素材中心-分类关联表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.VR_MATERIAL_COMMEN_CATEGORY_FIND_PAGE_ERROR,"VR公用素材中心-分类关联表信息不存在错误.！",e);
		}
		Page<FindVrMaterialCommenCategoryPageReturn> returnPage = new Page<FindVrMaterialCommenCategoryPageReturn>(returnList, count, findVrMaterialCommenCategoryPage);

		logger.debug("findVrMaterialCommenCategoryPage(FindVrMaterialCommenCategoryPage) - end - return value={}", returnPage); 
		return  returnPage;
	} */


}
